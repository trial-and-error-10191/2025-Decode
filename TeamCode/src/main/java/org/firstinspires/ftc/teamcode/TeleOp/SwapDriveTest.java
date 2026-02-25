package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;

@TeleOp (name = "SwapDriveTest", group = "Test")
public class SwapDriveTest extends LinearOpMode {
    boolean squaredCheck = false;
    boolean squareRootedCheck = false;
    @Override
    public void runOpMode() {
        DriveTrain drive = new DriveTrain(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            drive.powerChanged(-gamepad1.left_stick_y, gamepad1.right_stick_x, squaredCheck, squareRootedCheck);
            if (gamepad1.dpad_up) {
                if (!squaredCheck) {
                    squaredCheck = true;
                } else {
                    squaredCheck = false;
                }
                squareRootedCheck = false;
            }
            if (gamepad1.dpad_down) {
                if (!squareRootedCheck) {
                    squareRootedCheck = true;
                } else {
                    squareRootedCheck = false;
                }
                squaredCheck = false;
            }
        }
    }
}
