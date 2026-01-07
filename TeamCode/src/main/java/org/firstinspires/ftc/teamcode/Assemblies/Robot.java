package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    boolean rotateDone = false;
    ElapsedTime ShootWaitTimer = new ElapsedTime();
    long start = System.nanoTime();
    public AprilTagDetection aprilTagDetection;
    public AprilTagFindCait aprilTagFind;
    public AprilTagProcessor aprilTag;
    public ArtifactPaddles artifactPaddles;
    public AutoBase autoBase;
    public BallDetect ballDetect;
    public BallRelease ballRelease;
    public CameraDefinition cameraDefinition;
    public DriveTrain driveTrain;
    public DriveByAprilTagGoal driveByAprilTagGoal;
    public IntakeThatDoesNotExist intake;
    public ObeliskOrder obeliskOrder;
    public RPMlaunchWheels wheels;
    public TagOrientation tagOrientation;
    public TelemetryUI UI;
    Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        aprilTagFind = new AprilTagFindCait(aprilTag, telemetry);
        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
        autoBase = new AutoBase(hwMap, telemetry);
        ballDetect = new BallDetect(hwMap);
        ballRelease = new BallRelease(hwMap, telemetry);
        cameraDefinition = new CameraDefinition(hwMap, telemetry);
        driveTrain = new DriveTrain(hwMap, telemetry);
        driveByAprilTagGoal = new DriveByAprilTagGoal(telemetry);
        intake = new IntakeThatDoesNotExist(hwMap);
        obeliskOrder = new ObeliskOrder(hwMap, aprilTag, telemetry);
        tagOrientation = new TagOrientation(hwMap);
        UI = new TelemetryUI(telemetry, this);
        wheels = new RPMlaunchWheels(hwMap, telemetry);
        order.add(Color.Green);
        order.add(Color.Purple);
        order.add(Color.Purple);

        this.telemetry = telemetry;
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

    public enum Distance {
        Short(3300),
        Long(3000);

        final int RPM;

        Distance(int RPM) {
            this.RPM = RPM;
        }
    }

    private Distance howFar;

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
        artifactPaddles.AutoRot(0, true, order);
        obeliskOrder.findTag(aprilTag);
        telemetry.addData("Obelisk Tag", obeliskOrder.desiredTagObelisk);
        // Makes the robot's ball holder set up to shoot the balls it contains in the order told by the obelisk.
        if (obeliskOrder.desiredTagObelisk == 22 && !rotateDone) {
            telemetry.addData("Running rotation for: ", obeliskOrder.desiredTagObelisk);
            long start = System.nanoTime();
            artifactPaddles.AutoRot(2, true, order);
            while (System.nanoTime() - start <= 1.16E9) { // used to be 1.16 secs
                // Waiting
            }
            rotateDone = true;
        } if (obeliskOrder.desiredTagObelisk == 23 && !rotateDone) {
            telemetry.addData("Running rotation for: ", obeliskOrder.desiredTagObelisk);
            long start = System.nanoTime();
            artifactPaddles.AutoRot(1, true, order);
            while (System.nanoTime() - start <= 1.16E9) {
                // Waiting
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

    public void swapMode(Distance newFar) {
        howFar = newFar;
        wheels.rpmReset(newFar.RPM);
    }
}