package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.BallRelease;

@Disabled
@TeleOp (name = "PaddleTest", group = "LinearOpMode")
public class PaddleTest extends LinearOpMode {
    BallRelease ballRelease = new BallRelease(hardwareMap, telemetry);
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                ballRelease.Open();
            } else if (gamepad1.b) {
                ballRelease.Close();
            }
        }
    }
}
