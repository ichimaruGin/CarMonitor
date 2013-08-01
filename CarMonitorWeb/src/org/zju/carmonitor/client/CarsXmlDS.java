package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;

public class CarsXmlDS extends DataSource {

    private static CarsXmlDS instance = null;

    public static CarsXmlDS getInstance() {
        if (instance == null) {
            instance = new CarsXmlDS("carsXmlDS");
        }
        return instance;
    }

    public CarsXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/car");
        DataSourceIntegerField pkField = new DataSourceIntegerField("id");
        pkField.setHidden(true);
        pkField.setPrimaryKey(true);

        DataSourceTextField terminalIdField = new DataSourceTextField("terminalId", "终端号", 20, true);
        DataSourceTextField terminalIdField_ = new DataSourceTextField("termianlId_");
        terminalIdField_.setHidden(true);
        DataSourceTextField departmentLongNameField = new DataSourceTextField("departmentName", "所属单位", 40, true);
        DataSourceTextField departmentId = new DataSourceTextField("departmentId");
        departmentId.setHidden(true);
        
        departmentLongNameField.setForeignKey("departmentsXmlDS.name");

        DataSourceTextField carRegNumberField = new DataSourceTextField("carRegNumber", "车牌号码", 20, true);
        DataSourceTextField carDriverNameField = new DataSourceTextField("driverName", "司机名字", 20, true);
        DataSourceTextField carDriverPhoneField = new DataSourceTextField("driverPhone", "司机电话", 20, true);
        DataSourceTextField carType = new DataSourceTextField("carType", "车型", 20, true);
        DataSourceField carEditButton = new DataSourceField("carEditButton", FieldType.ANY);

        setFields(pkField, departmentId, terminalIdField, terminalIdField_, departmentLongNameField, carRegNumberField, carType,
                carDriverNameField, carDriverPhoneField, carEditButton);

        setDataURL(GWT.getModuleBaseURL()+"xmlservlet?param=cars");
        setClientOnly(true);
    }
}
