package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;

@TeleOp (name = "DriveOnly", group = "LinearOpMode")
public class DriveOnly extends LinearOpMode {
    @Override
    public void runOpMode() {
        DriveTrain driveTrain = new DriveTrain(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            driveTrain.easingDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}
