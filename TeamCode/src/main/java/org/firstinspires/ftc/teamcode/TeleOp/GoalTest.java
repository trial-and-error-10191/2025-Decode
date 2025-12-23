package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Assemblies.Robot;

@TeleOp(name = "GoalTest", group = "LinearOpMode")
public class GoalTest extends LinearOpMode {
    private final ElapsedTime Time = new ElapsedTime();
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        robot.ballRelease.Close();
        waitForStart();
        while (opModeIsActive()) {
            robot.wheels.wheelsTick();
            telemetry.addData("WheelPower1", robot.wheels.MainMotor1.getPower());
            telemetry.addData("WheelPower2", robot.wheels.MainMotor2.getPower());
            telemetry.update();
            robot.ballRelease.Open();
            robot.driveTrain.allMotorsDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);
        }
    }
}
