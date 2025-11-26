package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.ArtifactPaddles;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

import java.util.ArrayList;

@TeleOp (name = "RotateTest", group = "LinearOpMode")
public class RotateTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        ArtifactPaddles paddles = new ArtifactPaddles(hardwareMap, telemetry);
        ArrayList<Robot.Color> order = new ArrayList<>();
        order.add(Robot.Color.Purple);
        order.add(Robot.Color.Purple);
        order.add(Robot.Color.Purple);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.a) {
                paddles.AutoRot(1, true, order);
            }
        }
    }
}
