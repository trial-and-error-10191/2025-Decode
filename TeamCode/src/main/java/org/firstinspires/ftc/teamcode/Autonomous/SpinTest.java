package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "Spin", group = "Test")
public class SpinTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.AprilTagAmount(robot, robot.obeliskOrder.findTag(robot.cameraDefinition.aprilTag));
        waitForStart();
        robot.patternMatchAuto();
        robot.autoBase.Wait(5);
    }
}
