package ttfft.vision;

public class Settings implements Cloneable {
	public int outputImageWidth = 320;
	public int outputImageHeight = 200;
	
	public int inputImageWidth = 640;
	public int inputImageHeight = 480;

	public double targetAngle = 10.0/180 * Math.PI;
	public double targetSizeDiff = 0.8; // 80% same size
	public double targetRelativeDistance = 1.9; // distance between centers as percentage of average diameter
	public long targetCenterSpan = 90; // TODO pixels, move to RobotMap or some vision constants file
	
	/// Statics
	public static final String OPENCV_LIBRARY_VERSION = "opencv_java310";
	/**
	 * Distance between tape centers in M
	 */
	public static final double CENTER_SPAN_IN_M = 7.5 * 25.4 / 1000; 	// 25.4 mm per inch, span is 7.5in
	
	/**
	 * Tape height in M
	 */
	public static final double BLOB_DIAMETER_IN_M = 5 * 25.4 / 1000;		// tape is 5in long

	/**
	 * Field of view angle, in radians (estimated as 37.5 degrees)
	 */
	public static final double FOV_ANGLE = 37.4 * Math.PI/180;
				
	/**
	 * Target distance from the gear drop face
	 * TODO - measure this
	 */
	public static final double TARGET_DISTANCE_IN_M = 0.4;	
	
	/**
	 * Target center span distance, in percentage of image width
	 * TODO - measure this using camera capture
	 */
	public static final double TARGET_CENTER_SPAN_IN_PERCENT = 0.5;	
}
