package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

    @TeleOp(name = "test23", group = "LinearOpMode")
    public class testFiring extends LinearOpMode {
        @Override
        public void runOpMode() {
            Robot robot = new Robot(hardwareMap, telemetry);
            waitForStart();

            while (opModeIsActive()) {
                if (gamepad1.right_bumper) {
                    robot.FireVariable(Robot.FireAmount.One);
                }
                if (gamepad1.left_bumper) {
                    robot.FireVariable(Robot.FireAmount.Two);
                }
                if (gamepad1.left_stick_button) {
                    robot.FireVariable(Robot.FireAmount.Three);
                }

            }
        }
    }
