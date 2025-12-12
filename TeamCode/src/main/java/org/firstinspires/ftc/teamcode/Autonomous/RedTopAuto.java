package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import java.util.ArrayList;

@Autonomous(name = "RedTop", group = "Robot")
public class RedTopAuto extends LinearOpMode {
    long start = System.nanoTime();
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        robot.artifactPaddles.AutoRot(1, true, robot.order);
        robot.wheels.rpmTarget = 3200;
        robot.wheels.wheelsTick();
        robot.driveTrain.autoDriveStraight(-robot.autoBase.power, 1.8);
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.3);
//        robot.obeliskOrder.patternOrder();
//        robot.patternMatchAuto();
//        robot.driveTrain.autoTurn(robot.autoBase.power, 0.3);
        robot.driveTrain.autoTurn(-robot.autoBase.power * 0.5, 0.01);
        robot.autoBase.Shoot(robot);
        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.2);
        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 1);
        // This part of the path will be used when we hopefully get an intake system
//        robot.driveTrain.autoTurn(-robot.autoBase.power, 0.1);
//        robot.driveTrain.autoDriveStraight(robot.autoBase.power, 0.2
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
