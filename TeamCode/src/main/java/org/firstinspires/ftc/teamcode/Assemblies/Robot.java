package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public AprilTags aprilTagDetect;
    public BallDetect ballDetect;
    public LEDLight displayLED;
    public DriveTrain driveTrain;


    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        aprilTagDetect = new AprilTags();
        ballDetect = new BallDetect();
        displayLED = new LEDLight();
        driveTrain = new DriveTrain(hwMap, telemetry);
    }
}