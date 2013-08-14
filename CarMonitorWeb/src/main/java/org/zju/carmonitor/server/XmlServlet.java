package org.zju.carmonitor.server;

import org.apache.log4j.Logger;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.db.CAT718EventAttribute;
import org.zju.car_monitor.db.CAT718TerminalEvent;
import org.zju.car_monitor.db.CATOBDTerminalEvent;
import org.zju.car_monitor.db.Car;
import org.zju.car_monitor.db.Department;
import org.zju.car_monitor.db.DepartmentsCurdTest;
import org.zju.car_monitor.db.TerminalEventAttrChar;
import org.zju.car_monitor.db.TerminalEventAttrLong;
import org.zju.car_monitor.db.TerminalException;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.XmlUtil;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req != null){
            resp.setCharacterEncoding("UTF-8");
            String para = req.getParameter("param");
            logger.info("URL Param: " + para);
            if (para.equals("departments")) {
                resp.getWriter().write(Department.createDepartmentsXML());
            } else if (para.equals("cars")) {
            	resp.getWriter().write(Car.createCarsXML());
            } else if (para.equals("events")){
            	String eventType = req.getParameter("eventType");
            	String type = req.getParameter("dataType");
            	String terminalId = req.getParameter("terminalId");
            	if (Constants.EVENT_TYPE_CAT718.equals(eventType)) {
            		resp.getWriter().write(CAT718TerminalEvent.createCAT718EventsXML(terminalId, type));
            	} else if (Constants.EVENT_TYPE_EXCEPTION.equals(eventType)) {
            		resp.getWriter().write(TerminalException.createExceptionsXML(terminalId, type));
            	}
            } else {
                logger.error("error match param");
            }
            resp.flushBuffer();
        }
    }
}

