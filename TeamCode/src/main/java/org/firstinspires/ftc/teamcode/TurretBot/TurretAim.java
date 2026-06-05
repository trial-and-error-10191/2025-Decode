package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretAim {
    CRServo servo;
    double xCoordinate = 0;
    double yCoordinate = 0;
    double bearingToTurret = 0;
    double lastBearingPos = 0;
    double crServoPower = 1;
    long start = System.nanoTime();

    public TurretAim(HardwareMap hwMap) {
        servo = hwMap.get(CRServo.class, "TurretServo");
    }

    public void TurretServoAim(CameraFindDistanceAndBearing Find) {
//        xCoordinate = Math.sin(Math.toRadians(Find.bearing)) * Find.distance + 5.125;
//        yCoordinate = Math.cos(Math.toRadians(Find.bearing)) * Find.distance - 7.5;
//        // Translating the bearing found from the camera "coordinate plane" to the servo "coordinate plane"
//        bearingToTurret = Math.toDegrees(Math.atan(xCoordinate/yCoordinate));
//        // The equation below gets the finalTurretTarget's value by using the slope intercept form (y = mx + b)
//        double finalTurretTarget = 0.0039215686274509803921568627451 * bearingToTurret + 0.5;
//        servo.setPosition(finalTurretTarget);
    }

    public void TurretServoAimSimple(CameraFindDistanceAndBearing Find) {
//        double finalTurretTarget = 0.0039215686274509803921568627451 * -Find.bearing + 0.5;
//        servo.setPosition(finalTurretTarget);
    }

    public void findLastBearingPos(CameraFindDistanceAndBearing Find) {
        lastBearingPos = Find.bearing;
    }

    public void TurnWithCRServo(CameraFindDistanceAndBearing Find) {
        boolean bearingPositive;
        start = System.nanoTime();
        while (lastBearingPos != Find.bearing) {
            if (Find.bearing - lastBearingPos >= 0) {
                bearingPositive = true;
            } else {
                bearingPositive = false;
            }
            servo.setPower(bearingPositive ? -crServoPower : crServoPower);
            if (Math.abs(lastBearingPos - Find.bearing) <= 0.5) {
                servo.setPower(0);
                findLastBearingPos(Find);
                break;
            }
        }
    }
    public void SpinServo(boolean spinLeft, boolean spinRight) {
        if (spinLeft) {
            servo.setPower(crServoPower);
        } else if (spinRight) {
            servo.setPower(-crServoPower);
        }
    }
}