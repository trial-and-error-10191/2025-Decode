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
}