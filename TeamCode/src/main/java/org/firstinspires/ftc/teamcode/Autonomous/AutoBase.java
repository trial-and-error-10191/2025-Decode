package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;

@Autonomous
public class AutoBase extends LinearOpMode {
    private final ElapsedTime Time = new ElapsedTime();

    public void runOpMode() {
//        LEDLight displayLED = new LEDLight();

        waitForStart();
        while (opModeIsActive()) {
//            displayLED.IntakeCheck(true);
        }
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
