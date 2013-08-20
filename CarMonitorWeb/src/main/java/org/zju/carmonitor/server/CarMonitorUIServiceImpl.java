package org.zju.carmonitor.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.zju.car_monitor.client.CAT718TerminalEventDto;
import org.zju.car_monitor.client.CATOBDTerminalEventDto;
import org.zju.car_monitor.client.CarDto;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.client.ExceptionDataDto;
import org.zju.car_monitor.db.CAT718EventAttribute;
import org.zju.car_monitor.db.CAT718TerminalEvent;
import org.zju.car_monitor.db.CATOBDTerminalEvent;
import org.zju.car_monitor.db.Car;
import org.zju.car_monitor.db.Department;
import org.zju.car_monitor.db.EventType;
import org.zju.car_monitor.db.Terminal;
import org.zju.car_monitor.db.TerminalEventAttrChar;
import org.zju.car_monitor.db.TerminalEventAttrLong;
import org.zju.car_monitor.db.TerminalException;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.ReadWriteTask;
import org.zju.carmonitor.client.CarMonitorUIService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CarMonitorUIServiceImpl extends RemoteServiceServlet implements CarMonitorUIService {
    
	private static Logger logger = Logger.getLogger(CarMonitorUIServiceImpl.class);
	
	// Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
    
    
	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, String> getDepartmentsMap() {
		
		logger.info("Get departments map");
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
		
		logger.info("Get terminals map");
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
				Car existingCar = Car.findCarByTerminalUUId(carDto.getTerminalId());
				if (existingCar == null) {
					Car.saveCar(carDto);
				} else {
					Car.updateCar(existingCar, carDto);
				}
				
			}
		});
		
	}


	@SuppressWarnings("unchecked")
	public java.util.HashMap<String, String> getDepartmentsIdsParentIdsMap() {
		
		logger.info("Get departments id parent ids map");
		
		return (java.util.HashMap<String, String>) Hibernate.readOnly(new ReadOnlyTask<HashMap<String, String>>(){

			public HashMap<String, String> doWork() {
				
				List<Department> departments = Department.findAllDepartments();
				HashMap<String, String> idsMap = new HashMap<String, String>();
				
				for(Department department: departments) {
					idsMap.put(department.getId(), department.getParentId());
				}
				
				return idsMap;

			}
			
		});
		
	}


	public CAT718TerminalEventDto getCAT718TerminalEvent(final String terminalId) {

		return (CAT718TerminalEventDto) Hibernate.readOnly(new ReadOnlyTask() {

			public Object doWork() {
				CAT718TerminalEvent event = CAT718TerminalEvent.findLatestEvent(terminalId);
                CAT718TerminalEventDto dto = new CAT718TerminalEventDto();
				if (event != null) {
					List<TerminalEventAttrLong> list = TerminalEventAttrLong.getEventAttrLongByEventId(event.getId());
                    for (TerminalEventAttrLong eventAttrLong: list) {
                        String attrCode = eventAttrLong.getAttribute().getAttrCode();

                       if (attrCode.equals(CAT718EventAttribute.CAR_SPEED_PARAM)){
                            String attrValue = eventAttrLong.getAttrValue() + " 公里每小时";
                            dto.setCurrentSpeed(attrValue);
                        } else if (attrCode.equals(CAT718EventAttribute.CAR_WATER_TEMP_PARAM)) {
                            String attrValue = (eventAttrLong.getAttrValue() - 40) + " 摄氏度";
                            dto.setCurrentWaterTemp(attrValue);
                        } else if (attrCode.equals(CAT718EventAttribute.CAR_RPM_PARAM)) {
                            String attrValue = (eventAttrLong.getAttrValue() /4 ) + " 转每分钟";
                            dto.setCurrentRpm(attrValue);
                        }

                    }

                    List<TerminalEventAttrChar> listChar = TerminalEventAttrChar.getEventAttrCharByEventId(event.getId());
                    for (TerminalEventAttrChar eventAttrChar: listChar) {
                        String attrCode = eventAttrChar.getAttribute().getAttrCode();

                        if (attrCode.equals(CAT718EventAttribute.CAR_LATITUDE)) {
                        	String data = eventAttrChar.getAttrValue();
                        	if (data!= null && data.length() == 10) {
                        		String conv = data.substring(0, 2) + "." + data.substring(2, 4) + data.substring(5);
                        		dto.setCurrentLatitude(conv);
                        	} else {
                        		dto.setCurrentLatitude("无效数据");
                        	}
                        } else if (attrCode.equals(CAT718EventAttribute.CAR_LONGITUDE)) {
                        	String data = eventAttrChar.getAttrValue();
                        	if (data!= null && data.length() == 11) {
                        		String conv = data.substring(0, 3) + "." + data.substring(3, 5) + data.substring(6);
                        		dto.setCurrentLongitude(conv);
                        	} else {
                        		dto.setCurrentLongitude("无效数据");
                        	}
                        }  else {
                            logger.error("unknown char attribute " + attrCode);
                        }
                    }

                    dto.setEventType(EventType.CAR718.toString());
                    dto.setUpdatedTime(event.getUpdatedAt().toString());
                    dto.setTerminalId(terminalId);
                    return dto;
				} 
			return null;
		
			}
			
		});
	}


	public CATOBDTerminalEventDto getCATOBDTerminalEvent(final String terminalId) {
		return (CATOBDTerminalEventDto) Hibernate.readOnly(new ReadOnlyTask<CATOBDTerminalEventDto>() {
			
			public CATOBDTerminalEventDto doWork() {
				CATOBDTerminalEventDto dto = new CATOBDTerminalEventDto();
				CATOBDTerminalEvent event = CATOBDTerminalEvent.findLatestEvent(terminalId);
				if (event != null) {
					List<TerminalEventAttrChar> values = TerminalEventAttrChar.getEventAttrCharByEventId(event.getId());
					for (TerminalEventAttrChar value: values) {
						dto.addMessage(value.getAttrValue());
					}
					return dto;
				} else return null;
				
			}
		});
	}


	@SuppressWarnings("unchecked")
	public List<ExceptionDataDto> getExceptionDataList(final String terminalId) {
		return (List<ExceptionDataDto>) Hibernate.readOnly(new ReadOnlyTask<List<ExceptionDataDto>>() {
			
			public List<ExceptionDataDto> doWork() {
				List<ExceptionDataDto> dtoList = new ArrayList<ExceptionDataDto>();
				
				List<TerminalException> exceptions = new ArrayList<TerminalException>(); 
				exceptions.add(TerminalException.findUnProcessEventsByCode(terminalId, Constants.EXCEPTION_CODE_HIGH_SPEED));
				exceptions.add(TerminalException.findUnProcessEventsByCode(terminalId, Constants.EXCEPTION_CODE_TIRED_DRIVE));
				exceptions.add(TerminalException.findUnProcessEventsByCode(terminalId, Constants.EXCEPTION_CODE_OBD_ERR));
				exceptions.add(TerminalException.findUnProcessEventsByCode(terminalId, Constants.EXCEPTION_CODE_DRUNK));
				
				if (exceptions != null) {
					for (TerminalException exception: exceptions) {
						if (exception != null) {
							ExceptionDataDto dto = new ExceptionDataDto();
							String code = exception.getCode();
							dto.setCode(code);
							String value;
							if (exception.getCharValue() != null) {
								value = exception.getCharValue();
							} else {
								value = exception.getLongValue() +"";
							}
							if (code.equals(Constants.EXCEPTION_CODE_HIGH_SPEED)) {
								value = value + " 公里每小时";
							} else if (code.equals(Constants.EXCEPTION_CODE_TIRED_DRIVE)) {
								value = value + " 分钟";
							} else if (code.equals(Constants.EXCEPTION_CODE_DRUNK)) {
								value = value + " 酒精度";
							}
							dto.setMessage(value);
							dto.setTime(exception.getCreatedAt().toString());
							dtoList.add(dto);
						}
							
					}
					return dtoList;
				} else return null;
				
			}
		});
	}


	public void processException(final String id) {
		Hibernate.readWrite(new ReadWriteTask() {

			public void doWork() {
				TerminalException.processException(id);
				logger.info("processed " + id);
			}
			
		});
		
	}


	public void setDataParameter(String terminalId, String eventType,
			String dataType) {
		HttpSession session = this.getThreadLocalRequest().getSession();
		session.setAttribute("terminalId", terminalId);
		session.setAttribute("eventType", eventType);
		session.setAttribute("dataType", dataType);
	}


}