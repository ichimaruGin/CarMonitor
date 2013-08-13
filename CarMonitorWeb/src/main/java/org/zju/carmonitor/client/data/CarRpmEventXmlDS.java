package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class CarRpmEventXmlDS extends TerminalEventXmlDS{
	
	public static CarRpmEventXmlDS instance;

	public static CarRpmEventXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.CAR_RPM_PARAM;
    	eventType = Constants.EVENT_TYPE_CAT718;
        if (instance == null) {
            instance = new CarRpmEventXmlDS("CarRpmEventXmlDS");
        }
        return instance;
    }
	
	public CarRpmEventXmlDS(String id) {
		super(id, false);
	}

}
