package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretAim {
    Servo servo;

    public TurretAim(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, "TurretServo");
    }

    double finalTurretTarget;

    public void TurretServoAim() {
        servo.setPosition(finalTurretTarget);
    }
}
