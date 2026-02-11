package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RPMlaunchWheels {

    // Define wheels
    public DcMotor left;
    public DcMotor right;

    // Define telemetry
    Telemetry telemetry;

    // Define restrictive variables
    private float changeBy = 0.0003f;

    public double rpmTarget = 0;
    public double rpmMultiplier = 1;

    private int rpmLeniency = 60;
    double leftRpm = 0;
    private double rightRpm = 0;
    public double dualRPM = 1.0;
    private double lastKnownMS = 0;
    private double lastKnownEncL = 0;
    private double lastKnownEncR = 0;
    private boolean cataclysmicError = false;

    // Start motors with minimum movement power (0.13 minimum required power)
    private double rightPower = 0.3;
    private double leftPower = 0.3;
    private double dualPower = 0.3;


    // Define fixed values
    private double encodersPerRevolution = 28;

    // Define the Elapsed time for checking RPM
    public ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public RPMlaunchWheels(HardwareMap hwMap, Telemetry telemetry) {
        // Valuate telemetry and wheels
        this.telemetry = telemetry;
        left = hwMap.get(DcMotor.class, "LaunchWheel1");
        right = hwMap.get(DcMotor.class, "LaunchWheel2");

        // Reverse the wheels to obtain the desired direction
        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.FORWARD);

    }

    /**
     * performs the wheel logic every iteration
     */
    public void wheelsTick() {

        // Wait to get accurate RPM
        if (runTime.milliseconds() > lastKnownMS + 80) {

            // Set initial power equalization's
            if (!(leftRpm > (rpmTarget * rpmMultiplier) - rpmLeniency && leftRpm < (rpmTarget * rpmMultiplier) + rpmLeniency)) {
                leftPower += leftRpm > (rpmTarget * rpmMultiplier) + rpmLeniency ? -changeBy * ((Math.abs(leftRpm - (rpmTarget * rpmMultiplier))) / 10) : 0;
                leftPower += leftRpm < (rpmTarget * rpmMultiplier) - rpmLeniency ? changeBy * ((Math.abs(leftRpm - (rpmTarget * rpmMultiplier))) / 10) : 0;
                leftPower = Math.max(-1, Math.min(1, leftPower));
            }
            if (!(rightRpm > (rpmTarget * rpmMultiplier) - rpmLeniency && rightRpm < (rpmTarget * rpmMultiplier) + rpmLeniency)) {
                rightPower += rightRpm > (rpmTarget * rpmMultiplier) ? -changeBy * ((Math.abs(rightRpm - (rpmTarget * rpmMultiplier))) / 10): 0;
                rightPower += rightRpm < (rpmTarget * rpmMultiplier) ? changeBy * ((Math.abs(rightRpm - (rpmTarget * rpmMultiplier))) / 10) : 0;
                rightPower = Math.max(-1, Math.min(1, rightPower));
            }

            // Set dual power equalization
            if (!(dualRPM > (rpmTarget * rpmMultiplier) - rpmLeniency && dualRPM < (rpmTarget * rpmMultiplier) + rpmLeniency)) {
                dualPower += dualRPM > (rpmTarget * rpmMultiplier) ? -changeBy * ((Math.abs(dualRPM - (rpmTarget * rpmMultiplier))) / 10): 0;
                dualPower += dualRPM < (rpmTarget * rpmMultiplier) ? changeBy * ((Math.abs(dualRPM - (rpmTarget * rpmMultiplier))) / 10) : 0;
                dualPower = Math.max(-1, Math.min(1, dualPower));
            }

            // Final power equalization
            left.setPower(cataclysmicError ?  dualPower : leftPower);
            right.setPower(cataclysmicError ? dualPower : rightPower);

            leftRpm =  (((left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));
            rightRpm = (((right.getCurrentPosition() - lastKnownEncR) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));
            dualRPM = (leftRpm + rightRpm) / 2;

            lastKnownMS = runTime.milliseconds();
            lastKnownEncL = left.getCurrentPosition();
            lastKnownEncR = right.getCurrentPosition();

            cataclysmicError = (Math.abs(leftRpm - rightRpm) / (rpmTarget * rpmMultiplier) * 100) > 20;
        }
    }

    /**
     * set a new rpm goal
     * @param newRPM the new rpm goal
     */
    public void rpmReset(int newRPM) {
        rpmTarget = newRPM;
    }

    /**
     * retrieve the left rpm
     * @return the left rpm
     */
    public double rpmL() {
        return leftRpm;
    }

    /**
     * retrieve the right rpm
     * @return the right rpm
     */
    public double rpmR() {
        return  rightRpm;
    }

    /**
     * directly set the motor powers (will cause issues if used in tandem with wheelsTick())
     * @param leftP new power for the left motor
     * @param rightP new power for the right motor
     */
    public void setMotorPowers(double leftP, double rightP) {
        left.setPower(leftP);
        right.setPower(rightP);
        leftPower = leftP;
        rightPower = rightP;
        dualPower = (leftP + rightP) / 2;
    }

    /**
     * experimental ability to set the power to a specific rpm based off of empirically calculated data
     * @param RPM the expected rpm to achieve
     */
    public void setPowerByRpm(double RPM) {
        double judgedPower = (double) (rpmTarget * rpmMultiplier) / 6000;
        setMotorPowers(judgedPower, judgedPower);
    }

    /**
     * calculate the accuracy of the RPM to the target (exp target = 1000 actual = 800. accuracy = 80 = 80%)
     * @return the accuracy
     */
    public double calculateRpmAccuracy() {
        double accuracy = cataclysmicError ? dualRPM / (rpmTarget * rpmMultiplier) : ((leftRpm + rightRpm) / 2) / (rpmTarget * rpmMultiplier);
        accuracy *= 100;

        return accuracy;
    }
}