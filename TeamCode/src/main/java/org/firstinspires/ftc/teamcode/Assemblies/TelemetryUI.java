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
    ArrayList<String> list = new ArrayList<>();
    HashMap<String, String> links = new HashMap<String, String>();
    String greenArtifact = "";
    String purpleArtifact = "";
    String emptySlot = "";

    // calculation values. //
    // Length unit : Meters //
    boolean canLand;
    float aprilTagHeight = 0;
    float finalHeight = 0;
    float initVel = 0;
    float initHeight = 0;
    float distance = 0;
    float acceleration = -9.8f;
    float initAngle = 0;

    public TelemetryUI(Telemetry telemetry) {
        this.telemetry = telemetry;
        telemetry.setCaptionValueSeparator("");
        telemetry.setItemSeparator("");

        list.add("blue");
        list.add("green");
        list.add("purple");

        links.put("green", greenArtifact);
        links.put("purple", purpleArtifact);
        links.put("", emptySlot);

        for (int i = 0; i < 20; i++) {
            lines.add(telemetry.addLine());
        }
    }

    @SuppressLint("DefaultLocale")
    public void update(ArrayList<Robot.Color> order) {
        // calculate if the ball can land in //
        finalHeight = initHeight;
        finalHeight += (float) (distance * (Math.tan(initAngle)));
        finalHeight += (float) ((1/2) * acceleration * ((distance * distance) / (initVel * initVel * Math.cos(initAngle) * Math.cos(initAngle))));
        Boolean heightCheck = finalHeight > 1.1 && finalHeight < 1.4;

        lines.get(0).addData("", String.format("%27s", "⌦")).addData(" ", heightCheck).addData(" ", "⌫");
        lines.get(1).addData(" test ", "\n" + " test 2");

        for (int i = 1; i < 4; i++) {
            lines.get((i * 3) - 2).addData(String.format("%27s", ""), "┌─────┐");
            lines.get((i * 3) - 1).addData(String.format("%27s", ""), "│" + list.get(i - 1) + "    │");
            lines.get((i * 3) - 0).addData(String.format("%27s", ""), "└─────┘");
        }





        // push final product to the DS //
        telemetry.update();
    }
}
