package org.zju.car_monitor.db;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

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
}
