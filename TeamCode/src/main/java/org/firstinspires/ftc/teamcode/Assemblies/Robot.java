package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    boolean rotateDone = false;
    ElapsedTime ShootWaitTimer = new ElapsedTime();
    long start = System.nanoTime();
    public AprilTagFindCait aprilTagFind;
    public AprilTagProcessor aprilTag;
    public ArtifactPaddles artifactPaddles;
    public AutoBase autoBase;
    public BallDetect ballDetect;
    public BallRelease ballRelease;
    public DriveTrain driveTrain;
    public DriveByAprilTagGoal driveByAprilTagGoal;
    public IntakeThatDoesNotExist intake;
    public ObeliskOrder obeliskOrder;
    public RPMlaunchWheels wheels;
    public TagOrientation tagOrientation;
    public TelemetryUI UI;
    public VisionPortal visionPortal;
    Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        aprilTagFind = new AprilTagFindCait(aprilTag, telemetry);
        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
        autoBase = new AutoBase(hwMap, telemetry);
        ballDetect = new BallDetect(hwMap);
        ballRelease = new BallRelease(hwMap, telemetry);
        driveTrain = new DriveTrain(hwMap, telemetry);
        driveByAprilTagGoal = new DriveByAprilTagGoal(telemetry);
        intake = new IntakeThatDoesNotExist(hwMap);
        obeliskOrder = new ObeliskOrder(aprilTag, telemetry);
        tagOrientation = new TagOrientation(hwMap);
        UI = new TelemetryUI(telemetry, this);
        wheels = new RPMlaunchWheels(hwMap, telemetry);
        order.add(Color.Green);
        order.add(Color.Purple);
        order.add(Color.Purple);
        this.telemetry = telemetry;

        /// This first part sets up the camera so it can scan AprilTags
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                .build();

        // Lets the camera see the obelisk April Tag from far away, as we only need to see that one once.
        aprilTag.setDecimation(1);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(hwMap.get(WebcamName.class, "Webcam 1"));

//        // Set and enable the processor.
        builder.addProcessor(aprilTag);
//
//        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();
        // Wait for the driver to press Start
        telemetry.addData("Camera preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to start OpMode");
        telemetry.update();
    }

//    public void intakeArtifact() {
//        if (badFishLaunch.bandIntake.getPower() == 0) {
//            artifactPaddles.AutoRot(1, true, order);
//            autoBase.Wait(90);
//        }
//    }

    public enum Color {
        Purple("Purple"),
        Green("Green"),
        Nothing("Empty");

        final String stringOf;

        Color(String stringOf) {
            this.stringOf = stringOf;
        }
    }

    // Array to store artifact color and spot
    public ArrayList<Color> order = new ArrayList<>();

    public ArrayList<Color> Cycle(ArrayList<Color> cycleTarget, boolean forward) {
        ArrayList<Color> cycleTemp = new ArrayList<>(); // store temporary new values

        if (forward) {
            cycleTemp.add(cycleTarget.get(cycleTarget.size() - 1));
            for (int i = 0; i < cycleTarget.size() - 1; i++) { // only iterate if forward is true
                cycleTemp.add(cycleTarget.get(i - 1));
            }
        } else {
            for (int i = 1; i < cycleTarget.size() - 1; i++) { // only iterate if forward is false
                cycleTemp.add(cycleTarget.get(i));
            }
            cycleTemp.add(cycleTarget.get(0));
        }

        cycleTarget = cycleTemp;
        return cycleTarget;
    }
    public void ShootAll(boolean sendAll) {
        if (sendAll) {
            telemetry.addData("All Artifacts Launching" , "");
            telemetry.update();
            float wait = 1f;
//            ballRelease.Open();
            ShootWaitTimer.reset();
            // Need to move the paddle twice
            for (int i = 0; i < 2; i++) {
                // Just casually waiting for time to pass
                while (ShootWaitTimer.seconds() <= wait) {ballRelease.Open();}
                artifactPaddles.AutoRot(1, true, order);
                ShootWaitTimer.reset();
                telemetry.addData("Iteration Number", "%d", i);
                telemetry.update();
            }
            // Want to ensure the ball fully falls through before closing the release paddle
            while (ShootWaitTimer.seconds() <= wait) {}
            ballRelease.Close();
            telemetry.addData("All Shot", "");
            telemetry.update();
        }
    }
    public void ShootOnce(float shoot) {
        if (shoot > 0) {
            ballRelease.Open();
            start = System.nanoTime();
            while (System.nanoTime() - start <= 2E9) {
                // Wait
            }
            ballRelease.Close();
            artifactPaddles.AutoRot(1, true, order);
        }
    }
    public void patternMatchAuto() {
        obeliskOrder.findTag(aprilTag);
        telemetry.addData("Obelisk Tag", obeliskOrder.desiredTagObelisk);
        // Makes the robot's ball holder set up to shoot the balls it contains in the order told by the obelisk.
        if (obeliskOrder.desiredTagObelisk == 22 && !rotateDone) {
            telemetry.addData("Running rotation for: ", obeliskOrder.desiredTagObelisk);
            long start = System.nanoTime();
            while (System.nanoTime() - start <= 1.16E9) {
                artifactPaddles.AutoRot(1, false, order);
            }
            rotateDone = true;
        } if (obeliskOrder.desiredTagObelisk == 23 && !rotateDone) {
            telemetry.addData("Running rotation for: ", obeliskOrder.desiredTagObelisk);
            long start = System.nanoTime();
            while (System.nanoTime() - start <= 1.16E9) {
                artifactPaddles.AutoRot(1, true, order);
            }
            rotateDone = true;
        }
    }
    public void patternCorrectionTeleOp (boolean patternCorrection) {
        // Makes the robot's ball order in the ball holder move over by one in case we need it.
        if (patternCorrection) {
            artifactPaddles.AutoRot(1, true, order);
            telemetry.addData("Order set", order);
            telemetry.update();
        }
    }
    public void GoalMove(boolean blue, AprilTagProcessor tagProcessor) {
        tagOrientation.findGoalTag(blue);
        List<AprilTagDetection> currentDetections = tagProcessor.getDetections();
        for (AprilTagDetection detection : currentDetections) {
            if (tagOrientation.desiredTagOrient == detection.id && detection.id == 20) {
                driveTrain.moveRobot(driveByAprilTagGoal.drive, driveByAprilTagGoal.turn);
            } if (tagOrientation.desiredTagOrient == detection.id && detection.id == 24) {
                driveTrain.moveRobot(driveByAprilTagGoal.drive, driveByAprilTagGoal.turn);
            }
        }
    }
}