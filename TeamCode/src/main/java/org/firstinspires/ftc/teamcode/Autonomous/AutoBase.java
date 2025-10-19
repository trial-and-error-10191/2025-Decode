package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "AutoBase", group = "Autonomous")
public class AutoBase extends LinearOpMode {
    private final ElapsedTime Time = new ElapsedTime();

    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            robot.aprilTagDetect.locateTargetAprilTag();
            robot.displayLED.IntakeCheck(true);
            // Path stuff maybe?
        }
    }
}
