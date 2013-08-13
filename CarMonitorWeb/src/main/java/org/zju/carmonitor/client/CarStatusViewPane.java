package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import java.util.List;

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
import org.zju.car_monitor.client.ExceptionDataDto;
import org.zju.car_monitor.client.Constants;
import org.zju.carmonitor.client.data.CarObdRecordXmlDS;
import org.zju.carmonitor.client.data.CarRpmEventXmlDS;
import org.zju.carmonitor.client.data.CarSpeedEventXmlDS;
import org.zju.carmonitor.client.data.CarWaterTempXmlDS;
import org.zju.carmonitor.client.data.HighSpeedRecordXmlDS;
import org.zju.carmonitor.client.data.TiredDriveRecordXmlDS;

public class CarStatusViewPane extends TabSet {
	
	private StaticTextItem speedTextItem = new StatusTextItem("当前车速");
	private ButtonItem speedButtonItem = new StatusButtonItem("车速历史纪录");
	private StaticTextItem rpmTextItem = new StatusTextItem("当前转速");
	private ButtonItem rpmButtonItem = new StatusButtonItem("转速历史纪录");
	private StaticTextItem waterTempTextItem = new StatusTextItem("当前水温");
	private ButtonItem waterTempButtonItem = new StatusButtonItem("水温历史纪录");
	private StaticTextItem positionItem = new StatusTextItem("当前位置(经纬度)");
	private ButtonItem positionButtonItem = new StatusButtonItem("地图位置信息");
	private Tab carStatusTab = new Tab("车辆状态");
	private Label eventUpdateTime = new com.smartgwt.client.widgets.Label("最近更新时间:");
	private static DateTimeFormat format = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss");
	
	private ButtonItem highSpeedButton = new ButtonItem("超速纪录");
	private ButtonItem tiredDriveButton = new ButtonItem("疲劳纪录");
	private ButtonItem drunkDriveButton = new ButtonItem("酒驾纪录");
	private ButtonItem errItem = new ButtonItem("故障纪录");
	
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
                } else {
                	speedTextItem.clearValue();
                	rpmTextItem.clearValue();
                	waterTempTextItem.clearValue();
                	positionItem.clearValue();
                }
                updateCurrentTime();

            }
        });
        
        CarMonitorUIService.App.getInstance().getExceptionDataList(terminalId, new AsyncCallback<List<ExceptionDataDto>>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(List<ExceptionDataDto> result) {
				if (result != null) {
					boolean hasHighSpeedRecord = false;
					boolean hasTiredDriveRecord = false;
					boolean hasObdErrRecord = false;
					
					for (ExceptionDataDto dto: result) {
						if (dto.getCode().equals(Constants.EXCEPTION_CODE_HIGH_SPEED)) {
							hasHighSpeedRecord = true;
						} else if (dto.getCode().equals(Constants.EXCEPTION_CODE_TIRED_DRIVE)) {
							hasTiredDriveRecord = true;
						} else if (dto.getCode().equals(Constants.EXCEPTION_CODE_OBD_ERR)) {
							hasObdErrRecord = true;
						}
					}
					switchStyles(highSpeedButton, hasHighSpeedRecord);
					switchStyles(tiredDriveButton, hasTiredDriveRecord);
					switchStyles(errItem, hasObdErrRecord);
					
				}
				
			}
        	
        });
        
    }
	
	private void switchStyles(ButtonItem item, boolean colorFul) {
		if (colorFul) {
			item.setHint("有纪录!");
			item.setHintStyle("redColor");
		}else {
			item.setHint("");
		}
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
    	statusForm.setNumCols(4);
    	statusForm.setColWidths("10%","15%","25%", "50%");
    	statusForm.setHeight("60%");
    	//statusForm.setShowEdges(true);
        rpmTextItem.setStartRow(true);
        waterTempTextItem.setStartRow(true);
        positionItem.setStartRow(true);
        speedButtonItem.setEndRow(false);
        speedButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarSpeedEventXmlDS ds = CarSpeedEventXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("车速历史数据", ds, false);
				window.show();
			}
        	
        });
        
        rpmButtonItem.setEndRow(false);
        rpmButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarRpmEventXmlDS ds = CarRpmEventXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("转速历史数据", ds, false);
				window.show();
			}
        	
        });
        
        highSpeedButton.setStartRow(false);
        highSpeedButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				HighSpeedRecordXmlDS ds = HighSpeedRecordXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("超速历史纪录", ds, true);
				window.show();
			}
        
        });
        
        tiredDriveButton.setStartRow(false);
        tiredDriveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				TiredDriveRecordXmlDS ds = TiredDriveRecordXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("疲劳驾驶历史纪录", ds, true);
				window.show();
			}
        	
        });
        
        drunkDriveButton.setStartRow(false);
        errItem.setStartRow(false);
        errItem.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				CarObdRecordXmlDS ds = CarObdRecordXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("故障纪录", ds, true);
				window.show();
			}
        	
        });

        
        waterTempButtonItem.setEndRow(false);
        waterTempButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				CarWaterTempXmlDS ds = CarWaterTempXmlDS.getInstance(selectedTerminalId);
				TerminalEventWindow window = new TerminalEventWindow("水温历史数据", ds, false);
				window.show();
			}
        	
        });
        
        positionButtonItem.setEndRow(false);
        highSpeedButton.setValue("无");
        tiredDriveButton.setValue("无");
        drunkDriveButton.setValue("无");
        errItem.setValue("无");
    	statusForm.setFields(speedTextItem, speedButtonItem, highSpeedButton,
    			rpmTextItem, rpmButtonItem, tiredDriveButton, 
    			waterTempTextItem, waterTempButtonItem, drunkDriveButton, 
    			positionItem, positionButtonItem, errItem);
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
