package org.firstinspires.ftc.teamcode.Assemblies;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class TelemetryUI {

    // telemetry object to write too //
    Telemetry telemetry;

    // robot object for use //
    Robot robot;

    // storage for ball types //
    ArrayList<String> list = new ArrayList<>();

    // calcultion values //
    boolean canLand;
    float aprilTagHeight = 0;
    float finalHeight = 0;
    float initVel = 0;
    float initHeight = 0;
    float distance = 0;
    float acceleration = -9.8f;
    float initAngle = 0;

    TelemetryUI(Telemetry telemetry, Robot robot) {
        this.telemetry = telemetry;
        telemetry.setCaptionValueSeparator("");
        list.add("blue");
        list.add("green");
        list.add("purple");

        this.robot = robot;
    }

    public void update() {
        // calculate if the ball can land in //
        finalHeight = initHeight;
        finalHeight += (float) (distance * (Math.tan(initAngle)));
        finalHeight += (float) ((1/2) * acceleration * ((distance * distance) / (initVel * initVel * Math.cos(initAngle) * Math.cos(initAngle))));
        if (finalHeight > aprilTagHeight + 6 && finalHeight < aprilTagHeight + 12) {
            telemetry.addData("", true);
            canLand = true;
        } else {
            telemetry.addData("", false);
            canLand = false;
        }


        telemetry.addData("", "================================");
        telemetry.addData("", list.get(1) + "\t" + list.get(2) + "\t" + list.get(3) + "\t");
        telemetry.addData("", "================================");
    }
}
