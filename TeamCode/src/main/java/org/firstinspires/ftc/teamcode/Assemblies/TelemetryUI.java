package org.firstinspires.ftc.teamcode.Assemblies;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.HashMap;

public class TelemetryUI {

    // telemetry object to write too //
    Telemetry telemetry;
    ArrayList<Telemetry.Line> lines = new ArrayList<>();

    // robot object for use //
    Robot robot;

    // storage for ball types //
    HashMap<String, String> spacelinks = new HashMap<String, String>();
    String greenArtifact = "    │";
    String purpleArtifact = "   │";
    String emptySlot = "   │";

    /** calculation values.
        Length unit : Meters
        Time unit: Seconds **/
    boolean canLand;
    float aprilTagHeight = 0;
    float finalHeight = 0;
    float initVel = 0;
    float initHeight = 0;
    float distance = 0;
    float acceleration = -9.8f;
    float initAngle = 0;
    float wheelRadius = 0;

    public TelemetryUI(Telemetry telemetry, Robot robot) {
        this.telemetry = telemetry;
        telemetry.setCaptionValueSeparator("");
        telemetry.setItemSeparator("");

        spacelinks.put("Green", greenArtifact);
        spacelinks.put("Purple", purpleArtifact);
        spacelinks.put("Empty", emptySlot);

        for (int i = 0; i < 20; i++) {
            lines.add(telemetry.addLine());
        }

        initVel = (float) ((robot.wheels.dualRPM / 60) * wheelRadius);
    }

    @SuppressLint("DefaultLocale")
    public void update(ArrayList<Robot.Color> order) {
        // calculate if the ball can land in //
        finalHeight = initHeight;
        finalHeight += (float) (distance * (Math.tan(initAngle)));
        finalHeight += (float) ((1/2) * acceleration * ((distance * distance) / (initVel * initVel * Math.cos(initAngle) * Math.cos(initAngle))));
        Boolean heightCheck = finalHeight > 1.1 && finalHeight < 1.4;

        lines.get(0).addData("", String.format("%27s", "⌦")).addData(" ", heightCheck).addData(" ", "⌫");

        for (int i = 1; i < 4; i++) {
            lines.get((i * 3) - 2).addData(String.format("%27s", ""), "┌─────┐");
            lines.get((i * 3) - 1).addData(String.format("%27s", ""), "│" + order.get(i - 1).stringOf + spacelinks.get(order.get(i - 1).stringOf));
            lines.get((i * 3) - 0).addData(String.format("%27s", ""), "└─────┘");
        }





        // push final product to the DS //
        telemetry.update();
    }
}
