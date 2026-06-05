package org.firstinspires.ftc.teamcode.TurretBot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "TurretBot", group = "LinearOpMode")
public class TeleOpTurretBot extends LinearOpMode {
    long start = System.nanoTime();
    @Override
    public void runOpMode() {
        TurretRobot robot = new TurretRobot(hardwareMap, telemetry);
        robot.camFindDistAndBearing.distanceBearingFind(robot.cameraDefinition.aprilTag);
        waitForStart();
        while (opModeIsActive()) {
//            robot.turretAim.findLastBearingPos(robot.camFindDistAndBearing);
//            robot.turretAim.TurnWithCRServo(robot.camFindDistAndBearing);
            if (gamepad1.dpad_left) {
                robot.camFindDistAndBearing.goalID = 20;
            } else if (gamepad1.dpad_right) {
                robot.camFindDistAndBearing.goalID = 24;
            }
            robot.turretAim.SpinServo(gamepad1.x, gamepad1.b);
            robot.newDriveTrain.easingDrive(gamepad1.right_stick_x, gamepad1.left_stick_y);
            // The distance telemetry is measured in inches.
//            telemetry.addData("Distance from the goal AprilTag", robot.camFindDistAndBearing.distance);
            // The bearing telemetry is measured in
            telemetry.addData("Angle to the camera", robot.camFindDistAndBearing.bearing);
            telemetry.addData("Servo Power", robot.turretAim.servo.getPower());
            telemetry.addData("Last bearing position", robot.turretAim.lastBearingPos);
            telemetry.update();
        }
    }
}
