package org.usfirst.frc3543.Team3543Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignToGearDropCommand extends CommandGroup {
	
	public AlignToGearDropCommand(double angle, double distance) {		
		addSequential(new RotateByAngleCommand(angle));
		addSequential(new DriveForwardByDistanceCommand(distance));
		addSequential(new RotateByAngleCommand(-angle));		
	}
	
	
	
//	@Override
//	protected boolean isFinished() {
//		return super.isFinished();
//	}
//
//	@Override
//	protected void initialize() {
//		super.initialize();
//	}
//
//	@Override
//	protected void execute() {
//		super.execute();
//	}
//
//	@Override
//	protected void end() {
//		super.end();
//	}
//
//	@Override
//	protected void interrupted() {
//		super.interrupted();
//	}

}
