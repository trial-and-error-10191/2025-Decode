package org.firstinspires.ftc.teamcode.CustomOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;

/**
 * custom OpMode intended to be used with SafeThread <br>
 * the purpose of this opMode is to provide a way for threads to be used safely and easily within the FTC framework/hardware.
 */
public abstract class ThreadSafeOpMode extends OpMode {

    // threads //
    private ArrayList<SafeThread> threads = new ArrayList<SafeThread>();

    @Override
    public void init() {}
    @Override
    public final void loop() {whileActive();}
    @Override
    public final void start() {onStart();}

    /**
     * called by the OpMode function loop() repeatedly while the op-mode is running.
     */
    public abstract void whileActive();

    /**
     * called by the OpMode function Start() once when the start button is pressed on the driver station.
     */
    public abstract void onStart();

    @Override
    public final void stop() {
        for ( SafeThread thread : threads) {
            if (thread.thread.isAlive()) {
                thread.terminateThread();
                try {
                    thread.thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public final void begin_thread(SafeThread thread) {
        if (!threads.contains(thread)) {
            threads.add(thread);
            thread.start();
        }
    }
}
