package org.zju.car_monitor.db;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jiezhen 7/21/13
 */
@Entity
@DiscriminatorValue("CAT718")
public class CAT718EventAttribute extends TerminalEventAttribute{

    public static String CAR_WATER_TEMP_PARAM = "CAR_WATER_TEMP_PARAM";
    public static String CAR_OIL_PARAM = "CAR_OIL_PARAM";
    public static String CAR_SPEED_PARAM = "CAR_SPEED_PARAM";
    public static String CAR_RPM_PARAM = "CAR_RPM_PARAM";
    public static String DRUNK_DRIVE = "DRUNK_DRIVE";
    public static String TIRED_DRIVE_STATE = "TIRED_DRIVE_STATE";
    public static String CAR_LATITUDE = "CAR_LATITUDE";
    public static String CAR_LONGITUDE = "CAR_LONGITUDE";
    
    private static CAT718EventAttribute carWaterTempAttribute = null;
    

    public static CAT718EventAttribute carWaterTempAttribute() {
    	if (carWaterTempAttribute == null){
    		return (CAT718EventAttribute) findByCode("CAR_WATER_TEMP_PARAM");
    	} else 
    		return carWaterTempAttribute;
    	
    }
    
    private static CAT718EventAttribute carOilAttribute = null;
    
    public static CAT718EventAttribute carOilAttribute() {
    	if (carOilAttribute == null) {
    		carOilAttribute = (CAT718EventAttribute) findByCode("CAR_OIL_PARAM");
    	} 
    	return carOilAttribute;
    	
    }
    
    private static CAT718EventAttribute carSpeedAttribute = null; 
    public static CAT718EventAttribute carSpeedAttribute() {
    	if (carSpeedAttribute == null) {
    		carSpeedAttribute = (CAT718EventAttribute) findByCode("CAR_SPEED_PARAM");
    	} 
    		return carSpeedAttribute;
    	
    }
    private static CAT718EventAttribute carRpmAttribute = null;
    public static CAT718EventAttribute carRpmAttribute() {
    	if (carRpmAttribute == null) {
    		carRpmAttribute = (CAT718EventAttribute) findByCode("CAR_RPM_PARAM");
    	} 
    	return carRpmAttribute;
    	
    }
    
    private static CAT718EventAttribute drunkDriveAttribute = null;
    public static CAT718EventAttribute drunkDriveAttribute() {
    	if (drunkDriveAttribute == null) {
    		drunkDriveAttribute = (CAT718EventAttribute) findByCode("DRUNK_DRIVE");	
    	}
    	return drunkDriveAttribute;
    	
    }
    
    private static CAT718EventAttribute tiedDriveStateAttribute = null;
    public static CAT718EventAttribute tiedDriveStateAttribute() {
    	if (tiedDriveStateAttribute == null) {
    		tiedDriveStateAttribute = (CAT718EventAttribute) findByCode("TIRED_DRIVE_STATE");
    	} 
    	return tiedDriveStateAttribute;
    
    }
    
    private static CAT718EventAttribute carLattAttribute = null;
    public static CAT718EventAttribute carLatitudeAttribute() {
    	if (carLattAttribute == null) {
    		carLattAttribute = (CAT718EventAttribute) findByCode("CAR_LATITUDE");
    	} return carLattAttribute;
    	
    }
    
    private static CAT718EventAttribute carLongitudeAttribute = null;
    public static CAT718EventAttribute carLongitudeAttribute() {
    	if (carLongitudeAttribute == null) {
    		carLongitudeAttribute = (CAT718EventAttribute) findByCode("CAR_LONGITUDE");
    	} 
    	return carLongitudeAttribute;
    	
    }
}
