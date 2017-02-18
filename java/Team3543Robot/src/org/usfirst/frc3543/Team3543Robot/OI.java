// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3543.Team3543Robot;

import org.usfirst.frc3543.Team3543Robot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc3543.Team3543Robot.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public static final String GEARFINDER_ANGLE_KEY = "GearFinderAngle";
	public static final String GEARFINDER_SIZE_DIFF_KEY = "GearFinderSizeDiff";
	public static final String GEARFINDER_D2D_KEY = "GearFinderD2D";
	public static final String GEARFINDER_MODE = "GearFinderMode";
	public static final String GEARFINDER_FOUND_GEAR = "GearFinderStatus";
	public static final String GEARFINDER_BLOB_COUNT = "GearFinderBlobCount";
	public static final String GEARFINDER_LOCATION = "GearFinderLocation";
	public static final String GEARFINDER_CURVE_GAIN = "GearFinderCurveGain";
	public static final String GEARFINDER_SPEED_GAIN = "GearFinderSpeedGain";
	public static final String DEFAULT_LINEAR_GAIN = "DefaultLinearGain";
	public static final String DEFAULT_ROTATION_GAIN = "DefaultRotationGain";
	public static final String GYRO = "Gyro";
	public static final String LIFT_GAIN = "Lift Gain";
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton speedControlButton;
    public Joystick leftJoystick;
    public Joystick rightJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
       

        rightJoystick = new Joystick(1);
        
        leftJoystick = new Joystick(0);
        
//        speedControlButton = new JoystickButton(leftJoystick, 1);
//        speedControlButton.whileHeld(new BallPickupCommand());

        speedControlButton = new JoystickButton(rightJoystick, 6);
        speedControlButton.whileHeld(new LiftRobotCommand());

        speedControlButton = new JoystickButton(rightJoystick, 7);
        speedControlButton.whileHeld(new LowerRobotCommand());
                       
        FeedbackApproachGearDropCommand feedbackApproach = new FeedbackApproachGearDropCommand();

        speedControlButton = new JoystickButton(rightJoystick, 11);
        speedControlButton.whenPressed(feedbackApproach);
        
        speedControlButton = new JoystickButton(rightJoystick, 1);
        speedControlButton.whenPressed(new ArcadeDriveWithJoystick());
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // SmartDashboard Buttons
        SmartDashboard.putData("LowerBallChuteCommand", new LowerBallChuteCommand());
        SmartDashboard.putData("RaiseBallChuteCommand", new RaiseBallChuteCommand());
        SmartDashboard.putData("ArcadeDriveWithJoystick", new ArcadeDriveWithJoystick());
        SmartDashboard.putData("Pickup Gear Command Group", new PickupGearCommandGroup());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Place Gear Command Group", new PlaceGearCommandGroup());
        SmartDashboard.putData("TankDriveWithJoysticks", new TankDriveWithJoysticks());
        SmartDashboard.putData("BallPickupCommand", new BallPickupCommand());
//        SmartDashboard.putData("ApproachGearDropCommand", new ApproachGearDropCommand());
        
        SmartDashboard.putData("LiftRobotCommand", new LiftRobotCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        SmartDashboard.putData("LocateGearDropUsingVisionCommand", new LocateGearDropUsingVisionCommand(5));
        SmartDashboard.putData("DriveForwardByDistanceCommand-12in", new DriveForwardByDistanceCommand(12, 0.3));       
        SmartDashboard.putData("RotateByAngleCommand-ninety", new RotateByAngleCommand(Math.toRadians(90), 0.3));       
        SmartDashboard.putData("AlignToGearDropCommand-right-fwd-left", new AlignToGearDropCommand(Math.toRadians(15), 24));       
        SmartDashboard.putData("FeedbackApproach", feedbackApproach);       
        SmartDashboard.putData("FeedbackApproachRight", new GearApproachRightCommandGroup());    
        
    	// Buttons for subsystems
//        SmartDashboard.putNumber(GEARFINDER_ANGLE_KEY, 0);
//        SmartDashboard.putNumber(GEARFINDER_SIZE_DIFF_KEY, 0);
//        SmartDashboard.putNumber(GEARFINDER_D2D_KEY, 0);
        SmartDashboard.putString(GEARFINDER_MODE, "off");
        SmartDashboard.putBoolean(GEARFINDER_FOUND_GEAR, false);
        SmartDashboard.putNumber(GEARFINDER_BLOB_COUNT, 0);
        SmartDashboard.putString(GEARFINDER_LOCATION, "NONE");
        SmartDashboard.putNumber(GEARFINDER_CURVE_GAIN, RobotMap.GEAR_APPROACH_CURVE_GAIN);
        SmartDashboard.putNumber(GEARFINDER_SPEED_GAIN, RobotMap.GEAR_APPROACH_SPEED_GAIN);
        SmartDashboard.putNumber("LeftEncoder", 0);
        SmartDashboard.putNumber("Distance", 0);
        SmartDashboard.putNumber("DistancePerPulse", RobotMap.DEFAULT_DISTANCE_PER_PULSE);
        SmartDashboard.putNumber(GYRO, 0);
        SmartDashboard.putNumber(DEFAULT_LINEAR_GAIN, RobotMap.DEFAULT_LINEAR_GAIN);
        SmartDashboard.putNumber(DEFAULT_ROTATION_GAIN, RobotMap.DEFAULT_ROTATION_GAIN);   
        SmartDashboard.putNumber(LIFT_GAIN, RobotMap.DEFAULT_LIFT_GAIN);   
        
        SmartDashboard.putNumber("GearApproachDist", 220);
        SmartDashboard.putNumber("GearApproachAngle", -56);
        
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLeftJoystick() {
        return leftJoystick;
    }

    public Joystick getRightJoystick() {
        return rightJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

