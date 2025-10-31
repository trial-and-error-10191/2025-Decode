package org.firstinspires.ftc.teamcode.Assemblies;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryUI {

    // telemetry object to write too //
    Telemetry telemetry;

    TelemetryUI(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
String str = "\\" + "u" + Math.round(Math.random() * 4000);
    public void update() {
        telemetry.addData("\\" + "u" + Math.round(Math.random() * 4000), "/t");
    }
}
