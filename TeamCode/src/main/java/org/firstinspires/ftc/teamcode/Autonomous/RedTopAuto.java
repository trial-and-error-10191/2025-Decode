package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedTop", group = "Robot")
public class RedTopAuto extends LinearOpMode {
    static final double TURN_SPEED = 0.7;
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            robot.driveTrain.driveStraight(TURN_SPEED, 20, 0);
            robot.driveTrain.turnToHeading(TURN_SPEED, 100);
            robot.obeliskOrder.patternOrder();
            robot.patternMatchAuto();
            robot.wheels.wheelsTick();
            robot.driveTrain.turnToHeading(TURN_SPEED, 130);
            robot.ShootAll(true);
//            robot.wheels.MainMotor.setPower(0);
            // Next 2 lines moves bot out of the way since we can't get anymore points
            robot.driveTrain.turnToHeading(TURN_SPEED, 100);
            robot.driveTrain.driveStraight(TURN_SPEED, 15, 100);
            // This part of the path will be used when we hopefully get an intake system
            robot.driveTrain.turnToHeading(TURN_SPEED, -10);
            robot.intake.IntakeSpin(true);
            robot.driveTrain.driveStraight(TURN_SPEED, 35, -10);
            robot.intake.IntakeSpin(false);
            robot.driveTrain.driveStraight(TURN_SPEED, -35, -10);
            robot.wheels.wheelsTick();
            robot.driveTrain.turnToHeading(TURN_SPEED, 130);
            robot.ShootAll(true);
//            robot.wheels.MainMotor.setPower(0);
        }
    }
}
