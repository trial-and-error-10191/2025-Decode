package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public interface DriveTrain {
    void DriveByPower(Gamepad gp1, Gamepad gp2);

    void DriveByEncoder(Gamepad gp1, Gamepad gp2);

    void DriveFieldOriented(Gamepad gp1, Gamepad gp2);
}
