package org.usfirst.frc3543.Team3543Robot.commands;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.util.DegreesToRadiansNumberProvider;
import org.usfirst.frc3543.Team3543Robot.util.NumberProvider;
import org.usfirst.frc3543.Team3543Robot.util.SmartDashboardNumberProvider;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearDropAutonomousCommandGroup extends CommandGroup {
	DriveForwardByDistanceCommand driveForward;
	RotateByAngleCommand rotate;
	
	public GearDropAutonomousCommandGroup(NumberProvider distanceProvider, NumberProvider angleInRadiansProvider) {
		driveForward = new DriveForwardByDistanceCommand(distanceProvider, new SmartDashboardNumberProvider(OI.DEFAULT_LINEAR_GAIN, RobotMap.DEFAULT_LINEAR_GAIN));
		rotate = new RotateByAngleCommand(angleInRadiansProvider, new SmartDashboardNumberProvider(OI.DEFAULT_ROTATION_GAIN, RobotMap.DEFAULT_ROTATION_GAIN));
		addSequential(driveForward);
		addSequential(rotate);
		addSequential(new FeedbackApproachGearDropCommand());
	}
	
	public GearDropAutonomousCommandGroup() {
		this(new SmartDashboardNumberProvider(OI.GEARDROP_APPROACH_INIITAL_STATIC_DISTANCE, 0),
			new DegreesToRadiansNumberProvider(new SmartDashboardNumberProvider(OI.GEARDROP_APPROACH_INITIAL_STATIC_ROTATION, 0))
		);
	}
	
	@Override
	protected void initialize() {
		super.initialize();
	}
}
