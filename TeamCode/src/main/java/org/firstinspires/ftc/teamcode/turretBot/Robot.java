package org.firstinspires.ftc.teamcode.turretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.opencv.core.Point;

import java.util.ArrayList;

public class Robot {

    // objects for every assembly and or dependency //
    public DriveTrain driveTrain;
    public CameraDefinition camera;
    public Turret turret;

    // telemetry and hardware map //
    Telemetry telemetry;
    HardwareMap hwMap;

    // robot position //
    public Vector2 position;

    /**
     * constructor for robot class.
     * @param hwMap hardware map to distribute to all objects
     * @param telemetry telemetry to output with internal functions
     */
    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        position = new Vector2(0);
        this.telemetry = telemetry;
        this.hwMap = hwMap;
        driveTrain = new DriveTrain(hwMap, telemetry);
        camera = new CameraDefinition(hwMap, telemetry);
        turret = new Turret(hwMap,telemetry);
    }

    /**
     * Enumeration for tag id's
     */
    public enum tags {
        redTeamGoal(24),
        blueTeamGoal(20);

        final int id;

        tags(int id) {
            this.id = id;
        }
    }

    int negXRestriction = 35;
    int posXRestriction = 35;

    public boolean alignRobot() {

        boolean aligned = false;

        ArrayList<AprilTagDetection> detections = camera.aprilTag.getDetections();

        int noticedDetections = 0;

        // check the amount of valid apriltags seen
        for (AprilTagDetection detection : detections) {
            if (detection.id == tags.blueTeamGoal.id || detection.id == tags.redTeamGoal.id) {
                noticedDetections++;
            }
        }

        // run the check
        if (noticedDetections == 1) {
            Point tagCenter = detections.get(0).center;
            double screenCenterLineCord = ((double) 640 / 2);
            double speed = 0.3;

            //if (speed < 0.1) {
//                aligned = true;
//            }

            // speed is based on distance from center line. approaching a multiplier of 0 at it approached
            speed = Math.max(speed * (Math.abs(screenCenterLineCord - tagCenter.x) / screenCenterLineCord), 0.10);


            if (tagCenter.x > screenCenterLineCord + posXRestriction) {
                turret.motor.setPower(speed);
            } else if (tagCenter.x < screenCenterLineCord - negXRestriction) {
                turret.motor.setPower(-speed);
            } else {
                aligned = true;
                turret.motor.setPower(0);
            }
        } else {
            turret.motor.setPower(0);
        }

        return aligned;
    }
}

}
