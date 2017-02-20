package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.World;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;
import org.usfirst.frc3543.Team3543Robot.util.SmartDashboardNumberProvider;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ttfft.vision.GearDrop;

/**
 * Command to approach the gear drop using feedback control based on vision
 * 
 * The robot will attempt to detect the gear drop and use the drive line
 * to centre on the gear drop and drive towards it.  When it is inside
 * 33in and the gear drop tape is likely to be going off the bottom of the 
 * image, compute the remaining distance and use the drive forward command
 * 
 * @see GearDropAutonomousCommandGroup
 * @author MK
 *
 */
public class FeedbackApproachGearDropCommand extends Command {
	GearDrop gearDrop = null;
	public static final double MIN_DETECT_DISTANCE = 33; // inches
	NumberProvider rotationGainProvider;
	NumberProvider linearGainProvider;
	
	public FeedbackApproachGearDropCommand(NumberProvider linearGainProvider, NumberProvider rotationGainProvider) {	
		requires(Robot.driveLine);
		requires(Robot.visionSubsystem);
		this.linearGainProvider = linearGainProvider;
		this.rotationGainProvider = rotationGainProvider;
	}
	
	public FeedbackApproachGearDropCommand() {
		this(
				new SmartDashboardNumberProvider(OI.DEFAULT_LINEAR_GAIN, RobotMap.DEFAULT_LINEAR_GAIN),
				new SmartDashboardNumberProvider(OI.DEFAULT_ROTATION_GAIN, RobotMap.DEFAULT_ROTATION_GAIN)
				);
	}
	
	@Override
	protected void initialize() {
		gearDrop = null;
		Robot.driveLine.disablePID();
	}
		
	boolean first = true;
	
	@Override
	public void execute() {
		if (gearDrop == null || gearDrop.distanceFromTarget >= MIN_DETECT_DISTANCE) {
			gearDrop = detectGearDrop();
		}		
		
		if (gearDrop != null) {
			if (first) {
				Robot.driveLine.enablePID();
				first = false;
			}
			double distance = gearDrop.distanceFromTarget; // just on
			OI.dashboard.putGearfinderLocation(String.format("Gear drop at %.1f in", distance));

			double gain = linearGainProvider.getValue();
			
			boolean closeIn = gearDrop.distanceFromTarget <= MIN_DETECT_DISTANCE;
			if (closeIn) {
				// do nothing
				OI.dashboard.putGearfinderLocation(String.format("Closing %.1f in", distance));
				Robot.driveLine.stop();
				Scheduler.getInstance().add(new DriveForwardByDistanceCommand(distance, gain));
			}
			else {
				double rotationGain = rotationGainProvider.getValue();				
				double offset = gearDrop.offsetFromCenter;
				double angle = computeAngleToGearDropPerpendicular(gearDrop);
				// use 10 degrees = -1 angle
				double limit = Math.toRadians(10);
				// -10 to 10 maps to -1 to 1
				double curveGain = Math.max(-1, Math.min(1, angle/limit));
				Robot.driveLine.drive(gain, curveGain * rotationGain);
				//Robot.driveLine.drive(gain, (angle < 0 ? -1 : 1) * rotationGain);
			}
		}
		else {
			Robot.driveLine.disablePID();
			first = true;
			Robot.LOGGER.info("Oops, lots visibility");
			Robot.driveLine.stop();
			OI.dashboard.putGearfinderLocation("No gear drop");
		}		
	}
	
	protected GearDrop detectGearDrop() {
		GearDrop gd = Robot.visionSubsystem.detectGearDrop();
		if (gd == null) {
			Robot.driveLine.stop();
			for (int i=0; i<4;i++) {
				gd = Robot.visionSubsystem.detectGearDrop();
				if (gd != null) break;
			}			
		}
		return gd;
	}
	
	@Override
	protected void end() {
		Robot.driveLine.stop();
	}
	
	/**
	 * Compute the approximate angle to the line perpendicular to the gear drop
	 * 
	 * @param gearDrop2
	 * @return
	 */
	protected double computeAngleToGearDropPerpendicular(GearDrop gearDrop2) {
		double d = gearDrop.distanceFromTarget;
		double x = gearDrop.offsetFromCenter;
		return  Math.asin(x/d);
	}


	/**
	 * This command is done when we're inside the minimum distance to
	 * use vision to detect the gear.  Notice in the execute we
	 * kick off a DriveForwardByDisanceCommand once we are inside it.
	 * 
	 * We're also done if we no longer know where the gear drop is.
	 */
	@Override
	protected boolean isFinished() {
		return gearDrop == null || gearDrop.distanceFromTarget < MIN_DETECT_DISTANCE;		
	}
}
