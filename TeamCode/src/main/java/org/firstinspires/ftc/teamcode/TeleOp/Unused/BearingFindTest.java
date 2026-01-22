package org.firstinspires.ftc.teamcode.TeleOp.Unused;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Disabled
@TeleOp (name = "BearingFindTest", group = "Test")
public class BearingFindTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.autoBase.TurnPrecision(robot, 15, 20);
//            driveTrain.ReadBearing(definition.aprilTag);
        }
    }
}
