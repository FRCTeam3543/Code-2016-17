package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

/**
 * Opposite of the LiftRobotCommand, running the motor backwards in order to lower it
 * 
 * @author MK
 * @see LiftRobotCommand
 */
public class LowerRobotCommand extends LiftRobotCommand {

	@Override
	public void execute() {
    	Robot.liftSubsystem.lower(getGain());
    }
}
