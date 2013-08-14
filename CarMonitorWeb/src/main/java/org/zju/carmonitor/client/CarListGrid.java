package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

class CarTerminalEditButton extends IButton {
	
	public CarTerminalEditButton(final ListGrid listGrid, final ListGridRecord record) {
        this.setHeight(18);  
        this.setWidth(80);                      
        this.setTitle("查看更新配置参数");  
        this.addClickHandler(new ClickHandler() {  
            public void onClick(ClickEvent event) {
            	String departmentId = record.getAttribute("departmentId"); 
            	String terminalId_ = record.getAttribute("terminalId_");
            	String carRegNumber = record.getAttribute("carRegNumber");
    			String carType = record.getAttribute("carType");
    			String driverName = record.getAttribute("driverName");
    			String driverPhone = record.getAttribute("driverPhone");
    			Window carTerminalWindow = new Window();
    			carTerminalWindow.setHeight("50%");
    			carTerminalWindow.setWidth("50%");
    			carTerminalWindow.setTitle("查看更新车辆终端参数");
    			carTerminalWindow.setShowMinimizeButton(false);
    			carTerminalWindow.setIsModal(true);
    			carTerminalWindow.setShowModalMask(true);
    			carTerminalWindow.centerInPage();
    			CarTerminalTabSet tabSet = new CarTerminalTabSet(carTerminalWindow, listGrid);
    			tabSet.fillCarData(departmentId, terminalId_, carRegNumber, carType, driverName, driverPhone);
    			carTerminalWindow.addItem(tabSet);
    			carTerminalWindow.show();
            }  
        });  
	}
	
}


public class CarListGrid extends ListGrid {
	
	
	@Override
	 protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
		String fieldName = this.getFieldName(colNum);
		if (fieldName.equals("carEditButton")) {
		    return new CarTerminalEditButton(this, record);  
		} else {
			return null;			
		}
	 }
	 
    public CarListGrid(DataSource carXmlDs) {

        setDataSource(carXmlDs);
        setUseAllDataSourceFields(true);

        setAlign(Alignment.CENTER);
        setShowRecordComponents(true);
        setShowRecordComponentsByCell(true);
        ListGridField terminalId = new ListGridField("terminalId", "终端号");
        terminalId.setAlign(Alignment.CENTER);
        terminalId.setShowHover(true);
        terminalId.setWidth(100);
        ListGridField terminalId_ = new ListGridField("terminalId_");
        terminalId_.setHidden(true);
        ListGridField departmentName = new ListGridField("departmentName");
        departmentName.setAlign(Alignment.CENTER);
        departmentName.setWidth(250);
        ListGridField departmentId = new ListGridField("departmentId");
        departmentId.setHidden(true);
        ListGridField carRegNumber = new ListGridField("carRegNumber");
        carRegNumber.setAlign(Alignment.CENTER);
        ListGridField carDriverName = new ListGridField("driverName");
        carDriverName.setShowHover(true);
        ListGridField carDriverPhone = new ListGridField("driverPhone");
        carDriverPhone.setAlign(Alignment.CENTER);
        carDriverName.setAlign(Alignment.CENTER);
        ListGridField carType = new ListGridField("carType");
        carType.setAlign(Alignment.CENTER);
        
        ListGridField carEditButton = new ListGridField("carEditButton", " ");
        carEditButton.setAlign(Alignment.CENTER);
        setFields(terminalId, terminalId_, carRegNumber, departmentId, departmentName, carDriverName, carDriverPhone, carType, carEditButton);
        setCanEdit(false);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
    }
}