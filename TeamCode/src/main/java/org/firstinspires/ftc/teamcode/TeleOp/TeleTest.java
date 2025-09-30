package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DisplayLED;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "TeleTest", group = "LinearOpMode")
public class TeleTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        DisplayLED displayLED;
        displayLED = new DisplayLED(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            displayLED.IntakeCheck(true, robot);
        }
    }
}
