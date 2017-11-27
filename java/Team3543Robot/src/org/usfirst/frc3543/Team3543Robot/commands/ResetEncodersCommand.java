package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetEncodersCommand extends Command {

	boolean _once = false;
	public ResetEncodersCommand() {
		requires(Robot.driveLine);
	}

	public void execute() {
		Robot.driveLine.resetEncoders();
		Robot.driveLine.resetGyro();
		_once = true;
	}
	
	@Override
	protected boolean isFinished() {
		return _once;
	}
}
