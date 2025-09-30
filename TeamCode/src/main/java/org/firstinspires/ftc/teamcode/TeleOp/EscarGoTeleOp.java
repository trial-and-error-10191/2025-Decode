package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "EscarGoTeleOp", group = "LinearOpMode")
public class EscarGoTeleOp extends LinearOpMode {
    public void runOpMode() {
        // Initiates the robots system and subsystems!
        Robot robot = new Robot(hardwareMap, telemetry);

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();
        //robot.escarGOMech.initRotateByPower();
        while(opModeIsActive()) {
            robot.driveTrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
            robot.displayLED.IntakeCheck(true);
//            robot.escarGOMech.BallIntake(gamepad2.a);
//            robot.escarGOMech.WheelLaunch();
        }
    }
}