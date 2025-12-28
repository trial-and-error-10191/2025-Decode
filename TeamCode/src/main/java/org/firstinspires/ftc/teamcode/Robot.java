package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    private Telemetry telemetry;
    private HardwareMap hwMap;
    MecanumDriveTrain driveTrain;
    private RobotState state = RobotState.DRIVING;
    private final double TRIGGER_BUTTON_DEADZONE = 0.05;
    private enum RobotState {
        DRIVING,
        SHOOTING,
        PARKING
    }

    public Robot(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        this.hwMap = hwMap;
        driveTrain = new MecanumDriveTrain(telemetry, hwMap);
    }

    public void UpdateState(Gamepad gp1, Gamepad gp2) {
        // Deadzone for right trigger button
        if (gp1.left_trigger > TRIGGER_BUTTON_DEADZONE) {
            state = RobotState.PARKING;
        } else if (gp1.right_trigger > TRIGGER_BUTTON_DEADZONE) {
            state = RobotState.SHOOTING;
        } else {
            state = RobotState.DRIVING;
        }

        switch(state) {
            case DRIVING:
                driveTrain.SetSensitivity(1.0);
                break;
            case PARKING:
                driveTrain.SetSensitivity(0.4);
                break;
            case SHOOTING:
                break;
            default:
                telemetry.addData("ERROR", "Unknown State");
        }
    }

    public void OperateRobot(Gamepad gp1, Gamepad gp2) {
        switch(state) {
            case DRIVING:
                driveTrain.DriveByPower(gp1, gp2);
                break;
            case PARKING:
                driveTrain.DriveByPower(gp1, gp2);
                break;
            case SHOOTING:
                break;
            default:
                telemetry.addData("ERROR", "Unknown State");
        }
    }
}
