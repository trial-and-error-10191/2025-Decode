//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Assemblies.EscarGOMech;
//
//@Autonomous(name="IntakeAuto", group="Robot")
//public class IntakeAuto extends LinearOpMode {
//    EscarGOMech launcher;
//    private final ElapsedTime Time   = new ElapsedTime();
//    @Override
//    public void runOpMode() {
//        launcher = new EscarGOMech(hardwareMap, telemetry);
//        launcher.WheelLaunch();
//        launcher.AutoIntakeGrab();
//        Wait(2);
//        launcher.AutoIntakeLaunch();
//    }
//    public void Wait(double seconds) {
//        Time.reset();
//        while (Time.milliseconds()  < seconds * 1000) {
//            // doesn't need anything
//        } // end of while loop
//    } // end of public void Wait
//}
