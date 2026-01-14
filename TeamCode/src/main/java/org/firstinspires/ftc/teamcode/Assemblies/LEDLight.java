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
    private ElapsedTime timer;

    private LightMode currentEasingMode = LightMode.Flat; // start with default flat easing mode
    private boolean forwardDirection = true; // store direction for rainbow back and forth easing

    public double flatColor = ColorValues.Black.color; // store flat mode color
    private final ArrayList<Double> easingColor; // store values for dual value easing

    public double currentColor = flatColor;

    // local timing vars
    private double deltaStorage = 0;
    private double changeDuration = 10;

    public enum ColorValues {
        Red(0.290),
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
        Flashing(),
        Flat();
    }

    // mix two Values from color (exp) Violet + Indigo averages to 0.69
    public double ColorMix(ColorValues... colors) {
        double mixedColor = 0;

        for (ColorValues color : colors) {
            mixedColor += color.color;
        }

        mixedColor /= colors.length;

        return mixedColor;
    }

    public LEDLight(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        easingColor = new ArrayList<>(2);

        LEDLight = hwMap.get(Servo.class, "LED_Light");

        this.timer = new ElapsedTime();
        this.timer.reset();
    }

    // tick forward all easing modes
    public void easingTick() {

        // deltaTime for accurate timing
        double deltaTime = timer.seconds() - deltaStorage;
        deltaStorage = timer.seconds();

      if (currentEasingMode.equals(LightMode.Rainbow)) { // go back and forth from 0 - 1 & 1 - 0 over @changeDuration Seconds

          double gradientDifference = Math.abs(ColorValues.Violet.color - ColorValues.Red.color);

           if (forwardDirection) {
               currentColor += (gradientDifference / changeDuration) * deltaTime;

                if (currentColor >= ColorValues.Violet.color) {
                  forwardDirection = !forwardDirection;
                }
           } else {
               currentColor -= (gradientDifference / changeDuration) * deltaTime;

               if (currentColor <= ColorValues.Red.color) {
                 forwardDirection = !forwardDirection;
               }
           }

           telemetry.addData("stupid", currentColor);

      } else if (currentEasingMode.equals(LightMode.Easing)) { // change values for A (.get0)  -> B (.get1) over @changeDuration Seconds

              currentColor += ((easingColor.get(1) - easingColor.get(0)) / changeDuration) * deltaTime; // adds a one-hundredth every second

              boolean TerminationClause = ((easingColor.get(1) - easingColor.get(0)) < 0) ? (currentColor < easingColor.get(1)) : (currentColor > easingColor.get(1));

              if (TerminationClause) {
                  currentColor = easingColor.get(1);
              }

          telemetry.addData("stupid2", currentColor);
      } else if (currentEasingMode.equals(LightMode.Flashing)) {

             if (timer.seconds() > changeDuration) {
                 timer.reset();

                 if (currentColor != 0) {
                     currentColor = ColorValues.Black.color;
                 } else {
                     currentColor = flatColor;
                 }
             }


             telemetry.addData("time", timer.seconds());

      } else if (currentEasingMode.equals(LightMode.Flat)) {

          currentColor = flatColor;

      }

     setColor(currentColor);
    }

    // set the easing mode to a valid mode from @LightMode
    public void setEasingMode(LightMode mode) {
        currentEasingMode = mode;

        if (mode.equals(LightMode.Rainbow)) {
           currentColor = ColorValues.Black.color;
           forwardDirection = true;
        }

        if (mode.equals(LightMode.Flashing)) {
            currentColor = 0;
        }
    }

    // easing values for A -> B over @changeDuration Seconds
    public void setEasingColors(double initColor, double endColor) {
            easingColor.clear();
            easingColor.add(initColor);
            easingColor.add(endColor);
    }

    // set the LED color
    public void setFlatColor(double color) {
        flatColor = color;
    }

    // set the duration of color easing
    public void setEasingDuration(double seconds) {
        this.changeDuration = seconds;
    }

    private void setColor(double color) {
        LEDLight.setPosition(color);
    }
}
