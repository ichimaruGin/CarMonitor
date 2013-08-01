package org.zju.carmonitor.client;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class LayoutWithButtons extends HLayout {
	protected IButton cancel = new IButton("关闭");
	protected IButton ok = new IButton("保存");
	
	public IButton okButton() {
		return ok;
	}
	
	public IButton cancelButton() {
		return cancel;
	}
    
	public void setCancelClickHandler(ClickHandler handler) {
		cancel.addClickHandler(handler);
	}
	private Canvas parentCanvas;
	public LayoutWithButtons(Canvas parentCanvas) {
		this.parentCanvas = parentCanvas;
        cancel.setWidth(80);
        ok.setWidth(80);
        this.addMember(cancel);
        this.addMember(ok);
        this.setAlign(Alignment.RIGHT);
        this.setMembersMargin(5);
	}
	
	public void setOkClickHandler(ClickHandler handler) {
		ok.addClickHandler(handler);
	}
	
}
