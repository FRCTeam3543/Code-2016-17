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

import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.commands.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Subsystem to operate the lift motors
 * 
 * Contains three high-level methods lift(), lower() and stop().  The lower() method
 * just calls lift() with a negative magnitude.  
 * 
 * @author MK
 *
 */
public class LiftSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon cANTalon5 = RobotMap.liftSubsystemCANTalon5;
    private final CANTalon cANTalon6 = RobotMap.liftSubsystemCANTalon6;
    private final RobotDrive liftSubsystemDrive = RobotMap.liftSubsystemLiftSubsystemDrive;
    private final Encoder liftSubsystemQuadratureEncoder = RobotMap.liftSubsystemLiftSubsystemQuadratureEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    public static class DefaultCommand extends Command {
    	public DefaultCommand() {
    		requires(Robot.liftSubsystem);
    	}
    	
    	@Override 
    	protected void initialize() {
    		Robot.liftSubsystem.stop();
    	}
    	
		@Override
		protected boolean isFinished() {
			return false;
		}    	
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
    	liftSubsystemDrive.setSafetyEnabled(false);
        setDefaultCommand(new DefaultCommand());
//        liftSubsystemDrive.setSafetyEnabled(false);
    }
    
    public void stop() {
    	liftSubsystemDrive.stopMotor();
    }
    
    public void lower() {
    	lift(-RobotMap.DEFAULT_LIFT_GAIN);
    }
    
    public void lower(double mag) {
    	lift(-mag);
    }
    
    public void lift(double mag) {
    	liftSubsystemDrive.drive(-mag, 0);
    }
    
    public void lift() {
    	lift(RobotMap.DEFAULT_LIFT_GAIN);
    }
}

