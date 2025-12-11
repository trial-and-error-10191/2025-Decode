package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "FrankenFishSoloTeleOp", group = "LinearOpMode")
public class FrankFishSoloTeleOp extends LinearOpMode {
    boolean lastInputUp = false;
    boolean lastInputDown = false;
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
//        robot.ballRelease.Close();
        waitForStart();
        while (opModeIsActive()) {
            robot.wheels.wheelsTick();
            robot.driveTrain.allMotorsDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            if (gamepad1.dpad_up && !lastInputUp) {
                if (robot.wheels.rpmTarget == 3320) {
                    robot.wheels.rpmTarget = 3000;
                } else {
                    robot.wheels.rpmTarget = 3320;
                }
            }
            lastInputUp = gamepad1.dpad_up;
            if (gamepad1.dpad_down && !lastInputDown) {
                if (robot.wheels.rpmTarget == 3000) {
                    robot.wheels.rpmTarget = 3320;
                } else {
                    robot.wheels.rpmTarget = 3000;
                }
            }
            lastInputDown = gamepad1.dpad_down;
            telemetry.addData("Rpm", robot.wheels.rpmTarget);
            telemetry.update();
            if (gamepad1.right_bumper || gamepad1.right_trigger > 0) {
                // Stops movement of the robot so it doesn't keep driving when we're supposed to shoot,
                // as there's still a bit of motor power even after the joysticks aren't pressed.
                robot.driveTrain.moveRobot(0, 0);
                robot.ShootOnce(gamepad1.right_trigger);
                robot.ShootAll(gamepad1.right_bumper);
            }
            robot.patternCorrectionTeleOp(gamepad1.a);
        }
    }
}
