package org.zju.car_monitor.util;

import java.util.HashMap;

import org.zju.car_monitor.db.Car;

public class CarCache extends AbstractCache{
	
	public static HashMap<String, Car> data = new HashMap<String, Car>();
	public static Car getCarByTerminalUUID(String terminalId) {
		if (shouldExpire()) {
			data = new HashMap<String, Car>();
			advanceTimer();
		}
		Car car = data.get(terminalId);
		if (car == null) {
			car = Car.findCarByTerminalUUId(terminalId);
			data.put(terminalId, car);
		}
		
		return car;
		
	}
	

	

}
