package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import java.util.List;

import org.zju.car_monitor.client.CAT718TerminalEventDto;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.client.ExceptionDataDto;
import org.zju.carmonitor.client.data.TerminalEventXmlDS;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

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
	
	private CAT718TerminalEventDto lastDto = null;

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
                    positionItem.setValue("(" + result.getCurrentLongitude() + "," + result.getCurrentLatitude() + ")");
                    lastDto = result;
                } else {
                	speedTextItem.setValue("--");
                	rpmTextItem.setValue("--");
                	waterTempTextItem.setValue("--");
                	positionItem.setValue("--");
                }
                updateCurrentTime();

            }
        });
        
        CarMonitorUIService.App.getInstance().getExceptionDataList(terminalId, new AsyncCallback<List<ExceptionDataDto>>() {

			public void onFailure(Throwable caught) {
			}

			public void onSuccess(List<ExceptionDataDto> result) {
				if (result != null) {
					ExceptionDataDto highSpeedDto = null;
					ExceptionDataDto tireDriveDto = null;
					ExceptionDataDto obdErrDto = null;
					ExceptionDataDto drunkDto = null;
					
					for (ExceptionDataDto dto: result) {
						if (dto.getCode().equals(Constants.EXCEPTION_CODE_HIGH_SPEED)) {
							highSpeedDto = dto;
						} else if (dto.getCode().equals(Constants.EXCEPTION_CODE_TIRED_DRIVE)) {
							tireDriveDto = dto;
						} else if (dto.getCode().equals(Constants.EXCEPTION_CODE_OBD_ERR)) {
							obdErrDto = dto;
						} else if (dto.getCode().equals(Constants.EXCEPTION_CODE_DRUNK)) {
							drunkDto = dto;
						}
					}
					switchStyles(highSpeedButton, highSpeedDto);
					switchStyles(tiredDriveButton, tireDriveDto);
					switchStyles(errItem, obdErrDto);
					switchStyles(drunkDriveButton, drunkDto);
					
				}
				
			}
        	
        });
        
    }
	
	private void switchStyles(ButtonItem item, ExceptionDataDto dto) {
		if (dto != null) {
			String valueToShow = "最近未处理纪录: " + dto.getMessage() + " " + dto.getTime();
			item.setHint(valueToShow);
			item.setBaseStyle("redColor");
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
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_CAT718, Constants.CAR_SPEED_PARAM, false);  
				TerminalEventWindow window = new TerminalEventWindow("车速历史数据", ds, false);
				window.show();
			}
        	
        });
        
        rpmButtonItem.setEndRow(false);
        rpmButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_CAT718, Constants.CAR_RPM_PARAM, false);
				TerminalEventWindow window = new TerminalEventWindow("转速历史数据", ds, false);
				window.show();
			}
        	
        });
        
        highSpeedButton.setStartRow(false);
        highSpeedButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_EXCEPTION, Constants.EXCEPTION_CODE_HIGH_SPEED, true);
				TerminalEventWindow window = new TerminalEventWindow("超速历史纪录", ds, true);
				window.show();
			}
        
        });
        
        tiredDriveButton.setStartRow(false);
        tiredDriveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_EXCEPTION, Constants.EXCEPTION_CODE_TIRED_DRIVE, true);
		    	TerminalEventWindow window = new TerminalEventWindow("疲劳驾驶历史纪录", ds, true);
				window.show();
			}
        	
        });
        
        drunkDriveButton.setStartRow(false);
        drunkDriveButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_EXCEPTION, Constants.EXCEPTION_CODE_DRUNK, true);
				TerminalEventWindow window = new TerminalEventWindow("酒驾历史纪录", ds, true);
				window.show();
			}
        	
        });

        
        errItem.setStartRow(false);
        errItem.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_EXCEPTION, Constants.EXCEPTION_CODE_OBD_ERR, true);
				TerminalEventWindow window = new TerminalEventWindow("故障纪录", ds, true);
				window.show();
			}
        	
        });

        
        waterTempButtonItem.setEndRow(false);
        waterTempButtonItem.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				TerminalEventXmlDS ds = TerminalEventXmlDS.getInstance(selectedTerminalId, 
						Constants.EVENT_TYPE_CAT718, Constants.CAR_WATER_TEMP_PARAM, true);
				TerminalEventWindow window = new TerminalEventWindow("水温历史数据", ds, false);
				window.show();
			}
        	
        });
        
        positionButtonItem.setEndRow(false);
        positionButtonItem.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (lastDto == null || !lastDto.getTerminalId().equals(selectedTerminalId)) {
					SC.say("无位置数据");
				} else {
					final MapHtmlPane pane = new MapHtmlPane(lastDto.getCurrentLongitude(), lastDto.getCurrentLatitude());
				}
				
			}
        	
        });
        
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
