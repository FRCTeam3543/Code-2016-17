// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot.commands;
import java.util.logging.Logger;

import org.usfirst.frc3543.Team3543Robot.OI;
import org.usfirst.frc3543.Team3543Robot.Robot;
import org.usfirst.frc3543.Team3543Robot.RobotMap;
import org.usfirst.frc3543.Team3543Robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import ttfft.vision.GearDrop;

import ttfft.vision.Settings;


/**
 *
 */
public class ApproachGearDropCommand extends Command {
	
	public static final Logger LOGGER = Logger.getLogger(ApproachGearDropCommand.class.getSimpleName());
	
	public static final long DEFAULT_MAX_SEARCH_TIME = 15000;	// 15 seconds

	public static final double MAX_SPEED = RobotMap.MAX_SPEED;
	
	boolean giveUp = false;
	long searchStartTime = System.currentTimeMillis();
	long maxSearchTime = DEFAULT_MAX_SEARCH_TIME;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ApproachGearDropCommand() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.visionSubsystem);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveLine);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// reset the vision system - will nullify the detection
    	giveUp = false;
    	searchStartTime = System.currentTimeMillis();
    	Robot.visionSubsystem.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.log("EXECUTE approach gear");

    	// check the vision system to see if we "see" the gear drop
    	if (giveUp) {
			abandonSearch();
    	}
    	else if (Robot.visionSubsystem.isGearDropDetected()) {
        	// if so, approach it
        	Robot.log("FOUND approach gear");
    		SmartDashboard.putString(OI.GEARFINDER_MODE, "FOUND GEAR");    		

    		approachGearDrop(Robot.visionSubsystem.getGearDrop());
    	}
    	else if (System.currentTimeMillis() - searchStartTime > maxSearchTime) {
        	Robot.log("TIMEOUT approach gear");
        	// if our search fails, quit
    		abandonSearch();
    	}
    	else {
    		SmartDashboard.putString(OI.GEARFINDER_MODE, "searching");    		
        	// otherwise we search for it    		
        	System.out.println("SEARCH approach gear");

    		searchForGearDrop();
    	}
    }
    
    private void searchForGearDrop() {
		// TODO Auto-generated method stub
		// how do we search?  turn around until we see it?  how fast can we turn?
    	LOGGER.info("Search for gear drop gain "+RobotMap.GEAR_SEARCH_GAIN );
    	Robot.driveLine.turnInPlace(RobotMap.GEAR_SEARCH_GAIN);
	}

	private void abandonSearch() {
		giveUp = true;
		end();		
	}

	private void approachGearDrop(GearDrop gearDrop) {
		// TODO - figure this out
		// if we are rotated away from it, rotate towards it
		// if we are offset, turn towards it, drive towards it		
		double speed = 0;
		double curve = 0;
		
		// are we too close?  back up		
		// are we too far? drive towards it
		// i.e. base speed is proportional to the difference between the target size of the line separating the centers of the two blobs
		// (smaller is forward, larger is back up)
		// as a percentage

		speed =  Math.max(- MAX_SPEED, 
					Math.min(MAX_SPEED, (gearDrop.distanceFromTarget - Settings.TARGET_DISTANCE_IN_M) * RobotMap.GEAR_APPROACH_SPEED_GAIN)
				);
		
		// is it offset left? turn CCW meaning increase right speed and decrease left		
		// is it offset right? turn CW meaning increase left speed and decrease right		
		// max turn speed at 1/4 width

		curve += Math.max(1, Math.min(-1, - gearDrop.offsetAsPercentage[0] ));
		
		// is left side smaller?  turn CW if leftRightSizeDiff < 1
		// is right side smaller?  turn CCW if leftRightSizeDiff > 1
		// max turn speed at +/-25%
		curve += Math.max(1, Math.min(-1, (1 - gearDrop.leftRightSizeDiff) * 4));
		

		Robot.driveLine.drive(speed, curve);
	}

	public void setMaxSearchTime(long time) {
    	maxSearchTime = time;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return giveUp;
    }

    // Called once after isFinished returns true
    protected void end() {
		SmartDashboard.putString(OI.GEARFINDER_MODE, "off");
    	LOGGER.info("STOP ApproachGearCommand");
    	System.out.println("STOP approach gear");
    	Robot.driveLine.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	abandonSearch();
    }
}
