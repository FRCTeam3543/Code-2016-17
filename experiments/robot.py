#!/usr/bin/env python3

import wpilib

class MyRobot(wpilib.IterativeRobot):
    '''Main robot class'''
    
    def robotInit(self):
        '''Robot-wide initialization code should go here'''        
        # Left-hand stick Y controls left side motor
        self.lstick = wpilib.Joystick(0)
        # Left-hand stick Z controls right side motor
        self.rstick = wpilib.Joystick(1)
        
        self.l_motor = wpilib.Jaguar(1)
        self.r_motor = wpilib.Jaguar(2)
        
        # Position gets automatically updated as robot moves
        self.gyro = wpilib.AnalogGyro(1)
        
        self.robot_drive = wpilib.RobotDrive(self.l_motor, self.r_motor)
        
        self.motor = wpilib.Jaguar(4)
        
        self.limit1 = wpilib.DigitalInput(1)
        self.limit2 = wpilib.DigitalInput(2)
        
        self.position = wpilib.AnalogInput(2)

        # timer to time things
        self.timer = wpilib.Timer()
        
    def disabledInit(self):
        '''Called when the robot is disabled'''
        pass

    def autonomousInit(self):
        '''Called when autonomous mode is enabled'''
        self.timer.start()

    def autonomousPeriodic(self):
        t = self.timer.get()
        if t < 0.28:
            self.robot_drive.tankDrive(1.0, -1.0) # turn left
        elif t < 3.0:
            self.robot_drive.tankDrive(-1.0, -1.0) # go straight
        elif t < 3.28:
            self.robot_drive.tankDrive(-1.0, 1.0) # go right
        elif t < 4.0:
            self.robot_drive.tankDrive(-1.0, -1.0) # go right            
        elif t < 4.28:
            self.robot_drive.tankDrive(-1.0, 1.0) # go right            
        elif t < 7.0:
            self.robot_drive.tankDrive(-1.0, -1.0) # go right                        
        else:
            self.robot_drive.arcadeDrive(0,0) # stop            

        # pass
        # if timer.get() < 2.0:
        # else:
        #     self.robot_drive.arcadeDrive(0, 0)
        # wpilib.Timer.delay(0.01)
        # self.robot_drive.arcadeDrive(-1.0, -.3)

    def teleopInit(self):
        pass

    def teleopPeriodic(self):
        '''Called when operation control mode is enabled'''
        self.robot_drive.tankDrive(self.lstick, self.rstick)

        # Move a motor with a Joystick
        # left_y = self.rstick.getY()
        # right_y = self.lstick.getY()

        # stop movement backwards when 1 is on
        # if self.limit1.get():
        #     left_y = max(0, left_y)
        #     right_y = max(0, right_y)

        # stop movement forwards when 2 is on
        # if self.limit2.get():
        #     left_y = min(0, left_y)
        #     right_y = min(0, right_y)
        # self.l_motor.set(left_y)
        # self.l_motor.set(right_y)
#        self.motor.set(y)

if __name__ == '__main__':
    wpilib.run(MyRobot,
               physics_enabled=True)

