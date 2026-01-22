package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedBottom", group = "Robot")
public class RedBottomAuto extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
//        robot.wheels.rpmTarget = 3800;
        robot.autoBase.GoalSet(robot.driveTrain, false);
        robot.autoBase.SetToEncoders(robot.driveTrain);
        waitForStart();
        robot.autoBase.AprilTagAmount(robot);
        robot.autoBase.PatternMatch(robot);
        robot.driveTrain.driveWithEncoders(50, 30);
        robot.driveTrain.turnWithEncoders(30, 30);
        robot.autoBase.SetToPower(robot.driveTrain);
        robot.driveTrain.autoTurn(0.5, 0.1);
        robot.autoBase.AprilTagAmount(robot);
        robot.autoBase.TurnPrecision(robot, 3, 24);
        robot.autoBase.DrivePrecision(robot, 120, 24);
        robot.autoBase.Shoot(robot);
//        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.01);
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.3);
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