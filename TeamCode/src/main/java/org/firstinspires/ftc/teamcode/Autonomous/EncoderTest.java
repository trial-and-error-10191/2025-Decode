package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@Autonomous (name = "EncoderTest", group = "Robot")
public class EncoderTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.autoBase.SetToEncoders(robot.driveTrain);
        waitForStart();
        robot.driveTrain.driveWithEncoders(-750);
        robot.driveTrain.driveWithEncoders(750);
        robot.autoBase.SetToPower(robot.driveTrain);
        robot.driveTrain.autoDriveStraight(0.5, 1);
        robot.driveTrain.autoDriveStraight(-0.5, 1);
        // Turning testing for later
//        robot.autoBase.SetToEncoders(robot.driveTrain);
//        robot.driveTrain.turnWithEncoders(10);
//        robot.driveTrain.turnWithEncoders(-10);
//        robot.autoBase.SetToPower(robot.driveTrain);
//        robot.driveTrain.autoTurn(0.5, 1);
//        robot.driveTrain.autoTurn(-0.5, 1);
    }
}
