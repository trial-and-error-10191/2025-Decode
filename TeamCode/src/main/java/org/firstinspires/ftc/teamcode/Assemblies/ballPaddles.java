package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ballPaddles {
    CRServo paddles;

    ballPaddles(Telemetry telemetry, HardwareMap hwMap) {
        paddles = hwMap.get(CRServo.class, "paddles");
    }


}
