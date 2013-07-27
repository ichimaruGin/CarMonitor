package org.zju.carmonitor.client;

/**
 * @author jiezhen 7/23/13
 */
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.*;

public class CarListGrid extends ListGrid {

    public CarListGrid(DataSource carXmlDs) {

        setDataSource(carXmlDs);
        setUseAllDataSourceFields(true);

        setAlign(Alignment.CENTER);
        ListGridField terminalId = new ListGridField("terminalId", "终端号");
        terminalId.setAlign(Alignment.CENTER);
        terminalId.setShowHover(true);
        terminalId.setWidth(100);
        ListGridField departmentName = new ListGridField("departmentName");
        departmentName.setAlign(Alignment.CENTER);
        departmentName.setWidth(300);
        ListGridField carRegNumber = new ListGridField("carRegNumber");
        carRegNumber.setAlign(Alignment.CENTER);
        ListGridField carDriverName = new ListGridField("driverName");
        carDriverName.setShowHover(true);
        ListGridField carDriverPhone = new ListGridField("driverPhone");
        carDriverPhone.setAlign(Alignment.CENTER);
        carDriverName.setAlign(Alignment.CENTER);
        ListGridField carType = new ListGridField("carType");
        carType.setAlign(Alignment.CENTER);

        setFields(terminalId, carRegNumber, departmentName, carDriverName, carDriverPhone, carType);
        setCanEdit(false);
        setCanDragRecordsOut(true);
        setHoverWidth(200);
        setHoverHeight(20);
        setSelectionType(SelectionStyle.SINGLE);
    }
}