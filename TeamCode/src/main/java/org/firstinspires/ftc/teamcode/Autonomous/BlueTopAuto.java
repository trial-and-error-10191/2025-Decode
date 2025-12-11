package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "BlueTop", group = "Robot")
public class BlueTopAuto extends LinearOpMode {
    static final double TURN_SPEED = 0.7;
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        robot.wheels.rpmTarget = 3000;
        robot.wheels.wheelsTick();
        robot.driveTrain.autoDriveStraight(-0.5, 2);
//        robot.driveTrain.autoTurn(0.5, 0.3);
//        robot.obeliskOrder.patternOrder();
//        robot.patternMatchAuto();
//        robot.driveTrain.autoTurn(-0.5, 0.3);
        robot.ShootAll(true);
        robot.wheels.rpmTarget = 0;
        // Next 2 lines moves bot out of the way since we can't get anymore points
        robot.driveTrain.autoTurn(0.5, 0.2);
        robot.driveTrain.autoDriveStraight(0.5, 1);
            // This part of the path will be used when we hopefully get an intake system
//        robot.driveTrain.turnToHeading(TURN_SPEED, 10);
//        robot.intake.IntakeSpin(true);
//        robot.driveTrain.driveStraight(TURN_SPEED, 35, -10);
//        robot.intake.IntakeSpin(false);
//        robot.driveTrain.driveStraight(TURN_SPEED, -35, -10);
//        robot.driveTrain.turnToHeading(TURN_SPEED, -130);
//        robot.ShootAll(true);
    }
}
