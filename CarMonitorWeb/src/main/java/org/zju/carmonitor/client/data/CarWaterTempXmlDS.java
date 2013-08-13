package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;


public class CarWaterTempXmlDS extends TerminalEventXmlDS{
	
	public static CarWaterTempXmlDS instance;

	public static CarWaterTempXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.CAR_WATER_TEMP_PARAM;
    	eventType = Constants.EVENT_TYPE_CAT718;
        
        if (instance == null) {
            instance = new CarWaterTempXmlDS("carWaterTempXmlDS");
        }
        return instance;
    }
	
	public CarWaterTempXmlDS(String id) {
		super(id, false);
	}

}
