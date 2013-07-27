package org.zju.carmonitor.client;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataStoreFromServer {
	
	public static LinkedHashMap<String, String> departmentsMap = null;
	public static LinkedHashMap<String, String> terminalMap = null;
	public static void init() {
		CarMonitorUIService.App.getInstance().getDepartmentsMap(new AsyncCallback<LinkedHashMap<String,String>>(){
			public void onFailure(Throwable caught) {
				
			}
			public void onSuccess(LinkedHashMap<String, String> result) {
				departmentsMap = result;
			}
			
		});
		
		CarMonitorUIService.App.getInstance().getTerminalsMap(new AsyncCallback<LinkedHashMap<String,String>>(){

			public void onFailure(Throwable caught) {
				
			}
			public void onSuccess(LinkedHashMap<String, String> result) {
				terminalMap = result;
			}
			
		});
	}

}
