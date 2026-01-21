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
    private float changeBy = 0.0003f;
    public double rpmTarget = 3300;
    private int rpmLeniency = 60;
    double leftRpm = 0;
    private double rightRpm = 0;
    private double dualRPM = 1.0;
    private double lastKnownMS = 0;
    private double lastKnownEncL = 0;
    private double lastKnownEncR = 0;
    private boolean cataclysmicError = false;

    // start motors with minimum movement power (0.13 minimum required power)
    private double rightPower = 0.3;
    private double leftPower = 0.3;
    private double dualPower = 0.3;


    // define fixed values
    private double encodersPerRevolution = 28;

    // define the Elapsed time for checking RPM
    public ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public RPMlaunchWheels(HardwareMap hwMap, Telemetry telemetry) {
        // valuate telemetry and wheels
        this.telemetry = telemetry;
        left = hwMap.get(DcMotor.class, "LaunchWheel1");
        right = hwMap.get(DcMotor.class, "LaunchWheel2");

        // reverse the right wheel to obtain the desired direction
        left.setDirection(DcMotor.Direction.REVERSE);
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

            // set dual power equalization
            if (!(dualRPM > rpmTarget - rpmLeniency && dualRPM < rpmTarget + rpmLeniency)) {
                dualPower += dualRPM > rpmTarget ? -changeBy * ((Math.abs(dualRPM - rpmTarget)) / 10): 0;
                dualPower += dualRPM < rpmTarget ? changeBy * ((Math.abs(dualRPM - rpmTarget)) / 10) : 0;
                dualPower = Math.max(-1, Math.min(1, dualPower));
            }

            // final power equalization
            left.setPower(cataclysmicError ?  dualPower : leftPower);
            right.setPower(cataclysmicError ? dualPower : rightPower);

            leftRpm =  (((left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));
            rightRpm = (((right.getCurrentPosition() - lastKnownEncR) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));
            dualRPM = (leftRpm + rightRpm) / 2;

            lastKnownMS = runTime.milliseconds();
            lastKnownEncL = left.getCurrentPosition();
            lastKnownEncR = right.getCurrentPosition();

            cataclysmicError = (Math.abs(leftRpm - rightRpm) / rpmTarget * 100) > 20;
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
        leftPower = leftP;
        rightPower = rightP;
        dualPower = (leftP + rightP) / 2;
    }

    public void setPowerByRpm(double RPM) {
        double judgedPower = (double) rpmTarget / 6000;
        setMotorPowers(judgedPower, judgedPower);
    }
}