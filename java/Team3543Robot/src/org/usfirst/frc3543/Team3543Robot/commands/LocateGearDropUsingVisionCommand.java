package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.util.GearDropProvider;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ttfft.vision.GearDrop;

public class LocateGearDropUsingVisionCommand extends Command implements GearDropProvider {

	private GearDrop gearDrop = null;
		
	private double recheckInterval;
	private int maxRechecks;
	private Timer recheckTimer = new Timer();
	private int recheckCount = 0;
	
	public LocateGearDropUsingVisionCommand(double recheckInterval, int maxRechecks) {
		super();
		this.recheckInterval = recheckInterval;
		this.maxRechecks = maxRechecks;
	}	

	public void reset() {	
		gearDrop = null;
		recheckCount = 0;
		recheckTimer.reset();
		recheckTimer.start();
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
		if (recheckTimer.hasPeriodPassed(recheckInterval)) {
			recheckCount++;			
			gearDrop = Robot.visionSubsystem.detectGearDrop();
			if (gearDrop != null) {
				SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("FOUND IT %.1f m", gearDrop.distanceFromTarget));
			}
			recheckTimer.reset();
		}
		else {
			// do nothing, wait...
			SmartDashboard.putString(OI.GEARFINDER_LOCATION, String.format("Nothing ...(%d)", recheckCount));
		}
	}

	@Override
	protected boolean isFinished() {
		return (gearDrop != null) || (recheckCount >= maxRechecks);
	}
	
	@Override
	protected void end() {
		super.end();
		recheckTimer.stop();
	}


	@Override
	public GearDrop getGearDrop() {
		return gearDrop;
	}

}
