package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceGearCommand extends CommandGroup {

	public PlaceGearCommand(double gain) {
		addSequential(new MoveGearDumperCommand(RobotMap.DEFAULT_GEAR_DUMPER_UP));
		addSequential(new WaitCommand(6.0));
		addSequential(new DriveForwardByDistanceCommand(-RobotMap.DEFAULT_GEAR_DUMPER_BACKUP, gain));
		addSequential(new MoveGearDumperCommand(RobotMap.DEFAULT_GEAR_DUMPER_DOWN));		
	}
	
	public PlaceGearCommand() {
		this(RobotMap.DEFAULT_LINEAR_GAIN);
	}
}
