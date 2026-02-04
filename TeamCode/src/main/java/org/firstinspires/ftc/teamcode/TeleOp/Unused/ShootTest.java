package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@TeleOp (name = "ShootTest", group = "Test")
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
