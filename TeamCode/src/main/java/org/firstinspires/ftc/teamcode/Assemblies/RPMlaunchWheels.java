package org.firstinspires.ftc.teamcode.Assemblies;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RPMlaunchWheels {

    // define wheels
    public DcMotor MainMotor;

    // define telemetry
    Telemetry telemetry;

    // define restrictive variables
    float changeBy = 0.0001f;
    public int rpmTarget = 4000;
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
        MainMotor = hwMap.get(DcMotor.class, "LaunchWheel");

        // reverse the MainMotor wheel to obtain the desired direction
        MainMotor.setDirection(DcMotor.Direction.REVERSE);

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

                    MainMotor.setPower(Power);

                    RPM = (((MainMotor.getCurrentPosition() - lastKnownEncC) / encodersPerRevolution) * (60000 / (runTime.milliseconds() - lastKnownMS)));

                    lastKnownMS = runTime.milliseconds();
                    lastKnownEncC = MainMotor.getCurrentPosition();

                }


    }

    // alter target RPM
    public void rpmReset(int newRPM) {
        rpmTarget = newRPM;
    }
}
