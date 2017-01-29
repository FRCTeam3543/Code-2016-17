package ttfft.vision;

import org.opencv.*;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class GearDropPipelineTest {
	static {
		System.loadLibrary(Settings.OPENCV_LIBRARY_VERSION);
	}
	
	public static void main(String [] args) {
		// load an image
		String base = "D:\\Projects\\FRCTeam3543\\Code-2016-17\\java\\Capture";
		String img = base + ".png";
		String imgOut = base + "-out.png";
		String maskOut = base + "-mask.png";
		String thresholdOut = base + "-threshold.png";
		
		System.out.println("Load "+img);
		Mat m = Imgcodecs.imread(img);
	
		Settings settings = new Settings();
		settings.inputImageWidth = m.cols();
		settings.inputImageHeight = m.rows();
		GearDropPipeline pipeline = new GearDropPipeline(settings);
		pipeline.process(m);
		
		// detect
		
		GearDrop drop = pipeline.detectGearDropOutput();
		Imgcodecs.imwrite(imgOut, m);
		Imgcodecs.imwrite(maskOut, pipeline.maskOutput());
		Imgcodecs.imwrite(thresholdOut, pipeline.hsvThresholdOutput());
		if (drop != null) {
			System.out.println(String.format("FOUND IT AT: [%d, %d]", drop.gearDropPoint[0], drop.gearDropPoint[1]));
		}
		else {
			System.out.println("No blobs found");
		}
	}
}