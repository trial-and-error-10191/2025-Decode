package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "PatternShift", group = "LinearOpMode")
public class PatternShift extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.patternCorrectionTeleOp(gamepad2.a);
            robot.patternMatchAuto();
            telemetry.addData("Tag ID: ", robot.obeliskOrder.desiredTagObelisk);
            telemetry.addData("Holder Order: ", robot.order);
            telemetry.update();
        }
    }
}
