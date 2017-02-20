package org.usfirst.frc3543.Team3543Robot.subsystems;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import ttfft.vision.GearDrop;

public class PIDForDistance extends PIDSubsystem {
	private double currentOutput = 0;
	
	public PIDForDistance(double p, double i, double d) {
		super(p, i, d);				
	}

	@Override
	protected double returnPIDInput() {
		GearDrop gd = Robot.visionSubsystem.lastKnownGearDrop();
		if (gd == null) return 0;
		else {
			return gd.distanceFromTarget;
		}
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.currentOutput = output;
		Robot.driveLine.updateOutputFromPIDControllers();
	}
	
	public double getCurrentOutput() {
		return currentOutput;
	}

	@Override
	protected void initDefaultCommand() {
		// empty on purpose
	}

}
