package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;

public class Robot {
    public AprilTags aprilTags;
    public AutoBase autoBase;
//    public BadFishLaunch badFishLaunch;
    public BallDetect ballDetect;
    public LEDLight displayLED;
    public DriveTrain driveTrain;


    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        aprilTags = new AprilTags();
        autoBase = new AutoBase();
//        badFishLaunch = new BadFishLaunch(hwMap);
        ballDetect = new BallDetect();
        displayLED = new LEDLight();
        driveTrain = new DriveTrain(hwMap, telemetry);
    }
}