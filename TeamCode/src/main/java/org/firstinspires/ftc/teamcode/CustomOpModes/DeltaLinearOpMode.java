package org.firstinspires.ftc.teamcode.CustomOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DeltaLinearOpMode extends LinearOpMode implements Delta {

    // delta logs //
    private double lastNS;

    // delta //
    private double delta;

    public DeltaLinearOpMode() {
        super();
        lastNS = System.nanoTime();
        delta = 0;
    }

    /**
     * must be called alongside opModeIsActive(). for example. <br>
     * {@code while (opModeIsActive() && deltaIsCalculated()) {} } <br>
     * @return true if the delta was able to calculate properly. false if there is a problem.
     */
    public boolean deltaIsCalculated() {
        deltaCalculate();
        return true;
    }

    /**
     * retrieve the internal calculated delta value.
     * @return the internal delta value in fractions of a second. EXP 10ms of delta would be 10/1000 of a second.
     */
    @Override
    public double getDelta() {
        return delta;
    }

    @Override
    public void waitForStart() {
        super.waitForStart();
        deltaCalculate();
    }

    @Override
    public void runOpMode() throws InterruptedException {}

    /**
     * only call once per iteration at the beginning.
     */
    private void deltaCalculate() {
        delta = Math.abs((System.nanoTime() - lastNS) / 1e+9);
        lastNS = System.nanoTime();;
    }
}
