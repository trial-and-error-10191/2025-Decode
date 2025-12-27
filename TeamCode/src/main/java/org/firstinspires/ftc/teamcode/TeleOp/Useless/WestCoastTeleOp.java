package org.firstinspires.ftc.teamcode.TeleOp.Useless;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Assemblies.*;

@Disabled
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
        RPMlaunchWheels wheels = new RPMlaunchWheels(hardwareMap, telemetry);
        DriveTrain tank = new DriveTrain(hardwareMap, telemetry);

        while (opModeIsActive()) {

//            if (gamepad1.a) {
//                release.Open();
//            } else if (gamepad1.b) {
//                release.Close();
//            }

//            paddles.IteratePaddles();

            wheels.wheelsTick();

            tank.allMotorsDrive(-gamepad1.left_stick_y, gamepad1.right_stick_x);

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
//
//             // count iterations
             iterations++;

             telemetry.addData("SP", iterations);
             telemetry.update();
        }
    }
}