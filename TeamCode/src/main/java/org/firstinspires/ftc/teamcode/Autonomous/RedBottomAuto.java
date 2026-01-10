package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedBottom", group = "Robot")
public class RedBottomAuto extends LinearOpMode {
    long start = System.nanoTime();
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
//        robot.autoBase.GoalSet(robot.driveTrain, false);
//        robot.autoBase.AprilTagAmount(robot);
        waitForStart();
        robot.wheels.rpmTarget = 3320;
//        robot.patternMatchAuto();
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.2);
        robot.driveTrain.autoTurn(robot.autoBase.power, 0.03);
//        robot.autoBase.TurnPrecision(robot, 10);
//        robot.autoBase.DrivePrecision(robot, 200);
        robot.autoBase.Shoot(robot);
        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.01);
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.2);
        // This part of the path will be used when we hopefully get an intake system
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.1);
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.intake.IntakeSpin(true);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.1);
//        robot.wheels.rpmTarget = 3320;
//        robot.intake.IntakeSpin(false);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.1);
//        robot.ShootAll(true);
    }
}
