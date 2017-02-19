package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;
import org.usfirst.frc3543.Team3543Robot.util.SmartDashboardNumberProvider;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardByDistanceCommand extends Command {
	protected double targetDistance = 0;
	protected double startingEncoderValue = 0;
	protected double powerGain = 1;
	
	protected NumberProvider distanceProvider;
	protected NumberProvider gainProvider;
	
	public static final double START_TRAPEZOID_POINT = 0.2;
	public static final double END_TRAPEZOID_POINT = 0.8;
	public static final double MIN_MAGNITUDE = 0.12;
	
	public static final double SENSITIVITY = 0.5; //inches
	public DriveForwardByDistanceCommand(double distanceInInches, double powerGain) {
		this(NumberProvider.fixedValue(distanceInInches), NumberProvider.fixedValue(powerGain));
	}
	
	public DriveForwardByDistanceCommand(NumberProvider distanceProvider, NumberProvider gainProvider) {
		requires(Robot.driveLine);
		this.distanceProvider = distanceProvider;
		this.gainProvider = gainProvider;
	}
	
	public DriveForwardByDistanceCommand(NumberProvider distanceProvider) {
		this(distanceProvider,  new SmartDashboardNumberProvider(OI.DEFAULT_LINEAR_GAIN, RobotMap.DEFAULT_LINEAR_GAIN));
	}
	
	public DriveForwardByDistanceCommand(double distanceInInches) {
		this(NumberProvider.fixedValue(distanceInInches));
	}
	
	public void setTargetDistance(double distanceInInches) {
		this.targetDistance = distanceInInches;
	}
	
	public void setPowerGain(double powerGain) {
		this.powerGain = powerGain;
	}
	
	@Override 
	protected void initialize() {
		// read the starting encoder values
		Robot.driveLine.resetEncoders();		
		this.setTargetDistance(distanceProvider.getValue());
		this.setPowerGain(gainProvider.getValue());
	}
	
	@Override
	public void execute() {
		// trapezoid func for velocity for smooth drive.		
		Robot.driveLine.updateDashboard();
		double dist =  Math.max(this.targetDistance, Robot.driveLine.getLeftEncoderValue());
		double percentTraveled = (this.targetDistance - dist) / this.targetDistance;
		double mag = 1;
		if (percentTraveled < START_TRAPEZOID_POINT) {
			mag *= (START_TRAPEZOID_POINT - percentTraveled) / START_TRAPEZOID_POINT;
		}
		else if (percentTraveled > END_TRAPEZOID_POINT) {
			mag *= (1 - (percentTraveled - END_TRAPEZOID_POINT)) / START_TRAPEZOID_POINT;
		}
		mag = Math.max(MIN_MAGNITUDE, mag);
		Robot.driveLine.drive(mag * this.powerGain);
	}

	@Override
	protected boolean isFinished() {
		double enc = Robot.driveLine.getLeftEncoderValue();
		OI.dashboard.putDistanceRemaining(this.targetDistance - enc);
		return (this.targetDistance -enc)  < SENSITIVITY;
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.driveLine.stop();
		Robot.driveLine.resetEncoders();
	}
}
