package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedTop", group = "Robot")
public class RedTopAuto extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.GoalSet(robot.driveTrain, false);
        robot.autoBase.SetToEncoders(robot.driveTrain);
        robot.wheels.wheelsTick();
        waitForStart();
        robot.driveTrain.driveWithEncoders(-1800, 30);
        robot.driveTrain.turnWithEncoders(-300, 30);
        robot.autoBase.AprilTagAmount(robot, robot.obeliskOrder.findTag(robot.cameraDefinition.aprilTag));
        robot.autoBase.PatternMatch(robot);
        robot.driveTrain.turnWithEncoders(160, 30);
        robot.autoBase.SetToPower(robot.driveTrain);
        robot.autoBase.AprilTagAmount(robot, 24);
        robot.autoBase.TurnPrecision(robot, 3, 24);
        robot.autoBase.DrivePrecision(robot, 60, 24);
        robot.wheels.rpmReset(Robot.Distance.Short.RPM);
        robot.autoBase.Shoot(robot);
        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.2);
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 1);
        // This part of the path will be used when we hopefully get an intake system
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.2);
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.intake.IntakeSpin(true);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.1);
//        robot.wheels.rpmTarget = 3000;
//        robot.intake.IntakeSpin(false);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.2);
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.1);
//        robot.ShootAll(true);
    }
}