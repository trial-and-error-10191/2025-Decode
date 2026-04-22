package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretAim {
    Servo servo;

    public TurretAim(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "TurretServo");
    }

    public void TurretServoAim(CameraFindDistanceAndBearing bearing) {
        // The equation below gets the finalTurretTarget's value by using the slope intercept form (y = mx + b)
        double finalTurretTarget = 0.0039215686274509803921568627451 * -bearing.bearing + 0.5;
        servo.setPosition(finalTurretTarget);
    }

    public void ServoSet0(boolean trigger) {
        if (trigger) {
            servo.setPosition(0);
        }
    }

    public void ServoSet1(boolean trigger) {
        if (trigger) {
            servo.setPosition(1);
        }
    }
}
