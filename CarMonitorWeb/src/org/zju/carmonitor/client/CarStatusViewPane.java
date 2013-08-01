package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.Label;

import org.zju.car_monitor.client.CAT718TerminalEventDto;

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
	private Label eventUpdateTime = new com.smartgwt.client.widgets.Label("");
	
	public void updateCarStatus(String terminalId, String carRegNumber) {
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
                    eventUpdateTime.setTitle(result.getUpdatedTime());
                }
            }
        });
	}
	
    public CarStatusViewPane() {
    	
    	HLayout hLayout = new HLayout();
    	hLayout.setHeight100();
    	hLayout.setWidth100();
    	hLayout.setMembersMargin(3);
    	hLayout.setLayoutAlign(Alignment.CENTER);
    	hLayout.setLayoutAlign(VerticalAlignment.CENTER);
    	hLayout.setLayoutMargin(10);
    	DynamicForm statusForm = new DynamicForm();
    	statusForm.setNumCols(4);
    	statusForm.setColWidths("10%","15%","15%", "42%");
    	statusForm.setHeight("60%");
        rpmTextItem.setStartRow(true);
        waterTempTextItem.setStartRow(true);
        positionItem.setStartRow(true);
    	statusForm.setFields(speedTextItem, speedButtonItem,
    			rpmTextItem, rpmButtonItem, waterTempTextItem,
    			waterTempButtonItem, positionItem, positionButtonItem);
    	statusForm.setIsGroup(true);
    	statusForm.setGroupTitle("车辆运行状态");
    	statusForm.setAlign(Alignment.CENTER);
    	hLayout.addMember(statusForm);
    	hLayout.addMember(eventUpdateTime);
    	
    	carStatusTab.setPane(hLayout);
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
