#!/usr/bin/env python3

"""
Sample robot in python using RobotPy

Adapted from http://robotpy.readthedocs.io/en/latest/guide/anatomy.html#create-your-robot-code
"""

from team3543 import Team3543Robot as robot

import sys

# This imports the WPILib Python library
import wpilib

# set up a Robot controller by creating a class
class MyRobot(robot):
   pass

# if this script is called, run the robot
if __name__ == '__main__':
    wpilib.run(MyRobot)
    