package org.zju.carmonitor.client.data;

import java.util.HashMap;

import org.zju.carmonitor.client.CarMonitorUIService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.FieldType;

public class TerminalEventXmlDS extends DataSource{
	
	private static TerminalEventXmlDS instance = null;
	private static TerminalEventXmlDS instanceWithProcessFields = null;
	
	
	public static TerminalEventXmlDS getInstance(String terminalId_, String eventType_, String dataType_, boolean showProcess) {
		if (showProcess) {
			if (instance == null) {
	            instance = new TerminalEventXmlDS("terminalEventXmlDS", true);
	        }
	        CarMonitorUIService.App.getInstance().setDataParameter(terminalId_, eventType_, dataType_, new AsyncCallback<Void>() {

				public void onFailure(Throwable caught) {
				}

				public void onSuccess(Void result) {
					instance.fetchData();
				}
	    		
	    	});

	        return instance;

		} else {
			if (instanceWithProcessFields == null) {
				instanceWithProcessFields = new TerminalEventXmlDS("terminalEventXmlDS2", false);
	        }
	        CarMonitorUIService.App.getInstance().setDataParameter(terminalId_, eventType_, dataType_, new AsyncCallback<Void>() {

				public void onFailure(Throwable caught) {
				}

				public void onSuccess(Void result) {
					instanceWithProcessFields.fetchData();
				}
	    		
	    	});

	        return instanceWithProcessFields;

		}
    }


    public TerminalEventXmlDS(String id, boolean showProcess) {

        setID(id);
        setRecordXPath("/List/event");
        DataSourceTextField idField = new DataSourceTextField("id", null);
        idField.setHidden(true);
        idField.setPrimaryKey(true);
        
        DataSourceField terminal = new DataSourceTextField("terminal", "终端号");
        DataSourceField carRegNumber = new DataSourceTextField("carRegNumber", "车牌号");
        DataSourceField driverName = new DataSourceTextField("carDriverName", "司机姓名");
        
        DataSourceField sampleTime = new DataSourceTextField("time", "时间");
        DataSourceField value = new DataSourceTextField("value", "数值");
        DataSourceField process = new DataSourceTextField("processFlag", "处理状态");
        DataSourceField processButton = new DataSourceField("processButton", FieldType.ANY);

        
        if (!showProcess) {
        	process.setHidden(true);
        	processButton.setHidden(true);
        }
        
        setFields(idField, sampleTime, terminal, carRegNumber, driverName, value, process, processButton);
        
        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=events");

        setClientOnly(true);

    }


}