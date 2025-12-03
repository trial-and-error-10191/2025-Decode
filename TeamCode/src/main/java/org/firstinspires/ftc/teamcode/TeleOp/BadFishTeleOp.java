package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "BasFishTeleOp", group = "LinearOpMode")
@Disabled
public class BadFishTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initiates the robots system and subsystems!
        Robot robot = new Robot(hardwareMap, telemetry);

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
//            robot.artifactPaddles.AutoRot(1, true);
//            robot.badFishLaunch.Yeet();
//            robot.badFishLaunch.Intake(gamepad2.b);
//            robot.badFishLaunch.Rotate(gamepad2.left_stick_y);
//            robot.badFishLaunch.ballHoldRotate(gamepad2.right_stick_x);
//            robot.driveTrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
//            robot.displayLED.IntakeCheck(true);
        }
    }
}
