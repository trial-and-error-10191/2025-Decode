package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class CameraFindDistanceAndBearing {
    private Telemetry telemetry;
    int goalID = 20;
    double distance = 0;
    double bearing = 0;

    public CameraFindDistanceAndBearing (HardwareMap hwMap, AprilTagProcessor aprilTag, Telemetry telemetry) {
        aprilTag = new AprilTagProcessor.Builder()

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);

        this.telemetry = telemetry;
    }
    public void distanceBearingFind (AprilTagProcessor aprilTag) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detections : currentDetections) {
            if (detections.id == goalID) {
                // The range unit is in inches.
                distance = detections.ftcPose.range;
                // The bearing unit is in
                bearing = detections.ftcPose.bearing;
            }
        }
    }
}
