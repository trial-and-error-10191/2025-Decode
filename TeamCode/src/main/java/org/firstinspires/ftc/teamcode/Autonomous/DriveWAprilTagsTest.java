package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp (name = "DriveW/ApriltagsTest", group = "Test")
public class DriveWAprilTagsTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.autoBase.AprilTagAmount(robot, 23);
            while (!gamepad1.a) {}
            robot.autoBase.DriveUntilDistance(robot, 23, 15, 0.5, -0.1, 0);
            while (!gamepad1.a) {}
            robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 0, 0, -0.9, 0.4);
            while (!gamepad1.a) {}
            robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 0.1, -1, 0, 0.75);
            while (!gamepad1.a) {}
            robot.autoBase.AprilTagAmount(robot, 22);
            while (!gamepad1.a) {}
            robot.autoBase.TurnUntilBearing(robot, 22, -1, 2);
            while (!gamepad1.a) {}
            robot.autoBase.AprilTagAmount(robot, 22);
            robot.autoBase.DriveUntilDistance(robot, 22, 15, 0.1, -0.5, 0);
            while (!gamepad1.a) {}
            robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 0, 0, -0.9, 0.3);
            while (!gamepad1.a) {}
//        robot.autoBase.AprilTagAmount(robot, 21);
//        robot.autoBase.TurnUntilBearing(robot, 21, 1, 2);
//        robot.autoBase.AprilTagAmount(robot, 21);
//        robot.autoBase.DriveUntilDistance(robot, 21, 7, -0.5, 0.1, 0);
            robot.autoBase.SitAndSpin(robot.driveTrainMecanum, 0, 0, 0, 0.4);
        }
    }
}
