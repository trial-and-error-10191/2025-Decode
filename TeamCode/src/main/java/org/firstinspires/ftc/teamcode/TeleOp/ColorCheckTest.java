package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.BallDetect;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "ColorCheckTest", group = "LinearOpMode")
public class ColorCheckTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initiates the robots system and subsystems!
        BallDetect ballDetect = new BallDetect(hardwareMap);

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            int color = ballDetect.colorFind(true);
            if (color == 1) {
                telemetry.addData("Color Found Green", "");
            } else if (color == 2) {
                telemetry.addData("Color Found Purple", "");
            } else if (color == -1) {
                telemetry.addData("Rate Limit Has Expired", "");
            } else {
                telemetry.addData("Color Null", "");
            }
            telemetry.update();
        }
    }
}
