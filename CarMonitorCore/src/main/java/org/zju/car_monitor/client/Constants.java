package org.zju.car_monitor.client;

public interface Constants extends java.io.Serializable{
	public static String CAR_WATER_TEMP_PARAM = "CAR_WATER_TEMP_PARAM";
    public static String CAR_OIL_PARAM = "CAR_OIL_PARAM";
    public static String CAR_SPEED_PARAM = "CAR_SPEED_PARAM";
    public static String CAR_RPM_PARAM = "CAR_RPM_PARAM";
   
    public static String EXCEPTION_CODE_OBD_ERR = "CAR_OBD_ERR";
    public static String EXCEPTION_CODE_HIGH_SPEED = "HIGH_SPEED";
    public static String EXCEPTION_CODE_TIRED_DRIVE = "TIRED_DRIVE";
    public static String EXCEPTION_CODE_DRUNK = "DRUNK_DRIVE";
    
    public static String DRUNK_DRIVE = "DRUNK_DRIVE";
    public static String TIRED_DRIVE_STATE = "TIRED_DRIVE_STATE";
    
    
    public static String CAR_LATITUDE = "CAR_LATITUDE";
    public static String CAR_LONGITUDE = "CAR_LONGITUDE";
    
    public static String EVENT_TYPE_CAT718 = "CAT718";
    public static String EVENT_TYPE_EXCEPTION = "EXCEPTION";
    
    public int TIME_OUT_SECONDS = 30;
    

}
