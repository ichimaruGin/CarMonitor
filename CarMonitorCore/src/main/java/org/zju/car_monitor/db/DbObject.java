package org.zju.car_monitor.db;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author jiezhen 7/17/13
 */

@MappedSuperclass
public abstract class DbObject {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

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

    public String save() {
        return (String)Hibernate.currentSession().save(this);
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