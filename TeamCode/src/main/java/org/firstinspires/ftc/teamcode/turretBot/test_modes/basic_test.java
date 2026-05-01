package org.firstinspires.ftc.teamcode.turretBot.test_modes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.turretBot.Robot;

@TeleOp(name = "LaunchTest", group = "Test")
public class basic_test extends LinearOpMode {

    public Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap,telemetry);

        while (opModeIsActive()) {
            robot.alignRobot();
            robot.driveTrain.easingDrive(gamepad1.left_stick_x, gamepad1.right_stick_y);
        }
    }
}
