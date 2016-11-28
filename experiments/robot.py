#!/usr/bin/env python3

import wpilib
import wpilib.command as command

class MyRobot(wpilib.IterativeRobot):
    '''Main robot class'''
    
    def robotInit(self):
        '''Robot-wide initialization code should go here'''
        # Left-hand stick Y controls left side motor
        self.lstick = wpilib.Joystick(0)
        # Left-hand stick Z controls right side motor
        self.rstick = wpilib.Joystick(1)
        # self.l_motor = wpilib.Jaguar(1)
        # self.r_motor = wpilib.Jaguar(2)

        # Position gets automatically updated as robot moves
        # self.gyro = wpilib.AnalogGyro(1)

        # self.robot_drive = wpilib.RobotDrive(self.l_motor, self.r_motor)
        self.driveline_subsystem = Driveline(self)
        # self.motor = wpilib.Jaguar(4)

        self.limit1 = wpilib.DigitalInput(1)
        self.limit2 = wpilib.DigitalInput(2)

        self.position = wpilib.AnalogInput(2)

        # timer to time things
        self.timer = wpilib.Timer()

        self.autonomous_command = NavigateMaze(self)

    def disabledInit(self):
        '''Called when the robot is disabled'''
        pass

    def autonomousInit(self):
        '''Called when autonomous mode is enabled'''
        self.autonomous_command.start()

    def autonomousPeriodic(self):
        command.Scheduler.getInstance().run()
        # t = self.timer.get()
        # g = self.gyro.getAngle()
        # print("Gyro is {0}".format(g))
        # if g > -90.0:
        #     self.robot_drive.tankDrive(1.0, -1.0) # turn left
        # elif t < 3.0:
        #     self.robot_drive.tankDrive(-1.0, -1.0) # go straight
        # elif g < 0:
        #     self.robot_drive.tankDrive(-1.0, 1.0) # go right
        # elif t < 4.0:
        #     self.robot_drive.tankDrive(-1.0, -1.0) # go right            
        # elif g < 90:
        #     self.robot_drive.tankDrive(-1.0, 1.0) # go right            
        # elif t < 7.0:
        #     self.robot_drive.tankDrive(-1.0, -1.0) # go right                        
        # else:
        #     self.robot_drive.arcadeDrive(0,0) # stop            

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
        self.driveline_subsystem.drive(self.lstick, self.rstick)

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

class NavigateMaze(command.CommandGroup):
    '''Navigate the maze'''
    def __init__(self, robot, name=None):
        super().__init__(name=name)
        self.requires(robot.driveline_subsystem)
        self.robot = robot
        self.addSequential(TurnCommand(robot, amount=-90.0))
        self.addSequential(GoStraightCommand(robot, time=2.5))
        self.addSequential(TurnCommand(robot, amount=90.0))
        self.addSequential(GoStraightCommand(robot, time=2.5))
        self.addSequential(TurnCommand(robot, amount=90.0))
        self.addSequential(GoStraightCommand(robot, time=2.5))
        self.addSequential(TurnCommand(robot, amount=-90.0))
        self.addSequential(GoStraightCommand(robot, time=2.0))
        self.addSequential(TurnCommand(robot, amount=-90.0))
        self.addSequential(GoStraightCommand(robot, time=2.0))

    def end(self):
        print("ENDING")
        self.robot.driveline_subsystem.stop()        

class TurnCommand(command.Command):
    '''Make the robot turn'''
    def __init__(self, robot, name=None, timeout=None, amount=90.0):
        super(TurnCommand, self).__init__(name=name, timeout=timeout)
        self.robot = robot
        self.amount = amount
        self.initialHeading = 0.0

    def initialize(self):
        # set the initial heading
        print("Turning")
        self.initialHeading = self.robot.driveline_subsystem.getHeading()

    def isFinished(self):
        if self.amount < 0:
            return (self.robot.driveline_subsystem.getHeading() - self.initialHeading) <= self.amount
        else:
            return (self.robot.driveline_subsystem.getHeading() - self.initialHeading) >= self.amount

    def execute(self):
        if self.amount < 0:
            self.robot.driveline_subsystem.turn_left()
        else:
            self.robot.driveline_subsystem.turn_right()

class GoStraightCommand(command.Command):
    '''Go Straight ahead'''
    def __init__(self, robot, name=None, timeout=None, time=4.0):
        super().__init__(name=name, timeout=timeout)
        self.timer = wpilib.Timer()
        self.duration = time
        self.startTime = 0
        self.robot = robot
    
    def initialize(self):
        print("Going Straight")
        self.timer.start()
        self.startTime = self.timer.get()

    def execute(self):
        self.robot.driveline_subsystem.drive_forward()

    def isFinished(self):
        return (self.timer.get() - self.startTime) >= self.duration

class Driveline(command.Subsystem):
    '''Driveline subsystem'''
    def __init__(self, robot, name=None):
        '''Initialize'''
        super().__init__(name=name)
        self.robot = robot
        self.gyro = wpilib.AnalogGyro(1)
        self.left_motor = wpilib.Jaguar(1)
        self.right_motor = wpilib.Jaguar(2)
        self.robot_drive = wpilib.RobotDrive(self.left_motor, self.right_motor)

    def initialize(self, robot):
        """Docstring"""
        pass

    def getHeading(self):
        return self.gyro.getAngle()

    def drive(self, l, r):
        self.robot_drive.tankDrive(l, r)        

    def turn_left(self):
        self.drive(0.5, -0.5)

    def turn_right(self):
        self.drive(-0.5, 0.5)

    def drive_forward(self):
        '''Drive the robot forward for a time'''
        self.drive(-1,-1)
    
    def stop(self):
        self.drive(0,0)
        self.robot_drive.stopMotor()
    
if __name__ == '__main__':
    wpilib.run(MyRobot, physics_enabled=True)

