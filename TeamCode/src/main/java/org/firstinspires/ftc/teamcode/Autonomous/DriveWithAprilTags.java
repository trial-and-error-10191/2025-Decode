package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "DriveWithAprilTags", group = "Robot")
public class DriveWithAprilTags extends LinearOpMode {
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.autoBase.AprilTagAmount(robot, 6);
            robot.autoBase.DriveUntilDistance(robot, 6, 50);
            robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,1,0.5);
//            robot.autoBase.AprilTagAmount(robot, 1);
//            robot.autoBase.DriveUntilDistance(robot, 1, 14);;
//            robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,1,0.5);
//            robot.autoBase.AprilTagAmount(robot, 5);
//            robot.autoBase.DriveUntilDistance(robot, 5, 14);;
//            robot.autoBase.SitAndSpin(robot.driveTrainMecanum,0,0,1,0.5);
        }
    }
}
