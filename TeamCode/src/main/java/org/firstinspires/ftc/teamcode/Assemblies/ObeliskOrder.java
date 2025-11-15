package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class ObeliskOrder {
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    int desiredTag;

    public ObeliskOrder(HardwareMap hwMap) {
        /// This first part sets up the camera so it can scan AprilTags
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();
            builder.setCamera(hwMap.get(WebcamName.class, "Webcam 1"));

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();
    }
    public int findTag() {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Only changes the desiredTag variable if the April Tag seen is from the obelisk
            if (detection.id == 21 || detection.id == 22 || detection.id == 23) {
                desiredTag = detection.id;
            } else {
                return desiredTag;
            }
        }
        return desiredTag;
    }

    public ArrayList<Robot.Color> patternOrder() { // Used to contain pattern info from the obelisk
        ArrayList<Robot.Color> patternOrder = new ArrayList<>();
        if (desiredTag == 21) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Green);
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Purple);
        } if (desiredTag == 22) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Green);
            patternOrder.add(Robot.Color.Purple);
        } if (desiredTag == 23) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Green);
        }
        return patternOrder; // Returning the order of the pattern
    }
}