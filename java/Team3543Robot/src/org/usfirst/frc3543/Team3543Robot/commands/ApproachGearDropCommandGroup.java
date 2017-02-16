package org.usfirst.frc3543.Team3543Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ApproachGearDropCommandGroup extends CommandGroup {
	public enum Mode {
		LEFT,
		MIDDLE,
		RIGHT		
	};
	
	public ApproachGearDropCommandGroup(Mode mode) {
		if (mode.equals(Mode.LEFT)) {
			addSequential(new GearDropLeftApproachCommand());
		}
		else if (mode.equals(Mode.RIGHT)) {
			addSequential(new GearDropRightApproachCommand());
		}
		
		LocateGearDropUsingVisionCommand locateCmd = new LocateGearDropUsingVisionCommand(0.3, 5);
		addSequential(locateCmd);
		addSequential(new ApproachGearDropUsingMotionProfileCommand(locateCmd));
		
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished();
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		super.execute();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		super.end();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		super.interrupted();
	}

}
