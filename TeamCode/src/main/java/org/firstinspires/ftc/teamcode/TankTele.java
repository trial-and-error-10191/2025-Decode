package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TankTele", group = "LinearOpMode")
public class TankTele extends LinearOpMode {

    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        // Initiates the robots system and subsystems!

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();

        while(opModeIsActive()) {
            robot.driveTrain.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_x);
        }
    }
}