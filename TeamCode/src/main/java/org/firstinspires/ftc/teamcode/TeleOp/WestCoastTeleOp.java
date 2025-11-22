package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Assemblies.*;

@TeleOp(name = "small and focused program where we can drive and operate the west coast drivetrain", group  = "LinearOpMode")
public class WestCoastTeleOp extends LinearOpMode {

    // create essential tracking values
    int iterationsTarget = 700;
    int iterations = 0;
    boolean isPressed = false;


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        // create an object of each assembly
        ArtifactPaddles paddles = new ArtifactPaddles(hardwareMap, telemetry);
        BallRelease release = new BallRelease(hardwareMap, telemetry);
        RPMlaunchWheels wheels = new RPMlaunchWheels(telemetry, hardwareMap);
        DriveTrain tank = new DriveTrain(hardwareMap, telemetry);

        while (opModeIsActive()) {

             paddles.IteratePaddles();

             wheels.wheelsTick();

             tank.drive(gamepad1.left_stick_y * 1.2, gamepad1.right_stick_x * 5);

            // logic for opening thingymabober
             if (gamepad1.right_bumper && !isPressed) {
                 paddles.QueueCooldowns(1, true);
                 iterations = 0;
                 isPressed = true;
             } else if (isPressed && iterations > iterationsTarget) {
                 release.Open();
                 if (iterations > iterationsTarget + (iterationsTarget / 2)) {
                     release.Close();
                     isPressed = false;
                 }
             }

             // count iterations
             iterations++;

             telemetry.addData("SP", iterations);
            telemetry.update();
        }
    }

}
