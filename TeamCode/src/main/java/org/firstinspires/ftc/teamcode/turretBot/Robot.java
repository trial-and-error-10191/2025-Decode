package org.firstinspires.ftc.teamcode.turretBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
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
    public Gamepad active_gamepad;
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

    // data for alignTurretPower() //
    int negXRestriction = 35;
    int posXRestriction = 35;

    /**
     * align the turret to the camera using Power
     * @return true if aligned, and false if still aligning.
     */
    public boolean alignTurretPower() {

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
            double speed = 0.1;

 //           telemetry.addData("center_distance", (Math.abs(screenCenterLineCord - tagCenter.x) / screenCenterLineCord));

            // speed is based on distance from center line. approaching a multiplier of 0 at it approached
            speed = speed * (Math.abs(screenCenterLineCord - tagCenter.x) / screenCenterLineCord);

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

        telemetry.addData("power", turret.motor.getPower());
        telemetry.update();
        return aligned;
    }

    // data for alignTurretPosition() //
    public double dampingFactor = 0.3;
    public int previous_direction = 0;

    /**
     * align the turret to the camera using @param dampingFactor as a speed multiplier.
     */
    public void alignTurretPosition() {

        ArrayList<AprilTagDetection> detections = camera.aprilTag.getDetections();
        AprilTagDetection detectionPrimary = null;

        int noticedDetections = 0;

        // check the amount of valid apriltags seen
        for (AprilTagDetection detection : detections) {
            if (detection.id == tags.blueTeamGoal.id || detection.id == tags.redTeamGoal.id) {
                noticedDetections++;
                detectionPrimary = detection;
            }
        }

        // run the check
        if (noticedDetections == 1) {
            turret.motor.setPower(1);
            turret.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            double Change = ( -detectionPrimary.ftcPose.bearing / 360) * turret.encoder_counts_per_rotation * dampingFactor;
            turret.motor.setTargetPosition( (int) (turret.motor.getCurrentPosition() + Change));

            if ( active_gamepad.left_stick_y > 0) {
                previous_direction = 1;
            } else {
                previous_direction = -1;
            }

            telemetry.addData("direction", previous_direction);

        } else if (noticedDetections == 0) {
            if (previous_direction > 0) {
                turret.motor.setTargetPosition( (int) (turret.motor.getCurrentPosition() + 10));
            } else if (previous_direction < 0) {
                turret.motor.setTargetPosition( (int) (turret.motor.getCurrentPosition() - 10));
                telemetry.addData("TargetEC", turret.motor.getTargetPosition());
            }
        }
    }
}
