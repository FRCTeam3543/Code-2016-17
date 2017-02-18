// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot.subsystems;

import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.commands.*;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfile;
import org.usfirst.frc3543.Team3543Robot.util.MotionProfilePlanGenerator.MotionProfilePlan;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveLine extends Subsystem {
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon cANTalon1 = RobotMap.driveLineCANTalon1;
    private final CANTalon cANTalon2 = RobotMap.driveLineCANTalon2;
    private final CANTalon cANTalon3 = RobotMap.driveLineCANTalon3;
    private final CANTalon cANTalon4 = RobotMap.driveLineCANTalon4;
    private final RobotDrive robotDrive = RobotMap.driveLineRobotDrive;
    private final AnalogGyro analogGyro1 = RobotMap.driveLineAnalogGyro1;
    private final Encoder quadratureEncoderLeft = RobotMap.driveLineQuadratureEncoderLeft;
    private final Encoder quadratureEncoderRight = RobotMap.driveLineQuadratureEncoderRight;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public double gyroSensitivity = 0.007;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
//    	robotDrive.setSafetyEnabled(false);
    	
    	analogGyro1.setSensitivity(RobotMap.GYRO_SENSITIVITY);    
		this.analogGyro1.calibrate();
    }
    
    public void tankDrive(Joystick left, Joystick right) {
    	robotDrive.tankDrive(left, right); // 
    }
    
    public void drive(double magnitude, double curve) {
    	robotDrive.drive(magnitude, curve);
    }
    
    public void drive(double speed) {
    	drive(speed, 0);
    }
    
    public void driveStraight(double speed) {
    	// see http://wpilib.screenstepslive.com/s/3120/m/7912/l/85772-gyros-to-control-robot-driving-direction
    	double angle = getGyroAngle();
    	drive(speed, - angle * gyroSensitivity);    	
    }
    
    public void turnInPlace(double speed) {
    	tankDrive(speed, -speed);
    }
    
    public void tankDrive(double lspeed, double rspeed) {
    	robotDrive.tankDrive(lspeed, rspeed);
    }
    
    public void tankDrive(double speed) {
    	tankDrive(speed, speed);
    }
        
    public void arcadeDrive(Joystick stick) {
    	robotDrive.arcadeDrive(stick);
    }
    
    public void stop() {
    	robotDrive.stopMotor();
    }
    
    public double getGyroAngle() {
    	return analogGyro1.getAngle();
    }
    
    public double getGyroAngleRadians() {
    	return Math.toRadians(getGyroAngle());
    }

	public void init() {
		robotDrive.setSafetyEnabled(false);
	}

	public MotionProfileController followMotionProfileController(MotionProfilePlan plan) {
		MotionProfileController controller = new MotionProfileController(this, plan);
		
		return controller;
	}
	
	public static class MotionProfileController {
		MotionProfile leftProfile;
		MotionProfile rightProfile;
		boolean started = false;
		
		MotionProfileController(DriveLine driveLine, MotionProfilePlan plan) {
			leftProfile = new MotionProfile(driveLine.cANTalon1, plan.leftSide);
			rightProfile = new MotionProfile(driveLine.cANTalon3, plan.rightSide);
		}
		
		public void reset() {
			leftProfile.reset();
			rightProfile.reset();
			started = false;
		}
		
		public void control() {
			leftProfile.control();
			rightProfile.control();
			
			leftProfile.updateTalonOutputValue();
			rightProfile.updateTalonOutputValue();
		}
		
		public boolean isFinished() {
			return this.leftProfile.isDone() && this.rightProfile.isDone();
		}
		
		public boolean isStarted() {
			return started;
		}
		
		public void start() {			
			started = true;			
			leftProfile.updateTalonOutputValue();
			rightProfile.updateTalonOutputValue();

			leftProfile.startMotionProfile();
			rightProfile.startMotionProfile();
		}
	}

	public void resetEncoders() {
		double dpp = SmartDashboard.getNumber("DistancePerPulse", RobotMap.DEFAULT_DISTANCE_PER_PULSE);

		this.quadratureEncoderLeft.reset();
		this.quadratureEncoderLeft.setDistancePerPulse(dpp);
		this.quadratureEncoderRight.reset();
		this.quadratureEncoderRight.setDistancePerPulse(dpp);
	}
	
	public void resetGyro() {
		this.analogGyro1.reset();
	}
	
	public double getLeftEncoderValue() {
		
		return this.quadratureEncoderLeft.getDistance();
	}
	
	public double getRightEncoderValue() {
		return this.quadratureEncoderRight.getDistance();
	}
	
}

