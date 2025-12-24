package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Disabled
public class ObeliskOrder {
    private final AprilTagProcessor aprilTag;

    int desiredTag;
    public ObeliskOrder() {

        aprilTag = new AprilTagProcessor.Builder()

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);
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
        findTag();
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