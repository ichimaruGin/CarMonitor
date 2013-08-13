package org.zju.carmonitor.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ExceptionListGrid extends TerminalEventListGrid{

	public ExceptionListGrid(DataSource ds) {
		super(ds);
		
        setUseAllDataSourceFields(true);
        setAlign(Alignment.CENTER);
        setShowRecordComponents(true);
        setShowRecordComponentsByCell(true);
        
        ListGridField idField = new ListGridField("id", "id");
        idField.setHidden(true);
        
        ListGridField timeField = new ListGridField("time", "时间");
        ListGridField valueField = new ListGridField("value", "数值");
        ListGridField processFlag = new ListGridField("processFlag", "处理状态");
        ListGridField processButton = new ListGridField("processButton", " ");
        processButton.setAlign(Alignment.CENTER);
        
        setFields(idField, timeField, valueField, processFlag, processButton);
        setCanEdit(false);
        setCanDragRecordsOut(true);
	}
	
	@Override
	 protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum);
		if (fieldName.equals("processButton")) {
		    return new ProcessButton(this, record);  
		} else {
			return null;			
		}
	 }
	

}

class ProcessButton extends IButton {
	
	public ProcessButton(final ListGrid listGrid, final ListGridRecord record) {
        this.setHeight(18);  
        this.setWidth(80);                      
        this.setTitle("设为已处理");  
        this.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
            	
            	String id = record.getAttribute("id");
            	CarMonitorUIService.App.getInstance().processException(id, new AsyncCallback<Void>(){

					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					public void onSuccess(Void result) {
						SC.say("处理完毕");
						listGrid.getDataSource().invalidateCache();
						listGrid.getDataSource().fetchData();
						listGrid.invalidateCache();
						listGrid.fetchData();
					}
            		
            	});
            	
            }  
        });  
	}
	
}

