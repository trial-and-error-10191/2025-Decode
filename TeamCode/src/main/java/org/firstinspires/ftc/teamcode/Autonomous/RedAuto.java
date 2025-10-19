package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "RedAuto", group = "Robot")
public class RedAuto extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {
            robot.autoBase.runOpMode();
            /// All of this may need to be fine-tuned
            robot.driveTrain.driveStraight(1.0, 20, 0);
            robot.driveTrain.driveStraight(1.0, 60, -90);
            robot.driveTrain.driveStraight(1.0, 20, 0);
            robot.driveTrain.turnToHeading(1.0, 30);
        }
    }
}
