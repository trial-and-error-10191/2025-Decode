package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArtifactPaddles {

    /* create the servo */
    CRServo paddles;

    /* parameter variables */
    int oneMS = 0;
    int onePower = 0;

    /* location variables */
    int State = 1;

    /* time */
    ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    ArtifactPaddles(Telemetry telemetry, HardwareMap hwMap) {
        paddles = hwMap.get(CRServo.class, "paddles");
    }


    public void setRot(int state) {
        double time = runTime.milliseconds();
        boolean pos = (this.State - state) > 0;
        while (Math.abs(this.State - state) * oneMS > runTime.milliseconds() - time) {
            paddles.setPower(onePower);
        }
    }
}
