package org.zju.car_monitor.db;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;

import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;


    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

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