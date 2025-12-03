package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous(name = "MoveUp", group = "Robot")
public class MoveUp extends LinearOpMode {
    static final double TURN_SPEED = 0.7;
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive()) {
            robot.driveTrain.driveStraight(TURN_SPEED, 4, 0);
            robot.autoBase.Wait(30);
            robot.driveTrain.driveStraight(TURN_SPEED,0,0);
        }
    }
}
