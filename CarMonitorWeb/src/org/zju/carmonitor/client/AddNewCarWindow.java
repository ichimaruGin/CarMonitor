package org.zju.carmonitor.client;

import java.util.LinkedHashMap;

import javax.security.sasl.SaslClient;

import org.zju.carmonitor.client.shared.CarDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author jiezhen 7/23/13
 */
public class AddNewCarWindow {
	
	public Window getWindow() {
		return window;
	}
	
	public void show() {
		window.show();
	}
	
	private Window window = new Window();
	private SelectItem department = new DepartmentSelectItem();
	private SelectItem terminal = new TerminalSelectItem();
	private TextItem carRegNumberTextItem = new TextItem("车辆号码");
	private TextItem carTypeTextItem = new TextItem("车辆类型");
	private TextItem driverNameTextItem = new TextItem("司机姓名");
	private TextItem driverPhoneTextItem = new TextItem("司机手机");
	private DynamicForm form = new LineDataHolder(department, terminal, carRegNumberTextItem, carTypeTextItem, driverNameTextItem, driverPhoneTextItem);
	private IButton cancel = new IButton("取消");
	private IButton ok = new IButton("保存");
    
    public AddNewCarWindow() {
        window.setWidth("50%");
        window.setHeight("25%");
        window.setTitle("添加新车辆");
        window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        
        VLayout vLayout = new VLayout();
        vLayout.setWidth100();
        vLayout.setHeight100();
        vLayout.setMembersMargin(5);
        vLayout.setMargin(5);
        
        HLayout hLayout = new HLayout();
        cancel.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				window.destroy();
			}
        	
        });
        cancel.setWidth(80);
        ok.setWidth(80);
        ok.addClickHandler(new ClickHandler(){

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
						SC.say("保存成功");
						window.destroy();
					}
					
				});
					
			}
        	
        });
        
        hLayout.addMember(cancel);
        hLayout.addMember(ok);
        hLayout.setAlign(Alignment.RIGHT);
        hLayout.setMembersMargin(5);
        
        vLayout.addMember(form);
        vLayout.addMember(hLayout);
        
        window.addItem(vLayout);
        window.show();
    }

}

class LineDataHolder extends DynamicForm {
	public LineDataHolder(FormItem... forms) {
		this.setWidth100();
		this.setHeight100();
		this.setPadding(5);
		this.setNumCols(4);
		this.setLayoutAlign(Alignment.CENTER);
		this.setFields(forms);
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


