package org.zju.carmonitor.client;

import org.zju.carmonitor.client.data.TerminalEventXmlDS;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TerminalEventWindow {
	
	private Window window;
	
	
	public void show() {
		window.show();
	}
	
	
	public TerminalEventWindow(String title, DataSource ds, boolean hasProcessButton) {
		
		window = new Window();
		window.setTitle(title);
		window.setWidth("50%");
		window.setHeight("50%");
		window.setShowMinimizeButton(false);
        window.setIsModal(true);
        window.setShowModalMask(true);
        window.centerInPage();
        

		TabSet tabSet = new TabSet();
		Tab tab = new Tab("数据表格展示");
		Tab tab2 = new Tab("数据图形展示");
		tabSet.addTab(tab);
		tabSet.addTab(tab2);
		HLayout hlayout = new HLayout();
		
		ListGrid eventGrid = null; 
		if (!hasProcessButton) eventGrid = new TerminalEventListGrid(ds); else eventGrid = new ExceptionListGrid(ds);
		ds.invalidateCache();
		eventGrid.setAutoFetchData(true);
		tab.setPane(hlayout);
		hlayout.addMember(eventGrid);
		window.addItem(tabSet);
		
	}

}
