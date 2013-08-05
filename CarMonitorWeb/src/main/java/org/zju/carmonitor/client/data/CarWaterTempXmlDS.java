package org.zju.carmonitor.client.data;


public class CarWaterTempXmlDS extends TerminalEventXmlDS{
	
	public static CarWaterTempXmlDS instance;

	public static CarWaterTempXmlDS getInstance(String terminalId_, String dataType_) {
    	terminalId = terminalId_;
    	dataType = dataType_;
        if (instance == null) {
            instance = new CarWaterTempXmlDS("carWaterTempXmlDS");
        }
        return instance;
    }
	
	public CarWaterTempXmlDS(String id) {
		super(id);
	}

}
