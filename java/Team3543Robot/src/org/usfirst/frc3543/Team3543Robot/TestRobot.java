package org.usfirst.frc3543.Team3543Robot;

public class TestRobot extends Robot {

	private static TestRobot _robot = null;
	
	public static TestRobot instance() {
		if (_robot == null) {
			_robot = new TestRobot();
			_robot.robotInit();
		}
		return _robot;
	}
	
}
