package org.zju.carmonitor.client.data;

import org.zju.car_monitor.client.Constants;

public class CarObdRecordXmlDS extends TerminalEventXmlDS{
	
	public static CarObdRecordXmlDS instance;

	public static CarObdRecordXmlDS getInstance(String terminalId_) {
    	terminalId = terminalId_;
    	// not used
    	dataType = Constants.EXCEPTION_CODE_OBD_ERR;
    	eventType = Constants.EVENT_TYPE_EXCEPTION;
        if (instance == null) {
            instance = new CarObdRecordXmlDS("CarObdEventXmlDS");
        }
        return instance;
    }
	
	public CarObdRecordXmlDS(String id) {
		super(id, true);
	}


}
