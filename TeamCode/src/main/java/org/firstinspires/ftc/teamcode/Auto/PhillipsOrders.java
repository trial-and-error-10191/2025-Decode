package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.ArtifactPaddles;

@Autonomous(name = "lets get this mode working by the end of the meeting so it can get the paddles to move")
public class PhillipsOrders extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        ArtifactPaddles paddle = new ArtifactPaddles(telemetry, hardwareMap);

        waitForStart();

        paddle.AutoRot(1, true);

        Thread.sleep(100);

        paddle.AutoRot(1, true);

    }
}
