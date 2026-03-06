package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "Shoot", group = "Test")
public class StationShot extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.wheels.wheelsTick();
        waitForStart();
        robot.wheels.rpmReset(3300);
        robot.autoBase.Shoot(robot);
        robot.wheels.rpmTarget = 0;
    }
}
