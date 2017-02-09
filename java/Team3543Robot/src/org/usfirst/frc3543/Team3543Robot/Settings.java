package org.usfirst.frc3543.Team3543Robot;

import java.io.IOException;
import java.util.Properties;

/**
 * This class contains the ONLY settings you should be tweaking at runtime!
 * 
 * @author mfkah
 *
 */
public class Settings {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("robot.properties"));
		} catch (IOException e) {
			System.err.println("Failed to load robot.properties");
			e.printStackTrace();			
		}
	}
	
	public static String get(String key) {
		return get(key, null);
	}
	
	public static String get(String key, String defaultValue) {
		String val = properties.getProperty(key);
		if (val == null) {
			return defaultValue;
		}
		else {
			return val;
		}
	}
}
