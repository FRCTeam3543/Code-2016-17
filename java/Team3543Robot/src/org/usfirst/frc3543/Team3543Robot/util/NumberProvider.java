package org.usfirst.frc3543.Team3543Robot.util;

/**
 * Interface to something that provides input to a command.  
 * 
 * Intended to be used by an initialize() method to get the inputs/gains etc. to the command
 * 
 * @author MK
 *
 */
public interface NumberProvider {

	public double getValue();
	
	public static NumberProvider fixedValue(double value) {
		return new NumberProvider() {

			@Override
			public double getValue() {
				return value;
			}
		};
	}
}
