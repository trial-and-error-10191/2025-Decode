package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

public class AutoBase {
    AprilTagDetection desiredTagGoal = null;
    private final ElapsedTime Time = new ElapsedTime();
    public double power = 0.5;
    long start = System.nanoTime();
    public AutoBase(HardwareMap hwMap, Telemetry telemetry) {
    }
    public void Shoot(Robot robot) {
        start = System.nanoTime();
        while (System.nanoTime() - start < 5E9) {
            robot.wheels.wheelsTick();
            while (System.nanoTime() - start > 3E9) {
                robot.wheels.wheelsTick();
                if (System.nanoTime() - start >= 5E9) {
                    robot.ShootAll(true);
                    break;
                }
            }
        }
    }
    public void PatternMatch(Robot robot) {
        start = System.nanoTime();
        while (System.nanoTime() - start <= 5E9) {
            robot.patternMatchAuto();
        }
    }
    public void GoalSet(DriveTrain driveTrain, boolean blue) {
        if (blue) {
            driveTrain.DESIRED_TAG_ID = 20;
        } else {
            driveTrain.DESIRED_TAG_ID = 24;
        }
    }
    public void DrivePrecision(Robot robot, double desireSpot, AprilTagDetection tagDetection) {
        List<AprilTagDetection> currentDetections = robot.cameraDefinition.aprilTag.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            desiredTagGoal = detection;
            break;
        }
        while (true) {
            robot.driveTrain.DriveByAprilTag(desireSpot, robot.cameraDefinition.aprilTag);
            if (desireSpot == tagDetection.ftcPose.range) {
                break;
            }
        }
    }
    public void TurnPrecision() {
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
