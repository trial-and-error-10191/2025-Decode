package org.firstinspires.ftc.teamcode.SafeThreading.Tests.Threads;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SafeThreading.SafeThread;

public class testThread extends SafeThread {

    // telemetry object
    Telemetry telemetry = null;

    public testThread(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void Logic() {
        telemetry.addData("thread", "isactive!");
        telemetry.update();
    }

}
