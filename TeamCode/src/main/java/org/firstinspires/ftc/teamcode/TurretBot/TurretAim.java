package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretAim {
    Servo servo;
    double xCoordinate = 0;
    double yCoordinate = 0;

    public TurretAim(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "TurretServo");
    }

    public void TurretServoAim(CameraFindDistanceAndBearing Find) {
        xCoordinate = Math.sin(Math.toRadians(Find.bearing)) * Find.distance + 5.125;
        yCoordinate = Math.cos(Math.toRadians(Find.bearing)) * Find.distance - 7.5;
        // Translating the bearing found from the camera "coordinate plane" to the servo "coordinate plane"
        double bearingToTurret = Math.atan(xCoordinate/yCoordinate);
        // The equation below gets the finalTurretTarget's value by using the slope intercept form (y = mx + b)
        double finalTurretTarget = 0.0039215686274509803921568627451 * -bearingToTurret + 0.5;
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
