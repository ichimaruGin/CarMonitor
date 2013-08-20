package org.zju.carmonitor.client;

import com.smartgwt.client.types.ContentsType;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;

public class MapHtmlPane{
	
	private HTMLPane pane = new HTMLPane();
	private Window window = new Window(); 
	
	public MapHtmlPane(String longitude, String latitude) {
		
		window.setWidth(1024);
		window.setHeight(500);
		window.setTitle("当前位置－红色标志");
		window.setShowMinimizeButton(false);
		window.setIsModal(true);
		window.setShowModalMask(true);
		window.centerInPage();
		window.addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				pane.destroy();
				window.destroy();
		}});
        
        pane.setContentsType(ContentsType.PAGE);
		window.addItem(pane);
		String center = longitude.substring(0, 9) + "," + latitude.substring(0, 10);
		String markers = "&markers=" + center + "&markerStyles=m,C,0xFF0000";
		pane.setContentsURL("http://api.map.baidu.com/staticimage?center=" + center + markers + "&width=1024&height=500&zoom=13");
		window.show();
	}
}
