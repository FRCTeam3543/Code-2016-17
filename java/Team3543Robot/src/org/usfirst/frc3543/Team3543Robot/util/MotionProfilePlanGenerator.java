package org.usfirst.frc3543.Team3543Robot.util;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotGeometry;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfile.PVTPoint;

import com.ctre.CANTalon;
import com.team254.lib.trajectory.Path;
import com.team254.lib.trajectory.PathGenerator;
import com.team254.lib.trajectory.Trajectory;
import com.team254.lib.trajectory.Trajectory.Segment;
import com.team254.lib.trajectory.TrajectoryGenerator;
import com.team254.lib.trajectory.WaypointSequence;
import com.team254.lib.trajectory.WaypointSequence.Waypoint;

/**
 * Take a set of x,y Points and generate a Path then a Talon SRX MotionProfile.Point array
 * 
 * This generates the profiles for the left and right motors based on two points (start, dest)
 * 
 * @author mfkah
 *
 */
public class MotionProfilePlanGenerator {

	public static final double SEGMENTS_PER_INCH = 1/6;	// points every 6 inches
	
	public static class MotionProfilePlan {
		public MotionProfile.PVTPoint[] leftSide;
		public MotionProfile.PVTPoint[] rightSide;				
	}	
	
	public MotionProfilePlan generate(Waypoint from, Waypoint to) {
				
		// zero velocity at each
		MotionProfilePlan result = new MotionProfilePlan();
		
		WaypointSequence seq = new WaypointSequence(2);
		
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
		
		seq.addWaypoint(from);
		seq.addWaypoint(to);

		// stolen from Main in the TrajectoryLib example
	    TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
	    config.dt = .05;
	    config.max_acc = 10.0;
	    config.max_jerk = 60.0;
	    config.max_vel = 15.0;	    

		Path path = PathGenerator.makePath(seq, config, Robot.geometry.wheelBase, getClass().getSimpleName() + "Path");
		// now convert the curve (XYTPoint[]) to CANTalon p/v/t trajectory
		// now get a trajectory
		Trajectory leftWheel = path.getLeftWheelTrajectory();
		Trajectory rightWheel = path.getRightWheelTrajectory();
		System.out.println(String.format("Left points %d", leftWheel.getNumSegments()));
		
		// now we need to convert a Trajectory in team254-land to a motion profile for the canTalons
		result.leftSide = trajectoryToPVTPoints(leftWheel);
		result.rightSide = trajectoryToPVTPoints(rightWheel);
		return result;
	}
	
	public static PVTPoint[] trajectoryToPVTPoints(Trajectory traj) {
		PVTPoint[] points = new PVTPoint[traj.getNumSegments()];
		for (int i = 0; i<points.length; i++) {
			Segment seg = traj.getSegment(i);
			points[i] = new PVTPoint(seg.pos, seg.vel, seg.dt);
		}
		return points;
	}
	
	public static void main(String [] args) {
		Waypoint from = new Waypoint(0,0,0);
		Waypoint to = new Waypoint(4, 60, 0);
		
		MotionProfilePlanGenerator gen = new MotionProfilePlanGenerator();
		MotionProfilePlan res = gen.generate(from, to);
	}
}
