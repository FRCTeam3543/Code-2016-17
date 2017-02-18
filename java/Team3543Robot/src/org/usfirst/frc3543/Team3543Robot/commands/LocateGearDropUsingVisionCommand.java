package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.util.GearDropConsumer;
import org.usfirst.frc3543.Team3543Robot.util.GearDropProvider;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ttfft.vision.GearDrop;

public class LocateGearDropUsingVisionCommand extends Command implements GearDropProvider {

	private GearDrop gearDrop = null;
		
	private int maxRechecks;
	private boolean finished = false;
	private GearDropConsumer consumer = null;
	
	public LocateGearDropUsingVisionCommand(int maxRechecks, GearDropConsumer consumer) {
		super();
		this.maxRechecks = maxRechecks;
		this.consumer = consumer;
	}
	
	public LocateGearDropUsingVisionCommand() {
		this(5);
	}
	
	public LocateGearDropUsingVisionCommand(int maxRechecks) {
		this(maxRechecks, null);
	}

	public void reset() {	
		gearDrop = null;
		finished = false;
	}
	
	@Override
	protected void initialize() {
		super.initialize();	
		reset();
	}

	@Override
	protected void execute() {
		// see if we can see a gear drop. Should be on first try, but we'll look a couple
		// of times just in case
		for (int i=0; i< maxRechecks; i++) {
			gearDrop = Robot.visionSubsystem.detectGearDrop();
			if (gearDrop != null) {
				SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("FOUND IT %.1f m", gearDrop.distanceFromTarget));
				break; // we got it!
			}	
			else {
				SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("Finding Nothing ..."));				
			}
			Timer.delay(0.05);
		}
		finished = true;
		if (gearDrop != null) {
			SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("FOUND IT %.1f m", gearDrop.distanceFromTarget));
		}
		else {
			SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("NOT FOUND"));				
		}
	}

	@Override
	protected boolean isFinished() {
		if (consumer != null) {
			consumer.setGearDrop(gearDrop);
		}
		return finished;
	}
	
	@Override
	protected void end() {
		super.end();
		finished = true;
	}


	@Override
	public GearDrop getGearDrop() {
		return gearDrop;
	}

}
