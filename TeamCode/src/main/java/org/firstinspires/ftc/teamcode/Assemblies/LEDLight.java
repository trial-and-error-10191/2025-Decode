package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
public class LEDLight {
    public Servo LEDLight;
    private Telemetry telemetry;

    enum ColorValues {
        Red(0.277),
        Orange(0.333),
        Yellow(0.388),
        Sage(0.444),
        Green(0.500),
        Azure(0.555),
        Blue(0.611),
        Indigo(0.666),
        Violet(0.722),
        Black(0),
        White(1);

        final double color;

        ColorValues(double color) {
            this.color = color;
        }
    }

    public double ColorMix(ColorValues... colors) {
        double mixedColor = 0;

        for (ColorValues color : colors) {
            mixedColor += color.color;
        }

        mixedColor /= colors.length;

        return mixedColor;
    }

    public void setColor(double color) {
        LEDLight.setPosition(color);
    }

    LEDLight(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        LEDLight = hwMap.get(Servo.class, "LED_Light");
    }

    public void IntakeCheck(boolean lightOn) {
        while (lightOn) {
            LEDLight.setPosition(0.666); // turns indigo
        }
    }
}
