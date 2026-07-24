
package org.firstinspires.ftc.teamcode.DeltaMode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.DeltaMode.DeltaLinearOpMode;

import java.util.ArrayList;

@TeleOp(name = "Delta Testing", group = "Test")
public class DeltaTest extends DeltaLinearOpMode {

    ArrayList<String> spaceWaster = null;
    double iterate = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive() && deltaIsCalculated()) {

            spaceWaster = new ArrayList<>(70);
            for (int i = 0; i < 10; i++) {
                spaceWaster.add(Integer.toString((int) (Math.random() * Math.random() * 1000)));
            }
            telemetry.addData("junk", spaceWaster);

            if (gamepad1.a) {
                iterate += (0.2 * getDelta());
            };

            telemetry.addData("iterator", iterate);
            telemetry.addData("Delta", getDelta());
            telemetry.update();


        }
    }
}
