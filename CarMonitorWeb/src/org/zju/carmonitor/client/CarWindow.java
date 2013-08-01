package org.zju.carmonitor.client;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jiezhen 7/23/13
 */



public class CarWindow {
	
	private ListGrid listGrid;
	public Window getWindow() {
		return window;
	}
	
	public void show() {
		window.show();
	}
	
	private Window window = new Window();
	
	public CarWindow(ListGrid list, String departmentId, String terminalId, String carRegNumber, 
			String carType, String driverName, String driverPhone) {
		listGrid = list;
		window.setTitle("更新车辆数据");
	}
	
	
	private VLayout vLayout = new VLayout();
	
	public Canvas getCanvas() {
		return vLayout;
	}
	
    public CarWindow() {
    	CarCanvas canvas = new CarCanvas();
    	canvas.setListGrid(listGrid);
    	window.setWidth("50%");
        window.setHeight("25%");
        window.setTitle("添加新车辆");
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        window.addItem(canvas);
        canvas.getButtonLayout().setCancelClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				window.destroy();
			}
        });
    }

}

class LineDataHolder extends DynamicForm {
	public LineDataHolder(int numCols, FormItem... forms) {
		this.setWidth100();
		this.setHeight100();
		this.setPadding(5);
		this.setNumCols(numCols);
		this.setLayoutAlign(Alignment.CENTER);
		this.setFields(forms);
		this.setBorder("1px");
	}
}

class TerminalSelectItem extends SelectItem {
	public TerminalSelectItem() {
		this.setTitle("选择终端");
		this.setValueMap(DataStoreFromServer.terminalMap);
	}
}
class DepartmentSelectItem extends SelectItem {
	public DepartmentSelectItem() {
		this.setTitle("所属单位");
		this.setValueMap(DataStoreFromServer.departmentsMap);
	}
}


