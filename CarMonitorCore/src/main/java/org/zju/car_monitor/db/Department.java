package org.zju.car_monitor.db;

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

    @Column(name = "parent_id")
    String parentId = null;

    @Column(name = "name")
    String name = null;

    public static List<Department> findAllDepartments() {
        return Hibernate.currentSession().createCriteria(Department.class).list();
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
