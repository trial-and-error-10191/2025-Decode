package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

import org.firstinspires.ftc.teamcode.Assemblies.TelemetryUI;
import org.firstinspires.ftc.teamcode.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

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