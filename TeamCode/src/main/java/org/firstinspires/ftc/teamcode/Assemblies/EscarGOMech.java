package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//@Disabled
public class EscarGOMech {
    boolean lastInput = false;
    CRServo intakeFlywheelLeft;
    CRServo intakeFlywheelRight;
    Servo wallServo;
    public DcMotor launchFlywheel;
    final double closePos = 0.8;   // Open position for the wall servo
    final double openPos = 0.47; // Closed position for the wall servo

    public EscarGOMech(HardwareMap hwMap, Telemetry telemetry) {
        launchFlywheel = hwMap.get(DcMotor.class, "launchFlywheel");
        launchFlywheel.setDirection(DcMotor.Direction.REVERSE);

        intakeFlywheelLeft = hwMap.get(CRServo.class, "intakeFlywheelLeft");
        intakeFlywheelRight = hwMap.get(CRServo.class, "intakeFlywheelRight");

//        wallServo = hwMap.get(Servo.class, "wallServo");
    }

    public void BallIntake(boolean intakeSpin) {
        if (!lastInput && intakeSpin) {
//            wallServo.setPosition(closePos);
            intakeFlywheelLeft.setPower(-1);
            intakeFlywheelRight.setPower(1);
        } else {
//            wallServo.setPosition(openPos);
            intakeFlywheelLeft.setPower(0);
            intakeFlywheelRight.setPower(0);
        }
    }

    public void AutoIntakeGrab() {
//        wallServo.setPosition(closePos);
        intakeFlywheelLeft.setPower(-1);
        intakeFlywheelRight.setPower(1);
    }

    public void AutoIntakeLaunch() {
//        wallServo.setPosition(openPos);
        intakeFlywheelLeft.setPower(0);
        intakeFlywheelRight.setPower(0);
    }

    public void WheelLaunch() {
        launchFlywheel.setPower(1);
    }
}