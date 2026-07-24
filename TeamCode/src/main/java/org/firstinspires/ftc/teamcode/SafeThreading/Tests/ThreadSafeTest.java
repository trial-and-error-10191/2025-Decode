package org.firstinspires.ftc.teamcode.SafeThreading.Tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.SafeThreading.SafeThread;
import org.firstinspires.ftc.teamcode.SafeThreading.ThreadSafeOpMode;
import org.firstinspires.ftc.teamcode.SafeThreading.Tests.Threads.testThread;

@TeleOp(name = "Thread testing", group = "Test")
public class ThreadSafeTest extends ThreadSafeOpMode {

    SafeThread tel = new testThread(telemetry);

    @Override
    public void onStart() {
        begin_thread(tel);
    }

    @Override
    public void whileActive() {

    }
}
