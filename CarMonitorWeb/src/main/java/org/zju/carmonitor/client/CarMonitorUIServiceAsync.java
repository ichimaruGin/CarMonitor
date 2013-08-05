package org.zju.carmonitor.client;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.zju.car_monitor.client.CAT718TerminalEventDto;
import org.zju.car_monitor.client.CATOBDTerminalEventDto;
import org.zju.car_monitor.client.CarDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CarMonitorUIServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);

	void getDepartmentsMap(AsyncCallback<LinkedHashMap<String, String>> callback);

	void getTerminalsMap(AsyncCallback<LinkedHashMap<String, String>> callback);

	void saveCar(CarDto carDto, AsyncCallback<Void> callback);

	void getDepartmentsIdsParentIdsMap(
			AsyncCallback<HashMap<String, String>> callback);

	void getCAT718TerminalEvent(String terminalId,
			AsyncCallback<CAT718TerminalEventDto> callback);

	void getCATOBDTerminalEvent(String terminalId,
			AsyncCallback<CATOBDTerminalEventDto> callback);
}
