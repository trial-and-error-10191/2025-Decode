package org.firstinspires.ftc.teamcode.Delta;

/**
 * Basic class meant to be extended from.
 */
abstract class Delta {

    // internal delta value //
    protected double delta = 0;

    /**
     * creates an object of the best implementation of delta.
     */
    Delta() {}

    /**
     * Base function for retrieving delta.
     * @return the time the last frame ran for. in decimal seconds, EXP.. half a second since the last frame would be 0.5
     */
    double getDelta() {
        return delta;
    }
}
