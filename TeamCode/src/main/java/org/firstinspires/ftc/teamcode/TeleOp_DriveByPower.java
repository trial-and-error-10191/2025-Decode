package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive By Power")
public class TeleOp_DriveByPower extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot sparky = new Robot(telemetry, hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            sparky.UpdateState(gamepad1, gamepad2);
            sparky.OperateRobot(gamepad1, gamepad2);
            telemetry.update();
        }
    }
}
