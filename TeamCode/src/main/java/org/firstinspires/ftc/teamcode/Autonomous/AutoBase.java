package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Assemblies.RPMlaunchWheels;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

public class AutoBase {
    private final ElapsedTime Time = new ElapsedTime();

    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
