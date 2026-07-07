package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    boolean rotateDone = false;

    ElapsedTime ShootWaitTimer = new ElapsedTime();
    ElapsedTime runTime = new ElapsedTime();

    long start = System.nanoTime();
    public AprilTagProcessor aprilTag;
    public AutoBase autoBase;
    public BallDetect ballDetect;
    public CameraDefinition camDef;
    public CameraFindDistanceAndBearing camFindDistAndBearing;
    public DriveByAprilTagGoal driveByAprilTagGoal;
    public DriveTrain driveTrain;
    public DriveTrainMecanum driveTrainMecanum;
    public IntakeThatDoesNotExist intake;
    public ObeliskOrder obeliskOrder;
    public RPMlaunchWheels wheels;
    public TagOrientation tagOrientation;
    public TelemetryUI UI;

    Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        autoBase = new AutoBase(telemetry);
        ballDetect = new BallDetect(hwMap);
        camDef = new CameraDefinition(hwMap, telemetry);
        camFindDistAndBearing = new CameraFindDistanceAndBearing(hwMap, camDef.aprilTag, telemetry);
        driveByAprilTagGoal = new DriveByAprilTagGoal(telemetry);
        driveTrain = new DriveTrain(hwMap, telemetry);
        driveTrainMecanum = new DriveTrainMecanum(hwMap, telemetry);
        intake = new IntakeThatDoesNotExist(hwMap);
        obeliskOrder = new ObeliskOrder(hwMap, camDef.aprilTag, telemetry);
        tagOrientation = new TagOrientation(hwMap);
        UI = new TelemetryUI(telemetry, this);

        order.add(Color.Green);
        order.add(Color.Purple);
        order.add(Color.Purple);

        ShootWaitTimer.reset();
        runTime.reset();

        this.telemetry = telemetry;
    }

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
        Short(2950),
        Long(3300),
        None(0);

        public final int RPM;

        Distance(int RPM) {
            this.RPM = RPM;
        }
    }

    public enum tags {
        redTeamGoal(24),
        blueTeamGoal(20);

        final int id;

        tags(int id) {
           this.id = id;
        }

    }

    private Distance mapPosistion;

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

    int negXRestriction = 35;
    int posXRestriction = 35;

    public boolean alignRobot() {

        boolean aligned = false;

        ArrayList<AprilTagDetection>  detections = camDef.aprilTag.getDetections();

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

            // speed is based on distance from center line. approaching a multiplier of 0 at it approached
            speed = Math.max(speed * (Math.abs(screenCenterLineCord - tagCenter.x) / screenCenterLineCord), 0.10);


            if (tagCenter.x > screenCenterLineCord + posXRestriction) {
                driveTrain.moveRobot(0, speed);
            } else if (tagCenter.x < screenCenterLineCord - negXRestriction) {
                driveTrain.moveRobot(0, -speed);
            } else {
                aligned = true;
                driveTrain.moveRobot(0,0);
            }
        } else {
            driveTrain.moveRobot(0,0);
        }

        return aligned;
    }
}