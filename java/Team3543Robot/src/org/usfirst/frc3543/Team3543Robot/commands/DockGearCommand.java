package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DockGearCommand extends CommandGroup {

	public DockGearCommand(double inches, double gain) {
		// rotate a bit
		addSequential(new RotateByAngleCommand(Math.toRadians(0.5), gain));
		addSequential(new DriveForwardByDistanceCommand(inches, gain));		
		// new for North Bay - dock the gear
		addSequential(new PlaceGearCommand());
	}
}
