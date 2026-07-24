package org.firstinspires.ftc.teamcode.SafeThreading;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * custom OpMode intended to be used with SafeThread <br>
 * the purpose of this opMode is to provide a way for threads to be used safely and easily within the FTC framework/hardware.
 */
public abstract class ThreadSafeOpMode extends OpMode {

    // threads //
    private final ArrayList<SafeThread> threads = new ArrayList<SafeThread>(); // list of user defined threads.
    private final SafeThread monitoring_thread = new monitor(this); // thread for stuck monitoring

    // iteration counter //
    protected final AtomicInteger iteration_counter = new AtomicInteger(0);

    /** don't use. */
    @Override
    public void init() {}

    /** loop from base class OpMode, DO NOT USE. */
    @Override
    public final void loop() {
        whileActive();
    }

    /** start func from base class OpMode, DO NOT USE. */
    @Override
    public final void start() {
        onStart();
        // monitoring_thread.start();
    }

    /** called by the OpMode function Start() once when the start button is pressed on the driver station. */
    public abstract void onStart();

    /** called by the OpMode function loop() repeatedly while the op-mode is running. */
    public abstract void whileActive();

    /**
     * terminate all active SafeThreads on stop.
     */
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

    /**
     * start the thread.
     * @param thread the thread to start.
     */
    public final void begin_thread(SafeThread thread) {
        if (!threads.contains(thread)) {
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * thread to monitor the OpMode for illegal loops.
     */
    static class monitor extends SafeThread {

        // OpMode to monitor
        OpMode mode = null;

        monitor(OpMode mode) {
            this.mode = mode;
        }

        @Override
        public void Logic() {

        }
    }
}
