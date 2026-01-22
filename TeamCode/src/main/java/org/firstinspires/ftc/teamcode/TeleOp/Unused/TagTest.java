package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@Disabled
@TeleOp (name = "TagTest", group = "Test")
public class TagTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                List<AprilTagDetection> currentDetections = robot.cameraDefinition.aprilTag.getDetections();
                telemetry.addData("AprilTag Seen", currentDetections.size());
                telemetry.update();
            }
        }
    }
}
