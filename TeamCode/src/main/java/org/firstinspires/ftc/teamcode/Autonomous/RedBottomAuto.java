package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedBottom", group = "Robot")
public class RedBottomAuto extends LinearOpMode {
    static final double TURN_SPEED = 0.7;
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
//            robot.wheels.wheelsTick();
//            robot.obeliskOrder.patternOrder();
//            robot.patternMatchAuto();
            robot.driveTrain.driveStraight(TURN_SPEED, 20, 0);
            robot.autoBase.Wait(1);
            robot.driveTrain.turnToHeading(TURN_SPEED, 50);
            robot.ShootAll(true);
            // Next 2 lines moves bot out of the way since we can't get anymore points
            robot.driveTrain.turnToHeading(TURN_SPEED, 130);
            robot.autoBase.Wait(1);
            robot.driveTrain.driveStraight(TURN_SPEED, 25, 130);
            // This part of the path will be used when we hopefully get an intake system
//            robot.driveTrain.turnToHeading(TURN_SPEED, 100);
//            robot.driveTrain.driveStraight(TURN_SPEED, 10, 100);
//            robot.driveTrain.turnToHeading(TURN_SPEED, 90);
//            robot.intake.IntakeSpin(true);
//            robot.driveTrain.driveStraight(TURN_SPEED, 15, 90);
//            robot.intake.IntakeSpin(false);
//            robot.driveTrain.driveStraight(TURN_SPEED, -35, 90);
//            robot.driveTrain.turnToHeading(TURN_SPEED, 0);
//            robot.driveTrain.driveStraight(TURN_SPEED, 5, 0);
//            robot.driveTrain.turnToHeading(TURN_SPEED, 50);
//            robot.ShootAll(true);
        }
    }
}
