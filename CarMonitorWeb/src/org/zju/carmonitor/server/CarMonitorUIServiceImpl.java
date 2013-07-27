package org.zju.carmonitor.server;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.apache.commons.collections.map.HashedMap;
import org.zju.car_monitor.db.Car;
import org.zju.car_monitor.db.Department;
import org.zju.car_monitor.db.Terminal;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.ReadWriteTask;
import org.zju.carmonitor.client.CarMonitorUIService;
import org.zju.carmonitor.client.shared.CarDto;

public class CarMonitorUIServiceImpl extends RemoteServiceServlet implements CarMonitorUIService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
    
    
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getDepartmentsMap() {
		return (LinkedHashMap<String, String>)
				 Hibernate.readOnly(new ReadOnlyTask<LinkedHashMap<String, String>>() {

			public LinkedHashMap<String, String> doWork() {
				List<Department> departments = Department.findAllDepartments();
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				
				for(Department department: departments) {
					linkedHashMap.put(department.getId(), department.getLongName());
				}
				
				return linkedHashMap;
			}
			
		});
	}

	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getTerminalsMap() {
		
		return (LinkedHashMap<String, String>)Hibernate.readOnly(new ReadOnlyTask<LinkedHashMap<String, String>>(){

			public LinkedHashMap<String, String> doWork() {
				List<Terminal> terminals = Terminal.findAll();
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				
				for (Terminal terminal: terminals) {
					linkedHashMap.put(terminal.getId(), terminal.getTerminalId());
				}
				return linkedHashMap;
				
			}
			
		});
		
	}

	public void saveCar(final CarDto carDto) {
		Hibernate.readWrite(new ReadWriteTask() {
			
			public void doWork() {
				Car car = new Car();
				car.setRegNumber(carDto.getCarRegNumber());
				car.setType(carDto.getCarType());
				car.setDriverName(carDto.getDriverName());
				car.setDriverPhone(carDto.getDriverPhone());
				Department department = (Department) Hibernate.currentSession().get(Department.class, carDto.getDepartmentId());
				Terminal terminal = (Terminal) Hibernate.currentSession().get(Terminal.class, carDto.getTerminalId());
				car.setDepartment(department);
				car.setTerminal(terminal);
				car.save();
			}
		});
		
	}
}