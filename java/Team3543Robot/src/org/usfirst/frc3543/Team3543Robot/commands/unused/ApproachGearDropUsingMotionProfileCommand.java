package org.usfirst.frc3543.Team3543Robot.commands.unused;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.subsystems.DriveLine.MotionProfileController;
import org.usfirst.frc3543.Team3543Robot.util.GearDropProvider;
import org.usfirst.frc3543.Team3543Robot.util.unused.MotionProfile;
import org.usfirst.frc3543.Team3543Robot.util.unused.MotionProfilePlan;
import org.usfirst.frc3543.Team3543Robot.util.unused.MotionProfilePlanGenerator;
import org.usfirst.frc3543.Team3543Robot.util.unused.XYTPoint;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;

import edu.wpi.first.wpilibj.command.Command;
import ttfft.vision.GearDrop;

public class ApproachGearDropUsingMotionProfileCommand extends Command {

	private GearDropProvider gearDropProvider = null;
	private GearDrop gearDrop = null;
	private MotionProfilePlan plan = null;
	private MotionProfilePlanGenerator planGenerator = null;
	
	private MotionProfileController controller = null;	
	
	public ApproachGearDropUsingMotionProfileCommand(MotionProfilePlanGenerator generator, GearDropProvider gearDropProvider) {
		this.gearDropProvider = gearDropProvider;
		this.planGenerator = generator;
	}
	
	@Override
	protected void initialize() {		
		super.initialize();
		gearDrop = gearDropProvider.getGearDrop();
		if (gearDrop != null) {
			Robot.LOGGER.info("Compute motion profile");
			plan = computeMotionProfiles();
			Robot.LOGGER.info(String.format("%d path elements", plan.leftSide.length));

			controller = Robot.driveLine.followMotionProfileController(plan);
			controller.reset();
		}
		else {
			Robot.LOGGER.info("gear Drop is NULL");
		}
	}

	@Override
	public void execute() {
		if (plan != null) {
			controller.control();
		}
	}
	
	protected MotionProfilePlanGenerator getPlanGenerator() {
		return this.planGenerator;
	}
	
	protected MotionProfilePlan computeMotionProfiles() {
		XYTPoint from = new XYTPoint( 0, 0, 0 ); 
		XYTPoint to = new XYTPoint( gearDrop.offsetFromCenter, gearDrop.distanceFromTarget, gearDrop.skewAngle);

		double timeInterval = this.guessTimeInterval(gearDrop.distanceFromTarget);
		
		MotionProfilePlan plan = this.getPlanGenerator().generate(from, to, timeInterval);
		return plan;
	}
	
	protected double guessTimeInterval(double distanceFromTarget) {
		// FIXME - this is just an example
		// we want to max out at ~ 12 inches per second (6 seconds for 10 feet)
		return distanceFromTarget * (double)10/6;
	}

	@Override
	protected boolean isFinished() {
		// done if no gear drop or we are done trajectory
		return (gearDrop == null && plan != null) || isTrajectoryComplete();
	}
	
	@Override
	public void end() {
		if (controller != null) {
			controller.reset();
		}
	}

	protected boolean isTrajectoryComplete() {
		return (controller == null) || controller.isFinished();
	}

}
