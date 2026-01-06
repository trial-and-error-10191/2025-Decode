package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "DriveByAprilTagAuto", group = "Robot")
public class AutoDriveByAprilTag extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.GoalSet(robot.driveTrain, false);
        waitForStart();
//        robot.driveTrain.TurnToAprilTag(0, robot.cameraDefinition.aprilTag);
        robot.autoBase.DrivePrecision(robot, 80);
    }
}
