package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallRelease {

    // define the servo
    Servo servo;

    // define supportive vars
    boolean previousInput = false;
    boolean open = false;

    public BallRelease(HardwareMap hwMap, Telemetry telemetry) {
        servo = hwMap.get(Servo.class, "dropServo");
    }

    // function to drop the ball
    public void Open() {
        servo.setPosition(0.5);
        open = true;
    }

    // function to close the hole for next ball
    public void Close() {
        servo.setPosition(0);
        open = false;
    }

    public void releaseLogic(boolean release) {
        if (!previousInput && release) {
            if (open) {
                Close();
            } else {
                Open();
            }
            previousInput = true;
        }

        if (!release) {
            previousInput = false;
        }
    }
}
