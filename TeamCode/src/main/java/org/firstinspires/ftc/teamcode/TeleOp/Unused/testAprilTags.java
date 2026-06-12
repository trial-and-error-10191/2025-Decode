package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@TeleOp(name = "cameraTest", group = "LinearOpMode")
public class testAprilTags extends LinearOpMode {

    Robot robot;

    @Override
    public void runOpMode() throws InterruptedException {


//        CameraDefinition camera = new CameraDefinition(hardwareMap, telemetry);
//        Point point = null;
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            if (!camera.aprilTag.getDetections().isEmpty()) {
//                point = camera.aprilTag.getDetections().get(0).center;
//            } else {
//                point = null;
//            }
//
//                    if (point != null) {
//                        telemetry.addData("Tag X : ", point.x);
//                        telemetry.addData("Tag Y : ", point.y);
//                    } else {
//                        telemetry.addData("Tag not detected", "");
//                    }
//
//                    telemetry.update();
//                }
    }
}
