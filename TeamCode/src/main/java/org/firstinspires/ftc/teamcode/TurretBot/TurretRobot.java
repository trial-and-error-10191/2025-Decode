package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TurretRobot {
    public DriveTrainTurretBot driveTrain;
    public TurretAim turretAim;
    public TurretRobot (HardwareMap hwMap, Telemetry telemetry) {
        driveTrain = new DriveTrainTurretBot(hwMap, telemetry);
        turretAim = new TurretAim(hwMap);
    }
}
