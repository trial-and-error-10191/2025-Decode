package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@Disabled
public class ObeliskOrder {

    public int desiredTagObelisk;

    //  Set the GAIN constants to control the relationship between the measured position error, and how much power is
    //  applied to the drive motors to correct the error.
    private Telemetry telemetry;

    public ObeliskOrder(HardwareMap hardwareMap, AprilTagProcessor aprilTag, Telemetry telemetry) {
        aprilTag = new AprilTagProcessor.Builder()

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);

        this.telemetry = telemetry;
    }
    public int findTag(AprilTagProcessor aprilTag) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                if (detection.id == 21 || detection.id == 22 || detection.id == 23) {
                    desiredTagObelisk = detection.id;
                }
            }
        }
        return desiredTagObelisk;
    }
    public ArrayList<Robot.Color> patternOrder(AprilTagProcessor aprilTag) { // Used to contain pattern info from the obelisk
        findTag(aprilTag);
        ArrayList<Robot.Color> patternOrder = new ArrayList<>();
        if (desiredTagObelisk == 21) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Green);
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Purple);
        } if (desiredTagObelisk == 22) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Green);
            patternOrder.add(Robot.Color.Purple);
        } if (desiredTagObelisk == 23) { // Setting up order for the balls
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Purple);
            patternOrder.add(Robot.Color.Green);
        }
        return patternOrder; // Returning the order of the pattern
    }
}