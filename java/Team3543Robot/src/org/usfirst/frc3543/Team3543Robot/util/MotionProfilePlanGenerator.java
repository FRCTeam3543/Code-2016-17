package org.usfirst.frc3543.Team3543Robot.util;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotGeometry;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfile.PVTPoint;

import com.ctre.CANTalon;
//import com.team254.lib.trajectory.Path;
//import com.team254.lib.trajectory.PathGenerator;
//import com.team254.lib.trajectory.Trajectory;
//import com.team254.lib.trajectory.Trajectory.Segment;
//import com.team254.lib.trajectory.TrajectoryGenerator;
//import com.team254.lib.trajectory.WaypointSequence;
//import com.team254.lib.trajectory.WaypointSequence.Waypoint;

import usfirst.frc.team2168.robot.FalconPathPlanner;

/**
 * Take a set of x,y Points and generate a Path then a Talon SRX MotionProfile.Point array
 * 
 * This generates the profiles for the left and right motors based on two points (start, dest)
 * 
 * @author mfkah
 * @see https://github.com/KHEngineering/SmoothPathPlanner
 */
public class MotionProfilePlanGenerator {

	public static final double SEGMENTS_PER_INCH = 1/6;	// points every 6 inches
	
	public static class MotionProfilePlan {
		public MotionProfile.PVTPoint[] leftSide;
		public MotionProfile.PVTPoint[] rightSide;	
		public FalconPathPlanner path;
	}	
	
	
	public MotionProfilePlan generate(double[] from, double[] to) {
		return generate(new double[][] { from, to });
	}
	
	public MotionProfilePlan generate(double[][] waypoints) {
		
		double totalTime = 8; //max seconds we want to drive the path
		double timeStep = 0.1; //period of control loop on Rio, seconds
		double robotTrackWidth = Robot.geometry.wheelBase / 12; //distance between left and right wheels, feet

		FalconPathPlanner path = new FalconPathPlanner(waypoints);
		path.calculate(totalTime, timeStep, robotTrackWidth);
		
		// zero velocity at each
		MotionProfilePlan result = new MotionProfilePlan();
		result.path = path;
//		
//		WaypointSequence seq = new WaypointSequence(points.length);
		
		// first, generate the left and right profiles.  The from point is the "nose" of the
		// robot.  First we need to find the relative points for the left and right wheels
//		XYPoint leftWheel = from.dup().add(-Robot.geometry.wheelBase / 2, -Robot.geometry.frontAxleZ);
//		XYPoint rightWheel = from.dup().add(Robot.geometry.wheelBase / 2, -Robot.geometry.frontAxleZ);		
		// first, let's locate the axle
//		XYPoint axle = from.dup().add(0, -Robot.geometry.frontAxleZ);
		
		// now, generate a 20 point cubic bezier, using points 1/3 of the Y distance for the control points
		// the points are in a direction at right angles to the
//		double straightLineDistance = to.distance(from);
//		int segments = (int)Math.round(straightLineDistance / SEGMENTS_PER_INCH);
//		XYPoint[] curve = new XYPoint[segments];
		// TODO
		// create the control points.
//		XYPoint controlPoint1 = null;
//		XYPoint controlPoint2 = null;
//		CubicBezierPathCalculator calc = new CubicBezierPathCalculator(from, controlPoint1, controlPoint2, to);
//		calc.calculate(curve);
//		for (int i=0; i<points.length; i++) {
//			seq.addWaypoint(points[i]);			
//		}
//
//		// stolen from Main in the TrajectoryLib example
//	    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
//	    config.dt = .05;
//	    config.max_acc = 10.0;
//	    config.max_jerk = 60.0;
//	    config.max_vel = 75.0;	// walking speed	    	    
//
//		path = PathGenerator.makePath(seq, config, Robot.geometry.wheelBase, getClass().getSimpleName() + "Path");
//		// now convert the curve (XYTPoint[]) to CANTalon p/v/t trajectory
//		// now get a trajectory
//		Trajectory leftWheel = path.getLeftWheelTrajectory();
//		Trajectory rightWheel = path.getRightWheelTrajectory();
//		System.out.println(String.format("Left points %d", leftWheel.getNumSegments()));
//		
//		// now we need to convert a Trajectory in team254-land to a motion profile for the canTalons
//		result.leftSide = trajectoryToPVTPoints(leftWheel);
//		result.rightSide = trajectoryToPVTPoints(rightWheel);
		
		return result;
	}
	
//	public static PVTPoint[] trajectoryToPVTPoints(Trajectory traj) {
//		PVTPoint[] points = new PVTPoint[traj.getNumSegments()];
//		for (int i = 0; i<points.length; i++) {
//			Segment seg = traj.getSegment(i);
//			points[i] = new PVTPoint(seg.pos, seg.vel, seg.dt);
//		}
//		return points;
//	}
	
	public static void main(String [] args) {
		double [] from =  { 0, 0 };
		double [] to = { 4, 60 };
		
		MotionProfilePlanGenerator gen = new MotionProfilePlanGenerator();
		MotionProfilePlan res = gen.generate(from, to);
		
//		Path path = gen.path;
		
//		Trajectory left = path.getLeftWheelTrajectory();
//		Trajectory right = path.getRightWheelTrajectory();
//		System.out.println(String.format("Segments %d and %d", left.getNumSegments(), right.getNumSegments() ));
//		int i;
//		double t = 0;
//		System.out.println("Left:");
//		for (i=0; i<left.getNumSegments(); i++) {
//			t+= left.getSegment(i).dt;
//			System.out.println(String.format("%.2f\t%.2f\t%.2f", t, left.getSegment(i).pos, left.getSegment(i).vel));
//		}
//		System.out.println("Right:");
//		t = 0;
//		for (i=0; i<right.getNumSegments(); i++) {
//			t+= left.getSegment(i).dt;
//			System.out.println(String.format("%.2f\t%.2f\t%.2f", t, right.getSegment(i).pos, right.getSegment(i).vel));
//		}
		
	}
}
