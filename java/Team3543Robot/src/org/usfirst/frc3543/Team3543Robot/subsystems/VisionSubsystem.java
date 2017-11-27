// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot.subsystems;

import java.util.logging.Logger;

import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import ttfft.vision.GearDrop;
import ttfft.vision.GearDropPipeline;
import ttfft.vision.GripPipeline;

/**
 * Vision subsystem encapsulates the gear drop detection vision pipeline
 * 
 * Note - we were originally running the vision code on a background thread, but
 * it seems to perform OK on the main thread so we eliminated the complexity.
 * 
 * @author MK
 * @see ttfft.vision
 * 
 */
public class VisionSubsystem extends Subsystem {
	public static final Logger LOGGER = Logger.getLogger(VisionSubsystem.class.getSimpleName());
		
	// The object to synchronize on to make sure the vision thread doesn't
    // write to variables the main thread is using.
    public final Object visionLock = new Object();

    private AxisCamera camera = null;
    private CvSink sink = null;
    private GearDropPipeline gearDropPipeline;
    private int visionFailures = 0;
    protected static final int MAX_VISION_FAILURES = 2000;
    private AxisCamera camera;
    private CvSink sink;
    private GearDropPipeline gearDropPipeline;
    
	public VisionSubsystem() {
		super();
	}
		
	public GearDrop detectGearDrop() {
		synchronized(visionLock) {
//			Robot.log("Grabbing an image");
			Mat image = new Mat();
			// not well-coded but we need to stop issues of camera 
			// failing causing teleop to fail
			if (sink.grabFrame(image) == 0) { // 640 by 480
				Robot.log("Image fetch failed: "+sink.getError());
				visionFailures++;
				return null;
			}
			gearDropPipeline.process(image);		
			MatOfKeyPoint points = gearDropPipeline.findBlobsOutput();
			// Leaving this in for now, plots the detected gear drop on the image
			for (KeyPoint point: points.toList()) {
				// original image is twice as big as the processed one
				Imgproc.circle(image, new Point(point.pt.x * 2, point.pt.y * 2), Math.round(point.size * 2), new Scalar(255,255,200));
			}
			// but for now we're not writing it anywhere
//			outputStream.putFrame(image);
			GearDrop gd = gearDropPipeline.detectGearDropOutput();
			return gd;
		}
	}
	
	public void init() {
		// try/catch here
		camera = CameraServer.getInstance().addAxisCamera(RobotMap.AXIS_CAMERA_HOST);		
		camera.setResolution(640, 480);		
		sink = CameraServer.getInstance().getVideo();
		gearDropPipeline = new GearDropPipeline();
	}
	
	/**
	 * Resets the foundGearDrop
	 */
	public void reset() {
		
	}
		
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
        
}

