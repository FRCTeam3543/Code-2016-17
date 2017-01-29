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
	
	public static final String OPENCV_LIBRARY_VERSION = "opencv_java310";
}
