package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Array;
import java.util.ArrayList;

//@Disabled
public class ArtifactPaddles {

    /* create the servo */
    CRServo paddles;

    /* create the sensor */
    public TouchSensor sensor;

    /* variable for storing the queued pass amounts and pass lock */
    int queuedMovements = 0;
    boolean moveCooldown = false;
    boolean forward = false;
    double lastMS = 0;
    double paddlePower = 0.8;

    /* time for iteration limitation */
    ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public ArtifactPaddles(HardwareMap hwMap, Telemetry telemetry) {
        paddles = hwMap.get(CRServo.class, "paddles");
        sensor = hwMap.get(TouchSensor.class, "MagneticSwitch");
    }

    // function to rotate by unit amount
    public void IteratePaddles() {
        if (queuedMovements == 0) {
            moveCooldown = false;
            paddles.setPower(0);
        } else {
            paddles.setPower(forward  ? paddlePower : -paddlePower);
        }
        if (sensor.isPressed()) {
            if (runTime.milliseconds() > lastMS + 500) {
                queuedMovements--;
                queuedMovements = Math.max(0, queuedMovements);
            }
        }
    }

    public void QueueNoCooldown(int state, boolean forward) {
        state = Math.max(0, Math.min(state, 3));
        queuedMovements = state;
        this.forward = forward;
    }

    public void QueueCooldowns(int state, boolean forward) {
        if (!moveCooldown) {
            runTime.reset();
            lastMS = 0;
            state = Math.max(0, Math.min(state, 3));
            queuedMovements = state;
            moveCooldown = true;
            this.forward = forward;
        }
    }
    // autonomous function -_-
    public void AutoRot(int state, boolean forward, ArrayList<Robot.Color> order) {
        QueueCooldowns(state, forward);

        while (queuedMovements > 0) {
            IteratePaddles();
            // Changes the order of the balls in the code for telemetry purposes
            Robot.Color valueHold = order.get(0);
            order.set(0, order.get(1));
            order.set(1, order.get(2));
            order.set(2, valueHold);
        }
    }
}