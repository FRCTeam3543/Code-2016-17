package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateByAngleCommand extends Command {
	protected double targetAngle = 0;
	protected double rotateBy = 0;
	protected double startingAngle = 0;
	protected double sensitivity = 0.01;
	protected double targetRotation = 0;
	public static final double SENSITIVITY = 0.01;// Radians
	protected double gain = 0.4;
	
	public RotateByAngleCommand(double angleInRadians) {
		this(angleInRadians, 0.4);
	}
	
	public RotateByAngleCommand(double angleInRadians, double gain) {
		requires(Robot.driveLine);
		this.gain = gain;
//		if (Math.abs(angleInRadians) > Math.PI) {
//			// rotate the other way instead
//			if (angleInRadians > 0) {
//				angleInRadians = angleInRadians - Math.PI * 2;
//			}
//			else {
//				angleInRadians = Math.PI * 2 - angleInRadians;
//			}
//		}
		this.rotateBy = angleInRadians;
	}
	
	@Override 
	protected void initialize() {		
		Robot.driveLine.resetGyro();
		this.startingAngle = Robot.driveLine.getGyroAngleRadians();
		this.targetAngle = startingAngle + rotateBy;
		this.sensitivity = Robot.driveLine.gyroSensitivity * 2;		
	}
	
	@Override
	public void execute() {
		double diff = Robot.driveLine.getGyroAngleRadians() - targetAngle;		
		// if diff > 0, rotate CW.  Two magnitudes, depending on abs angle
		double mag = 1;
		if (Math.abs(diff) < Math.toRadians(5)) {
			mag = 0.2;
		}
		if (diff > 0) { // go CW
			Robot.driveLine.drive(-mag * gain, -1);
		}
		else { // go CCW
			Robot.driveLine.drive(-mag * gain, 1);			
		}		
	}

	@Override
	protected boolean isFinished() {
		double angle = Robot.driveLine.getGyroAngleRadians();
        SmartDashboard.putNumber("Gyro", Math.toDegrees(angle));

		return Math.abs(angle - targetAngle) < sensitivity;
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.driveLine.stop();
	}
}
