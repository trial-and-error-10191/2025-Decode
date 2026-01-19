package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class BallRelease {

    // define the servo
    public Servo servo;

    // define supportive vars
    boolean previousInput = false;
    boolean open = false;
    boolean lastInput = false;
    boolean PaddleMove = false;

    public BallRelease(HardwareMap hwMap, Telemetry telemetry) {
        servo = hwMap.get(Servo.class, "dropServo");
    }

    public void DropBall(float drop) {
        if (!lastInput && drop > 0) {
            PaddleMove = !PaddleMove;
            if (PaddleMove) {
                Open();
            } else {
                Close();
            }
        }
        lastInput = drop > 0;
    }

    // function to drop the ball
    public void Open() {
        servo.setPosition(0.6);
        open = true;
    }

    // function to close the hole for next ball
    public void Close() {
        servo.setPosition(0.3);
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
