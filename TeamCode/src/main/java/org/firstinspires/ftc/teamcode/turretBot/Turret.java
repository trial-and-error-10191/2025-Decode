package org.firstinspires.ftc.teamcode.turretBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Turret {

    // required hardware map + telemetry //
    HardwareMap hwMap;
    Telemetry telemetry;

    // Motor object //
    public DcMotor motor;

    /**
     * constructor method for Turret assembly.
     * @param hwMap the hardware map for use in the motor
     * @param telemetry the telemetry object for write output
     */
    public Turret(HardwareMap hwMap, Telemetry telemetry) {
        motor = hwMap.get(DcMotor.class, "turret_motor");
        this.telemetry = telemetry;
        this.hwMap = hwMap;
    }


}
