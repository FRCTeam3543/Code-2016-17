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

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon driveLineCANTalon1;
    public static CANTalon driveLineCANTalon2;
    public static CANTalon driveLineCANTalon3;
    public static CANTalon driveLineCANTalon4;
    public static RobotDrive driveLineRobotDrive;
    public static AnalogGyro driveLineAnalogGyro1;
    public static SpeedController ballPickupSubsystemVictorSpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static final int USB_CAMERA_PORT = 0;
    public static final int GEAR_APPROACH_SPEED_GAIN = 1;	// gain function for the gear-approach feedback controller 
    
    public static final double GEAR_SEARCH_GAIN = 0.4;	// gain function for the gear-search feedback controller
    
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveLineCANTalon1 = new CANTalon(1);
        LiveWindow.addActuator("DriveLine", "CAN Talon 1", driveLineCANTalon1);
        
        driveLineCANTalon2 = new CANTalon(2);
        LiveWindow.addActuator("DriveLine", "CAN Talon 2", driveLineCANTalon2);
        
        driveLineCANTalon3 = new CANTalon(3);
        LiveWindow.addActuator("DriveLine", "CAN Talon 3", driveLineCANTalon3);
        
        driveLineCANTalon4 = new CANTalon(4);
        LiveWindow.addActuator("DriveLine", "CAN Talon 4", driveLineCANTalon4);
        
        driveLineRobotDrive = new RobotDrive(driveLineCANTalon3, driveLineCANTalon4,
              driveLineCANTalon1, driveLineCANTalon2);
        
        driveLineRobotDrive.setSafetyEnabled(true);
        driveLineRobotDrive.setExpiration(0.1);
        driveLineRobotDrive.setSensitivity(0.5);
        driveLineRobotDrive.setMaxOutput(1.0);

        driveLineAnalogGyro1 = new AnalogGyro(1);
        LiveWindow.addSensor("DriveLine", "AnalogGyro 1", driveLineAnalogGyro1);
        driveLineAnalogGyro1.setSensitivity(0.007);
        ballPickupSubsystemVictorSpeedController = new Victor(0);
        LiveWindow.addActuator("BallPickupSubsystem", "VictorSpeedController", (Victor) ballPickupSubsystemVictorSpeedController);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
