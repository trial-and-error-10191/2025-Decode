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

    // Motor parameters //
    // 1150 rpm - 145.1
    public float encoder_counts_per_rotation = 2786.2f;

    // rotational tracker //
    private float angle = 0.0f;
    private float prev_angle = angle;

    /**
     * constructor method for Turret assembly.
     * @param hwMap the hardware map for use in the motor
     * @param telemetry the telemetry object for write output
     */
    public Turret(HardwareMap hwMap, Telemetry telemetry) {
        motor = hwMap.get(DcMotor.class, "turret_motor");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.telemetry = telemetry;
        this.hwMap = hwMap;
    }


}
