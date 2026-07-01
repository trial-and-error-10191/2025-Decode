package org.firstinspires.ftc.teamcode.CustomOpModes;

/**
 * class intended to be used with the ThreadSafeOpMode.
 */
public abstract class SafeThread {

    // thread object //
    protected Thread thread = null;

    SafeThread() {

    }

    public final void start() {
        thread = new Thread(this::Logic);
    }

    /**
     * place all code to run on the thread inside this function. must be overridden.
     */
    public abstract void Logic();
}
