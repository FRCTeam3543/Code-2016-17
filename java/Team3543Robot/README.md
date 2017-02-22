# 2017 Team 3543 Robot

## Design Notes

ToDo...

## Calibration of the wheel encoders.

1. Put the robot at a known position, mark front edge with tape
2. enter 24 into the "Drive Forward Distance" and put in Teleop mode
3. click "Drive Forward"
4. measure distance traveled
5. change "Wheel encoder distance per pulse" to be current_value * 24 / distance_traveled
6. re-test (go to step 1) until satisfied with calibration

## Troubleshooting

Here are some troubleshooting tips.

### Suddenly Driver Station cannot communicate with RIO

Symptoms: "Address already in use" errors, RoboRio-3543-FRC.local not found, despite connection to router.  Happens on Windows (at least).

Solution: 1) Open windows Control Panel, then Services. 2) bounce (restart) all the NI services.

### Robot banging into gear drop in autonomous mode

Re-calibrate the wheel encoders.  See "calibration" above.


### Robot "shuddering" when moving with arcade drive

Check the battery charge, this is usually due to a low battery.