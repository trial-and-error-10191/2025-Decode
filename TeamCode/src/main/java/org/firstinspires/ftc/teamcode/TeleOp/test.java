package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;
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


        int i = 0;
        DriveTrain driveTrain = new DriveTrain(hardwareMap,telemetry);
        ArrayList<Robot.Color> colors = new ArrayList<>();
        colors.add(Robot.Color.Purple);
        colors.add(Robot.Color.Green);
        colors.add(Robot.Color.Nothing);

        while (opModeIsActive()) {
            i++;
          driveTrain.allMotorsDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
          telemetry.addLine(String.valueOf(i));
          telemetry.addLine(String.valueOf(driveTrain.spinDownReduction));
          telemetry.update();

          if (gamepad1.a) {
              driveTrain.spinDownReduction -= 0.001;
          } else if (gamepad1.y) {
              driveTrain.spinDownReduction += 0.001;
          }
        }
    }
}