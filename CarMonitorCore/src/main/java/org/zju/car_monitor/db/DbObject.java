package org.zju.car_monitor.db;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author jiezhen 7/17/13
 */

@MappedSuperclass
public abstract class DbObject {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.zju.car_monitor.db.UUIDGenerator")
    String id = null;

    @Column(name = "created_at")
    @Type(type = "date")
    Date createdAt = null;


    @Column(name = "updated_at")
    @Type(type = "date")
    Date updatedAt = null;

    public void save() {
        Hibernate.currentSession().save(this);
    }

    public void saveOrUpdate() {
        Hibernate.currentSession().saveOrUpdate(this);
    }

    public void delete() {
        Hibernate.currentSession().delete(this);
    }

    public void refresh() {
        Hibernate.currentSession().refresh(this);
    }

}
