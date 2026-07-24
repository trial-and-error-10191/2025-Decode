package org.firstinspires.ftc.teamcode.SafeThreading.Tests.Threads;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SafeThreading.SafeThread;

public class ServoThread extends SafeThread {

    // servo //
    Servo servo = null;

    // wait time //
    int wait_time = 0;
    int i = 0;

    // telemetry //
    Telemetry telemetry = null;

    public ServoThread(Servo servo, int wait_time, Telemetry telemetry) {
        this.servo = servo;
        this.wait_time = wait_time;
        this.telemetry = telemetry;
    }

    @Override
    public void Logic() {
        while (i < 10000 || is_terminated()) {
            telemetry.addData("i", i);
            telemetry.update();
            i++;
        }
        terminateThread();
    }
}
