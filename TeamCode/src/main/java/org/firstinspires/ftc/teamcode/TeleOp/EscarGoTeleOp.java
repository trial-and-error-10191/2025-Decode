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

        //robot.escarGOMech.initRotateByPower();
        while(opModeIsActive()) {
            robot.driveTrain.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
//            robot.escarGOMech.BallSuck(gamepad2.a);
//            robot.escarGOMech.WallStop(gamepad2.left_bumper || gamepad2.right_bumper);
//            robot.escarGOMech.WheelLaunch();
        }
    }
}