package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class EscarGOMech {
    boolean lastInput = false;
    CRServo intakeFlywheel;
    Servo wallServo;
    static final double openPos = 0.8;   // Open position for the wall servo
    static final double closePos = 0.47; // Closed position for the wall servo
    public DcMotor launchFlywheel;

    public EscarGOMech(HardwareMap hwMap, Telemetry telemetry) {
        launchFlywheel = hwMap.get(DcMotor.class, "launchFlywheel");
        launchFlywheel.setDirection(DcMotor.Direction.REVERSE);
        launchFlywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launchFlywheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intakeFlywheel = hwMap.get(CRServo.class, "intakeFlywheel");

        wallServo = hwMap.get(Servo.class, "wallServo");
    }

    public void BallIntake (boolean intakeSpin) {
        if (!lastInput && intakeSpin) {
            intakeFlywheel.setPower(1);
        } else {
            launchFlywheel.setPower(0);
        }
    }

    public void WallStop(boolean open) {
        if (!lastInput && open) {
            wallServo.setPosition(openPos);
        } else {
            wallServo.setPosition(closePos);
        }
        lastInput = open;
    }

    public void WheelLaunch() {
        launchFlywheel.setPower(1);
    }

    public void initRotateByPower() {
        launchFlywheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}