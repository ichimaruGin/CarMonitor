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
    private ComboBoxItem itemName;
    private ButtonItem findItem;


    public SearchForm(DataSource supplyItemDS) {

        setDataSource(supplyItemDS);
        setTop(20);
        setCellPadding(6);
        setNumCols(7);
  //      setStyleName("defaultBorder");

        findItem = new ButtonItem("Find");
//        findItem.setIcon("silk/find.png");
        findItem.setWidth(70);
        findItem.setEndRow(false);

        TextItem skuItem = new TextItem("SKU");

        itemName = new ComboBoxItem("itemName");
        itemName.setOptionDataSource(supplyItemDS);
        itemName.setPickListWidth(250);

        CheckboxItem findInCategory = new CheckboxItem("findInCategory");
        findInCategory.setTitle("Use Category");
        findInCategory.setDefaultValue(true);
        findInCategory.setShouldSaveValue(false);

        setItems(findItem, skuItem, itemName, findInCategory);
    }

    public ComboBoxItem getItemNameField() {
        return itemName;
    }

    public void addFindListener(ClickHandler handler) {
        findItem.addClickHandler(handler);
    }

}