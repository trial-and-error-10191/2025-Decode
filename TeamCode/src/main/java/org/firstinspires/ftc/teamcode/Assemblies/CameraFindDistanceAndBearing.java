package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class CameraFindDistanceAndBearing {
    private Telemetry telemetry;
    double distance = 0;
    double bearing = 0;
    int unknownID;

    public CameraFindDistanceAndBearing (HardwareMap hwMap, AprilTagProcessor aprilTag, Telemetry telemetry) {
//        aprilTag = new AprilTagProcessor.Builder()
//
//                .build();
//
//        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
//        aprilTag.setDecimation(1);

        this.telemetry = telemetry;
    }
    public void distanceBearingFind (CameraDefinition aprilTag, int id, List<AprilTagDetection> currentDetections) {
//        List<AprilTagDetection> currentDetections = aprilTag.aprilTag.getDetections();
        for (AprilTagDetection detections : currentDetections) {
            if (detections.id == id) {
                // The range unit is in inches.
                distance = detections.ftcPose.range;
                // The bearing unit is in
                bearing = detections.ftcPose.bearing;
            }
        }
    }
}