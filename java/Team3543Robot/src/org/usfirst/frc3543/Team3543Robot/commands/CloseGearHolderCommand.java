package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseGearHolderCommand extends OpenGearHolderCommand {
	public void execute() {
		Robot.gearHolder.close();			
	}
	
	public boolean isFinished() {
		return !Robot.gearHolder.isOpen();
	}
		
}
