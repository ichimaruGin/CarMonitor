package org.zju.carmonitor.client;

import org.zju.car_monitor.client.CarDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class CarCanvas extends VLayout {
	private SelectItem department = new DepartmentSelectItem();
	private SelectItem terminal = new TerminalSelectItem();
	private TextItem carRegNumberTextItem = new TextItem("车辆号码");
	private TextItem carTypeTextItem = new TextItem("车辆类型");
	private TextItem driverNameTextItem = new TextItem("司机姓名");
	private TextItem driverPhoneTextItem = new TextItem("司机手机");
	private DynamicForm form = new LineDataHolder(4, department, terminal, 
			carRegNumberTextItem, carTypeTextItem, 
			driverNameTextItem, driverPhoneTextItem);
	
	public void fillValues(String departmentId, String terminalId, String carRegNumber, 
		String carType, String driverName, String driverPhone) {
		department.setValue(departmentId);
		terminal.setValue(terminalId);
		carRegNumberTextItem.setValue(carRegNumber);
		carTypeTextItem.setValue(carType);
		driverNameTextItem.setValue(driverName);
		driverPhoneTextItem.setValue(driverPhone);
	}
	
	public CarCanvas(ListGrid list, String departmentId, String terminalId, String carRegNumber, 
			String carType, String driverName, String driverPhone) {
		this(list);
		department.setValue(departmentId);
		terminal.setValue(terminalId);
		carRegNumberTextItem.setValue(carRegNumber);
		carTypeTextItem.setValue(carType);
		driverNameTextItem.setValue(driverName);
		driverPhoneTextItem.setValue(driverPhone);
	}
	
	public LayoutWithButtons getButtonLayout() {
		return buttonLayout;
	}
	
	private LayoutWithButtons buttonLayout;
    
	private ListGrid listGrid;
	public void setListGrid(ListGrid list) {
		listGrid = list;
	}
	
	public CarCanvas(ListGrid list) {
	    this.setWidth100();
        this.setHeight100();
        this.setMembersMargin(5);
        this.setMargin(5);
        this.setListGrid(list);
        buttonLayout = new LayoutWithButtons(this);
        buttonLayout.setOkClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				String departmentId = department.getValueAsString();
				String terminalId = terminal.getValueAsString();
				String carRegNumber = carRegNumberTextItem.getValueAsString();
				String carTypeString = carTypeTextItem.getValueAsString();
				String driverNameString = driverNameTextItem.getValueAsString();
				String driverPhoneString = driverPhoneTextItem.getValueAsString();
				if(departmentId == null || carRegNumber == null || carTypeString == null
						|| driverNameString == null || driverPhoneString == null) {
					SC.say("请在保存之前填好相应属性");
					return;
				}
				
				CarDto carDto = new CarDto();
				carDto.setCarRegNumber(carRegNumber);
				carDto.setDepartmentId(departmentId);
				carDto.setCarType(carTypeString);
				carDto.setDriverName(driverNameString);
				carDto.setDriverPhone(driverPhoneString);
				carDto.setTerminalId(terminalId);
				CarMonitorUIService.App.getInstance().saveCar(carDto, new AsyncCallback<Void>(){

					public void onFailure(Throwable caught) {
						SC.say("保存失败");
					}

					public void onSuccess(Void result) {
						if (listGrid != null) {
							listGrid.invalidateCache();
							listGrid.getDataSource().invalidateCache();
							listGrid.getDataSource().fetchData();
							listGrid.fetchData();
						}
						SC.say("保存成功");
					}
					
				});
			}
        	
        });
        
        this.addMember(form);
        this.addMember(buttonLayout);

	}
}
