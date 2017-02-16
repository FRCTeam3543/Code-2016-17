package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.subsystems.DriveLine.MotionProfileController;
import org.usfirst.frc3543.Team3543Robot.util.GearDropProvider;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfile;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfilePlanGenerator;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfilePlanGenerator.MotionProfilePlan;

import com.team254.lib.trajectory.WaypointSequence.Waypoint;

import edu.wpi.first.wpilibj.command.Command;
import ttfft.vision.GearDrop;

public class ApproachGearDropUsingMotionProfileCommand extends Command {

	private GearDropProvider gearDropProvider = null;
	private GearDrop gearDrop = null;
	private MotionProfilePlan plan = null;
	
	private MotionProfileController controller = null;	
	
	public ApproachGearDropUsingMotionProfileCommand(GearDropProvider gearDropProvider) {
		this.gearDropProvider = gearDropProvider;
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
	
	protected MotionProfilePlan computeMotionProfiles() {
		Waypoint from = new Waypoint(0,0,0);
		// TODO - should not be zero?
		Waypoint to = new Waypoint(gearDrop.offsetFromCenter, gearDrop.distanceFromTarget, 0);
		
		MotionProfilePlan plan = (new MotionProfilePlanGenerator()).generate(from, to);
		return plan;
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
