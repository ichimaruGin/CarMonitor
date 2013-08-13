package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class HighSpeedRecordXmlDS extends TerminalEventXmlDS{
	
	public static HighSpeedRecordXmlDS instance;

	public static HighSpeedRecordXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.EXCEPTION_CODE_HIGH_SPEED;
    	eventType = Constants.EVENT_TYPE_EXCEPTION;
        
        if (instance == null) {
            instance = new HighSpeedRecordXmlDS("HighSpeedEventXmlDS");
        }
        return instance;
    }
	
	public HighSpeedRecordXmlDS(String id) {
		super(id, true);
	}


}
