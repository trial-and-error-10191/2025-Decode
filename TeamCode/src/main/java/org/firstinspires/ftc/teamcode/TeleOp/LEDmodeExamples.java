package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.LEDLight;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp(name = "LEDTEST", group = "LinearOpMode")
public class LEDmodeExamples extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        LEDLight led = new LEDLight(hardwareMap, telemetry);

        led.setEasingMode(LEDLight.LightMode.Rainbow);
        led.setEasingValues(LEDLight.ColorValues.Sage.color, LEDLight.ColorValues.Violet.color);

        while (opModeIsActive()) {
            led.easingTick();
        }
    }
}
