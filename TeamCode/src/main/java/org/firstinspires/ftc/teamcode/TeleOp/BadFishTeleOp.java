package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp
public class BadFishTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initiates the robots system and subsystems!
        Robot robot = new Robot(hardwareMap, telemetry);

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            robot.driveTrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.displayLED.IntakeCheck(true);
        }
    }
}
