package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TurretRobot {
    public CameraDefinitionTurretCam cameraDefinition;
    public CameraFindDistanceAndBearing camFindDistAndBearing;
    public DriveTrainTurretBot driveTrain;
    public TurretAim turretAim;
    public TurretRobot (HardwareMap hwMap, Telemetry telemetry) {
        cameraDefinition = new CameraDefinitionTurretCam(hwMap);
        camFindDistAndBearing = new CameraFindDistanceAndBearing(hwMap, cameraDefinition.aprilTag, telemetry);
        driveTrain = new DriveTrainTurretBot(hwMap, telemetry);
        turretAim = new TurretAim(hwMap);
    }
}
