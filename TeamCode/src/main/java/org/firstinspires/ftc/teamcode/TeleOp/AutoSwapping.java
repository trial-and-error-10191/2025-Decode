package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp(name = "AutoSwapping", group = "LinearOpMode")
public class AutoSwapping extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {

            robot.wheels.wheelsTick();
            robot.driveTrain.easingDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (robot.cameraDefinition.distanceFromTag(20) > 0 || robot.cameraDefinition.distanceFromTag(24) > 0) {
               double dist = robot.cameraDefinition.aprilTag.getDetections().get(0).ftcPose.range;

               if ( dist < 80) {
                 robot.swapMode(Robot.Distance.Long);
               } else {
                 robot.swapMode(Robot.Distance.Short);
               }
            }
        }
    }
}

