package org.usfirst.frc3543.Team3543Robot.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Number provider to fetch a value from a key in the SmartDashboard
 * 
 * @author MK
 */
public class SmartDashboardNumberProvider implements NumberProvider {
	private String key;
	private double defaultValue;
	
	public SmartDashboardNumberProvider(String key, double defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	@Override
	public double getValue() {
		return SmartDashboard.getNumber(key, defaultValue);
	}

}
