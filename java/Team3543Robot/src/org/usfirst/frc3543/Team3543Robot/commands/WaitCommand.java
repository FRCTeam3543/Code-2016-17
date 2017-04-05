package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command {
	private NumberProvider numberProvider;
	public long startTime = 0;
	
	public WaitCommand(double seconds) {
		this(NumberProvider.fixedValue(seconds));
	}
	
	public WaitCommand(NumberProvider numberProvider) {
		this.numberProvider = numberProvider;
	}
	
	@Override
	protected void initialize() {
		this.startTime = System.currentTimeMillis();
	}

	public void execute() {
		
	}
	
	@Override
	protected boolean isFinished() {
		long diff = (System.currentTimeMillis() - startTime);
		Robot.log("Time: "+diff+" vs "+ numberProvider.getValue() * 1000);
		return diff >= (numberProvider.getValue() * 1000);
	}
	
	
}
