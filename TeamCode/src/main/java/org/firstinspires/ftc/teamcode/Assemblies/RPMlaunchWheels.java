package org.firstinspires.ftc.teamcode.Assemblies;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RPMlaunchWheels {

    // define wheels
    public DcMotor MainMotor1;
    public DcMotor MainMotor2;

    // define telemetry
    Telemetry telemetry;

    // define restrictive variables
    float changeBy = 0.0001f;
    public int rpmTarget = 60; // Change back to 4,000 when done testing
    int rpmLeniency = 60;
    public double RPM = 0;
    public double lastKnownMS = 0;
    public double lastKnownEncC = 0;
    boolean cataclysmicError = false;

    // start motors with minimum movement power
    public double Power = 0.13;


    // define fixed values
    double encodersPerRevolution = 28;

    // define the Elapsed time for checking RPM
    public ElapsedTime runTime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    public RPMlaunchWheels(HardwareMap hwMap, Telemetry telemetry) {
        // valuate telemetry and wheels
        this.telemetry = telemetry;
        MainMotor1 = hwMap.get(DcMotor.class, "LaunchWheel1");
        MainMotor2 = hwMap.get(DcMotor.class, "LaunchWheel2");


        // reverse the MainMotor wheel to obtain the desired direction
        MainMotor1.setDirection(DcMotor.Direction.REVERSE);
        MainMotor2.setDirection(DcMotor.Direction.REVERSE);

        MainMotor1.setZeroPowerBehavior(FLOAT);
        MainMotor2.setZeroPowerBehavior(FLOAT);

        runTime.reset();
    }

    // tick the wheels forward
    public void wheelsTick() {
                // wait to get accurate RPM
                if (runTime.milliseconds() > lastKnownMS + 80) {

                    // set dual power equalization
                    if (!(RPM > rpmTarget - rpmLeniency && RPM < rpmTarget + rpmLeniency)) {
                        Power += RPM > rpmTarget ? -changeBy * ((Math.abs(RPM - rpmTarget)) / 10): 0;
                        Power += RPM < rpmTarget ? changeBy * ((Math.abs(RPM - rpmTarget)) / 10) : 0;
                        Power = Math.max(-1, Math.min(1, Power));
                    }

                    MainMotor1.setPower(Power);
                    MainMotor2.setPower(Power);

                    RPM = (((MainMotor2.getCurrentPosition() - lastKnownEncC) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));

                    lastKnownMS = runTime.milliseconds();
                    lastKnownEncC = MainMotor2.getCurrentPosition();
                }
    }

    // alter target RPM
    public void rpmReset(int newRPM) {
        rpmTarget = newRPM;
    }
}
