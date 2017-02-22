// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;
import org.usfirst.frc3543.Team3543Robot.util.SmartDashboardNumberProvider;

/**
 * Command to start the robot lift motor
 * 
 * @author MK
 */
public class LiftRobotCommand extends Command {
	NumberProvider gainProvider;
	
    public LiftRobotCommand() {
    	this(new SmartDashboardNumberProvider(OI.LIFT_GAIN, RobotMap.DEFAULT_LIFT_GAIN));
    }
    
    public LiftRobotCommand(NumberProvider gainProvider) {
    	this.gainProvider = gainProvider;
        requires(Robot.liftSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.liftSubsystem.lift(getGain());
    }

    protected double getGain() {
    	return gainProvider.getValue();
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {    	
        return false;	// always false, we cancel this using the dashboard
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.liftSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
