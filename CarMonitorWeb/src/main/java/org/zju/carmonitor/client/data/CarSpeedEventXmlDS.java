package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class CarSpeedEventXmlDS extends TerminalEventXmlDS{
	
	public static CarSpeedEventXmlDS instance;

	public static CarSpeedEventXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.CAR_SPEED_PARAM;
    	eventType = Constants.EVENT_TYPE_CAT718;
        if (instance == null) {
            instance = new CarSpeedEventXmlDS("carSpeedEventXmlDS");
        }
        return instance;
    }
	
	public CarSpeedEventXmlDS(String id) {
		super(id, false);
	}

}
