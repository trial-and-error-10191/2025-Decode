package org.firstinspires.ftc.teamcode.Autonomous.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@Autonomous (name = "DriveByAprilTagAuto", group = "Robot")
public class AutoDriveByAprilTag extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.GoalSet(robot.driveTrain, false);
        robot.autoBase.AprilTagAmount(robot);
        waitForStart();
        robot.autoBase.TurnPrecision(robot, 0, 24);
//        robot.autoBase.DrivePrecision(robot, 80);
    }
}
