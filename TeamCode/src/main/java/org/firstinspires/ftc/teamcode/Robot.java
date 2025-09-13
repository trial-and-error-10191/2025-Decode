package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    public EscarGOMech escarGOMech;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        escarGOMech = new EscarGOMech(hwMap, telemetry);
    }
}
