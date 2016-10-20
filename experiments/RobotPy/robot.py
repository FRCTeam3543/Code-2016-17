#!/usr/bin/env python3

"""
Sample robot in python using RobotPy

Adapted from http://robotpy.readthedocs.io/en/latest/guide/anatomy.html#create-your-robot-code
"""

# This imports the WPILib Python library
import wpilib

# set up a Robot controller by creating a class
class Team3543Robot(wpilib.IterativeRobot):
    def robotInit(self):
        """
            Initialize the robot, called no matter what mode
        """
        self.initMotors()
        self.initSensors()

    def autonomousInit(self):
        """This function is run once each time the robot enters autonomous mode."""
        # replace "pass" with your code
        pass

    def autonomousPeriodic(self):
        """This function is called periodically during autonomous."""        
        # replace "pass" with your code
        pass

    def disabledInit(self):
        """Initialization code for disabled mode should go here.

        Users should override this method for initialization code which will be
        called each time the robot enters disabled mode.
        """
        # replace "pass" with your code
        pass

    def disabledPeriodic(self):
        """Initialization code for disabled mode should go here.

        Users should override this method for initialization code which will be
        called periodically when the robot is in disabled mode.
        """
        # replace "pass" with your code
        pass
        
    def initMotors(self):
        """Initialize motors"""
        # replace "pass" with your code        
        pass

    def initSensors(self):
        """Initialize sensors"""  
        # replace "pass" with your code              
        pass

    def teleopPeriodic(self):
        """This function is called periodically during operator control."""
        self.robot_drive.arcadeDrive(self.stick)

    def testPeriodic(self):
        """This function is called periodically during test mode."""
        wpilib.LiveWindow.run()

# if this script is called, run the robot
if __name__ == '__main__':
    wpilib.run(Team3543Robot)
    