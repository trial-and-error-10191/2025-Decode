package org.firstinspires.ftc.teamcode.CustomOpModes;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * class intended to be used with the ThreadSafeOpMode.
 */
public abstract class SafeThread {

    // thread object //
    protected Thread thread = null;

    /**
     * kills the thread after your current Logic() iteration has concluded
     */
    public final void terminateThread() {
        if (!thread.isInterrupted()) {
            thread.interrupt();
        } else {
            throw new RuntimeException("Thread terminate called more than once per thread");
        }
    }

    private void LogicWrapper() {
        while (!thread.isInterrupted()) {
            Logic();
        }
    }

    /**
     * checks if the thread is dead, if not, starts the thread.
     */
    protected final void start() {

        if (thread != null) {
           throw new RuntimeException("!! Start() called more than once !!");
        }
        thread = new Thread(this::LogicWrapper);
        thread.start();
    }

    /**
     * The logic to run repeatedly in the loop. <br>
     * FUNCTION PRE-WRAPPED, DO NOT PLACE A WHILE LOOP
     */
    public abstract void Logic();
}
