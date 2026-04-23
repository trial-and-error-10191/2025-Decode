package org.firstinspires.ftc.teamcode.turretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {

    // objects for every assembly and or dependency //
    public DriveTrain driveTrain;
    public CameraDefinition camera;
    public Turret turret;

    // telemetry and hardware map //
    Telemetry telemetry;
    HardwareMap hwMap;

    // robot position //
    float x;
    float y;

    /**
     * constructor for robot class.
     * @param hwMap hardware map to distribute to all objects
     * @param telemetry telemetry to output with internal functions
     */
    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hwMap = hwMap;
        driveTrain = new DriveTrain(hwMap, telemetry);
        camera = new CameraDefinition(hwMap, telemetry);
        turret = new Turret(hwMap,telemetry);
    }



}
