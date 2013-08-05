package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.Label;

import org.zju.car_monitor.client.CAT718TerminalEventDto;
import org.zju.car_monitor.client.CATOBDTerminalEventDto;
import org.zju.car_monitor.client.Constants;
import org.zju.carmonitor.client.data.CarRpmEventXmlDS;
import org.zju.carmonitor.client.data.CarSpeedEventXmlDS;
import org.zju.carmonitor.client.data.CarWaterTempXmlDS;

public class CarStatusViewPane extends TabSet {
	
	private StaticTextItem speedTextItem = new StatusTextItem("当前车速");
	private ButtonItem speedButtonItem = new StatusButtonItem("查看车速历史纪录");
	private StaticTextItem rpmTextItem = new StatusTextItem("当前转速");
	private ButtonItem rpmButtonItem = new StatusButtonItem("查看转速历史纪录");
	private StaticTextItem waterTempTextItem = new StatusTextItem("当前水温");
	private ButtonItem waterTempButtonItem = new StatusButtonItem("查看水温历史纪录");
	private StaticTextItem positionItem = new StatusTextItem("当前位置(经纬度)");
	private ButtonItem positionButtonItem = new StatusButtonItem("查看地图位置信息");
	private Tab carStatusTab = new Tab("车辆状态");
	private Label eventUpdateTime = new com.smartgwt.client.widgets.Label("最近更新时间:");
	private static DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	
	private StaticTextItem powerErrItem = new StatusTextItem("动力系统故障");
	private StaticTextItem bodyErrItem = new StatusTextItem("车身类故障");
	private StaticTextItem diPanErrItem = new StatusTextItem("底盘类故障");
	private StaticTextItem networkErrItem = new StatusTextItem("网络类故障");
	
	private String selectedTerminalId = null;

	private void updateCurrentTime() {
		eventUpdateTime.setContents("最近自动刷新时间:  " + format.format(new java.util.Date(System.currentTimeMillis())));
	}
	
	public void updateCarStatus(String terminalId, String carRegNumber) {
		this.selectedTerminalId = terminalId;
		carStatusTab.setTitle(carRegNumber);
        CarMonitorUIService.App.getInstance().getCAT718TerminalEvent(terminalId, new AsyncCallback<CAT718TerminalEventDto>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(CAT718TerminalEventDto result) {
                if (result != null) {
                    speedTextItem.setValue(result.getCurrentSpeed());
                    rpmTextItem.setValue(result.getCurrentRpm());
                    waterTempTextItem.setValue(result.getCurrentWaterTemp());
                    positionItem.setValue("(" + result.getCurrentLatitude() + "," + result.getCurrentLongitude() + ")");
                    updateCurrentTime();
                }
            }
        });
        
        CarMonitorUIService.App.getInstance().getCATOBDTerminalEvent(terminalId, new AsyncCallback<CATOBDTerminalEventDto>() {

			public void onFailure(Throwable caught) {
				
			}
			
			private void setErrMessage(StaticTextItem item, String message) {
				if (message == null || message.length() ==0) {
					item.setValue("无");
				} else {
					item.setValue(message);
				}
			}

			public void onSuccess(CATOBDTerminalEventDto result) {
				if (result != null) {
					setErrMessage(powerErrItem, result.getPowerErrMessage());
					setErrMessage(bodyErrItem, result.getBodyErrMessage());
					setErrMessage(diPanErrItem, result.getChassisErrMessage());
					setErrMessage(networkErrItem, result.getNetworkErrMessage());
				}
				
			}
        	
		});
	}
	
    public CarStatusViewPane() {
    	
    	VLayout vLayout = new VLayout();
    	vLayout.setHeight100();
    	vLayout.setWidth100();
    	vLayout.setMembersMargin(1);
    	vLayout.setLayoutAlign(Alignment.CENTER);
    	vLayout.setLayoutAlign(VerticalAlignment.CENTER);
    	vLayout.setLayoutMargin(5);
    	DynamicForm statusForm = new DynamicForm();
    	statusForm.setNumCols(6);
    	statusForm.setColWidths("10%","15%","25%", "10%","15%", "25%");
    	statusForm.setHeight("60%");
    	//statusForm.setShowEdges(true);
        rpmTextItem.setStartRow(true);
        waterTempTextItem.setStartRow(true);
        positionItem.setStartRow(true);
        speedButtonItem.setEndRow(false);
        speedButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarSpeedEventXmlDS ds = CarSpeedEventXmlDS.getInstance(selectedTerminalId, Constants.CAR_SPEED_PARAM);
				TerminalEventWindow window = new TerminalEventWindow("车速历史数据", ds);
				window.show();
			}
        	
        });
        
        rpmButtonItem.setEndRow(false);
        rpmButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarRpmEventXmlDS ds = CarRpmEventXmlDS.getInstance(selectedTerminalId, Constants.CAR_RPM_PARAM);
				TerminalEventWindow window = new TerminalEventWindow("转速历史数据", ds);
				window.show();
			}
        	
        });
        waterTempButtonItem.setEndRow(false);
        waterTempButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarWaterTempXmlDS ds = CarWaterTempXmlDS.getInstance(selectedTerminalId, Constants.CAR_WATER_TEMP_PARAM);
				TerminalEventWindow window = new TerminalEventWindow("水温历史数据", ds);
				window.show();
			}
        	
        });
        
        positionButtonItem.setEndRow(false);
        powerErrItem.setValue("无");
        bodyErrItem.setValue("无");
        diPanErrItem.setValue("无");
        networkErrItem.setValue("无");
    	statusForm.setFields(speedTextItem, speedButtonItem, powerErrItem,
    			rpmTextItem, rpmButtonItem, bodyErrItem, 
    			waterTempTextItem, waterTempButtonItem, diPanErrItem, 
    			positionItem, positionButtonItem, networkErrItem);
    	statusForm.setIsGroup(true);
    	statusForm.setGroupTitle("车辆运行状态");
    	statusForm.setAlign(Alignment.CENTER);
    	eventUpdateTime.setValign(VerticalAlignment.TOP);
    	vLayout.addMember(statusForm);
    	vLayout.addMember(eventUpdateTime);
    	
    	carStatusTab.setPane(vLayout);
        setTabs(carStatusTab);
    }
    

}

class StatusTextItem extends StaticTextItem {
	public StatusTextItem(String itemText) {
		super(itemText);
	}
}


class StatusButtonItem extends ButtonItem {
	public StatusButtonItem(String itemText) {
		super(itemText);
		this.setStartRow(false);
	}
}
