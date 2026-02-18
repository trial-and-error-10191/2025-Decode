package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;
import org.opencv.core.Point;

public class testAprilTags extends LinearOpMode {

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            Point point = robot.cameraDefinition.aprilTag.getDetections().get(0).center;

            if (point != null) {
                telemetry.addData("Tag X : ", point.x);
                telemetry.addData("Tag Y : ", point.y);
            } else {
                telemetry.addData("Tag not detected", "");
            }

            telemetry.update();
        }
    }
}
