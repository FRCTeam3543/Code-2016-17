package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to close the gear holder
 * 
 * This is just the opposite of the OpenGearHolderCommand
 * 
 * @author MK
 */
public class CloseGearHolderCommand extends OpenGearHolderCommand {
		
	public void execute() {
		Robot.gearHolder.close();			
	}
	
	public boolean isFinished() {
		return !Robot.gearHolder.isOpen();
	}
		
}
