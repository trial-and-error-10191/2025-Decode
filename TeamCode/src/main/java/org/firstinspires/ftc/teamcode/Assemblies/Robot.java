package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
   // public EscarGOMech escarGOMech;
    public DriveTrain driveTrain;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        //escarGOMech = new EscarGOMech(hwMap, telemetry);
        driveTrain = new DriveTrain(hwMap, telemetry);
    }
}
