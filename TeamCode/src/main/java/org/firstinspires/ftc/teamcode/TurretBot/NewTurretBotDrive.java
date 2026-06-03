package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class NewTurretBotDrive {
    DcMotor leftFrontDrive, rightFrontDrive;
    private IMU imu = null;
    private ElapsedTime runtime;

    Telemetry telemetry;

    public double reductionSmoothing = 35; // driver tested ✅
    public double MSthreshold = 5;

    private double lastMS = 0.0;
    ArrayList<Double> leftPowerValues = new ArrayList<Double>(4000);
    ArrayList<Double> rightPowerValues = new ArrayList<Double>(4000);

    public NewTurretBotDrive(HardwareMap hwMap, Telemetry telemetry) {
        leftFrontDrive = hwMap.get(DcMotor.class, "leftFront");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFront");

        // Initializes motor directions:
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu = hwMap.get(IMU.class, "imu");
        imu.initialize(new IMU.Parameters(orientationOnRobot));
        this.telemetry = telemetry;
        imu.resetYaw();
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
        powerChange(rightFrontDrive, rightPower);
    }
    public void powerChange(DcMotor motor, double change) {
        double motorPower = motor.getPower();
        double motorChange = ((change - motorPower) / reductionSmoothing);

        if (Math.abs(change - motorPower) < 0.02) {
            motor.setPower(change);
        } else {
            motor.setPower(motorPower + motorChange);
        }
    }
}
