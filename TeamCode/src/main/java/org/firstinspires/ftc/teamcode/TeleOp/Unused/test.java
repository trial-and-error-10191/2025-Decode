package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.DriveTrain;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

import java.util.ArrayList;

//@Disabled
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
          driveTrain.easingDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);

          if (gamepad1.y) {
              driveTrain.reductionSmoothing += 0.0003;
          } else if (gamepad1.a) {
              driveTrain.reductionSmoothing -= 0.0003;
          } else if (gamepad1.x) {
              driveTrain.MSthreshold += 0.0003;
          } else if (gamepad1.b) {
              driveTrain.MSthreshold -= 0.0003;
          }

          driveTrain.reductionSmoothing = Math.max(driveTrain.reductionSmoothing, 0.1);

          telemetry.addData("smoothingVal : ", driveTrain.reductionSmoothing);
          telemetry.addData("Threshold in MS : ", driveTrain.MSthreshold);
          telemetry.update();


        }
    }
}