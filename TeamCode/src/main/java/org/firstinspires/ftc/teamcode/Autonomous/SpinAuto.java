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
        start = System.nanoTime();
        while (System.nanoTime() - start == 4E9) {
            robot.driveTrainMecanum.fieldOrientedAuto(1, 0, 1);
        }
    }
}