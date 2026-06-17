package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrainMecanum;

@TeleOp (name = "FieldOriented", group = "LinearOpMode")
public class FieldTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        DriveTrainMecanum driveTrain = new DriveTrainMecanum(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            driveTrain.fieldOriented(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);
        }
    }
}
