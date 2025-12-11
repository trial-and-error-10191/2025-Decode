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
            robot.driveTrain.allMotorsDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            if (gamepad1.dpad_up) {
                if (robot.wheels.rpmTarget == 3000) {
                    robot.wheels.rpmTarget = 3300;
                } if (robot.wheels.rpmTarget == 3300) {
                    robot.wheels.rpmTarget = 3000;
                }
            } if (gamepad1.dpad_down) {
                if (robot.wheels.rpmTarget == 3300) {
                    robot.wheels.rpmTarget = 3000;
                }
                robot.wheels.rpmTarget = 3300;
            }
            telemetry.addData("Rpm", robot.wheels.rpmTarget);
            telemetry.update();
            robot.ShootOnce(gamepad1.right_trigger);
            robot.ShootAll(gamepad1.right_bumper);
            robot.patternCorrectionTeleOp(gamepad1.a);
        }
    }
}
