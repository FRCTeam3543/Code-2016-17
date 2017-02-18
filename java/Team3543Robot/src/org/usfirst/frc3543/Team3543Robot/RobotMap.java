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
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;
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
    public static Encoder driveLineQuadratureEncoderLeft;
    public static Encoder driveLineQuadratureEncoderRight;
    public static Servo ballPickupSubsystemServo1;
    public static Servo ballPickupSubsystemServo2;
    public static SpeedController ballPickupSubsystemVictorSpeedController;
    public static Ultrasonic rangeFinderSubSystemUltrasonicFront;
    public static Ultrasonic rangeFinderSubSystemUltrasonicRear;
    public static CANTalon liftSubsystemCANTalon5;
    public static CANTalon liftSubsystemCANTalon6;
    public static RobotDrive liftSubsystemLiftSubsystemDrive;
    public static Encoder liftSubsystemLiftSubsystemQuadratureEncoder;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static final String AXIS_CAMERA_HOST = "10.35.43.77";
    public static final int USB_CAMERA_PORT = 0;
    public static final double GEAR_APPROACH_SPEED_GAIN = 0.20;	// gain function for the gear-approach feedback controller 
    public static final double GEAR_APPROACH_CURVE_GAIN = -1;	// gain function for the gear-approach feedback controller 
    
    public static final double GEAR_SEARCH_GAIN = 0.4;	// gain function for the gear-search feedback controller
	public static final double MAX_SPEED = 0.25;
	public static final double MIN_SPEED = 0.12;	
    public static final double DRIVELINE_TIMER_DELAY = 0.01;	// delay passed to Timer.delay() on driveline calls
    public static final double DEFAULT_DISTANCE_PER_PULSE = 0.052;
    public static final double GYRO_SENSITIVITY = 0.0019;	// volts per degree per second
    public static final double DEFAULT_LINEAR_GAIN = 0.25;
    public static final double DEFAULT_ROTATION_GAIN = 0.3;
    public static final double DEFAULT_LIFT_GAIN = 0.9;
    
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
        driveLineQuadratureEncoderLeft = new Encoder(0, 1, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveLine", "QuadratureEncoderLeft", driveLineQuadratureEncoderLeft);
        double dpp = 0.044;
        driveLineQuadratureEncoderLeft.setDistancePerPulse(dpp);
        driveLineQuadratureEncoderLeft.setPIDSourceType(PIDSourceType.kRate);
        driveLineQuadratureEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("DriveLine", "QuadratureEncoderRight", driveLineQuadratureEncoderRight);
        driveLineQuadratureEncoderRight.setDistancePerPulse(dpp);
        driveLineQuadratureEncoderRight.setPIDSourceType(PIDSourceType.kRate);
        ballPickupSubsystemServo1 = new Servo(1);
        LiveWindow.addActuator("BallPickupSubsystem", "Servo 1", ballPickupSubsystemServo1);
        
        ballPickupSubsystemServo2 = new Servo(2);
        LiveWindow.addActuator("BallPickupSubsystem", "Servo 2", ballPickupSubsystemServo2);
        
        ballPickupSubsystemVictorSpeedController = new Victor(0);
        LiveWindow.addActuator("BallPickupSubsystem", "VictorSpeedController", (Victor) ballPickupSubsystemVictorSpeedController);
        
        rangeFinderSubSystemUltrasonicFront = new Ultrasonic(10, 11);
        LiveWindow.addSensor("RangeFinderSubSystem", "UltrasonicFront", rangeFinderSubSystemUltrasonicFront);
        
        rangeFinderSubSystemUltrasonicRear = new Ultrasonic(12, 13);
        LiveWindow.addSensor("RangeFinderSubSystem", "UltrasonicRear", rangeFinderSubSystemUltrasonicRear);
        
        liftSubsystemCANTalon5 = new CANTalon(5);
        LiveWindow.addActuator("LiftSubsystem", "CAN Talon 5", liftSubsystemCANTalon5);
        
        liftSubsystemCANTalon6 = new CANTalon(6);
        LiveWindow.addActuator("LiftSubsystem", "CAN Talon 6", liftSubsystemCANTalon6);
        
        liftSubsystemLiftSubsystemDrive = new RobotDrive(liftSubsystemCANTalon5, liftSubsystemCANTalon6);
        
        liftSubsystemLiftSubsystemDrive.setSafetyEnabled(true);
        liftSubsystemLiftSubsystemDrive.setExpiration(0.1);
        liftSubsystemLiftSubsystemDrive.setSensitivity(0.5);
        liftSubsystemLiftSubsystemDrive.setMaxOutput(1.0);
//        liftSubsystemLiftSubsystemDrive.setInvertedMotor(MotorType.kFrontRight, true);
        
        liftSubsystemLiftSubsystemQuadratureEncoder = new Encoder(8, 9, false, EncodingType.k4X);
        LiveWindow.addSensor("LiftSubsystem", "LiftSubsystemQuadratureEncoder", liftSubsystemLiftSubsystemQuadratureEncoder);
        liftSubsystemLiftSubsystemQuadratureEncoder.setDistancePerPulse(1.0);
        liftSubsystemLiftSubsystemQuadratureEncoder.setPIDSourceType(PIDSourceType.kRate);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
