package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "FrankenFishSoloTeleOp", group = "LinearOpMode")
public class FrankFishSoloTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
//        robot.ballRelease.Close();
        waitForStart();
        while (opModeIsActive()) {
            robot.wheels.wheelsTick();
            robot.driveTrain.easingDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.autoTagSwap(Robot.tags.blueTeamGoal, Robot.tags.redTeamGoal);

            telemetry.addData("Rpm", robot.wheels.rpmTarget);

            if (gamepad1.right_bumper || gamepad1.right_trigger > 0) {
                // Stops movement of the robot so it doesn't keep driving when we're supposed to shoot,
                // as there's still a bit of motor power even after the joysticks aren't pressed.
                robot.driveTrain.moveRobot(0, 0);
                robot.ShootOnce(gamepad1.right_trigger);
                robot.ShootAll(gamepad1.right_bumper);
            }
            robot.patternCorrectionTeleOp(gamepad1.a);

            telemetry.addData("Mode", robot.wheels.rpmTarget);
            telemetry.update();
        }
    }
}
