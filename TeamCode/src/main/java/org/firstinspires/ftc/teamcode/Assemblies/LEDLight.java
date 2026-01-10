package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

@Disabled
public class LEDLight {
    private Servo LEDLight;
    private Telemetry telemetry;
    private ElapsedTime runTime;

    private double currentColor = ColorValues.Black.color; // default off

    private LightMode currentEasingMode = LightMode.Flat; // start with default flat easing mode
    private boolean forwardDirection = true; // store direction for rainbow back and forth easing
    private ArrayList<Double> easingValues; // store values for dual value easing

    private double deltaStorage = 0;
    private double changeDuration = 10;

    public enum ColorValues {
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

        public final double color;

        ColorValues(double color) {
            this.color = color;
        }
    }

    public enum LightMode {
        Rainbow(),
        Easing(),
        Flat();
    }


    public double ColorMix(ColorValues... colors) {
        double mixedColor = 0;

        for (ColorValues color : colors) {
            mixedColor += color.color;
        }

        mixedColor /= colors.length;

        return mixedColor;
    }

    // set the LED color
    public void setFlatColor(double color) {
        currentColor = color;
        LEDLight.setPosition(color);
    }

    public LEDLight(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        easingValues = new ArrayList<>(2);

        LEDLight = hwMap.get(Servo.class, "LED_Light");

        this.runTime = new ElapsedTime();

        this.runTime.reset();
    }

    // tick forward all easing modes
    public void easingTick() {

        // deltaTime for accurate timing
        double deltaTime = runTime.seconds() - deltaStorage;
        deltaStorage = runTime.seconds();

      if (currentEasingMode.equals(LightMode.Rainbow)) { // go back and forth from 0 - 1 & 1 - 0 over @changeDuration Seconds
           if (forwardDirection) {
               currentColor += (1 / changeDuration) * deltaTime;

                if (currentColor >= 1) {
                  forwardDirection = !forwardDirection;
                }
           } else {
               currentColor -= (-1 / changeDuration) * deltaTime;

               if (currentColor <= 0) {
                 forwardDirection = !forwardDirection;
               }
           }

           telemetry.addData("stupid", currentColor);
           telemetry.update();

      } else if (currentEasingMode.equals(LightMode.Easing)) { // change values .get(0) to .get(1) over @changeDuration Seconds

              currentColor += ((easingValues.get(1) - easingValues.get(0)) / changeDuration) * deltaTime; // adds a one-hundredth every second

              boolean TerminationClause = ((easingValues.get(1) - easingValues.get(0)) < 0) ? (currentColor < easingValues.get(1)) : (currentColor > easingValues.get(1));

              if (TerminationClause) {
                  currentColor = easingValues.get(1);
              }
        }

     setFlatColor(currentColor);
    }

    public void setEasingMode(LightMode mode) {
        currentEasingMode = mode;

        if (mode.equals(LightMode.Rainbow)) {
            setFlatColor(ColorValues.Black.color);
           forwardDirection = true;
        }
    }

    // easing values for A -> B over @changeDuration Seconds
    public void setEasingValues(double initColor, double endColor) {
            easingValues.clear();
            easingValues.add(initColor);
            easingValues.add(endColor);

            setFlatColor(initColor);
    }
}
