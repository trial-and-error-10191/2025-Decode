package org.firstinspires.ftc.teamcode.Assemblies;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DisplayLED {
    private SparkFunLEDStick LEDLight;
    Robot robot = new Robot(hardwareMap, telemetry);

    public DisplayLED(HardwareMap hwMap, Telemetry telemetry) {
        LEDLight = hwMap.get(SparkFunLEDStick.class, "LED_Light");
    }
    public void IntakeCheck(boolean lightOn) {
        while (lightOn) { // uhhh why is `LEDLight` producing an error"
            if (!robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.closePos)) {
                LEDLight.setColor(Color.CYAN); // If intake flywheel & wall servo work in sync
            } else if (!robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.openPos)) {
                LEDLight.setColor(Color.GREEN); // if intake flywheel works but not the wall servo
            } else if (robot.escarGOMech.intakeFlywheelLeft.equals(0) && robot.escarGOMech.wallServo.equals(robot.escarGOMech.closePos)) {
                LEDLight.setColor(Color.BLUE); // if the wall servo works but not the intake flywheel
            } else {
                LEDLight.setColor(Color.BLACK); // if the wall servo & intake flywheels aren't on
            }
        }
    }
}
