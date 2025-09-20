package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class launchWheels {

    // define wheels
    DcMotor left;
    DcMotor right;

    // define telemetry
    Telemetry telemetry;

    // define restrictive variables
    int rpmTarget = 10;
    int rpmLeniancy = 1;
    double leftRpm = 0;
    double rightRpm = 0;
    double leftPower = 0;
    double rightPower = 0;
    AtomicBoolean offFlag = new AtomicBoolean(true);

    // define fixed values
    double encodersPerRevolution = 537.6;

    // define the Elapsed time for checking RPM
    ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public launchWheels(Telemetry telemetry, HardwareMap hwMap) {
        // valuate telemetry and wheels
        this.telemetry = telemetry;
        left = hwMap.get(DcMotor.class, "Lwheel");
        right = hwMap.get(DcMotor.class, "Rwheel");
    }



    // anonymous runnable class for parallelization
    Thread wheels = new Thread(new Runnable() {

        double lastKnownMS = 0;
        double lastKnownEncL = 0;
        double lastKnownEncR = 0;

        @Override
        public void run() {
            while (offFlag.get()) {

                // set initial power equalization's
                if (!(leftRpm > rpmTarget - rpmLeniancy && leftRpm < rpmTarget + rpmLeniancy)) {
                    leftPower += leftRpm > rpmTarget + rpmLeniancy ? -0.1 : 0;
                    leftPower += leftRpm < rpmTarget - rpmLeniancy ? 0.1 : 0;
                }
                if (!(rightRpm > rpmTarget - rpmLeniancy && rightRpm < rpmTarget + rpmLeniancy)) {
                    rightPower += rightRpm > rpmTarget ? -0.1 : 0;
                    rightPower += rightRpm < rpmTarget ? 0.1 : 0;
                }

                // final power equalization
                left.setPower(leftPower);
                right.setPower(rightPower);

                // wait to get accurate RPM
                if (runTime.milliseconds() > lastKnownMS + 1000) {
                    leftRpm =  (((left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution) / (60000 / (runTime.milliseconds() - lastKnownMS)));
                    rightRpm = (((right.getCurrentPosition() - lastKnownEncR) / encodersPerRevolution) / (60000 / (runTime.milliseconds() - lastKnownMS)));

                    telemetry.addData("lrpm", (left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution);
                    telemetry.addData("rrpm", (right.getCurrentPosition() - lastKnownEncR) / encodersPerRevolution);
                    telemetry.addData("pos", right.getCurrentPosition());
                    telemetry.update();

                    lastKnownMS = runTime.milliseconds();
                    lastKnownEncL = left.getCurrentPosition();
                    lastKnownEncR = right.getCurrentPosition();
                }
            }
        }
    });


    // begin the wheels
    public void begin() {
        wheels.start();
    }

    public void terminateProcess() throws InterruptedException {
        offFlag.set(false);
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
}
