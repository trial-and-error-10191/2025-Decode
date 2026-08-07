package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "DriveWithAprilTags", group = "Robot")
public class DriveWithAprilTags extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        robot.autoBase.AprilTagAmount(robot, 23);
        robot.autoBase.DriveUntilDistance(robot, 23, 15, 0.5, -0.1, 0);
        robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,-0.9,0.4);
        robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 0.1, -1, 0, 0.75);
        robot.autoBase.AprilTagAmount(robot, 22);
        robot.autoBase.TurnUntilBearing(robot, 22, -1, 2);
        robot.autoBase.AprilTagAmount(robot, 22);
        robot.autoBase.DriveUntilDistance(robot, 22, 15, 0.1, -0.5, 0);
        robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,-0.9,0.3);
//        robot.autoBase.AprilTagAmount(robot, 21);
//        robot.autoBase.TurnUntilBearing(robot, 21, 1, 2);
//        robot.autoBase.AprilTagAmount(robot, 21);
//        robot.autoBase.DriveUntilDistance(robot, 21, 7, -0.5, 0.1, 0);
        robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,0,0.4);
    }
}
