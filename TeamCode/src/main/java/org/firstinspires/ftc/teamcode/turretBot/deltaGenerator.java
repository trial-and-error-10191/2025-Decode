package org.firstinspires.ftc.teamcode.turretBot;

public class deltaGenerator {

    // detectors //
    private int structureEnforcer = 0;

    // time //
    private double initialMs;
    private double endMS;

    // delta //
    public double delta = 0;

    /**
     * records the initial frame runtime. THIS MUST BE PLACED AT THE VERY BEGINNING OF YOUR LOGICAL LOOP
     */
    public void Begin() {
        if (structureEnforcer == 0) {
            throw new RuntimeException("Method End() not called. please place End() at the end of your logic loop.");
        }
        structureEnforcer = 0;

        delta = initialMs - endMS;

        initialMs = System.currentTimeMillis();
    }

    /**
     * records the ending time of the frame. THIS MUST BE PLACED AT THE VERY END OF YOUR LOGICAL LOOP
     */
    public void End() {
        structureEnforcer = 1;

        endMS = System.currentTimeMillis();
    }
}
