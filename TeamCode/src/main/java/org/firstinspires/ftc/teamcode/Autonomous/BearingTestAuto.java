package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "BearingTest", group = "Test")
public class BearingTestAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        robot.autoBase.AprilTagAmount(robot, 23);
        robot.autoBase.TurnUntilBearing(robot, 23, 0, 8);
    }
}
