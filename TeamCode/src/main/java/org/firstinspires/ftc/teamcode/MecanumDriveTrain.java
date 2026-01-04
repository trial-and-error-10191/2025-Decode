package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDriveTrain implements DriveTrain {
    private double sensitivity = 1.0;
    private final double AXIAL_SENSITIVITY = 1.0;
    private final double LATERAL_SENSITIVITY = 1.0;
    private final double YAW_SENSITIVITY = 0.8;
    private double deadZone = 0.05;
    private DcMotor leftFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor rightBackMotor = null;
    private Telemetry telemetry = null;
    private HardwareMap hwMap = null;
    private IMU imu;
    private Orientation orientation = Orientation.LOCAL;
    private DriveUnit driveUnit = DriveUnit.POWER;

    public MecanumDriveTrain(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        this.hwMap = hwMap;
        leftFrontMotor = hwMap.get(DcMotor.class, "LeftFront");
        leftBackMotor = hwMap.get(DcMotor.class, "LeftBack");
        rightFrontMotor = hwMap.get(DcMotor.class, "RightFront");
        rightBackMotor = hwMap.get(DcMotor.class, "RightBack");

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hwMap.get(IMU.class, "imu");
        // This needs to be changed to match the orientation on your robot
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    @Override
    public void DriveByPower(Gamepad gp1, Gamepad gp2) {
        // axial power from left stick up/down, reversed so that pushing up moves robot forward
        double axial = (Math.abs(gp1.left_stick_y) > deadZone ? -gp1.left_stick_y : 0.0);
        axial *= AXIAL_SENSITIVITY;
        // lateral power from left stick right/left
        double lateral = (Math.abs(gp1.left_stick_x) > deadZone ? gp1.left_stick_x : 0.0);
        lateral *= LATERAL_SENSITIVITY;
        // yaw power from right stick right/left
        double yaw = (Math.abs(gp1.right_stick_x) > deadZone ? gp1.right_stick_x : 0.0);
        yaw *= YAW_SENSITIVITY;

        double leftFrontPower = axial + lateral + yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double rightBackPower = axial + lateral - yaw;

        // Ensuring power value is between -1.0 and 1.0 (limits accepted by motors)
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(rightBackPower));
        if (max > 1.0) {
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }

        leftFrontPower *= sensitivity;
        leftBackPower *= sensitivity;
        rightFrontPower *= sensitivity;
        rightBackPower *= sensitivity;

        leftFrontMotor.setPower(leftFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightFrontMotor.setPower(rightFrontPower);
        rightBackMotor.setPower(rightBackPower);
    }

    @Override
    public void DriveByEncoder(Gamepad gp1, Gamepad gp2) {

    }

    @Override
    public void DriveFieldOriented(Gamepad gp1, Gamepad gp2) {
        // axial power from left stick up/down, reversed so that pushing up moves robot forward
        double axial = (Math.abs(gp1.left_stick_y) > deadZone ? -gp1.left_stick_y : 0.0);
        // lateral power from left stick right/left
        double lateral = (Math.abs(gp1.left_stick_x) > deadZone ? gp1.left_stick_x : 0.0);
        // yaw power from right stick right/left
        double yaw = (Math.abs(gp1.right_stick_x) > deadZone ? gp1.right_stick_x : 0.0);

        // First, convert direction being asked to drive to polar coordinates
        double theta = Math.atan2(axial, lateral);
        double r = Math.hypot(axial, lateral);

        // Second, rotate angle by the angle the robot is pointing
        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        // Third, convert back to cartesian
        axial = r * Math.sin(theta);
        lateral =  r * Math.cos(theta);

        axial *= AXIAL_SENSITIVITY;
        lateral *= LATERAL_SENSITIVITY;
        yaw *= YAW_SENSITIVITY;

        double leftFrontPower = axial + lateral + yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double rightBackPower = axial + lateral - yaw;

        // Ensuring power value is between -1.0 and 1.0 (limits accepted by motors)
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(rightBackPower));
        if (max > 1.0) {
            leftFrontPower /= max;
            leftBackPower /= max;
            rightFrontPower /= max;
            rightBackPower /= max;
        }

        leftFrontPower *= sensitivity;
        leftBackPower *= sensitivity;
        rightFrontPower *= sensitivity;
        rightBackPower *= sensitivity;

        leftFrontMotor.setPower(leftFrontPower);
        leftBackMotor.setPower(leftBackPower);
        rightFrontMotor.setPower(rightFrontPower);
        rightBackMotor.setPower(rightBackPower);
    }

    public void SetSensitivity(double s) {
        this.sensitivity = Range.clip(s, 0.0, 1.0);
    }

    public void SetDeadZone(double dz) {
        this.deadZone = Range.clip(dz, 0.0, 1.0);
    }

    public void ResetImu() {
        imu.resetYaw();
    }
}
