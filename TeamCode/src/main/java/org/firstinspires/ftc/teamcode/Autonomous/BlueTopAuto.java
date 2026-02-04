package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Autonomous(name = "BlueTop", group = "Robot")
public class BlueTopAuto extends LinearOpMode {
    long start = System.nanoTime();
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.GoalSet(robot.driveTrain, true);
        robot.autoBase.SetToEncoders(robot.driveTrain);
        robot.wheels.wheelsTick();
        waitForStart();
        robot.driveTrain.driveWithEncoders(-1500, 30);
        robot.driveTrain.turnWithEncoders(290, 30);
        robot.autoBase.AprilTagAmount(robot, robot.obeliskOrder.findTag(robot.cameraDefinition.aprilTag));
        robot.autoBase.PatternMatch(robot);
        robot.driveTrain.turnWithEncoders(-170, 30);
        robot.autoBase.SetToPower(robot.driveTrain);
        robot.autoBase.AprilTagAmount(robot, 20);
        robot.autoBase.TurnPrecision(robot, 6.2, 20);
        robot.autoBase.DrivePrecision(robot, 35, 20);
        robot.wheels.rpmTarget = 2450;
        robot.autoBase.SetToEncoders(robot.driveTrain);
        robot.driveTrain.turnWithEncoders(-40, 30);
        robot.autoBase.Shoot(robot);
        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.autoBase.SetToPower(robot.driveTrain);
        robot.driveTrain.autoTurn(robot.autoBase.power, 0.2);
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 1);
        // This part of the path will be used when we hopefully get an intake system
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.2
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.1);
//        robot.intake.IntakeSpin(true);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.1);
//        robot.wheels.rpmTarget = 3000;
//        robot.intake.IntakeSpin(false);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 0.2);
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.ShootAll(true);
    }
}
