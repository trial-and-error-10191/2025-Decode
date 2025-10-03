package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DisplayLED;

@TeleOp (name = "TeleTest", group = "LinearOpMode")
public class TeleTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        DisplayLED displayLED;
        displayLED = new DisplayLED(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            displayLED.IntakeCheck(true);
        }
    }
}
