package org.zju.car_monitor.db;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.XmlUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

/**
 * @author jiezhen 7/24/13
 */
@Entity
@Table(name = "departments")
public class Department extends DbObject {
	private static Logger logger = Logger.getLogger(Department.class);

    @Column(name = "parent_id")
    String parentId = null;

    @Column(name = "name")
    String name = null;

    @Column(name = "long_name")
    String longName = null;
    
    public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public static List<Department> findAllDepartments() {
        return Hibernate.currentSession().createCriteria(Department.class).list();
    }
	
	public static Department findDepartmentByLongName(String longName) {
		logger.info("Getting department by long name " + longName);
		return (Department)Hibernate.currentSession().createCriteria(Department.class).add(Restrictions.eq("longName", longName)).list().get(0);
	}

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static String createDepartmentsXML() {
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

}
