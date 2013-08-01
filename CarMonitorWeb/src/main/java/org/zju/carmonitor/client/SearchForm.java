package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class SearchForm extends com.smartgwt.client.widgets.form.SearchForm {
    private ComboBoxItem terminalIdBoxItem;
    private ButtonItem findItem;


    public SearchForm(DataSource supplyItemDS) {

        setDataSource(supplyItemDS);
        setTop(20);
        setCellPadding(6);
        setNumCols(7);
  //      setStyleName("defaultBorder");

        findItem = new ButtonItem("查找");
        findItem.setIcon("find.png");
        findItem.setWidth(70);
        findItem.setEndRow(false);

        TextItem carRegNumber = new TextItem("车牌号码");

        terminalIdBoxItem = new ComboBoxItem("终端号");
        terminalIdBoxItem.setOptionDataSource(supplyItemDS);
        terminalIdBoxItem.setPickListWidth(250);

        CheckboxItem findInDepartment = new CheckboxItem("findInDepartment");
        findInDepartment.setTitle("只查找所选单位");
        findInDepartment.setDefaultValue(true);
        findInDepartment.setShouldSaveValue(false);

        setItems(findItem, carRegNumber, terminalIdBoxItem, findInDepartment);
    }

    public ComboBoxItem getTerminalIdField() {
        return terminalIdBoxItem;
    }

    public void addFindListener(ClickHandler handler) {
        findItem.addClickHandler(handler);
    }

}