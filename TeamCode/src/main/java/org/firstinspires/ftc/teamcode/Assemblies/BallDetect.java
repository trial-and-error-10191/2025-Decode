package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
public class BallDetect {
    private final ElapsedTime Time = new ElapsedTime();
//    private final int READ_PERIOD = 1;

//    boolean findBall = true;

    int colorFound = 0;

    long start = System.nanoTime();
    long finish = System.nanoTime();
    long timeElapsed = finish - start;

    private HuskyLens huskyLens;

//    private Deadline rateLimit;

    public BallDetect(HardwareMap hwMap) {
//        huskyLens = hwMap.get(HuskyLens.class, "huskylens");

//        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);
    }

    public int colorFind(boolean findBall) {
        // Setting this up to check the a amount of time pasted since the last info gathering from the Huskylens
        timeElapsed = System.nanoTime() - start;

        if (findBall) {
            if (timeElapsed >= 1E9) {
                HuskyLens.Block[] blocks = huskyLens.blocks();

                if (blocks.length == 0) {
                    colorFound = 0;
                }
                for (int i = 0; i < blocks.length; i++) {
                    if (blocks[i].id == 1) { // Checking if it sees the color green
                        colorFound = 1;
                    } else if (blocks[i].id == 2) { // Checking if it sees the color purple
                        colorFound = 2;
                    }
                }
                start = System.nanoTime(); // Updates the starting time to after we got the data from the Huskylens
            } else {
                return colorFound;
            }
        }
        return colorFound;
    }

    public static class BallRelease {

        // define the servo
        Servo servo;

        // define supportive vars
        boolean previousInput = false;
        boolean open = false;

        public BallRelease(HardwareMap hwMap, Telemetry telemetry) {
            servo = hwMap.get(Servo.class, "dropServo");
        }

        // function to drop the ball
        public void Open() {
            servo.setPosition(0);
            open = true;
        }

        // function to close the hole for next ball
        public void Close() {
            servo.setPosition(0.5);
            open = false;
        }

        public void releaseLogic(boolean release) {
            if (!previousInput && release) {
                if (open) {
                    Close();
                } else {
                    Open();
                }
                previousInput = true;
            }

            if (!release) {
                previousInput = false;
            }
        }
    }
}