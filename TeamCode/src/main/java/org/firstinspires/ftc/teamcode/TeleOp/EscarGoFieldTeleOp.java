package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "EscarGoFieldTeleOp", group = "LinearOpMode")
public class EscarGoFieldTeleOp extends LinearOpMode {
    public DcMotor leftFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor rightBackDrive = null;
    public IMU imu = null;

    double angles = 0;

    public void runOpMode() {
        // Initiates the robots system and subsystems!
        Robot robot = new Robot(hardwareMap, telemetry);

        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");

        // Initializes motor directions:
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        /* The next two lines define Hub orientation.
         * The Default Orientation (shown) is when a hub is mounted horizontally with the printed logo pointing UP and the USB port pointing FORWARD.
         *
         * To Do:  EDIT these two lines to match YOUR mounting configuration.
         */
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // This sample expects the IMU to be in a REV Hub and named "imu".
        imu = hardwareMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        waitForStart();
        //robot.escarGOMech.initRotateByPower();

        double deadzone = 0.05;
        while (opModeIsActive()) {
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
            angles = -orientation.getYaw(AngleUnit.RADIANS);

            double fieldForward = -gamepad1.left_stick_y;
            double fieldTurn = gamepad1.right_stick_x;

            double robotForward = fieldForward * Math.cos(angles) * Math.sin(angles);
            double robotTurn = fieldTurn;

            double leftPower = 0;
            double rightPower = 0;

            if (Math.abs(robotForward) > deadzone || Math.abs(robotTurn) > deadzone) {
                leftPower = robotForward + robotTurn;
                rightPower = robotForward - robotTurn;
            }

            double max;

            // All code below this comment normalizes the values so no wheel power exceeds 100%.
            max = Math.max(Math.abs(leftPower), Math.abs(rightPower));

            if (max > 1.0) {
                leftPower /= max; // leftPower = leftPower / max;
                rightPower /= max;
            }

            double sensitivity = 0.65;
            // The next four lines gives the calculated power to each motor.
            leftFrontDrive.setPower(leftPower * sensitivity);
            rightFrontDrive.setPower(rightPower * sensitivity);
            leftBackDrive.setPower(leftPower * sensitivity);
            rightBackDrive.setPower(rightPower * sensitivity);

//            robot.escarGOMech.BallIntake(gamepad2.a);
//            robot.escarGOMech.WheelLaunch();
        }
    }
}
