package org.firstinspires.ftc.teamcode.TeleOp.Useless;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Assemblies.ObeliskOrder;
import org.firstinspires.ftc.teamcode.Assemblies.Robot;

import java.util.ArrayList;

//@Disabled
//@Autonomous(name = "OrderTest", group = "Robot")
//public class OrderTest extends LinearOpMode {
//    @Override
//    public void runOpMode() {
//        ObeliskOrder obeliskOrder = new ObeliskOrder(telemetry);
//
//        waitForStart();
//        while (opModeIsActive()) {
//            int id = obeliskOrder.findTag();
//            ArrayList<Robot.Color> order = obeliskOrder.patternOrder();
//            telemetry.addData("Tag ID", id);
//            telemetry.addData("Pattern Order", order);
//            telemetry.update();
//        }
//    }
//}