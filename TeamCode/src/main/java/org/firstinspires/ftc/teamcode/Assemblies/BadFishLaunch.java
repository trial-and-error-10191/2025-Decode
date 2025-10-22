package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

@Disabled
public class BadFishLaunch {
    boolean Spin = false;
    boolean Fall = false;
    boolean lastInput = false;

    DcMotor bandIntake;
    DcMotor wheelLaunch;
    DcMotor ferrisRotate;
    CRServo ballHold;

    public final double ROTATE_POWER = 0.75;   // Motor power for lift rotation
    int maxRotatePos = -2356;                  // max encoder counter for lift rotation
    int rotatePos;                         // Encoder counter for lift rotation

    public BadFishLaunch(HardwareMap hwMap) {
//        bandIntake = hwMap.get(DcMotor.class, "band_Intake");
//        wheelLaunch = hwMap.get(DcMotor.class, "wheel_Launch");
//        ballHold = hwMap.get(CRServo.class, "ball_Hold");
//
//        ferrisRotate = hwMap.get(DcMotor.class, "ferris_Rotate");
        ferrisRotate.setDirection(DcMotor.Direction.FORWARD);
        rotatePos = ferrisRotate.getCurrentPosition();
        ferrisRotate.setTargetPosition(rotatePos);
        ferrisRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ferrisRotate.setPower(ROTATE_POWER);

    }

    public void Intake(boolean intakeSpin) {
        if (!lastInput && intakeSpin) {
            Spin = !Spin;
            if (Spin) {
                bandIntake.setPower(1);
            } else {
                bandIntake.setPower(-1);
            }
        }
        lastInput = intakeSpin;
    }

    public void Yeet() {
        wheelLaunch.setPower(1);
    }

    public void Rotate(float turn) {
        float rotatePower = 0.0f;
        if (Math.abs(turn) > 0.05f) {
            rotatePower = turn;
        }
        rotatePos = ferrisRotate.getCurrentPosition();

        // Guard against rotating too far forward
        if (ferrisRotate.getCurrentPosition() <= maxRotatePos && turn < 0) {
            rotatePower = 0;
        }
        else if (rotatePos >= -200 && rotatePower > 0) {
            rotatePower *= 0.5f;
        }
        ferrisRotate.setPower(rotatePower);
    }

    public void ballHoldRotate(float rotate, boolean slow) {
        float rotatePower = 0.0f;
        if (Math.abs(rotate) > 0.05f) {
            rotatePower = rotate;
        }
        rotatePos = ferrisRotate.getCurrentPosition();
        ferrisRotate.setPower(rotatePower);

        if (!lastInput && slow) {
            Fall = !Fall;
            if (Fall) { // Makes a ball fall through the top hole
                ballHold.setPower(0.5);
            } else { // Makes a ball not fall through the top hole
                bandIntake.setPower(1);
            }
        }
        lastInput = slow;
    }
}
