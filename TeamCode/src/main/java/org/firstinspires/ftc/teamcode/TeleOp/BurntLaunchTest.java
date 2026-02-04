package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "LaunchTest", group = "Test")
public class BurntLaunchTest extends LinearOpMode {

    public DcMotor left;
    public DcMotor right;

    @Override
    public void runOpMode() {
        left = hardwareMap.get(DcMotor.class, "LaunchWheel1");
        right = hardwareMap.get(DcMotor.class, "LaunchWheel2");

        // Reverse the wheels to obtain the desired direction
        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                left.setPower(0.1);
            } else {
                right.setPower(0.1);
            }
        }
    }
}
