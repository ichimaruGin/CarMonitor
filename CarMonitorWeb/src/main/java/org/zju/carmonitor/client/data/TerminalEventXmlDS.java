package org.zju.carmonitor.client.data;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class TerminalEventXmlDS extends DataSource{
	private static TerminalEventXmlDS instance = null;
	protected static String terminalId = null;
	protected static String dataType = null;
	
    public static TerminalEventXmlDS getInstance(String terminalId_, String dataType_) {
    	terminalId = terminalId_;
    	dataType = dataType_;
        if (instance == null) {
            instance = new TerminalEventXmlDS("terminalEventXmlDS");
        }
        return instance;
    }

    public TerminalEventXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/event");
        DataSourceTextField idField = new DataSourceTextField("id", null);
        idField.setHidden(true);
        idField.setPrimaryKey(true);
        
        DataSourceField sampleTime = new DataSourceTextField("time", "采样时间");
        DataSourceField value = new DataSourceTextField("value", "数值");
        
        setFields(idField, sampleTime, value);
        
        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=events&terminalId="+terminalId + "&type=" + dataType);

        setClientOnly(true);

    }


}
