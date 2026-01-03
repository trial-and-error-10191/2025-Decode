package org.firstinspires.ftc.teamcode.TeleOp.Useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp (name = "BearingTest", group = "LinearOpMode")
public class BearingTest extends LinearOpMode {
    @Override
    public void runOpMode() {
//        CameraDefinition cameraDefinition = new CameraDefinition(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
//            List<AprilTagDetection> currentDetections = cameraDefinition.aprilTag.getDetections();
//            for (AprilTagDetection detection : currentDetections) {
//                if (detection.metadata != null) {
//                    if (detection.id == 24 || detection.id == 20) {
//                        telemetry.addData("Bearing", detection.ftcPose.bearing);
//                        telemetry.addData("Yaw", detection.ftcPose.yaw);
//                        telemetry.update();
//                    }
//                }
//            }
        }
    }
}
