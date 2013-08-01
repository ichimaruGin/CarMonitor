package org.zju.carmonitor.client.data;

/**
 * @author jiezhen 7/23/13
 */
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class DepartmentsXmlDS extends DataSource {

    private static DepartmentsXmlDS instance = null;

    public static DepartmentsXmlDS getInstance() {
        if (instance == null) {
            instance = new DepartmentsXmlDS("departmentsXmlDS");
        }
        return instance;
    }

    public DepartmentsXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/department");
        DataSourceTextField idField = new DataSourceTextField("id", null);
        idField.setHidden(true);
        idField.setPrimaryKey(true);
        DataSourceTextField departmentNameField = new DataSourceTextField("name", "所属单位", 128, true);

        DataSourceTextField parentField = new DataSourceTextField("parentId", null);
        parentField.setHidden(true);
        parentField.setRequired(true);
        parentField.setRootValue("root");
        parentField.setForeignKey("departmentsXmlDS.id");

        setFields(idField, departmentNameField, parentField);
        
        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=departments");

        setClientOnly(true);

    }
}
