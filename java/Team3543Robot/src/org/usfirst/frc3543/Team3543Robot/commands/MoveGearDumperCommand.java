package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;

import edu.wpi.first.wpilibj.command.Command;

public class MoveGearDumperCommand extends Command {
	private double position = 0.0;
	private NumberProvider numberProvider;
	
	public MoveGearDumperCommand() {
		this(0);
	}

	public MoveGearDumperCommand(double position) {
		this(NumberProvider.fixedValue(position));
	}

	public MoveGearDumperCommand(NumberProvider numberProvider) {
		requires(Robot.gearHolder);		
		this.numberProvider = numberProvider;
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	public void execute() {
		Robot.gearHolder.setAngle(this.numberProvider.getValue());
	}
		
	@Override
	protected boolean isFinished() {
		return Math.abs(Robot.gearHolder.getAngle() - this.numberProvider.getValue()) < 0.01;
	}

}
