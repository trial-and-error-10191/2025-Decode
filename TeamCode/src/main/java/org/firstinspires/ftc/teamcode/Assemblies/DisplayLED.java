package org.firstinspires.ftc.teamcode.Assemblies;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DisplayLED {
    private Servo LEDLight;
    public double WorksGreat = 0.611;  // Does this when if intake flywheel & wall servo work in sync, turns to a blue
    public double WorksBad = 0.277;    // Does this when intake flywheel works but not the wall servo, turns red
    public double WorksOkay = 0.666;   // Does this when wall servo works but not the intake flywheel, turns indigo
    public double WorksOff = 0;        // Does this when the wall servo & intake flywheels aren't on, turns off

    public DisplayLED(HardwareMap hwMap, Telemetry telemetry) {
        LEDLight = hwMap.get(Servo.class, "LED_Light");
//        escarGOMech = new EscarGOMech(hwMap, telemetry);
    }
//    public EscarGOMech escarGOMech;
    public void IntakeCheck(boolean lightOn) {
        while (lightOn) {
            LEDLight.setPosition(0.666); // turns indigo
//            if (!escarGOMech.intakeFlywheelLeft.equals(0) && escarGOMech.wallServo.equals(escarGOMech.closePos)) {
//                LEDLight.setColor(WorksGreat); // If the intake flywheel & wall servo work in sync
//            } else if (!escarGOMech.intakeFlywheelLeft.equals(0) && escarGOMech.wallServo.equals(escarGOMech.openPos)) {
//                LEDLight.setColor(WorksBad); // if the intake flywheel works but not the wall servo
//            } else if (escarGOMech.intakeFlywheelLeft.equals(0) && escarGOMech.wallServo.equals(escarGOMech.closePos)) {
//                LEDLight.setColor(WorksOkay); // if the wall servo works but not the intake flywheel
//            } else {
//                LEDLight.setColor(WorksOff); // if the wall servo & intake flywheels aren't on
//            }
        }
    }
}