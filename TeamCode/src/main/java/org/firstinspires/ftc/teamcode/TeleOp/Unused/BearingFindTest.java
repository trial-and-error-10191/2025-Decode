package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

//@Disabled
@TeleOp (name = "BearingFindTest", group = "Test")
public class BearingFindTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.AprilTagAmount(robot, 20);
        waitForStart();
        while (opModeIsActive()) {
            robot.autoBase.TurnPrecision(robot, 0, 20);
            robot.autoBase.Wait(30);
        }
    }
}
