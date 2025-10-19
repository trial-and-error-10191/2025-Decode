package org.firstinspires.ftc.teamcode.Assemblies;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Disabled
public class AprilTags {
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag; // Sets up the processing of April Tags
    private VisionPortal visionPortal; // Sets up camera
    boolean targetFound = false;
    private int DESIRED_TAG_ID = 20;
    private AprilTagDetection desiredTag = null;

    public DriveTrain driveTrain;

    public void locateTargetAprilTag() {
        driveTrain = new DriveTrain(hardwareMap, telemetry);

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
            if (DESIRED_TAG_ID == 20) { // Make the bot go to the blue goal
                driveTrain.turnToHeading(0.5, 30); // Probably needs fine-tuning
            } if (DESIRED_TAG_ID == 21) { // Shoot balls in green, purple, purple order

            } if (DESIRED_TAG_ID == 22) { // Shoot balls in purple, green, purple order

            } if (DESIRED_TAG_ID == 23) { // Shoot balls in purple, purple, green order

            } if (DESIRED_TAG_ID == 24) { // Make the bot go to the red goal
                driveTrain.turnToHeading(0.5, -30); // Probably needs fine-tuning
            }
        }
    } // end of locateTargetAprilTag
}
