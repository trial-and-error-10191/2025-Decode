package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class DriveByAprilTagGoal {
    // Adjust these numbers to suit your robot.

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    //  Drive = Error * Gain    Make these values smaller for smoother control, or larger for a more aggressive response.
    final double SPEED_GAIN =   0.02 ;   //  Speed Control "Gain". e.g. Ramp up to 50% power at a 25 inch error.   (0.50 / 25.0)
    final double TURN_GAIN  =   0.01 ;   //  Turn Control "Gain".  e.g. Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN  = 0.25;  //  Clip the turn speed to this max value (adjust for your robot)

    private static final int DESIRED_TAG_ID = 24;    // Choose the tag you want to approach or set to -1 for ANY tag.
    private AprilTagDetection desiredTag = null;     // Used to hold the data for a detected AprilTag
    private Telemetry telemetry;

    boolean targetFound     = false;    // Set to true when an AprilTag target is detected
    double  drive           = 0;        // Desired forward power/speed (-1 to +1) +ve is forward
    double  turn            = 0;        // Desired turning power/speed (-1 to +1) +ve is CounterClockwise

    public DriveByAprilTagGoal(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void DriveByAprilTag(double DESIRED_DISTANCE, AprilTagProcessor aprilTag) {
        targetFound = false;
        desiredTag = null;

        // Step through the list of detected tags and look for a matching tag
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            // Look to see if we have size info on this tag.
            if (detection.metadata != null) {
                //  Check to see if we want to track towards this tag.
                if ((DESIRED_TAG_ID < 0) || (detection.id == DESIRED_TAG_ID)) {
                    // Yes, we want to use this tag.
                    targetFound = true;
                    desiredTag = detection;
                    break;  // don't look any further.
                } else {
                    // This tag is in the library, but we do not want to track it right now.
                    telemetry.addData("Skipping", "Tag ID %d is not desired", detection.id);
                }
            } else {
                // This tag is NOT in the library, so we don't have enough information to track to it.
                telemetry.addData("Unknown", "Tag ID %d is not in TagLibrary", detection.id);
            }
        }
        // Tell the driver what we see, and what to do.
        if (targetFound) {
            telemetry.addData("\n>", "HOLD Left-Bumper to Drive to Target\n");
            telemetry.addData("Found", "ID %d (%s)", desiredTag.id, desiredTag.metadata.name);
            telemetry.addData("Range", "%5.1f inches", desiredTag.ftcPose.range);
            telemetry.addData("Bearing", "%3.0f degrees", desiredTag.ftcPose.bearing);
        } else {
            telemetry.addData("\n>", "Drive using joysticks to find valid target\n");
        }

        // If Left Bumper is being pressed, AND we have found the desired target, Drive to target Automatically .
        if (targetFound) {
            // Determine heading and range error so we can use them to control the robot automatically.
            double rangeError = (desiredTag.ftcPose.range - DESIRED_DISTANCE);
            double headingError = desiredTag.ftcPose.bearing;

            // Use the speed and turn "gains" to calculate how we want the robot to move.  Clip it to the maximum
            drive = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
            if (DESIRED_DISTANCE < 0) {
                drive *= -1;
            } if (DESIRED_DISTANCE > 0) {
                drive = Math.abs(drive);
            }
            turn = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);

            telemetry.addData("Auto", "Drive %5.2f, Turn %5.2f", drive, turn);
        }
        telemetry.update();
    }
}