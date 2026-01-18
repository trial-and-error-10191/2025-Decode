package org.firstinspires.ftc.teamcode.Autonomous.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@Autonomous (name = "PatternMatch", group = "Robot")
public class PatternMatch extends LinearOpMode {
    long start = System.nanoTime();
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        start = System.nanoTime();
        robot.autoBase.PatternMatch(robot);
    }
}
