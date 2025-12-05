package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeThatDoesNotExist {
    CRServo Intake;
    public IntakeThatDoesNotExist (HardwareMap hwMap) {
//        Intake = hwMap.get(CRServo.class, "Intake");
    }
    public void IntakeSpin(boolean Spin) {
        if (Spin) {
            Intake.setPower(1);
        }
    }
}
