#!/usr/bin/env python3

from common import Team3543Robot
import wpilib

class MyRobot(Team3543Robot):
    def initEverythingElse(self):
        """
        Initializes everything else        
        """
        print("Hello from the robot")


# if this script is called, run the robot
if __name__ == '__main__':
    wpilib.run(MyRobot)