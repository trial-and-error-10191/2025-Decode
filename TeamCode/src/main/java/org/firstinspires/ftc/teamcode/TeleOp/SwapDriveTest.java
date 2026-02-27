package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;
import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "SwapDriveTest", group = "Test")
public class SwapDriveTest extends LinearOpMode {
    boolean squaredCheck = false;
    boolean squareRootedCheck = false;

    enum mode {
        None(),
        ManualClose(),
        ManualFar()
    }
    FrankFishSoloTeleOp.mode currentMode = FrankFishSoloTeleOp.mode.None;
    boolean isLongPrevPressed = false;
    boolean isShortPrevPressed = false;
    boolean isSquaredCheck = false;
    boolean isSquareRootedCheck = false;

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.wheels.wheelsTick();
            robot.driveTrain.powerChanged(-gamepad1.left_stick_y, gamepad1.right_stick_x, squaredCheck, squareRootedCheck);
            if (gamepad1.dpad_up && !isSquaredCheck) {
                if (!squaredCheck) {
                    squaredCheck = true;
                } else {
                    squaredCheck = false;
                }
                squareRootedCheck = false;
            }
            if (gamepad1.dpad_down && !isSquareRootedCheck) {
                if (!squareRootedCheck) {
                    squareRootedCheck = true;
                } else {
                    squareRootedCheck = false;
                }
                squaredCheck = false;
            }
            telemetry.addData("Power Squared?", squaredCheck);
            telemetry.addData("Power Square Rooted?", squareRootedCheck);
            telemetry.update();

            isSquaredCheck = false;
            isSquareRootedCheck = false;
            if (gamepad1.dpad_up) {
                isSquaredCheck = true;
            } if (gamepad1.dpad_down) {
                isSquareRootedCheck = true;
            }

            if (!isShortPrevPressed && !isLongPrevPressed) {
                if (gamepad1.left_bumper) {
                    if (currentMode.equals(FrankFishSoloTeleOp.mode.ManualFar)) {
                        currentMode = FrankFishSoloTeleOp.mode.None;
                    } else {
                        currentMode = FrankFishSoloTeleOp.mode.ManualFar;
                    }
                }
                if (gamepad1.left_trigger > 0.05) {
                    if (currentMode.equals(FrankFishSoloTeleOp.mode.ManualClose)) {
                        currentMode = FrankFishSoloTeleOp.mode.None;
                    } else {
                        currentMode = FrankFishSoloTeleOp.mode.ManualClose;
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
            if (currentMode.equals(FrankFishSoloTeleOp.mode.ManualFar)) {
                robot.wheels.rpmReset(Robot.Distance.Long.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Red.color);
                robot.modeLed.easingTick();
            } else if (currentMode.equals(FrankFishSoloTeleOp.mode.ManualClose)) {
                robot.wheels.rpmReset(Robot.Distance.Short.RPM);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Green.color);
                robot.modeLed.easingTick();
            } else if (currentMode.equals(FrankFishSoloTeleOp.mode.None)) {
                robot.wheels.rpmReset(0);
                robot.modeLed.setEasingMode(LEDLight.LightMode.Flat);
                robot.modeLed.setFlatColor(LEDLight.ColorValues.Black.color);
                robot.modeLed.easingTick();
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
        }
    }
}
