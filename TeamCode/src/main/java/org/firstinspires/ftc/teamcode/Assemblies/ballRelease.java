package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ballRelease {
    Servo servo;

    ballRelease(Telemetry telemetry, HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "dropServo");
    }

    // function to drop the ball
    public void Open() {
        servo.setPosition(0);
    }

    // function to close the hole for next ball
    public void Close() {
        servo.setPosition(1);
    }
}
