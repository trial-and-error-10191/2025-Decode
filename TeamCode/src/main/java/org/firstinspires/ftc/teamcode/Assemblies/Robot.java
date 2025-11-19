package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Robot {
    public ArtifactPaddles artifactPaddles;
    public AutoBase autoBase;
    public BadFishLaunch badFishLaunch;
    public BallDetect ballDetect;
    public DriveTrain driveTrain;
    public LEDLight displayLED;
    public ObeliskOrder obeliskOrder;
    public TelemetryUI UI;
    public RPMlaunchWheels wheels;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
        UI = new TelemetryUI(telemetry, this);
        wheels = new RPMlaunchWheels(telemetry, hwMap);
        autoBase = new AutoBase();
        badFishLaunch = new BadFishLaunch(hwMap);
        ballDetect = new BallDetect(hwMap);
        driveTrain = new DriveTrain(hwMap, telemetry);
        displayLED = new LEDLight();
        obeliskOrder = new ObeliskOrder(hwMap);
    }

    public void intakeArtifact() {
        if (badFishLaunch.bandIntake.getPower() == 0) {
            artifactPaddles.AutoRot(1, true);
            autoBase.Wait(90);
        }
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

    public void colorCheck(int desireBall) {
        int color = ballDetect.colorFind(true);
        if (color == desireBall) { // Lets intake suck in balls of the correct color
            badFishLaunch.bandIntake.setPower(1);
        } else { // Prevents accidental intake of incorrect balls
            badFishLaunch.bandIntake.setPower(-0.1);
        }
        if (color == 1) {
            displayLED.LEDLight.setPosition(0.5); // turns the light green
        } if (color == 2) {
            displayLED.LEDLight.setPosition(0.722); // turns the light purple
        } else {
            displayLED.LEDLight.setPosition(0); // turns the light off
        }
    }
}