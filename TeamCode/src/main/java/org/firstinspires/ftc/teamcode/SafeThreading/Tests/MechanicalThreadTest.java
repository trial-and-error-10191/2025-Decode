package org.firstinspires.ftc.teamcode.SafeThreading.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.SafeThreading.SafeThread;
import org.firstinspires.ftc.teamcode.SafeThreading.Tests.Threads.ServoThread;
import org.firstinspires.ftc.teamcode.SafeThreading.ThreadSafeOpMode;

@TeleOp(name = "Mechanical Thread Testing", group = "Test")
public class MechanicalThreadTest extends ThreadSafeOpMode {

    // servos //
    Servo one = null;
    Servo two = null;

    SafeThread servo_control_1 = new ServoThread(one, 10, telemetry);
    SafeThread servo_control_2 = new ServoThread(two, 15, telemetry);

    @Override
    public void onStart() {
        one = hardwareMap.get(Servo.class, "servo1");
        two = hardwareMap.get(Servo.class, "servo2");
        begin_thread(servo_control_1);
    }

    @Override
    public void whileActive() {

    }
}
