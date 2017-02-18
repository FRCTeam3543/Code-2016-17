package org.usfirst.frc3543.Team3543Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignToGearDropCommand extends CommandGroup {
	
	RotateByAngleCommand firstRotation;
	DriveForwardByDistanceCommand goForward;
	RotateByAngleCommand rotateBack;
	
	public AlignToGearDropCommand(double angle, double distance) {		
		firstRotation = new RotateByAngleCommand(angle);
		rotateBack = new RotateByAngleCommand(-angle);
		goForward = new DriveForwardByDistanceCommand(distance);
		addSequential(firstRotation);
		addSequential(goForward);
		addSequential(rotateBack);
	}
	
	public void setAngle(double angle) {
		firstRotation.setRotationAngle(angle);
		rotateBack.setRotationAngle(-angle);
	}
	
	public void setDistance(double distance) {
		goForward.setTargetDistance(distance);
	}

}
