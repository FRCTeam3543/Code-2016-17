// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot.subsystems;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.RobotMap.AutonomousMode;
import org.usfirst.frc3543.Team3543Robot.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to track the Robot state.  For now just encapsulates the autonomous mode switch panel.
 * 
 * Prior to the autonomous portion of the match, when the robot is placed on the
 * field, set the switches to middle/side and left/right.  This ensures the robot
 * does the correct pattern when autonomous mode is launched.
 * 
 * @author MK
 * @See {@link RobotMap.AutonomousMode}
 */
public class RobotState extends Subsystem {
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DigitalInput autonomousMode = RobotMap.robotStateAutonomousMode;
    private final DigitalInput autonomousSide = RobotMap.robotStateAutonomousSide;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        setDefaultCommand(new DefaultCommand());
    }

    static class DefaultCommand extends Command {
    	DefaultCommand() {
    		requires(Robot.robotState);
    	}
    	
    	@Override
		protected boolean isFinished() {
			
			return false;
		}
		
		public void execute() {
			// update the dashboard
			Robot.robotState.updateDashboard();
		}
    }
    
	public AutonomousMode getAutonomousMode() {
		boolean side = !autonomousMode.get();
		boolean right = !autonomousSide.get();
		if (side) {
			return right ? RobotMap.AutonomousMode.RIGHT : RobotMap.AutonomousMode.LEFT;	
		}
		else {
			return RobotMap.AutonomousMode.MIDDLE;
		}
	}
	
	public void updateDashboard() {
		OI.dashboard.putAutonomousMode(getAutonomousMode());
	}
}

