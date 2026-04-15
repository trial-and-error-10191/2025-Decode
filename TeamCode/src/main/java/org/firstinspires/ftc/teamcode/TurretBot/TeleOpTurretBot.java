package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TurretBot", group = "LinearOpMode")
public class TeleOpTurretBot extends LinearOpMode {
    @Override
    public void runOpMode() {
        TurretRobot robot = new TurretRobot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.camFindDistAndBearing.distanceBearingFind(robot.cameraDefinition.aprilTag);
            if (gamepad1.dpad_left) {
                robot.camFindDistAndBearing.goalID = 20;
            } else if (gamepad1.dpad_right) {
                robot.camFindDistAndBearing.goalID = 24;
            }
            robot.driveTrain.drive(gamepad1.left_stick_y, -gamepad1.left_stick_x, gamepad1.right_stick_x);
            robot.turretAim.ServoSet0(gamepad1.x);
            robot.turretAim.ServoSet1(gamepad1.b);
            if (gamepad1.y) {
                robot.turretAim.servo.setPosition(0.5);
            }
            // The distance telemetry is measured in inches.
            telemetry.addData("Distance from the goal AprilTag", robot.camFindDistAndBearing.distance);
            // The bearing telemetry is measured in
            telemetry.addData("Angle to the camera", robot.camFindDistAndBearing.bearing);
            telemetry.update();
        }
    }
}
