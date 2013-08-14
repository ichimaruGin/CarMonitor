package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class DrunkDriveRecordXmlDS extends TerminalEventXmlDS{

	public static DrunkDriveRecordXmlDS instance;

	public static DrunkDriveRecordXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.EXCEPTION_CODE_DRUNK;
    	eventType = Constants.EVENT_TYPE_EXCEPTION;
        if (instance == null) {
            instance = new DrunkDriveRecordXmlDS("DrunkDriveRecordXmlDS");
        }
        return instance;
    }
	
	public DrunkDriveRecordXmlDS(String id) {
		super(id, true);
	}

}
