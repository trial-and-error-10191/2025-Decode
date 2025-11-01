package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

public class Robot {
//    public ArtifactPaddles artifactPaddles;
//    public AutoBase autoBase;
//    public BadFishLaunch badFishLaunch;
//    public BallDetect ballDetect;
    public HuskyLensSense huskyLensSense;
//    public LEDLight displayLED;
//    public DriveTrain driveTrain;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
//        artifactPaddles = new ArtifactPaddles(hwMap, telemetry);
//        autoBase = new AutoBase();
//        badFishLaunch = new BadFishLaunch(hwMap);
//        ballDetect = new BallDetect();
        huskyLensSense = new HuskyLensSense();
//        displayLED = new LEDLight();
//        driveTrain = new DriveTrain(hwMap, telemetry);
    }

//    public void intakeArtifact() {
//        if (badFishLaunch.bandIntake.getPower() == 0) {
//            artifactPaddles.AutoRot(1, true);
//            autoBase.Wait(90);
//        }
//    }
//    public void colorCheck(PredominantColorProcessor.Swatch desireBall) {
//        PredominantColorProcessor.Result result = ballDetect.colorSensor.getAnalysis();
//        if (result.closestSwatch == desireBall) { // Lets intake suck in balls of the correct color
//            badFishLaunch.bandIntake.setPower(1);
//        } else { // Prevents accidental intake of incorrect balls
//            badFishLaunch.bandIntake.setPower(-0.1);
//        }
//        if (result.closestSwatch == PredominantColorProcessor.Swatch.GREEN) {
//            displayLED.LEDLight.setPosition(0.5); // turns the light green
//        } if (result.closestSwatch == PredominantColorProcessor.Swatch.PURPLE) {
//            displayLED.LEDLight.setPosition(0.722); // turns the light purple
//        } else {
//            displayLED.LEDLight.setPosition(0); // turns the light off
//        }
//    }
}