package org.firstinspires.ftc.teamcode.TeleOp.Useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@TeleOp (name = "Wheels", group = "LinearOpMode")
public class LaunchTest extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.a) {
                robot.wheels.MainMotor1.setPower(0);
                robot.wheels.MainMotor2.setPower(1);
            } else if (gamepad2.b) {
                robot.wheels.MainMotor2.setPower(0);
                robot.wheels.MainMotor1.setPower(1);
            }
        }
    }
}
