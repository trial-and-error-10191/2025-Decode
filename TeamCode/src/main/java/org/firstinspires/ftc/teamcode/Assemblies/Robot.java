package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AprilTagDetect;
import org.firstinspires.ftc.teamcode.Autonomous.BallDetect;

public class Robot {
//    public AprilTagDetect aprilTagDetect;
//    public BallDetect ballDetect;
    public DisplayLED displayLED;
//    public DriveTrain driveTrain;
//    public EscarGOMech escarGOMech;


    public Robot(HardwareMap hwMap, Telemetry telemetry) {
//        aprilTagDetect = new AprilTagDetect();
//        ballDetect = new BallDetect();
        displayLED = new DisplayLED(hwMap, telemetry);
//        driveTrain = new DriveTrain(hwMap, telemetry);
//        escarGOMech = new EscarGOMech(hwMap, telemetry);
    }
}
