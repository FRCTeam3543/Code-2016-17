package org.usfirst.frc3543.Team3543Robot.util.unused;

/**
 * Take a set of x,y Points and generate a Path then a Talon SRX MotionProfile.Point array
 * 
 * This generates the profiles for the left and right motors based on two points (start, dest)
 * 
 * This was an experiment, most of the details are commented out, and the class is abstract
 * as we never found a path generation algorithm that did what we wanted on-board the RIO
 * 
 * @see https://github.com/KHEngineering/SmoothPathPlanner
 */
public abstract class MotionProfilePlanGenerator {

	public static final double SEGMENTS_PER_INCH = 1/6;			// points every 6 inches
	private static final double DEFAULT_RIO_TIME_STEP = 0.1;	// seconds	
	
	private double segmentsPerInch = SEGMENTS_PER_INCH;
	private double timeStep = DEFAULT_RIO_TIME_STEP;
	
	public MotionProfilePlanGenerator() {
		this(SEGMENTS_PER_INCH, DEFAULT_RIO_TIME_STEP);
	}
	
	public MotionProfilePlanGenerator(double segementsPerInch, double timeStep) {
		this.segmentsPerInch = segementsPerInch;
		this.timeStep = timeStep;
	}
	
	public MotionProfilePlan generate(XYTPoint from, XYTPoint to, double totalTime) {
		return generate(new XYTPoint[] {
				from, to
		}, totalTime);
	}

	public abstract MotionProfilePlan generate(XYTPoint[] waypoints, double totalTime);
	
	public double getSegmentsPerInch() {
		return segmentsPerInch;
	}

	public void setSegmentsPerInch(double segmentsPerInch) {
		this.segmentsPerInch = segmentsPerInch;
	}

	public double getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(double timeStep) {
		this.timeStep = timeStep;
	}
	
}
