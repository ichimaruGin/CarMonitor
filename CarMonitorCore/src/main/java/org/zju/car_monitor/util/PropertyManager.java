package org.zju.car_monitor.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Date: 3/20/13
 * Time: 2:22 PM
 */
public class PropertyManager {
	private static Logger logger = Logger.getLogger(PropertyManager.class);
	private static Properties properties;
	static {
		logger.debug("Initializing property Manager");
		properties = new Properties();
		try {
			properties.load(new FileInputStream("/properties/cashmanager.properties"));
			
		} catch (FileNotFoundException e) {
			try {
				properties.load(new FileInputStream("D:\\properties\\cashmanager.properties"));
			} catch (FileNotFoundException e1) {
				logger.error("File Not found", e);
			} catch (IOException e1) {
				logger.error(e1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String IN_FOLDER = "FILE_IN_FOLDER";
	private static String OUT_FOLDER = "FILE_OUT_FOLDER";
	private static String IMG_FOLDER = "IMAGE_FOLDER";
	private static String MYSQL_URL = "MYSQL_URL";
	private static String MYSQL_USERNAME = "MYSQL_USERNAME";
	private static String MYSQL_PASSWORD = "MYSQL_PASSWORD";
	private static String ENV = "ENV";
	private static String UPLOAD_FOLDER = "UPLOAD_FOLDER";
	
	
	public static String getEnv(){
		return getProperty(ENV);
	}
	
	public static String getInFileFolder(){
		return getProperty(IN_FOLDER);
	}
	
	public static String getMySQLURL(){
		return getProperty(MYSQL_URL);
	}
	
	public static String getMySQLUserName(){
		return getProperty(MYSQL_USERNAME);
	}
	
	public static String getMySQLPassword(){
		return getProperty(MYSQL_PASSWORD);
	}
	
	public  static String getOutFileFolder(){
		return getProperty(OUT_FOLDER);
	}
	public  static String getImgFileFolder(){
		return getProperty(IMG_FOLDER);
	}
	
	public static String getUploadFoder() {
		return getProperty(UPLOAD_FOLDER);
	}
	
	private static String getProperty(String prop){
		
		String str = System.getProperty(prop); 
		if (str == null){
			str = properties.getProperty(prop);
		}
		if (str == null){
			str = "";
		}
		logger.debug("Got property for " + prop + " " + str);
		return str;
	}
}
