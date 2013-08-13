package org.zju.carmonitor.client;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.zju.car_monitor.client.CAT718TerminalEventDto;
import org.zju.car_monitor.client.CATOBDTerminalEventDto;
import org.zju.car_monitor.client.CarDto;
import org.zju.car_monitor.client.ExceptionDataDto;

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

	void getExceptionDataList(String terminalId,
			AsyncCallback<List<ExceptionDataDto>> callback);

	void processException(String id, AsyncCallback<Void> callback);
}
