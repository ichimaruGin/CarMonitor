package org.zju.carmonitor.client;

import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class TerminalCanvas extends VLayout {
	private TextItem alcholTextItem = new TextItem("酒精灵敏度");
	private TextItem waterTempTextItem = new TextItem("水温报警(摄氏度)");
	private TextItem speedWarnTextItem = new TextItem("车速报警点(公里/小时)");
	private TextItem tiredDriveTextItem = new TextItem("疲劳驾驶时间(分钟)");
	
	private DynamicForm form;
	
	private LayoutWithButtons buttonLayout;
	
	public LayoutWithButtons getButtonLayout() {
		return buttonLayout;
	}
	
	public TerminalCanvas() {
		form = new LineDataHolder(2, alcholTextItem, waterTempTextItem, speedWarnTextItem, tiredDriveTextItem);
		buttonLayout = new LayoutWithButtons(this);
		buttonLayout.setOkClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				SC.say("ok button");
			}
		});
		
		this.addMember(form);
        this.addMember(buttonLayout);

	}
}
