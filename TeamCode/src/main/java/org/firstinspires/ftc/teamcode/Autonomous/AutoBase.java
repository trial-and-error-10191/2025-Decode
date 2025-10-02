package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;
import org.firstinspires.ftc.teamcode.Assemblies.EscarGOMech;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous
public class AutoBase extends LinearOpMode {
    AprilTagDetect aprilTag;
    DriveTrain drive;
    EscarGOMech launcher;

    private final ElapsedTime Time = new ElapsedTime();

    public void runOpMode() {
        aprilTag = new AprilTagDetect();
        launcher = new EscarGOMech(hardwareMap, telemetry);
        drive    = new DriveTrain(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            launcher.WheelLaunch();
            // Path stuff maybe?
        }
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}