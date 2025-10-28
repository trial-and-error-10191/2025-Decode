package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RPMlaunchWheels {

    // define wheels
    public DcMotor left;
    public DcMotor right;

    // define telemetry
    Telemetry telemetry;

    // define restrictive variables
    float changeBy = 0.0001f;
    public int rpmTarget = 4000;
    int rpmLeniency = 60;
    double leftRpm = 0;
    public double rightPower = 0;
    public double lastKnownMS = 0;
    public double lastKnownEncL = 0;
    public double lastKnownEncR = 0;

    // start motors with minimum movement power
    public double rightRpm = 0.13;
    public double leftPower = 0.13;


    // define fixed values
    double encodersPerRevolution = 28;

    // define the Elapsed time for checking RPM
    public ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public RPMlaunchWheels(Telemetry telemetry, HardwareMap hwMap) {
        // valuate telemetry and wheels
        this.telemetry = telemetry;
        left = hwMap.get(DcMotor.class, "Lwheel");
        right = hwMap.get(DcMotor.class, "Rwheel");

        // reverse the right wheel to obtain the desired direction
        right.setDirection(DcMotor.Direction.REVERSE);

    }

    // tick the wheels forward
    public void wheelsTick() {

                // wait to get accurate RPM
                if (runTime.milliseconds() > lastKnownMS + 80) {

                    // set initial power equalization's
                    if (!(leftRpm > rpmTarget - rpmLeniency && leftRpm < rpmTarget + rpmLeniency)) {
                        leftPower += leftRpm > rpmTarget + rpmLeniency ? -changeBy * ((Math.abs(leftRpm - rpmTarget)) / 10) : 0;
                        leftPower += leftRpm < rpmTarget - rpmLeniency ? changeBy * ((Math.abs(leftRpm - rpmTarget)) / 10) : 0;
                        leftPower = Math.max(-1, Math.min(1, leftPower));
                    }
                    if (!(rightRpm > rpmTarget - rpmLeniency && rightRpm < rpmTarget + rpmLeniency)) {
                        rightPower += rightRpm > rpmTarget ? -changeBy * ((Math.abs(rightRpm - rpmTarget)) / 10): 0;
                        rightPower += rightRpm < rpmTarget ? changeBy * ((Math.abs(rightRpm - rpmTarget)) / 10) : 0;
                        rightPower = Math.max(-1, Math.min(1, rightPower));
                    }

                    // final power equalization
                    left.setPower(leftPower);
                    right.setPower(rightPower);

                    leftRpm =  (((left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));
                    rightRpm = (((right.getCurrentPosition() - lastKnownEncR) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));

                    lastKnownMS = runTime.milliseconds();
                    lastKnownEncL = left.getCurrentPosition();
                    lastKnownEncR = right.getCurrentPosition();
                }
    }

    // alter target RPM
    public void rpmReset(int newRPM) {
        rpmTarget = newRPM;
    }

    // functions for retrieving left and right RPM
    public double rpmL() {
       return leftRpm;
    }
    public double rpmR() {
        return  rightRpm;
    }

    // phillips stupid idea
    public void setMotorPowers(double leftP, double rightP) {
        left.setPower(leftP);
        right.setPower(rightP);
    }
}
