package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "SpinAuto", group = "Robot")
public class SpinAuto extends LinearOpMode {
    long start = System.nanoTime();
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 1, 0, 1, 4);
    }
}