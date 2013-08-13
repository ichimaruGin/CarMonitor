package org.zju.carmonitor.server;

import org.apache.log4j.Logger;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.db.CAT718EventAttribute;
import org.zju.car_monitor.db.CAT718TerminalEvent;
import org.zju.car_monitor.db.CATOBDTerminalEvent;
import org.zju.car_monitor.db.Car;
import org.zju.car_monitor.db.Department;
import org.zju.car_monitor.db.TerminalEventAttrChar;
import org.zju.car_monitor.db.TerminalEventAttrLong;
import org.zju.car_monitor.db.TerminalException;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @author jiezhen 7/23/13
 */
@SuppressWarnings(value = {"serial" })
public class XmlServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(XmlServlet.class);

    private String createCarsXML() {
    	return (String) Hibernate.readOnly(new ReadOnlyTask<String>(){

			public String doWork() {
				List<Car> cars = Car.findAllCars();
				StringBuilder sb = new StringBuilder();
				sb.append("<List>");
				for (Car car: cars) {
					sb.append("<car>");
					sb.append(XmlUtil.pair("departmentName", car.getDepartment().getLongName()));
					sb.append(XmlUtil.pair("departmentId", car.getDepartment().getId()));
					sb.append(XmlUtil.pair("terminalId", car.getTerminal().getTerminalId()));
					sb.append(XmlUtil.pair("terminalId_", car.getTerminal().getId()));
					sb.append(XmlUtil.pair("driverName",car.getDriverName()));
					sb.append(XmlUtil.pair("driverPhone",car.getDriverPhone()));
					sb.append(XmlUtil.pair("carType", car.getType()));
					sb.append(XmlUtil.pair("carRegNumber", car.getRegNumber()));
					sb.append("</car>");
				}
				sb.append("</List>");
				
				return sb.toString();
			}
    		
    	});
    }
    
    private String createExceptionsXML(final String terminalId, final String type) {
    	String xml = (String) Hibernate.readOnly(new ReadOnlyTask<String>() {

			public String doWork() {
				List<TerminalException> list = TerminalException.findNoOfEvents(terminalId, 100);
				StringBuilder builder = new StringBuilder();
				builder.append("<List>");
				
				if (list != null) {
					for (TerminalException exception: list) {
						builder.append("<event>");
						builder.append(XmlUtil.pair("id", exception.getId()));
						builder.append(XmlUtil.pair("time", exception.getCreatedAt().toString()));
						if (type.equals(Constants.EXCEPTION_CODE_HIGH_SPEED)) {
							builder.append(XmlUtil.pair("value", exception.getLongValue() + " 公里每小时"));
						} else if (type.equals(Constants.EXCEPTION_CODE_TIRED_DRIVE)) {
							builder.append(XmlUtil.pair("value",  exception.getLongValue() + " 分钟"));
						} else if (type.equals(Constants.EXCEPTION_CODE_OBD_ERR)) {
							builder.append(XmlUtil.pair("value", exception.getCharValue()));
						} else if (type.equals(Constants.EXCEPTION_CODE_DRUNK)) {
							builder.append(XmlUtil.pair("value", Constants.EXCEPTION_CODE_DRUNK));
						}
						String process;
						if ("N".equals(exception.getProcessFlag())) {
							process = "未处理";
						}else {
							process = "已处理";
						}
						builder.append(XmlUtil.pair("processFlag", process));
						builder.append("</event>");
					}
				}
				builder.append("<List>");
				
				return builder.toString();
				
			}
    		
    	});
    	
    	return xml;

    }
    
      private String createCAT718EventsXML(final String terminalId, final String type) {
    	String xml = (String) Hibernate.readOnly(new ReadOnlyTask<String>() {

			public String doWork() {
				List<CAT718TerminalEvent> list = CAT718TerminalEvent.findNoOfEvents(terminalId, 100);
				StringBuilder builder = new StringBuilder();
				builder.append("<List>");
				
				if (list != null) {
					for (CAT718TerminalEvent event: list) {
						builder.append("<event>");
						TerminalEventAttrLong attr = TerminalEventAttrLong.getEventAttrLongByEventIdAndType(event.getId(), type);
						builder.append(XmlUtil.pair("time", attr.getCreatedAt().toString()));
						if (type.equals(Constants.CAR_SPEED_PARAM)) {
							builder.append(XmlUtil.pair("value", attr.getAttrValue() + " 公里每小时"));
						} else if (type.equals(Constants.CAR_RPM_PARAM)) {
							builder.append(XmlUtil.pair("value", (attr.getAttrValue() /4) + " 转每分钟"));
						} else if (type.equals(Constants.CAR_WATER_TEMP_PARAM)) {
							builder.append(XmlUtil.pair("value", (attr.getAttrValue() - 40) + " 摄氏度"));
						}
						
						builder.append("</event>");
					}
				}
				builder.append("<List>");
				
				return builder.toString();
				
			}
    		
    	});
    	
    	return xml;
    }
    
    private String createDepartmentsXML() {
        String xml = (String) Hibernate.readOnly(new ReadOnlyTask<String>() {
            public String doWork() {
                List<Department> departments = Department.findAllDepartments();
                StringBuilder sb = new StringBuilder();
                sb.append("<List>");

                for(Department department: departments) {
                	String parentId;
                	if (department.getId().equals(department.getParentId())){
                		parentId = "root";
                	}else {
                		parentId = department.getParentId();
                	}
                    sb.append("<department>");
                    sb.append(XmlUtil.pair("name", department.getName()));
                    sb.append(XmlUtil.pair("id", department.getId()));
                    sb.append(XmlUtil.pair("parentId", parentId));
                    sb.append("</department>");
                }
                sb.append("</List>");
                return sb.toString();
            }
        });
        return xml;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req != null){
            resp.setCharacterEncoding("UTF-8");
            String para = req.getParameter("param");
            logger.info("URL Param: " + para);
            if (para.equals("departments")) {
                resp.getWriter().write(createDepartmentsXML());
            } else if (para.equals("cars")) {
            	resp.getWriter().write(createCarsXML());
            } else if (para.equals("events")){
            	String eventType = req.getParameter("eventType");
            	String type = req.getParameter("dataType");
            	String terminalId = req.getParameter("terminalId");
            	if (Constants.EVENT_TYPE_CAT718.equals(eventType)) {
            		resp.getWriter().write(createCAT718EventsXML(terminalId, type));
            	} else if (Constants.EVENT_TYPE_EXCEPTION.equals(eventType)) {
            		resp.getWriter().write(createExceptionsXML(terminalId, type));
            	}
            } else {
                logger.error("error match param");
            }
            resp.flushBuffer();
        }
    }
}

