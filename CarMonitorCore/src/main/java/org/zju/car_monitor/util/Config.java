package org.zju.car_monitor.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * User: jiezhen
 * Date: 3/21/13
 * Time: 2:30 PM
 */
public class Config {
    
	private static Properties properties = null;
	static {
		try {
			
			InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("/app.properties");
			properties = new Properties();
			properties.load(inputStream);
			TIRED_DRIVE_REST_TIME = Long.parseLong((String) properties.get("tired_drive.rest_time")) * 60 * 1000;
			TIRED_DRIVE_MAX_DRIVE_TIME = Long.parseLong((String) properties.get("tired_drive.max_drive_time")) * 60 * 1000;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public static long TIRED_DRIVE_REST_TIME; 
	
	public static long TIRED_DRIVE_MAX_DRIVE_TIME; 
	
	

}
