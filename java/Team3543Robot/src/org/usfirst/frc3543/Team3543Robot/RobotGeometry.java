package org.usfirst.frc3543.Team3543Robot;

/**
 * Robot geometry - 
 * 
 * @author mfkah
 *
 */
public class RobotGeometry {
	
	// center-to-center wheel base, in inches
	public double wheelBase;	// FIXME - need the accurate value
	public double overallWidth;
	public double overallLength;
	public double overallHeight;	
	public double cameraY; 	// camera height from ground
	public double cameraX; 	// camera distance from robot centerline
	public double cameraZ; 	// camera distance from robot centerline (back from face)
	public double frontAxleZ;	// distance from the nose of the robot to the front Axle
	
	public RobotGeometry() {
		// TODO - set all these robot geometry values
		wheelBase = 24;
		overallWidth = 36;
		overallLength = overallWidth;
		overallHeight = 24;
		cameraY = 18;
		cameraX = 0;
		cameraZ = 4;		
		frontAxleZ = 4;	// ???
	}
	
	
}
