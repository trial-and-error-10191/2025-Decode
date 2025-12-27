package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class TagOrientation {
    private final AprilTagProcessor aprilTag;

    int desiredTagOrient;
    public TagOrientation(HardwareMap hwMap) {
        aprilTag = new AprilTagProcessor.Builder()

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);
    }
    public int findGoalTag(boolean blue) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        if (blue) {
            desiredTagOrient = 20;
        } if (!blue) {
            desiredTagOrient = 24;
        }
        return desiredTagOrient;
    }
}
