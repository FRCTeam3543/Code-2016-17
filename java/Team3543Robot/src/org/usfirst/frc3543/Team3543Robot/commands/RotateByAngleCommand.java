package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateByAngleCommand extends Command {
	protected double targetAngle = 0;
	protected double rotateBy = 0;
	protected double startingAngle = 0;
	protected double sensitivity = 0.01;
	protected double targetRotation = 0;
	public static final double SENSITIVITY = 0.01;// Radians
	protected double gain = RobotMap.DEFAULT_ROTATION_GAIN;
	
	public RotateByAngleCommand(double angleInRadians) {
		this(angleInRadians, RobotMap.DEFAULT_ROTATION_GAIN);
	}
	
	public RotateByAngleCommand(double angleInRadians, double gain) {
		requires(Robot.driveLine);
		this.setGain(gain);
		this.setRotationAngle(angleInRadians);
		setSensitivity(Math.toRadians(1));
	}
	
	public void setRotationAngle(double angleInRadians) {
		this.rotateBy = angleInRadians;
	}
	
	public void setSensitivity(double radians) {
		this.sensitivity = radians;
	}
	
	public void setGain(double gain) {
		this.gain = gain;
	}
	
	@Override 
	protected void initialize() {		
		Robot.LOGGER.info("ROTATE BY " +rotateBy);
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
		double absdiff = Math.abs(diff);
		// ease in
//		if (absdiff < Math.toRadians(5)) {
//			mag = mag * absdiff / 5;
//		}		
		// set a minimum, so we get there
		mag = Math.max(mag, 0.12);
		
		if (diff > 0) { // go CW
			Robot.driveLine.rotateClockwise(mag * gain);
		}
		else { // go CCW
			Robot.driveLine.rotateCounterClockwise(mag * gain);			
		}		
	}

	@Override
	protected boolean isFinished() {
		double angle = Robot.driveLine.getGyroAngleRadians();
        SmartDashboard.putNumber(OI.GYRO, Math.toDegrees(angle));

		boolean done = Math.abs(angle - targetAngle) < sensitivity;
		if (done) {
			Robot.LOGGER.info("ROTATE DONE");
		}
		else {
//			Robot.LOGGER.info(String.format("ROTATE NOT DONE target %.2f angle %.2f sens %.2f diff %.2f", targetAngle, angle, sensitivity, Math.abs(angle - targetAngle)) );
		}
		return done;
	}
	
	@Override
	protected void end() {
		super.end();
		Robot.driveLine.stop();
	}
}
