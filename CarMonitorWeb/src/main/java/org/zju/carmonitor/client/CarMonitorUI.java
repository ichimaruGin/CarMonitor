package org.zju.carmonitor.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FilterCriteriaFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickEvent;
import com.smartgwt.client.widgets.tree.events.NodeClickHandler;

import org.zju.carmonitor.client.data.DepartmentsXmlDS;

public class CarMonitorUI extends HLayout implements EntryPoint {
    private DepartmentsTreeGrid departmentsTreeGrid;
    private CarListGrid carList;
    private CarStatusViewPane carStatusViewPane;
    private String selectedTerminaId = null;
    private String selectedCarRegNumber = null;

    public void onModuleLoad() {
        setWidth100();
        setHeight100();
        setLayoutMargin(5);
        
        DataStoreFromServer.init();
        DataSource departmentsXmlDS = DepartmentsXmlDS.getInstance();
        DataSource carXmlDS = CarsXmlDS.getInstance();

        departmentsTreeGrid = new DepartmentsTreeGrid(departmentsXmlDS);
        departmentsTreeGrid.setAutoFetchData(true);
        departmentsTreeGrid.addNodeClickHandler(new NodeClickHandler() {
            public void onNodeClick(NodeClickEvent event) {
                String id = event.getNode().getAttribute("id");
                String[] idAndParentIds = DataStoreFromServer.getIdsAndParentIds(id);
                findItems(idAndParentIds);
            }
        });
        carStatusViewPane = new CarStatusViewPane();


        int _10_seconds = 10 * 1000;
        Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {

			public boolean execute() {
				if (selectedCarRegNumber != null && selectedTerminaId != null) {
					carStatusViewPane.updateCarStatus(selectedTerminaId, selectedCarRegNumber);
				}
				return true;
			}
        	
        }, _10_seconds);
        
        
        carList = new CarListGrid(carXmlDS);
        carList.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
            	if (event.getRecord() != null) {
            		String terminalId = event.getRecord().getAttribute("terminalId");
            		selectedTerminaId = terminalId;
                    String carRegNumber = event.getRecord().getAttribute("carRegNumber");
                    selectedCarRegNumber = carRegNumber;
                    carStatusViewPane.updateCarStatus(terminalId, carRegNumber);
            	}
            }
        });


        SectionStack leftSideLayout = new SectionStack();
        leftSideLayout.setWidth(280);
        leftSideLayout.setShowResizeBar(true);
        leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        leftSideLayout.setAnimateSections(true);

        SectionStackSection departmentsSection = new SectionStackSection("车辆所属单位");
        departmentsSection.setExpanded(true);
        departmentsSection.setCanCollapse(false);
        departmentsSection.setItems(departmentsTreeGrid);

        ImgButton addButton = new ImgButton();
        addButton.setSrc("[SKIN]actions/add.png");
        addButton.setSize(16);
        addButton.setShowFocused(false);
        addButton.setShowRollOver(false);
        addButton.setShowDown(false);
        addButton.setPrompt("添加新车辆");
        addButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                CarWindow addNewCarWindow = new CarWindow(carList);
                addNewCarWindow.show();
            }
        });


        departmentsSection.setControls(addButton);

        leftSideLayout.setSections(departmentsSection);

        SectionStack rightSideLayout = new SectionStack();
        rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        rightSideLayout.setAnimateSections(true);

        SectionStackSection carListSection = new SectionStackSection("车辆列表");
        carListSection.setItems(carList);
        carListSection.setExpanded(true);
        carListSection.setCanCollapse(false);


        SectionStackSection itemDetailsSection = new SectionStackSection("车辆详细信息");
        itemDetailsSection.setItems(carStatusViewPane);
        itemDetailsSection.setExpanded(true);
        itemDetailsSection.setCanCollapse(false);

        rightSideLayout.setSections(carListSection, itemDetailsSection);

        addMember(leftSideLayout);
        addMember(rightSideLayout);
        draw();
    }

    public void findItems(String[] ids) {

        Criteria findValues = new Criteria();
        findValues.addCriteria("departmentId", ids);
        carList.filterData(findValues);
    }

}

