package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.AprilTags;
import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;

@Autonomous
public class AutoBase extends LinearOpMode {
    private final ElapsedTime Time = new ElapsedTime();

    public void runOpMode() {
        LEDLight displayLED = new LEDLight();
        AprilTags aprilTags = new AprilTags();

        waitForStart();
        while (opModeIsActive()) {
            aprilTags.locateTargetAprilTag();
            displayLED.IntakeCheck(true);
        }
    }
}
