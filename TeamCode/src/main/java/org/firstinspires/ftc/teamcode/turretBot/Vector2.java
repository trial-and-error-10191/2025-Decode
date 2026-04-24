package org.firstinspires.ftc.teamcode.turretBot;

public class Vector2 {
    // x and y storage //
    float x;
    float y;

    /**
     * basic constructor for first values
     * @param x
     * @param y
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor for a single value, creates one vector with both values
     * @param value the value
     */
    public Vector2(float value) {
        this.x = value;
        this.y = value;
    }

    /**
     * multiply the current Vector by the quotient
     * @param quotient vector to be applied to the current vector
     */
    public void mul(Vector2 quotient) {
        x *= quotient.x;
        y *= quotient.y;
    }

    /**
     * shrink or glow the vector by a flat value
     * @param multiple factor by which to shrink or grow the vector
     */
    public void mul(float multiple) {
        x *= multiple;
        y *= multiple;
    }

    /**
     * add two vectors together
     * @param additive vector to add into this one
     */
    public void add(Vector2 additive) {
        x += additive.x;
        y += additive.y;
    }
}
