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
		
	boolean enabled = false;
	
	@Override
	public void execute() {
		boolean haveNewGearDrop = false;
		
		// try to update the gear drop.  See if we detected a new one
		GearDrop newGearDrop = detectGearDrop();
		// do we have a new one? Great!  Nope?  Continue to use the old one UNLESS IT IS REALLY OLD
		if (newGearDrop != null) {
			gearDrop = newGearDrop;
			haveNewGearDrop = true;
		}			
		
		// if the gear drop is REALLY OLD we can't trust it, so disable things
		// really, we should switch to a scanning mode where we turn in place to try and find it...
		if (gearDrop != null && (System.currentTimeMillis() > (gearDrop.timestamp + 2000))) {
			// 2 seconds old, way too old
			gearDrop = null;
		}

		// do we have a gear drop?
		if (gearDrop != null) {		
			// is it a new one?  We should update our set points based on the new distance and angle.
			if (haveNewGearDrop) {
				double distance = gearDrop.distanceFromTarget; // just on
				double angle = computeAngleToGearDropPerpendicular(gearDrop);

				OI.dashboard.putGearfinderLocation(String.format("Gear drop at %.1f in", distance));
//				double gain = linearGainProvider.getValue();
				// is this our first gear drop?  Enable PID.
				if (!enabled) {
					// enables PID and puts the setpoints at zero
					Robot.driveLine.enablePID();
					enabled = true;
				}				
				Robot.driveLine.setpoint(distance, angle);
			}
		}
		else {
			Robot.driveLine.disablePID();
			enabled = false;
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
	protected double computeAngleToGearDropPerpendicular(GearDrop gearDrop) {
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
