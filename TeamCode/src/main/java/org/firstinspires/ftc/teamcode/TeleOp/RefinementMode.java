package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Assemblies.launchWheels;

@TeleOp(name = "TuningMode", group  = "LinearOpMode")
public class RefinementMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        launchWheels wheels = new launchWheels(telemetry, hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            // make it so that the speed can be altered for proper tuning //
            if (gamepad1.dpad_up) {
                wheels.rpmTarget += 1;
                Thread.sleep(10);
            } else if (gamepad1.dpad_down) {
                wheels.rpmTarget -= 1;
                Thread.sleep(10);
            }

            // make sure the wheels move //
            wheels.wheelsTick();

            // allow the speed to be seen visually for data collection //
            telemetry.addData("RpmTarget : ", wheels.rpmTarget);
            telemetry.update();
        }
    }
}
