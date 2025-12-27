package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@TeleOp (name = "BearingTest", group = "LinearOpMode")
public class BearingTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            List<AprilTagDetection> currentDetections = robot.aprilTag.getDetections();
            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    if (detection.id == 24 || detection.id == 20) {
                        telemetry.addData("Bearing", detection.ftcPose.bearing);
                        telemetry.addData("Yaw", detection.ftcPose.yaw);
                        telemetry.update();
                    }
                }
            }
        }
    }
}
