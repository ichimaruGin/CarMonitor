package org.zju.carmonitor.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataStoreFromServer {
	
	public static LinkedHashMap<String, String> departmentsMap = null;
	public static LinkedHashMap<String, String> terminalMap = null;
	public static HashMap<String, String> departmentsIdsParentIdsMap = null;
	
	private static boolean is2parentOf1(String id1, String id2) {
		String parentId = departmentsIdsParentIdsMap.get(id1);
		String temp = null;
		while(!parentId.equals(id2) && (!parentId.equals(temp))) {
			temp = parentId;
			parentId = departmentsIdsParentIdsMap.get(temp);
		}
		
		return parentId.equals(id2);
		
	}
	public static String[] getIdsAndParentIds(String id) {
		
		ArrayList<String> idsList = new ArrayList<String>();
		idsList.add(id);
		for (String key: departmentsIdsParentIdsMap.keySet()) {
			if (key.equals(id)) continue;
			else if (is2parentOf1(key, id)) idsList.add(key);
		}
		
		String[] ids = new String[idsList.size()]; 
		idsList.toArray(ids);
		return ids;
	}
	
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
		
		CarMonitorUIService.App.getInstance().getDepartmentsIdsParentIdsMap(new AsyncCallback<HashMap<String,String>>(){

			public void onFailure(Throwable caught) {
				
			}
			public void onSuccess(HashMap<String, String> result) {
				departmentsIdsParentIdsMap = result;
			}
			
		});
		
				
	}

}
