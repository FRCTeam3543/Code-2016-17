package org.usfirst.frc3543.Team3543Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DockGearCommand extends CommandGroup {

	public DockGearCommand(double inches, double gain) {
		// rotate a bit
		addSequential(new RotateByAngleCommand(Math.toRadians(0.5), gain));
		addSequential(new DriveForwardByDistanceCommand(inches, gain));		
	}
}
