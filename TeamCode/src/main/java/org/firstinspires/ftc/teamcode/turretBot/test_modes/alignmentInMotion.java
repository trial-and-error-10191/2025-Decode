package org.firstinspires.ftc.teamcode.turretBot.test_modes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.turretBot.Robot;

@TeleOp(name = "alignment in motion test", group = "Test")
public class alignmentInMotion extends LinearOpMode {

    public Robot robot;
    double dampChange;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap,telemetry);
        robot.active_gamepad = gamepad1;
        dampChange = 0.001;

        waitForStart();
        robot.turret.motor.setTargetPosition(0);
        robot.turret.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.turret.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        while (opModeIsActive()) {

            if (gamepad1.dpad_right) {
                robot.dampingFactor += dampChange;
            } else if (gamepad1.dpad_left) {
                robot.dampingFactor -= dampChange;
            }

            robot.driveTrain.easingDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.alignTurretPosition();

            telemetry.addData("Damping Factor", robot.dampingFactor);
            telemetry.addData("EC", robot.turret.motor.getCurrentPosition());

            telemetry.update();
        }
    }
}