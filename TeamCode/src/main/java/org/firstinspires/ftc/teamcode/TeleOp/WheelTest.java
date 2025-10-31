package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.EscarGOMech;

@TeleOp(name="WheelTest", group="Robot")
public class WheelTest extends LinearOpMode {
    EscarGOMech launcher;
    private final ElapsedTime Time   = new ElapsedTime();
    @Override
    public void runOpMode() {
        waitForStart();
        while (opModeIsActive()) {
            launcher = new EscarGOMech(hardwareMap, telemetry);
//            launcher.WheelLaunch(gamepad2.right_trigger);
        }
    }
}
