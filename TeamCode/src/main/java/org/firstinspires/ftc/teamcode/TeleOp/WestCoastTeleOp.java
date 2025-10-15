package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Assemblies.*;

@TeleOp(name = "small and focused program where we can drive and operate the west coast drivetrain", group  = "LinearOpMode")
public class WestCoastTeleOp extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        // Initiates the robots system and subsystems!
//        Robot robot = new Robot(hardwareMap, telemetry);

        waitForStart();

        // create an object of each assembly
//        DriveTrain westCoast = new DriveTrain(hardwareMap, telemetry);
        launchWheels wheels = new launchWheels(telemetry, hardwareMap);
        ballRelease release = new ballRelease(telemetry, hardwareMap);
        ArtifactPaddles paddles = new ArtifactPaddles(telemetry, hardwareMap);

        while (opModeIsActive()) {
//           westCoast.drive(gamepad1.left_stick_y, gamepad1.right_stick_x);
             wheels.wheelsTick();

             release.releaseLogic(gamepad1);

             paddles.IteratePaddles();

             if (gamepad1.right_bumper) { paddles.QueueCooldowns(1, true); }

             telemetry.addData("SP", paddles.sensor.isPressed());
            telemetry.update();
        }
    }

}
