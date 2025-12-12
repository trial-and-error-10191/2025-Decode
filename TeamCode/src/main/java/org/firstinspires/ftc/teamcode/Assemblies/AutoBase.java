package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoBase {
    private final ElapsedTime Time = new ElapsedTime();
    public double power = 0.5;
    long start = System.nanoTime();
    public AutoBase(HardwareMap hwMap, Telemetry telemetry) {
    }
    public void Shoot(Robot robot) {
        start = System.nanoTime();
        while (System.nanoTime() - start < 5E9) {
            robot.wheels.wheelsTick();
            while (System.nanoTime() - start > 3E9) {
                robot.wheels.wheelsTick();
                if (System.nanoTime() - start >= 5E9) {
                    robot.ShootAll(true);
                    break;
                }
            }
        }
    }
    public void Wait(double seconds) {
        Time.reset();
        while (Time.milliseconds()  < seconds * 1000) {
            // doesn't need anything
        } // end of while loop
    } // end of public void Wait
}
