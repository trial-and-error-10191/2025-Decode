package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArtifactRamp {

    static double INCREMENT = 0.01;     // amount to slew servo each CYCLE_MS cycle
    static final double MAX_POS = 1.0;     // Maximum rotational position
    static final double MIN_POS = 0.0;     // Minimum rotational position
    double rotatePosition = 0.5;                  // Start at halfway position

    // Define class members
    public static Servo RampServo;

    public ArtifactRamp(Telemetry telemetry, HardwareMap hwMap) {
        RampServo = hwMap.get(Servo.class, "RampServo");
    }
    public void rampServo (boolean up, boolean down) {

    if (up) {                                     // lifts ramp up
        rotatePosition += INCREMENT;
        if (rotatePosition >= MAX_POS) {
            rotatePosition = MAX_POS;
        }
    } else if (down) {                               // lowers ramp down
        rotatePosition -= INCREMENT;
        if (rotatePosition <= MIN_POS) {
            rotatePosition = MIN_POS;
        }

    }
    RampServo.setPosition(rotatePosition);
}

}
// end of code