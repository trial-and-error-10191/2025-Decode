package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "ShootTest", group = "LinearOpMode")
public class ShootTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.ballRelease.Close();
        waitForStart();
        while (opModeIsActive()) {
            robot.ballRelease.DropBall(gamepad2.right_trigger);
        }
    }
}
