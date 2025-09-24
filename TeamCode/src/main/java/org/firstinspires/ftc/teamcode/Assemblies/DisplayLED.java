package org.firstinspires.ftc.teamcode.Assemblies;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DisplayLED {
    public int WorksGreat = Color.CYAN; // Does this when if intake flywheel & wall servo work in sync
    public int WorksBad = Color.RED;    // Does this when intake flywheel works but not the wall servo
    public int WorksOkay = Color.BLUE;  // Does this when wall servo works but not the intake flywheel
    public int WorksOff = Color.BLACK;  // Does this when the wall servo & intake flywheels aren't on
    private SparkFunLEDStick LEDLight;
    Robot robot = new Robot(hardwareMap, telemetry);

    public DisplayLED(HardwareMap hwMap, Telemetry telemetry) {
        LEDLight = hwMap.get(SparkFunLEDStick.class, "LED_Light");
    }
    public void IntakeCheck(boolean lightOn) {
        while (lightOn) {
            LEDLight.setColor(Color.BLUE);
//            if (!robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.closePos)) {
//                LEDLight.setColor(WorksGreat); // If the intake flywheel & wall servo work in sync
//            } else if (!robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.openPos)) {
//                LEDLight.setColor(WorksBad); // if the intake flywheel works but not the wall servo
//            } else if (robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.closePos)) {
//                LEDLight.setColor(WorksOkay); // if the wall servo works but not the intake flywheel
//            } else {
//                LEDLight.setColor(WorksOff); // if the wall servo & intake flywheels aren't on
//            }
        }
    }
}