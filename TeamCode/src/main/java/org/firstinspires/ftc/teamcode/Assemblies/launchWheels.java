package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class launchWheels {

    // define wheels
    DcMotor left;
    DcMotor right;

    // define telemetry
    Telemetry telemetry;

    // define restrictive variables
    int rpmTarget = 0;
    int rpmLeniancy = 10;
    int leftRpm = 0;
    int rightRpm = 0;
    double leftPower = 0;
    double rightPower = 0;

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

        @Override
        public void run() {

         // update the MS and ENC from the last iteration
         double lastKnownMS = runTime.milliseconds();
         double lastKnownEncL = left.getCurrentPosition();
         double lastKnownEncR = right.getCurrentPosition();

         // set initial power equalizations
         if (!(leftRpm > rpmTarget - rpmLeniancy && leftRpm < rpmTarget + rpmLeniancy)) {
            leftPower += leftRpm > rpmTarget + rpmLeniancy ? -0.1 : 0;
            leftPower += leftRpm < rpmTarget - rpmLeniancy ? 0.1 : 0;
         }
         if (!(rightRpm > rpmTarget - rpmLeniancy && rightRpm < rpmTarget + rpmLeniancy)) {
             rightPower += rightRpm > rpmTarget + rpmLeniancy ? -0.1 : 0;
             rightPower += rightRpm < rpmTarget - rpmLeniancy ? 0.1 : 0;
         }

         // final power equalization
         left.setPower(leftPower);
         right.setPower(rightPower);

         // get RPM
         leftRpm = (int) (((left.getCurrentPosition() - lastKnownEncL) / encodersPerRevolution) / (((runTime.milliseconds() - lastKnownMS) / 60000) * 100));
        }
    });

    // begin the wheels
    public void begin() {
        wheels.start();
    }

    // alter target RPM
    public void rpmReset(int newRPM) {
        rpmTarget = newRPM;
    }
}
