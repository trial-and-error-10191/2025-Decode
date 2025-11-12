package org.firstinspires.ftc.teamcode.Assemblies;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

//@Disabled
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
        huskyLens = hwMap.get(HuskyLens.class, "huskylens");

        /*
         * This sample rate limits the reads solely to allow a user time to observe
         * what is happening on the Driver Station telemetry.  Typical applications
         * would not likely rate limit.
         */
//        rateLimit = new Deadline(READ_PERIOD, TimeUnit.SECONDS);
//
//        /*
//         * Immediately expire so that the first time through we'll do the read.
//         */
//        rateLimit.expire();
    }

    public int colorFind(boolean findBall) {
        // Setting this up to check the a amount of time pasted since the last info gathering from the Huskylens
        timeElapsed = System.nanoTime() - start;
        /*
         * Basic check to see if the device is alive and communicating.  This is not
         * technically necessary here as the HuskyLens class does this in its
         * doInitialization() method which is called when the device is pulled out of
         * the hardware map.  However, sometimes it's unclear why a device reports as
         * failing on initialization.  In the case of this device, it's because the
         * call to knock() failed.
         */
//        if (!huskyLens.knock()) {
//            telemetry.addData(">>", "Problem communicating with" + huskyLens.getDeviceName());
//        } else {
//            telemetry.addData(">>", "Press start to continue");
//        }

        /*
         * The device uses the concept of an algorithm to determine what types of
         * objects it will look for and/or what mode it is in.  The algorithm may be
         * selected using the scroll wheel on the device, or via software as shown in
         * the call to selectAlgorithm().
         *
         * The SDK itself does not assume that the user wants a particular algorithm on
         * startup, and hence does not set an algorithm.
         *
         * Users, should, in general, explicitly choose the algorithm they want to use
         * within the OpMode by calling selectAlgorithm() and passing it one of the values
         * found in the enumeration HuskyLens.Algorithm.
         *
         * Other algorithm choices for FTC might be: OBJECT_RECOGNITION, COLOR_RECOGNITION or OBJECT_CLASSIFICATION.
         */
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

        /*
         * Looking for AprilTags per the call to selectAlgorithm() above.  A handy grid
         * for testing may be found at https://wiki.dfrobot.com/HUSKYLENS_V1.0_SKU_SEN0305_SEN0336#target_20.
         *
         * Note again that the device only recognizes the 36h11 family of tags out of the box.
         */
        if (findBall) {
//            if (rateLimit.hasExpired()) {
//                rateLimit.reset();
//                return -1;
//            }

            /*
             * All algorithms, except for LINE_TRACKING, return a list of Blocks where a
             * Block represents the outline of a recognized object along with its ID number.
             * ID numbers allow you to identify what the device saw.  See the HuskyLens documentation
             * referenced in the header comment above for more information on IDs and how to
             * assign them to objects.
             *
             * Returns an empty array if no objects are seen.
             */

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
