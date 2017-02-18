package org.usfirst.frc3543.Team3543Robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearApproachRightCommandGroup extends CommandGroup {
	DriveForwardByDistanceCommand driveForward;
	RotateByAngleCommand rotate;
	
	public GearApproachRightCommandGroup() {
		driveForward = new DriveForwardByDistanceCommand(12 * 6);
		rotate = new RotateByAngleCommand(Math.toRadians(60));
		addSequential(driveForward);
		addSequential(rotate);
		addSequential(new FeedbackApproachGearDropCommand());
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		driveForward.setTargetDistance(SmartDashboard.getNumber("GearApproachDist", 220));
		rotate.setRotationAngle(Math.toRadians(SmartDashboard.getNumber("GearApproachAngle", -56)));
		
	}
}
