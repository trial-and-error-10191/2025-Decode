package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AutoBase {
    AprilTagDetection desiredTagGoal = null;
    Telemetry telemetry;
    List<AprilTagDetection> currentDetections = null;
    private final ElapsedTime Time = new ElapsedTime();
    int killSwitch = 0;
    public double power = 0.5;
    long start = System.nanoTime();
    public AutoBase(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    public void Shoot(Robot robot) {
        start = System.nanoTime();
        while (System.nanoTime() - start < 5E9) {
            robot.wheels.wheelsTick();
            while (System.nanoTime() - start > 3E9) {
                robot.wheels.wheelsTick();
                telemetry.addData("RPM Target", robot.wheels.rpmTarget);
                telemetry.addData("Current RPM Left", robot.wheels.left.getPower());
                telemetry.addData("Current RPM Right", robot.wheels.right.getPower());
                telemetry.update();
                if (System.nanoTime() - start >= 5E9) {
                    robot.ShootAll(true);
                    break;
                }
            }
        }
    }
    public void PatternMatch(Robot robot) {
        start = System.nanoTime();
        while (System.nanoTime() - start <= 1E9) {
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
    public void AprilTagAmount(Robot robot, int id) {
        start = System.nanoTime();
        while (System.nanoTime() - start <= 3E9) {
            currentDetections = robot.cameraDefinition.aprilTag.getDetections();
            if (currentDetections.contains(id)) { // Makes the robot leave the loop if it detects the april tags early
                break;
            }
            telemetry.addData("AprilTag Seen", currentDetections.size());
            telemetry.update();
        }
    }
    public void SetToEncoders(DriveTrain driveTrain) {
        driveTrain.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveTrain.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        driveTrain.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveTrain.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        driveTrain.targetPositionDrive = driveTrain.leftFrontDrive.getCurrentPosition();
        driveTrain.leftFrontDrive.setTargetPosition(driveTrain.targetPositionDrive);
        driveTrain.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void SetToPower(DriveTrain driveTrain) {
        driveTrain.leftFrontDrive.setPower(0);
        driveTrain.rightFrontDrive.setPower(0);
        driveTrain.leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        driveTrain.rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        driveTrain.leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveTrain.rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void DrivePrecision(Robot robot, double desireSpot, int id) {
        killSwitch = 0;
        desiredTagGoal = null;
        currentDetections = robot.cameraDefinition.aprilTag.getDetections();
        while (true) {
            if (currentDetections.isEmpty()) { // Makes sure the robot doesn't crash if it doesn't see an april tag
                break;
            }
            robot.driveTrain.DriveByAprilTag(desireSpot, robot.cameraDefinition.aprilTag);
            currentDetections = robot.cameraDefinition.aprilTag.getDetections();
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == id) {
                    desiredTagGoal = detection;
                    killSwitch = 0;
                    break;
                } else {
                    killSwitch = 1;
                }
            }
            if (killSwitch == 1) {
                robot.driveTrain.stopMotors();
                break;
            }
            start = System.nanoTime();
            if (Math.abs(desiredTagGoal.ftcPose.range - desireSpot) <= 5 || System.nanoTime() - start <= 3E9) {
                telemetry.addData(".range not null!", "");
                robot.driveTrain.stopMotors();
                break;
            }
        }
    }
    public void TurnPrecision(Robot robot, double desireTurn, int id) {
        killSwitch = 0;
        desiredTagGoal = null;
        currentDetections = robot.cameraDefinition.aprilTag.getDetections();
        while (true) {
            if (currentDetections.isEmpty()) { // Makes sure the robot doesn't crash if it doesn't see an april tag
                break;
            }
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == id) {
                    desiredTagGoal = detection;
                    killSwitch = 0;
                    break;
                } else {
                    killSwitch = 1;
                }
            }
            if (killSwitch == 1) {
                robot.driveTrain.stopMotors();
                break;
            }
            robot.driveTrain.TurnToAprilTag(desireTurn, robot.cameraDefinition.aprilTag);
            currentDetections = robot.cameraDefinition.aprilTag.getDetections();
            telemetry.addData("Bearing", desiredTagGoal.ftcPose.bearing);
            telemetry.update();
//            Wait(20);
            start = System.nanoTime();
            if (Math.abs(desiredTagGoal.ftcPose.bearing - desireTurn) <= 3 || System.nanoTime() - start <= 3E9) {
                robot.driveTrain.stopMotors();
                break;
            }
        }
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
