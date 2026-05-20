package org.firstinspires.ftc.teamcode.turretBot.test_modes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.turretBot.Robot;

@TeleOp(name = "LaunchTest", group = "Test")
public class basic_test extends LinearOpMode {

    public Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap,telemetry);

        waitForStart();
        robot.turret.motor.setPower(1);
        robot.turret.motor.setTargetPosition(0);
        robot.turret.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.turret.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (opModeIsActive()) {
            if (gamepad1.dpadLeftWasPressed()) {
                robot.turret.motor.setTargetPosition(300);
            } else if (gamepad1.dpadRightWasPressed()) {
                robot.turret.motor.setTargetPosition(-300);
            }
            telemetry.addData("EC", robot.turret.motor.getCurrentPosition());
            telemetry.addData("power", robot.turret.motor.getPower());

//            robot.alignTurretPosition();
            telemetry.update();
        }
    }
}
