package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "FrankenFishSoloTeleOp", group = "LinearOpMode")
public class FrankFishSoloTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.wheels.wheelsTick();
            robot.driveTrain.easingDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            if (gamepad1.left_bumper) {
                robot.wheels.rpmReset(Robot.Distance.Long.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Orange.color);
                robot.modeLed.easingTick();
            } else if (gamepad1.left_trigger > 0.05) {
                robot.wheels.rpmReset(Robot.Distance.Short.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Yellow.color);
                robot.modeLed.easingTick();
            } else {
                robot.autoTagSwap(Robot.tags.blueTeamGoal, Robot.tags.redTeamGoal);
            }

            robot.checkEndGame();

            if (gamepad1.right_bumper || gamepad1.right_trigger > 0) {
                // Stops movement of the robot so it doesn't keep driving when we're supposed to shoot,
                // as there's still a bit of motor power even after the joysticks aren't pressed.
                robot.driveTrain.moveRobot(0, 0);
                robot.ShootOnce(gamepad1.right_trigger);
                robot.ShootAll(gamepad1.right_bumper);
            }
            robot.patternCorrectionTeleOp(gamepad1.a);
            robot.artifactPaddles.PaddleFix(gamepad1.x);
            if (gamepad1.b) {
                robot.artifactPaddles.PaddleFixHold();
            } else {
                robot.artifactPaddles.PaddleStop();
            }
        }
    }
}