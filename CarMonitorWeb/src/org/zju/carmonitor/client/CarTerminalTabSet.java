package org.zju.carmonitor.client;

import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class CarTerminalTabSet extends TabSet {
	
	private CarCanvas carCanvas = new CarCanvas();
	private TerminalCanvas terminalCanvas = new TerminalCanvas();
	
	public void fillCarData(String departmentId, String terminalId, String carRegNumber, 
			String carType, String driverName, String driverPhone) { 
		carCanvas.fillValues(departmentId, terminalId, carRegNumber, carType, driverName, driverPhone);
	}
	
	private Window parentWindow;
	public CarTerminalTabSet(Window outerWindow) {
		parentWindow = outerWindow;
		this.setWidth("100%");
		this.setHeight("100%");
		this.setTabBarPosition(Side.LEFT);
		Tab carTab = new Tab("车辆信息");
		carTab.setPane(carCanvas);
		
		ClickHandler handler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentWindow.destroy();
			}

		};

		carCanvas.getButtonLayout().setCancelClickHandler(handler);
		Tab terminalTab = new Tab("终端信息");
				
		terminalCanvas.getButtonLayout().setCancelClickHandler(handler);
		
		terminalTab.setPane(terminalCanvas);
		this.addTab(carTab);
		this.addTab(terminalTab);
	}
}
