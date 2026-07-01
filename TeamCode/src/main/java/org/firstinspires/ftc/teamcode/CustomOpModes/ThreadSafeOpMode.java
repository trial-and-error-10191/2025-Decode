package org.firstinspires.ftc.teamcode.CustomOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThreadSafeOpMode extends OpMode {

    // threads //
    private ArrayList<SafeThread> threads = new ArrayList<SafeThread>();

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        for ( SafeThread thread : threads) {
            thread.thread.interrupt();
            thread.thread.stop();
        }

    }
}
