package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class CategoriesXmlDS extends DataSource {

    private static CategoriesXmlDS instance = null;

    public static CategoriesXmlDS getInstance() {
        if (instance == null) {
            instance = new CategoriesXmlDS("categoriesXmlDS");
        }
        return instance;
    }

    public CategoriesXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/car");


        DataSourceTextField itemNameField = new DataSourceTextField("categoryName", "Item", 128, true);
        itemNameField.setPrimaryKey(true);

        DataSourceTextField parentField = new DataSourceTextField("parentID", null);
        parentField.setHidden(true);
        parentField.setRequired(true);
        parentField.setRootValue("root");
        parentField.setForeignKey("supplyCategoryDS.categoryName");


        setFields(itemNameField, parentField);

        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=");

        setClientOnly(true);

    }
}
