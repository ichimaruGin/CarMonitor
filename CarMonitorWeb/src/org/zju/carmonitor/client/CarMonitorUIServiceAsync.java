package org.zju.carmonitor.client;

import java.util.LinkedHashMap;

import org.zju.carmonitor.client.shared.CarDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarMonitorUIServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

	void getDepartmentsMap(AsyncCallback<LinkedHashMap<String, String>> callback);

	void getTerminalsMap(AsyncCallback<LinkedHashMap<String, String>> callback);

	void saveCar(CarDto carDto, AsyncCallback<Void> callback);
}
