package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
public class LEDLight {
    private Servo LEDLight;
    public double WorksGreat = 0.611;  // Does this when if intake flywheel & wall servo work in sync, turns to a blue
    public double WorksBad = 0.277;    // Does this when intake flywheel works but not the wall servo, turns red
    public double WorksOkay = 0.666;   // Does this when wall servo works but not the intake flywheel, turns indigo
    public double WorksOff = 0;        // Does this when the wall servo & intake flywheels aren't on, turns off

    public void DisplayLED(HardwareMap hwMap) {
        LEDLight = hwMap.get(Servo.class, "LED_Light");
    }
    //    public EscarGOMech escarGOMech;
    public void IntakeCheck(boolean lightOn) {
        while (lightOn) {
            LEDLight.setPosition(0.666); // turns indigo
        }
    }
}
