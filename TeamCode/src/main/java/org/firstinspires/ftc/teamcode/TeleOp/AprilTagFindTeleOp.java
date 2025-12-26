package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp (name = "AprilTagFind", group = "LinearOpMode")
public class AprilTagFindTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.aprilTagFind.aprilTagTelemetry(robot.aprilTag);
        }
    }
}
