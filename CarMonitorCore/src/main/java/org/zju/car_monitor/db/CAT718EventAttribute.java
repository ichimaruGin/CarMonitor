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

    public static CAT718EventAttribute carWaterTempAttribute = (CAT718EventAttribute) findByCode("CAR_WATER_TEMP_PARAM");
    public static CAT718EventAttribute carOilAttribute = (CAT718EventAttribute) findByCode("CAR_OIL_PARAM");
    public static CAT718EventAttribute carSpeedAttribute = (CAT718EventAttribute) findByCode("CAR_SPEED_PARAM");
    public static CAT718EventAttribute carRpmAttribute = (CAT718EventAttribute) findByCode("CAR_RPM_PARAM");
    public static CAT718EventAttribute drunkDriveAttribute = (CAT718EventAttribute) findByCode("DRUNK_DRIVE");
    public static CAT718EventAttribute tiedDriveStateAttribute = (CAT718EventAttribute) findByCode("TIRED_DRIVE_STATE");
    public static CAT718EventAttribute carLatitudeAttribute = (CAT718EventAttribute) findByCode("CAR_LATITUDE");
    public static CAT718EventAttribute carLongitudeAttribute = (CAT718EventAttribute) findByCode("CAR_LONGITUDE");
}
