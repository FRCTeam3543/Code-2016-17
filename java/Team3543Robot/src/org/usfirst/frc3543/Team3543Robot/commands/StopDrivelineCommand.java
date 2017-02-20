package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Simple command to stop the drive line,
 * 
 * @author MK
 */
public class StopDrivelineCommand extends Command {

	public StopDrivelineCommand() {
		requires(Robot.driveLine);
	}
	
	@Override
	protected void initialize() {
		Robot.driveLine.stop();
	}

	@Override
	protected boolean isFinished() {		
		return false;
	}
	
}
