package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "FrankenFish", group = "LinearOpMode")
public class FrankenFishTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.driveTrain.allMotorsDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.ShootOnce(gamepad2.right_trigger);
            robot.ShootAll(gamepad2.right_bumper);
            robot.patternCorrectionTeleOp(gamepad2.a);
        }
    }
}
