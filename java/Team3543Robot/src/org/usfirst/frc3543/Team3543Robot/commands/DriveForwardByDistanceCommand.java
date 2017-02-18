package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardByDistanceCommand extends Command {
	protected double targetDistance = 0;
	protected double startingEncoderValue = 0;
	protected double powerGain = 1;
	
	public static final double START_TRAPEZOID_POINT = 0.2;
	public static final double END_TRAPEZOID_POINT = 0.8;
	public static final double MIN_MAGNITUDE = 0.12;
	
	public static final double SENSITIVITY = 0.5; //inches
	
	public DriveForwardByDistanceCommand(double distanceInInches, double powerGain) {
		requires(Robot.driveLine);
		this.targetDistance = distanceInInches;
		this.powerGain = powerGain;
	}
	
	public DriveForwardByDistanceCommand(double distanceInInches) {
		this(distanceInInches, 0.5);
	}
	
	@Override 
	protected void initialize() {
		// read the starting encoder values
		Robot.driveLine.resetEncoders();		
	}
	
	@Override
	public void execute() {
		// trapezoid func for velocity for smooth drive.		
        SmartDashboard.putNumber("LeftEncoder", Robot.driveLine.getLeftEncoderValue());
		double dist =  Math.max(this.targetDistance, Robot.driveLine.getLeftEncoderValue());
		double percentTraveled = (dist - this.targetDistance) / this.targetDistance;
		double mag = 1;
		if (percentTraveled < START_TRAPEZOID_POINT) {
			mag *= (START_TRAPEZOID_POINT - percentTraveled) / START_TRAPEZOID_POINT;
		}
		else if (percentTraveled > END_TRAPEZOID_POINT) {
			mag *= (percentTraveled - END_TRAPEZOID_POINT) / START_TRAPEZOID_POINT;
		}
		mag = Math.max(MIN_MAGNITUDE, mag);
		// -mag because backwards!
		Robot.driveLine.drive(-mag * this.powerGain, 0);
	}

	@Override
	protected boolean isFinished() {
		double enc = Robot.driveLine.getLeftEncoderValue();
        SmartDashboard.putNumber("Distance", (this.targetDistance - enc));
		return (this.targetDistance -enc)  < SENSITIVITY;
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.driveLine.stop();
		Robot.driveLine.resetEncoders();
	}
}
