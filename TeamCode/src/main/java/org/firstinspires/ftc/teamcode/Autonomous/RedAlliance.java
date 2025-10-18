package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous
public class RedAlliance extends LinearOpMode {
    private final ElapsedTime Time = new ElapsedTime();

    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {

        }
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
