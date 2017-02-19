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
 *  0.8 -1.5  
 *  0.5
 *  1.8
 */
public class VisionSubsystem extends Subsystem {
	public static final Logger LOGGER = Logger.getLogger(VisionSubsystem.class.getSimpleName());
	
	public static final double TARGET_ANGLE = 10/180 * Math.PI;
	public static final double TARGET_SIZE_DIFF = 0.7; // 90% same size
	public static final double TARGET_RELATIVE_DISTANCE = 1.75; // distance between centers as percentage of average diameter
	public static final long TARGET_CENTER_SPAN = 90; // TODO pixels, move to RobotMap or some vision constants file
	
	// image pipline - width/height
	public static final int IMAGE_WIDTH = 160;
	public static final int IMAGE_HEIGHT = 100;
	
	Thread visionThread;
	// The object to synchronize on to make sure the vision thread doesn't
    // write to variables the main thread is using.
    public final Object visionLock = new Object();

    // The pipeline outputs we want
    private boolean _running = true; // lets us know when the pipeline has actually run
    private GearDrop foundGearDrop = null;
   
    private AxisCamera camera;
    private CvSink sink;
    private GearDropPipeline gearDropPipeline;
        
    public boolean isRunning() {
    	return _running;
    }
    
	public VisionSubsystem() {
		super();
	}
	
	public boolean isGearDropDetected() {
		synchronized (visionLock) {
			return foundGearDrop != null;			
		}
	}
	
	public GearDrop getGearDrop() {
		synchronized (visionLock) {
			return foundGearDrop;			
		}
	}
	
	public GearDrop detectGearDrop() {
		synchronized(visionLock) {
//			Robot.log("Grabbing an image");
			Mat image = new Mat();
			if (sink.grabFrame(image) == 0) { // 640 by 480
				Robot.log(sink.getError());
			}
			gearDropPipeline.process(image);		
			MatOfKeyPoint points = gearDropPipeline.findBlobsOutput();
			for (KeyPoint point: points.toList()) {
				// original image is twice as big as the processed one
				Imgproc.circle(image, new Point(point.pt.x * 2, point.pt.y * 2), Math.round(point.size * 2), new Scalar(255,255,200));
			}
//			outputStream.putFrame(image);
			GearDrop gd = gearDropPipeline.detectGearDropOutput();


			return gd;
		}
	}
	
	public void init() {
		// adapted from https://wpilib.screenstepslive.com/s/4485/m/24194/l/674733-using-generated-code-in-a-robot-program
		// (which didn't really work)
		
		camera = CameraServer.getInstance().addAxisCamera(RobotMap.AXIS_CAMERA_HOST);		
		camera.setResolution(640, 480);
		
		sink = CameraServer.getInstance().getVideo();
//		CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);
		
		gearDropPipeline = new GearDropPipeline();		

		// We're not using a separate thread now, don't need it
//		visionThread = new Thread(() -> {
//			while (isRunning()) {
//				GearDrop gd = detectGearDrop();
//				if (gd != null) {
//					foundGearDrop = gd;				
//					OI.dashboard.putGearfinderLocation( gd != null ? String.format("%.1f m", gd.distanceFromTarget) : "NONE");						
//					undetectedCount = 0;
//				}
//				else {
//					if (undetectedCount++ > 5) {
//						OI.dashboard.putGearfinderLocation(gd != null ? String.format("%.1f m", gd.distanceFromTarget) : "NOPE");													
//						foundGearDrop = null;
//					}
//				}
//				try {
//					Thread.sleep(500);  //TODO take this out
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}				
//			}
//			
//		});
		
//		visionThread = new VisionThread((VideoSource)camera, new GripPipeline(), pipeline -> {
//			
//			// thread safety
//			synchronized (visionLock) {
//				Robot.log("VISION SUBSYSTEM - pipeline ran");
//				pipelineRan = true;
//				// detect the tape from the Mat				
//				MatOfKeyPoint points = pipeline.findBlobsOutput();
//				foundGearDrop = detectGearDrop(points);
//			}
//			try {
//				Thread.sleep(3000);  //TODO take this out
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
		_running = true;
//		visionThread.setDaemon(true);
//		visionThread.start();
//		Robot.log("THREAD STARTED");
	}
	
	/**
	 * Resets the foundGearDrop
	 */
	public void reset() {
		foundGearDrop = null;
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

