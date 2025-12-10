package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoBase {
    private final ElapsedTime Time = new ElapsedTime();
    static final double TURN_SPEED = 0.7;
    public AutoBase(HardwareMap hwMap, Telemetry telemetry) {
    }
    public void Shoot (Robot robot, boolean top, boolean blue) {
        robot.wheels.wheelsTick();
        if (top) {
            robot.driveTrain.turnToHeading(TURN_SPEED, blue ? -130 : 130);
        } if (!top) {
            robot.driveTrain.turnToHeading(TURN_SPEED, blue ? -50 : 50);
        }
        robot.ShootAll(true);
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
