package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

//@Disabled
@TeleOp(name = "GoalTest", group = "Test")
public class GoalTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.a) {
                robot.driveTrain.DriveByAprilTag(40, robot.cameraDefinition.aprilTag);
            } else if (gamepad2.y) {
                robot.driveTrain.DriveByAprilTag(80, robot.cameraDefinition.aprilTag);
            } else if (gamepad2.b) {
                robot.driveTrain.TurnToAprilTag(0, robot.cameraDefinition.aprilTag);
            } else if (gamepad2.x) {
                robot.driveTrain.TurnToAprilTag(15, robot.cameraDefinition.aprilTag);
            } else {
                robot.driveTrain.moveRobot(0, 0);
            }
        }
    }
    private final ElapsedTime Time = new ElapsedTime();
}
