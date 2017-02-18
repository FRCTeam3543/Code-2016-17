package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.World;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ttfft.vision.GearDrop;

public class FeedbackApproachGearDropCommand extends Command {
	GearDrop gearDrop = null;
	public static final double MIN_DETECT_DISTANCE = 33; // inches
	public FeedbackApproachGearDropCommand() {
	
		requires(Robot.driveLine);
		requires(Robot.visionSubsystem);
	}
	
	@Override
	protected void initialize() {
		gearDrop = null;
	}
	
	@Override
	public void execute() {
		if (gearDrop == null || gearDrop.distanceFromTarget >= MIN_DETECT_DISTANCE) {
			gearDrop = detectGearDrop();
		}		
		
		if (gearDrop != null) {

			double distance = gearDrop.distanceFromTarget - World.GEAR_DROP_POST + 5; // just on
			SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("Gear drop at %.1f in", distance));

			double gain = SmartDashboard.getNumber(OI.DEFAULT_LINEAR_GAIN, RobotMap.DEFAULT_LINEAR_GAIN);
			
			boolean closeIn = gearDrop.distanceFromTarget <= MIN_DETECT_DISTANCE;
			if (closeIn) {
				// do nothing
				SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("Closing %.1f in", distance));
				Robot.driveLine.stop();
				Scheduler.getInstance().add(new DriveForwardByDistanceCommand(distance, gain));
			}
			else {
				double rotationGain = SmartDashboard.getNumber(OI.DEFAULT_ROTATION_GAIN, RobotMap.DEFAULT_ROTATION_GAIN);
				
				double offset = gearDrop.offsetFromCenter;
				double angle = computeAngleToGearDropPerpendicular(gearDrop);
				// use 10 degrees = -1 angle
				double limit = Math.toRadians(10);
				double curveGain = Math.max(-1, Math.min(1, angle/limit));
				//Robot.driveLine.drive(gain, (angle < 0 ? -1 : 1) * curveGain * rotationGain);
				Robot.driveLine.drive(gain, (angle < 0 ? -1 : 1) * rotationGain);

			}
		}
		else {
			Robot.LOGGER.info("Oops, lots visibility");
			Robot.driveLine.stop();
			SmartDashboard.putString(OI.GEARFINDER_LOCATION, "No gear drop");
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
	
	protected double computeAngleToGearDropPerpendicular(GearDrop gearDrop2) {
		double d = gearDrop.distanceFromTarget - World.GEAR_DROP_POST;
		double x = gearDrop.offsetFromCenter;
		return  Math.asin(x/d);
	}


	@Override
	protected boolean isFinished() {
		return gearDrop == null || gearDrop.distanceFromTarget < MIN_DETECT_DISTANCE;
		
	}
}
