package org.zju.carmonitor.client.data;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class TerminalEventXmlDS extends DataSource{
	private static TerminalEventXmlDS instance = null;
	protected static String terminalId = null;
	protected static String dataType = null;
	protected static String eventType = null;
	
    public static TerminalEventXmlDS getInstance(String eventType_, String terminalId_, String dataType_, boolean showProcess) {
    	terminalId = terminalId_;
    	dataType = dataType_;
    	eventType = eventType_;
        if (instance == null) {
            instance = new TerminalEventXmlDS("terminalEventXmlDS", showProcess);
        }
        return instance;
    }

    public TerminalEventXmlDS(String id, boolean showProcess) {

        setID(id);
        setRecordXPath("/List/event");
        DataSourceTextField idField = new DataSourceTextField("id", null);
        idField.setHidden(true);
        idField.setPrimaryKey(true);
        
        DataSourceField sampleTime = new DataSourceTextField("time", "时间");
        DataSourceField value = new DataSourceTextField("value", "数值");
        DataSourceField process = new DataSourceTextField("processFlag", "处理状态");
        DataSourceField processButton = new DataSourceField("processButton", FieldType.ANY);

        
        if (!showProcess) {
        	process.setHidden(true);
        	processButton.setHidden(true);
        }
        
        setFields(idField, sampleTime, value, process, processButton);
        
        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=events&terminalId="+terminalId + "&eventType=" + eventType + "&dataType=" + dataType);

        setClientOnly(true);

    }


}
