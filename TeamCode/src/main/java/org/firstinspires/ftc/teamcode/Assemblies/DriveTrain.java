package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

@Disabled
public class DriveTrain {
    DcMotor leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive;
    long start = System.nanoTime();
    private IMU imu = null;
    private ElapsedTime runtime;

    Telemetry telemetry;

    // variables for encoders and IMU
    static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 3.5;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double P_DRIVE_GAIN = 0.03;
    private double targetHeading = 0;
    private double driveSpeed = 0;
    private double turnSpeed = 0;
    private double leftSpeed = 0;
    private double rightSpeed = 0;
    public int targetPositionDrive = 0;
    public int targetPositionTurnLeft = 0;
    public int targetPositionTurnRight = 0;
    private int leftTarget = 0;
    private int rightTarget = 0;
    private double headingError = 0;
    private boolean lastInput = false;
    private boolean wheelSwitch = false;

    public double reductionSmoothing = 38.72; // driver tested ✅
    public double MSthreshold = 15;

    private AprilTagDetection desiredTagGoal = null;

    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN =   0.02 ;   //  Speed Control "Gain". e.g. Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double TURN_GAIN  =   0.01 ;   //  Turn Control "Gain".  e.g. Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN  = 0.5;  //  Clip the turn speed to this max value (adjust for your robot)

    public int DESIRED_TAG_ID = 24;    // Choose the tag you want to approach or set to -1 for ANY tag.

    boolean targetFound     = false;    // Set to true when an AprilTag target is detected
    double  drive           = 0;        // Desired forward power/speed (-1 to +1) +ve is forward
    double  turn            = 0;        // Desired turning power/speed (-1 to +1) +ve is CounterClockwise

    private double lastMS = 0.0;
    ArrayList<Double> leftPowerValues = new ArrayList<Double>(4000);
    ArrayList<Double> rightPowerValues = new ArrayList<Double>(4000);

    // All subsystems should have a hardware function that labels all of the hardware required of it.
    public DriveTrain(HardwareMap hwMap, Telemetry telemetry) {
        // Initializes motor names:f
        leftFrontDrive = hwMap.get(DcMotor.class, "leftFront");
        leftBackDrive = hwMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hwMap.get(DcMotor.class, "rightBack");

        // Initializes motor directions:
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        this.telemetry = telemetry;
        imu.resetYaw();

        runtime = new ElapsedTime();
        runtime.reset();
    }

    public void allMotorsDrive(double axial, double yaw) {
        // initializes deadzone
        double deadzone = 0.05;
        // initializes sensitivity
        double sensitivity = 0.75;

        double leftPower = 0;
        double rightPower = 0;

        if (Math.abs(axial) > deadzone || Math.abs(yaw) > deadzone) {
            leftPower = axial + yaw;
            rightPower = axial - yaw;
        }
        double max;

        // All code below this comment normalizes the values so no wheel power exceeds 100%.
        max = Math.max(Math.abs(leftPower), Math.abs(rightPower));


        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;
        }

        // Calculates power using sensitivity variable.
        leftPower *= sensitivity;
        rightPower *= sensitivity;

