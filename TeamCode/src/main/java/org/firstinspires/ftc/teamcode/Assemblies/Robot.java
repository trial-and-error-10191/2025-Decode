package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class Robot {
    long start = System.nanoTime();
    long finish = System.nanoTime();
    long timeElapsed = finish - start;
    public ArtifactPaddles artifactPaddles;
//    public AutoBase autoBase;
//    public BadFishLaunch badFishLaunch;
    public BallDetect ballDetect;
    public BallRelease ballRelease;
//    public DriveTrain driveTrain;
//    public LEDLight displayLED;
    public ObeliskOrder obeliskOrder;
    public TelemetryUI UI;
//    public RPMlaunchWheels wheels;
    Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
//        autoBase = new AutoBase();
//        badFishLaunch = new BadFishLaunch(hwMap);
        ballDetect = new BallDetect(hwMap);
        ballRelease = new BallRelease(hwMap, telemetry);
//        driveTrain = new DriveTrain(hwMap, telemetry);
//        displayLED = new LEDLight();
        obeliskOrder = new ObeliskOrder(hwMap);
        UI = new TelemetryUI(telemetry, this);
//        wheels = new RPMlaunchWheels(telemetry, hwMap);
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

    // Array to store artifact color and spot
    ArrayList<Color> order = new ArrayList<>();

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

//    public void colorCheck(int desireBall) {
//        int color = ballDetect.colorFind(true);
//        if (color == desireBall) { // Lets intake suck in balls of the correct color
//            badFishLaunch.bandIntake.setPower(1);
//        } else { // Prevents accidental intake of incorrect balls
//            badFishLaunch.bandIntake.setPower(-0.1);
//        }
//        if (color == 1) {
//            displayLED.LEDLight.setPosition(0.5); // turns the light green
//        } if (color == 2) {
//            displayLED.LEDLight.setPosition(0.722); // turns the light purple
//        } else {
//            displayLED.LEDLight.setPosition(0); // turns the light off
//        }
//    }
    public void ShootAll(boolean sendAll) {
        if (sendAll) {
            telemetry.addData("All Artifacts Launching" , "");
            telemetry.update();
            float wait = 0.5f;
            ElapsedTime timer = new ElapsedTime();
            ballRelease.Open();
            timer.reset();
            // Need to move the paddle twice
            for (int i = 0; i < 2; i++) {
                // Just casually waiting for time to pass
                while (timer.seconds() <= wait) {}
                artifactPaddles.AutoRot(1, true, order);
                timer.reset();
                telemetry.addData("Iteration Number", "%d", i);
                telemetry.update();
            }
            // Want to ensure the ball fully falls through before closing the release paddle
            while (timer.seconds() <= wait) {}
            ballRelease.Close();
//            timeElapsed = System.nanoTime() - start;
//            ballRelease.Open();
//            start = System.nanoTime();
//            while (timeElapsed == 3E8) {
//                artifactPaddles.AutoRot(1, true, order);
//                timeElapsed = System.nanoTime() - start;
//                start = System.nanoTime();
//                while (timeElapsed == 6E8) {
//                    artifactPaddles.AutoRot(1, true, order);
//                    ballRelease.Close();
//                    finish = System.nanoTime();
//                }
//            }
            telemetry.addData("All Shot", "");
            telemetry.update();
        }
    }
    public void patternMatchAuto() {
        // Makes the robot's ball holder set up to shoot the balls it contains in the order told by the obelisk.
        if (obeliskOrder.desiredTag == 22) {
            artifactPaddles.AutoRot(1, true, order);
        } if (obeliskOrder.desiredTag == 23) {
            artifactPaddles.AutoRot(2, true, order);
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
}