package org.zju.carmonitor.client.data;

public class CarSpeedEventXmlDS extends TerminalEventXmlDS{
	
	public static CarSpeedEventXmlDS instance;

	public static CarSpeedEventXmlDS getInstance(String terminalId_, String dataType_) {
    	terminalId = terminalId_;
    	dataType = dataType_;
        if (instance == null) {
            instance = new CarSpeedEventXmlDS("carSpeedEventXmlDS");
        }
        return instance;
    }
	
	public CarSpeedEventXmlDS(String id) {
		super(id);
	}

}