        // The next four lines gives the calculated power to each motor.
        leftFrontDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        leftBackDrive.setPower(leftPower);
        rightBackDrive.setPower(rightPower);
    }

    public void easingDrive(double axial, double yaw) {
        // initializes deadzone
        double deadzone = 0.05;

        double leftPower = 0;
        double rightPower = 0;

        if (Math.abs(axial) > deadzone || Math.abs(yaw) > deadzone) {
            leftPower = axial + yaw;
            rightPower = axial - yaw;
        }
        double max;

        // All code below this comment normalizes the values so no wheel power exceeds 100%.
        max = Math.max(Math.abs(leftPower), Math.abs(rightPower));


        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;
        }

        if ((runtime.milliseconds() - lastMS) <= MSthreshold) {
            leftPowerValues.add(leftPower);
            rightPowerValues.add(rightPower);
            return;
        }
        lastMS = runtime.milliseconds();


        for (double LP : leftPowerValues) {
            leftPower += LP;
        }
        leftPower /= leftPowerValues.size() + 1;
        leftPowerValues.clear();

        for (double LP : rightPowerValues) {
            rightPower += LP;
        }
        rightPower /= rightPowerValues.size() + 1;
        rightPowerValues.clear();

        // The next four lines gives the calculated power to each motor
        powerChange(leftFrontDrive, leftPower);
        powerChange(leftBackDrive, leftPower);
        powerChange(rightFrontDrive, rightPower);
        powerChange(rightBackDrive, rightPower);
    }

    public void wheelTest(boolean wheelSwap) {
        if (wheelSwap && !lastInput) {
            wheelSwitch = !wheelSwitch;
            if (wheelSwitch) {
                leftFrontDrive.setPower(1);
                rightFrontDrive.setPower(-1);
                leftBackDrive.setPower(0);
                rightBackDrive.setPower(0);
            } else {
                leftFrontDrive.setPower(0);
                rightFrontDrive.setPower(0);
                leftBackDrive.setPower(-1);
                rightBackDrive.setPower(1);
            }
        }
        lastInput = wheelSwap;
    }
    public void stopMotors () {
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    public void driveStraight(double maxDriveSpeed, double distance, double heading) {
        // Determine new target position, and pass to motor controller
        int moveCounts = (int) (distance * COUNTS_PER_INCH);
        leftTarget = leftFrontDrive.getCurrentPosition() + moveCounts;
        leftTarget = leftBackDrive.getCurrentPosition() + moveCounts;
        rightTarget = rightFrontDrive.getCurrentPosition() + moveCounts;
        rightTarget = rightBackDrive.getCurrentPosition() + moveCounts;

        // Set Target FIRST, then turn on RUN_TO_POSITION

        leftFrontDrive.setTargetPosition(leftTarget);
        rightFrontDrive.setTargetPosition(rightTarget);
        leftBackDrive.setTargetPosition(leftTarget);
        rightBackDrive.setTargetPosition(rightTarget);


        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set the required driving speed  (must be positive for RUN_TO_POSITION)
        // Start driving straight, and then enter the control loop
        maxDriveSpeed = Math.abs(maxDriveSpeed);
        moveRobot(maxDriveSpeed, 0);

        // keep looping while we are still active, and BOTH motors are running.
        while ((leftFrontDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && leftBackDrive.isBusy())) {
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_DRIVE_GAIN);
            telemetry.addData("Steering Correction", turnSpeed);
            telemetry.update();

            moveRobot(driveSpeed, -turnSpeed);
        }
    }

    public void driveWithEncoders(int drive, double timeSecs) {
        if (drive != 0) {
            targetPositionDrive = leftFrontDrive.getCurrentPosition() + drive;
        }
        leftFrontDrive.setTargetPosition(targetPositionDrive);
        rightFrontDrive.setTargetPosition(targetPositionDrive);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setPower(0.5);
        rightFrontDrive.setPower(-0.5);
        start = System.nanoTime();
        while ((System.nanoTime() - start <= timeSecs * 1E9) && leftFrontDrive.isBusy() && rightFrontDrive.isBusy()) {
            telemetry.addData("Where to Drive", targetPositionDrive);
            telemetry.addData("Left Location", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Location", rightFrontDrive.getCurrentPosition());
            telemetry.update();
        }
    }

    public void turnWithEncoders(int turn, double timeSecs) { // Positive turn turns the robot left, negative turn turns the robot right
        if (turn != 0) {
            targetPositionTurnLeft = leftFrontDrive.getCurrentPosition() + turn;
            targetPositionTurnRight = rightFrontDrive.getCurrentPosition() - turn;
        }
        leftFrontDrive.setTargetPosition(targetPositionTurnLeft);
        rightFrontDrive.setTargetPosition(targetPositionTurnRight);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setPower(0.5);
        rightFrontDrive.setPower(0.5);
        start = System.nanoTime();
        while ((System.nanoTime() - start <= timeSecs * 1E9) && leftFrontDrive.isBusy() && rightFrontDrive.isBusy()) {
            telemetry.addData("Where to Drive", targetPositionDrive);
            telemetry.addData("Left Location", leftFrontDrive.getCurrentPosition());
            telemetry.addData("Right Location", rightFrontDrive.getCurrentPosition());
            telemetry.update();
        }
    }

    public void moveRobot(double drive, double turn) {
        driveSpeed = drive;     // save this value as a class member so it can be used by telemetry.
        turnSpeed = turn;      // save this value as a class member so it can be used by telemetry.

        leftSpeed = drive + turn;
        rightSpeed = drive - turn;

        // Scale speeds down if either one exceeds +/- 1.0;
        double max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if (max > 1.0) {
            leftSpeed /= max;
            rightSpeed /= max;
        }
        telemetry.addData("LeftSpeed",leftSpeed); telemetry.addData("RightSpeed",rightSpeed);
        leftFrontDrive.setPower(leftSpeed);
        rightFrontDrive.setPower(rightSpeed);
        leftBackDrive.setPower(leftSpeed);
        rightBackDrive.setPower(rightSpeed);
    }

    public double getSteeringCorrection(double desiredHeading, double proportionalGain) {
        targetHeading = desiredHeading;  // Save for telemetry

        // Determine the heading current error
        headingError = targetHeading - getHeading();

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180) headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * proportionalGain, -1, 1);
    }


    private void sendTelemetry(boolean straight) {

        if (straight) {
            telemetry.addData("Motion", "Drive Straight");
            telemetry.addData("Target Pos L:R", "%7d:%7d", leftTarget, rightTarget);
            telemetry.addData("Actual Pos L:R", "%7d:%7d:%7d:%7d", leftFrontDrive.getCurrentPosition(),
                    rightFrontDrive.getCurrentPosition(), leftBackDrive.getCurrentPosition(), rightBackDrive.getCurrentPosition());
        } else {
            telemetry.addData("Motion", "Turning");
        }

        telemetry.addData("Heading- Target : Current", "%5.2f : %5.0f", targetHeading, getHeading());
        telemetry.addData("Error  : Steer Pwr", "%5.1f : %5.1f", headingError, turnSpeed);
        telemetry.addData("Wheel Speeds L : R", "%5.2f : %5.2f", leftSpeed, rightSpeed);
        telemetry.update();
    }
    public void autoDriveStraight (double power, double Time) {
        runtime.reset();
        while (runtime.milliseconds() <= Time * 1000) {
            leftFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightFrontDrive.setPower(power);
            rightBackDrive.setPower(power);
        }
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    public void autoTurn (double power, double Time) {
        runtime.reset();
        while (runtime.milliseconds() <= Time * 1000) {
            leftFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightFrontDrive.setPower(-power);
            rightBackDrive.setPower(-power);
        }
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);
    }
    public double getHeading() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        return orientation.getYaw(AngleUnit.DEGREES);
    }

    public void powerChange(DcMotor motor, double change) {
        double motorPower = motor.getPower();
        double motorChange = ((change - motorPower) / 20);

        if (Math.abs(change - motorPower) < 0.02) {
            motor.setPower(change);
        } else {
            motor.setPower(motorPower + motorChange);
        }
    }
    public void DriveByAprilTag(double DESIRED_DISTANCE, AprilTagProcessor aprilTag) {
        targetFound = false;
        desiredTagGoal = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.
            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                if ((DESIRED_TAG_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTagGoal = detection;
                    break;  // don't look any further.
                } else {
                    // This tag is in the library, but we do not want to track it right now.
                    telemetry.addData("Skipping", "Tag ID %d is not desired", detection.id);
                }
            } else {
                // This tag is NOT in the library, so we don't have enough information to track to it.
                telemetry.addData("Unknown", "Tag ID %d is not in TagLibrary", detection.id);
            }
        }
        // Tell the driver what we see, and what to do.
        if (targetFound) {
            telemetry.addData("\n>", "HOLD Left-Bumper to Drive to Target\n");
            telemetry.addData("Found", "ID %d (%s)", desiredTagGoal.id, desiredTagGoal.metadata.name);
            telemetry.addData("Range", "%5.1f inches", desiredTagGoal.ftcPose.range);
            telemetry.addData("Bearing", "%3.0f degrees", desiredTagGoal.ftcPose.bearing);
        } else {
            telemetry.addData("\n>", "Drive using joysticks to find valid target\n");
        }

        // If we have found the desired target, Drive to target Automatically .
        if (targetFound) {
            // Determine heading and range error so we can use them to control the robot automatically.
            double rangeError = (desiredTagGoal.ftcPose.range - DESIRED_DISTANCE);

            // Use the speed to calculate how we want the robot to move.  Clip it to the maximum
            drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            telemetry.addData("Auto", "Drive %5.2f", drive);
        } else {
            drive = 0;
        }
        telemetry.update();
        moveRobot(drive, 0);
    }
    public void TurnToAprilTag(double DESIRED_TURN, AprilTagProcessor aprilTag) {
        targetFound = false;
        desiredTagGoal = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.
            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                if ((DESIRED_TAG_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTagGoal = detection;
                    break;  // don't look any further.
                } else {
                    // This tag is in the library, but we do not want to track it right now.
                    telemetry.addData("Skipping", "Tag ID %d is not desired", detection.id);
                }
            } else {
                // This tag is NOT in the library, so we don't have enough information to track to it.
                telemetry.addData("Unknown", "Tag ID %d is not in TagLibrary", detection.id);
            }
        }
        // Tell the driver what we see, and what to do.
        if (targetFound) {
            telemetry.addData("\n>", "HOLD Left-Bumper to Drive to Target\n");
            telemetry.addData("Found", "ID %d (%s)", desiredTagGoal.id, desiredTagGoal.metadata.name);
            telemetry.addData("Range", "%5.1f inches", desiredTagGoal.ftcPose.range);
            telemetry.addData("Bearing", "%3.0f degrees", desiredTagGoal.ftcPose.bearing);
        } else {
            telemetry.addData("\n>", "Drive using joysticks to find valid target\n");
        }

        // If Left Bumper is being pressed, AND we have found the desired target, Drive to target Automatically .
        if (targetFound) {
            // Determine heading and range error so we can use them to control the robot automatically.
            double headingError = (DESIRED_TURN - desiredTagGoal.ftcPose.bearing) * 2;
            // Use the turn "gains" to calculate how we want the robot to move.  Clip it to the maximum
            turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            telemetry.addData("Auto", "Turn %5.2f", turn);
        } else {
            turn = 0;
        }
        telemetry.update();
        moveRobot(0, turn);
    }
}