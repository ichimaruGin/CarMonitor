package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class TiredDriveRecordXmlDS extends TerminalEventXmlDS{
	
	public static TiredDriveRecordXmlDS instance;

	public static TiredDriveRecordXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	dataType = Constants.EXCEPTION_CODE_TIRED_DRIVE;
    	eventType = Constants.EVENT_TYPE_EXCEPTION;
        if (instance == null) {
            instance = new TiredDriveRecordXmlDS("TiredDriveRecordXmlDS");
        }
        return instance;
    }
	
	public TiredDriveRecordXmlDS(String id) {
		super(id, true);
	}



}
