package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

public class LowerRobotCommand extends LiftRobotCommand {

	@Override
	public void execute() {
    	Robot.liftSubsystem.lift(-getGain());
    }
}
