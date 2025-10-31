//package org.firstinspires.ftc.teamcode.Autonomous;
//
//import android.util.Size;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//
//import org.firstinspires.ftc.vision.VisionPortal;
//import org.firstinspires.ftc.vision.opencv.ImageRegion;
//import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;
//
//@Disabled
//public class BallDetect {
//    PredominantColorProcessor colorSensor = new PredominantColorProcessor.Builder()
//            .setRoi(ImageRegion.asUnityCenterCoordinates(-0.1, 0.1, 0.1, -0.1))
//            .setSwatches(
//                    PredominantColorProcessor.Swatch.RED,
//                    PredominantColorProcessor.Swatch.BLUE,
//                    PredominantColorProcessor.Swatch.YELLOW,
//                    PredominantColorProcessor.Swatch.BLACK,
//                    PredominantColorProcessor.Swatch.WHITE)
//            .build();
//
//    /*
//            * Build a vision portal to run the Color Sensor process.
//            *
//            *  - Add the colorSensor process created above.
//            *  - Set the desired video resolution.
//            *      Since a high resolution will not improve this process, choose a lower resolution that is
//            *      supported by your camera.  This will improve overall performance and reduce latency.
//            *  - Choose your video source.  This may be
//            *      .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))  .....   for a webcam
//            *  or
//            *      .setCamera(BuiltinCameraDirection.BACK)    ... for a Phone Camera
//            */
//    VisionPortal portal = new VisionPortal.Builder()
//            .addProcessor(colorSensor)
//            .setCameraResolution(new Size(320, 240))
//            .build();
//}