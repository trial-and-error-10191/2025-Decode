package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;

@TeleOp(name = "LEDTEST", group = "LinearOpMode")
public class LEDmodeExamples extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        LEDLight led = new LEDLight(hardwareMap, telemetry, "modeSwapLed");

        // default Lightmode set to Flat
        led.setEasingMode(LEDLight.LightMode.Flat);

        // color for Lightmode.Flat
        led.setFlatColor(LEDLight.ColorValues.Red.color);

        // duration for ALL effects. EXP. rainbow speed, time to ease from A -> B colors and time between on/off states in flashing
        led.setEasingDuration(5);

        // colors to ease between in Lightmode.Easing
        led.setEasingColors(LEDLight.ColorValues.Sage.color, LEDLight.ColorValues.Violet.color);

        while (opModeIsActive()) {
            if (gamepad1.a) {

                led.setEasingDuration(5);
               led.setEasingMode(LEDLight.LightMode.Easing);
            } else if (gamepad1.b) {

                led.setEasingDuration(0.5);
                led.setEasingMode(LEDLight.LightMode.Flashing);
            } else if(gamepad1.x) {

                led.setEasingDuration(5);
                led.setEasingMode(LEDLight.LightMode.Flat);
            } else if (gamepad1.y) {

                led.setEasingDuration(5);
                led.setEasingMode(LEDLight.LightMode.Rainbow);
            }

            led.easingTick();

            telemetry.addData("mode", led.currentEasingMode);
            telemetry.addData("FlatColor", led.flatColor);
            telemetry.update();
        }
    }
}
