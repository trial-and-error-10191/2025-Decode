package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.CameraDefinition;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp (name = "BearingTest", group = "LinearOpMode")
public class BearingTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        CameraDefinition cameraDefinition = new CameraDefinition(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            List<AprilTagDetection> currentDetections = cameraDefinition.aprilTag.getDetections();
            for (AprilTagDetection detection : currentDetections) {
                if (detection.metadata != null) {
                    if (detection.id == 21 || detection.id == 20) {
                        telemetry.addData("Bearing", detection.ftcPose.bearing);
                        telemetry.addData("Yaw", detection.ftcPose.yaw);
                        telemetry.update();
                    }
                }
            }
        }
    }
}
