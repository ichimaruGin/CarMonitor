package org.zju.carmonitor.server;

import org.apache.log4j.Logger;
import org.zju.car_monitor.db.Department;
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
            } else {
                logger.error("error match param");
            }
            resp.flushBuffer();
        }
    }
}
