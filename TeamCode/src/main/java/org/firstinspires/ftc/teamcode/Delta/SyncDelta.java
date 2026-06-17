package org.firstinspires.ftc.teamcode.Delta;

public class SyncDelta extends Delta{

    // detectors //
    private int structureEnforcer;

    // time //
    private double initialMs;
    private double endMS;

    SyncDelta() {

        // initialize structure enforcer.
        structureEnforcer = 1;
    }
    

    /**
     * Place at the beginning of your primary loop before any code has executed.
     */
    void Begin() {
        if (structureEnforcer == 0) {
            throw new RuntimeException("Method End() not called. please place End() at the end of your logic loop.");
        }
        structureEnforcer = 0;

        delta = initialMs - endMS;

        initialMs = System.currentTimeMillis();
    }

    /**
     * place at the end of your primary loop after all code has executed.
     */
    public void End() {
        if (structureEnforcer == 1) {
            throw new RuntimeException("Method Begin() not called. please place Begin() at the beginning of your logic loop.");
        }
        structureEnforcer = 1;

        endMS = System.currentTimeMillis();

        delta = initialMs - endMS;
    }
}
