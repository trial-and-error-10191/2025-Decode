package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTagDetect {
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag; // Sets up the processing of April Tags
    private VisionPortal visionPortal; // Sets up camera
    boolean targetFound = false;
    private int DESIRED_TAG_ID = 1;
    private AprilTagDetection desiredTag = null;

    private void locateTargetAprilTag() {
        targetFound = false;
        desiredTag = null;

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.
            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                // **DESIRED TAG IS DETERMINED BY FIRST PIXEL CODE**

                if ((DESIRED_TAG_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTag = detection;

                    //gobbler.driveTrain.moveForward(1,0.5);

                    break;  // don't look any further.
                }
            }
        }
    } // end of locateTargetAprilTag
}
