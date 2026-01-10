package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Autonomous (name = "DriveByAprilTagAuto", group = "Robot")
public class AutoDriveByAprilTag extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.GoalSet(robot.driveTrain, false);
        robot.autoBase.AprilTagAmount(robot);
        waitForStart();
        robot.autoBase.TurnPrecision(robot, 0);
//        robot.autoBase.DrivePrecision(robot, 80);
    }
}
