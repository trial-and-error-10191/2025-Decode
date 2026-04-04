package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TurretBot", group = "LinearOpMode")
public class TeleOpTurretBot extends LinearOpMode {
    @Override
    public void runOpMode() {
        TurretRobot robot = new TurretRobot(hardwareMap, telemetry);
        waitForStart();
        robot.driveTrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}
