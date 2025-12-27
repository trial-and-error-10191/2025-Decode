package org.firstinspires.ftc.teamcode.TeleOp.Useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

import java.util.ArrayList;

@Disabled
@TeleOp(name = "test", group = "LinearOpMode")
public class test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

//        TelemetryUI telem = new TelemetryUI(telemetry);

        ArrayList<Robot.Color> colors = new ArrayList<>();
        colors.add(Robot.Color.Purple);
        colors.add(Robot.Color.Green);
        colors.add(Robot.Color.Nothing);

        while (opModeIsActive()) {
//            telem.update(colors);
        }
    }
}