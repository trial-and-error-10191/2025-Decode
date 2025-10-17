package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.ArtifactRamp;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp(name = "ArtifactRampOperation", group  = "LinearOpMode")
public class ArtifactRampTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Initiates the robots system and subsystems.
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        ArtifactRamp servo = new ArtifactRamp(telemetry, hardwareMap);

        while (opModeIsActive()) {

            servo.rampServo(gamepad2.dpad_up, gamepad2.dpad_down);

            telemetry.update();
        }

        }
}
