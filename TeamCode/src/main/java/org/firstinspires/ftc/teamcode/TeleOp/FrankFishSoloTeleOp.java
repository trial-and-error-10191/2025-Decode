package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "FrankenFishSoloTeleOp", group = "LinearOpMode")
public class FrankFishSoloTeleOp extends LinearOpMode {

    enum mode {
        ManualClose(),
        ManualFar(),
        Auto();
    }
    mode currentMode = mode.Auto;
    boolean isLongPrevPressed = false;
    boolean isShortPrevPressed = false;

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {

            robot.adjustRpmMultiplier();

            robot.wheels.wheelsTick();
            robot.driveTrain.easingDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);


            if (!isShortPrevPressed && !isLongPrevPressed) {
                if (gamepad1.left_bumper) {
                    if (currentMode.equals(mode.ManualFar)) {
                        currentMode = mode.Auto;
                    } else {
                        currentMode = mode.ManualFar;
                    }
                }
                if (gamepad1.left_trigger > 0.05) {
                    if (currentMode.equals(mode.ManualClose)) {
                        currentMode = mode.Auto;
                    } else {
                        currentMode = mode.ManualClose;
                    }
                }
            }

            // set the previousPressed conditionals. separate from the logic for clarity.
            isLongPrevPressed = false;
            isShortPrevPressed = false;
            if (gamepad1.left_bumper) {
                isLongPrevPressed = true;
            }
            if (gamepad1.left_trigger > 0.05) {
                isShortPrevPressed = true;
            }

            // set the LED actions based on the current robot LED mode
            if (currentMode.equals(mode.ManualFar)) {
                robot.wheels.rpmReset(Robot.Distance.Long.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Red.color + 0.03);
                robot.modeLed.easingTick();
            } else if (currentMode.equals(mode.ManualClose)) {
                robot.wheels.rpmReset(Robot.Distance.Short.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Green.color - 0.10);
                robot.modeLed.easingTick();
            } else if (currentMode.equals(mode.Auto)){
                robot.autoTagSwap(Robot.tags.blueTeamGoal, Robot.tags.redTeamGoal);
            }

            robot.checkEndGame();

            if ((gamepad1.right_bumper || gamepad1.right_trigger > 0) && robot.wheels.calculateRpmAccuracy() > 90) {
                // Stops movement of the robot so it doesn't keep driving when we're supposed to shoot,
                // as there's still a bit of motor power even after the joysticks aren't pressed.
                robot.driveTrain.moveRobot(0, 0);
                robot.ShootOnce(gamepad1.right_trigger);
                robot.ShootAll(gamepad1.right_bumper);
            }
            robot.patternCorrectionTeleOp(gamepad1.a);

            telemetry.addData("test", robot.wheels.calculateRpmAccuracy());
            telemetry.update();
        }
    }
}
