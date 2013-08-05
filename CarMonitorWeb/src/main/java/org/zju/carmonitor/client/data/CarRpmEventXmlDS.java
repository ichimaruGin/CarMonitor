package org.zju.carmonitor.client.data;

public class CarRpmEventXmlDS extends TerminalEventXmlDS{
	
	public static CarRpmEventXmlDS instance;

	public static CarRpmEventXmlDS getInstance(String terminalId_, String dataType_) {
    	terminalId = terminalId_;
    	dataType = dataType_;
        if (instance == null) {
            instance = new CarRpmEventXmlDS("CarRpmEventXmlDS");
        }
        return instance;
    }
	
	public CarRpmEventXmlDS(String id) {
		super(id);
	}

}
