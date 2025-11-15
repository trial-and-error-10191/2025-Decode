package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Robot {
    public ArtifactPaddles artifactPaddles;
    public AutoBase autoBase;
    public BadFishLaunch badFishLaunch;
    public BallDetect ballDetect;
    public LEDLight displayLED;
    public DriveTrain driveTrain;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
        autoBase = new AutoBase();
        badFishLaunch = new BadFishLaunch(hwMap);
        ballDetect = new BallDetect(hwMap);
        displayLED = new LEDLight();
        driveTrain = new DriveTrain(hwMap, telemetry);
    }

    public void intakeArtifact() {
        if (badFishLaunch.bandIntake.getPower() == 0) {
            artifactPaddles.AutoRot(1, true);
            autoBase.Wait(90);
        }
    }

    enum Color {
        Purple(),
        Green(),
        Nothing;
    }

    // Array to store artifact color and spot
    ArrayList<Color> order = new ArrayList<>();

    public ArrayList<Color> obeliskOrder(int desiredTag) { // Used to contain pattern info from the obelisk
        ArrayList<Color> patternOrder = new ArrayList<>();
        if (desiredTag == 21) { // Setting up order for the balls
           patternOrder.add(Color.Green);
           patternOrder.add(Color.Purple);
           patternOrder.add(Color.Purple);
        } if (desiredTag == 22) { // Setting up order for the balls
            patternOrder.add(Color.Purple);
            patternOrder.add(Color.Green);
            patternOrder.add(Color.Purple);
        } if (desiredTag == 23) { // Setting up order for the balls
            patternOrder.add(Color.Purple);
            patternOrder.add(Color.Purple);
            patternOrder.add(Color.Green);
        }
        return patternOrder; // Returning the order of the pattern
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