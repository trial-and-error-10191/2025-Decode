package org.firstinspires.ftc.teamcode.Assemblies;

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

@Disabled
public class DriveTrain {
    DcMotor leftFrontDrive, rightFrontDrive, leftBackDrive, rightBackDrive;
    private IMU imu = null;
    private ElapsedTime runtime = new ElapsedTime();

    Telemetry telemetry;

    // variables for encoders and IMU
    static final double COUNTS_PER_MOTOR_REV = 537.6;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 3.5;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double HEADING_THRESHOLD = 5.0;
    static final double P_TURN_GAIN = 0.02;     // Larger is more responsive, but also less stable.
    static final double P_DRIVE_GAIN = 0.03;
    private double targetHeading = 0;
    private double driveSpeed = 0;
    private double turnSpeed = 0;
    private double leftSpeed = 0;
    private double rightSpeed = 0;
    private int leftTarget = 0;
    private int rightTarget = 0;
    private double headingError = 0;
    private boolean lastInput = false;
    private boolean wheelSwitch = false;

    // All subsystems should have a hardware function that labels all of the hardware required of it.
    public DriveTrain(HardwareMap hwMap, Telemetry telemetry) {
        // Initializes motor names:
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


//        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        this.telemetry = telemetry;
        imu.resetYaw();
    }

    public void drive(double axial, double yaw) {

        // initializes deadzone
        double deadzone = 0.05;
        // initializes sensitivity
        double sensitivity = 0.75;

        double leftFrontPower = 0;
        double rightFrontPower = 0;
        double leftBackPower = 0;
        double rightBackPower = 0;

        if (Math.abs(axial) > deadzone || Math.abs(yaw) > deadzone) {
            leftFrontPower = axial + yaw;
            rightFrontPower = axial - yaw;
            leftBackPower = axial - yaw;
            rightBackPower = axial + yaw;

        }
        double max;

        // All code below this comment normalizes the values so no wheel power exceeds 100%.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));


        if (max > 1.0) {
            leftFrontPower /= max; // leftFrontPower = leftFrontPower / max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Calculates power using sensitivity variable.
        leftFrontPower *= sensitivity;
        rightFrontPower *= sensitivity;
        leftBackPower *= sensitivity;
        rightBackPower *= sensitivity;

        //leftPower *= 0.7; // this motor is 312 rpm, others are 223. 223/312 ~ 0.7

        // The next four lines gives the calculated power to each motor.
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
    }

    public void frontMotorsDrive(double axial, double yaw) {
        // initializes deadzone
        double deadzone = 0.05;
        // initializes sensitivity
        double sensitivity = 0.75;

        double leftFrontPower = 0;
        double rightFrontPower = 0;

        if (Math.abs(axial) > deadzone || Math.abs(yaw) > deadzone) {
            leftFrontPower = axial + yaw;
            rightFrontPower = axial - yaw;
        }
        double max;

        // All code below this comment normalizes the values so no wheel power exceeds 100%.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));


        if (max > 1.0) {
            leftFrontPower /= max; // leftFrontPower = leftFrontPower / max;
            rightFrontPower /= max;
        }

        // Calculates power using sensitivity variable.
        leftFrontPower *= sensitivity;
        rightFrontPower *= sensitivity;

        // The next four lines gives the calculated power to each motor.
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);
    }

    public void backMotorsDrive(double axial, double yaw) {
        // initializes deadzone
        double deadzone = 0.05;
        // initializes sensitivity
        double sensitivity = 0.75;

        double leftBackPower = 0;
        double rightBackPower = 0;

        if (Math.abs(axial) > deadzone || Math.abs(yaw) > deadzone) {
            leftBackPower = axial + yaw;
            rightBackPower = axial - yaw;
        }
        double max;

        // All code below this comment normalizes the values so no wheel power exceeds 100%.
        max = Math.max(Math.abs(leftBackPower), Math.abs(rightBackPower));


        if (max > 1.0) {
            leftBackPower /= max; // leftBackPower = leftBackPower / max;
            rightBackPower /= max;
        }

        // Calculates power using sensitivity variable.
        leftBackPower *= sensitivity;
        rightBackPower *= sensitivity;

        // The next four lines gives the calculated power to each motor.
        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);
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

    public void driveStraight(double maxDriveSpeed, double distance, double heading) {
        // Determine new target position, and pass to motor controller
        int moveCounts = (int) (distance * COUNTS_PER_INCH);
        leftTarget = leftFrontDrive.getCurrentPosition() + moveCounts;
        leftTarget = leftBackDrive.getCurrentPosition() + moveCounts;
        rightTarget = rightFrontDrive.getCurrentPosition() + moveCounts;
        rightTarget = rightBackDrive.getCurrentPosition() + moveCounts;

        // Set Target FIRST, then turn on RUN_TO_POSITION
        // If Strafing then reverse motor directions


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
// ;-; P A I N   I S   E V E R Y W H E R E ! ! ! !
        // keep looping while we are still active, and BOTH motors are running.
        while ((leftFrontDrive.isBusy() && rightFrontDrive.isBusy() && rightBackDrive.isBusy() && leftBackDrive.isBusy())) {
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_DRIVE_GAIN);
            telemetry.addData("Steering Correction", turnSpeed);
            telemetry.update();

            // if driving in reverse, the motor correction also needs to be reversed
//            if (distance < 0) {
//                turnSpeed *= -1.0;
//            }

            // Apply the turning correction to the current driving speed.
            moveRobot(driveSpeed, -turnSpeed);
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

    public void turnToHeading(double maxTurnSpeed, double heading) {

        // Run getSteeringCorrection() once to pre-calculate the current error
        getSteeringCorrection(heading, P_DRIVE_GAIN);

        // keep looping while we are still active, and not on heading.
        while ((Math.abs(headingError) > HEADING_THRESHOLD)) {

            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_TURN_GAIN);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxTurnSpeed, maxTurnSpeed);

            // Pivot in place by applying the turning correction
            moveRobot(0, -turnSpeed);

            // Display drive status for the driver.
            telemetry.addData("HeadingErr", headingError);
            telemetry.addData("HeadingThresh",HEADING_THRESHOLD);
            telemetry.addData("TurnSpeed",turnSpeed);
            telemetry.update();
        }

        // Stop all motion;
        moveRobot(0, 0);
    }

    public void holdHeading(double maxTurnSpeed, double heading, double holdTime) {

        ElapsedTime holdTimer = new ElapsedTime();
        holdTimer.reset();

        // keep looping while we have time remaining.
        while ((holdTimer.time() < holdTime)) {
            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_TURN_GAIN);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxTurnSpeed, maxTurnSpeed);

            // Pivot in place by applying the turning correction
            moveRobot(0, turnSpeed);

            // Display drive status for the driver.
            sendTelemetry(false);
        }

        // Stop all motion;
        moveRobot(0, 0);
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
}

