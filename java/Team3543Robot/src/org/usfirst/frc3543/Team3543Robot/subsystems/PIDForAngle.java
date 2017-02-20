package org.usfirst.frc3543.Team3543Robot.subsystems;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDForAngle extends PIDSubsystem {
	private double currentOutput = 0;
	
	public PIDForAngle(double p, double i, double d) {
		super(p, i, d);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		currentOutput = output;
		Robot.driveLine.updateOutputFromPIDControllers();
	}

	public double getCurrentOutput() {
		return currentOutput;
	}
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
