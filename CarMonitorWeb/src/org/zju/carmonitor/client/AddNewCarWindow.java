package org.zju.carmonitor.client;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * @author jiezhen 7/23/13
 */
public class AddNewCarWindow extends Window {

    public AddNewCarWindow() {
        this.setWidth(360);
        this.setHeight(115);
        this.setTitle("添加新车辆");
        this.setShowMinimizeButton(false);
        this.setIsModal(true);
        this.setShowModalMask(true);
        this.centerInPage();
        DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);

        TextItem textItem = new TextItem();
        textItem.setTitle("Text");
        DateItem dateItem = new DateItem();
        dateItem.setTitle("Date");
        DateItem dateItem2 = new DateItem();
        dateItem2.setTitle("Date");
        dateItem2.setUseTextField(true);
        form.setFields(textItem, dateItem, dateItem2);
        this.addItem(form);
        this.show();
    }

}


