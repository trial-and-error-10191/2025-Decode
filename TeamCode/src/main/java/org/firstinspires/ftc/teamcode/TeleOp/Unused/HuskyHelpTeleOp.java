package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.HuskyLensSense;

@Disabled
@TeleOp (name = "HuskyTeleOp", group = "Test")
public class HuskyHelpTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Initiates the robots system and subsystems!
        HuskyLensSense huskyLensSense = new HuskyLensSense(hardwareMap, telemetry);

        telemetry.addData("Status", "Waiting for Start");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            int color = huskyLensSense.SendHelp(true);
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
