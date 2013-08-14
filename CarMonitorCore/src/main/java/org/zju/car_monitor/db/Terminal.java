package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.util.Hibernate;

/**
 * @author jiezhen 7/17/13
 */

@Entity
@Table(name = "terminals")
public class Terminal extends DbObject {


    @Column(name = "terminal_id")
    String terminalId = null;

    @Column(name = "alcohol_limit")
    long alcoholLimit = 0;

    @Column(name = "water_temp_limit")
    long waterTempLimit = 0;

    @Column(name = "speed_limit")
    long speedLimit = 0;

    @Column(name = "tired_drive_limit_minutes")
    long tiredDriveLimitMinutes = 0;

    @Column(name = "sampling_interval_seconds")
    long samplingIntervalSeconds = 0;

    @Column(name = "sampling_data_upload_seconds")
    long samplingDataUploadSeconds = 0;

    @Column(name = "tl_wake_up_interval_seconds")
    long tlWakeUpIntervalSeconds = 0;

    @Column(name = "idle_status_upload_seconds")
    long idleStatusUploadSeconds = 0;

    public void setWaterTempLimit(long waterTempLimit) {
        this.waterTempLimit = waterTempLimit;
    }

    public void setSpeedLimit(long speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setTiredDriveLimitMinutes(long tiredDriveLimitMinutes) {
        this.tiredDriveLimitMinutes = tiredDriveLimitMinutes;
    }

    public void setSamplingIntervalSeconds(long samplingIntervalSeconds) {
        this.samplingIntervalSeconds = samplingIntervalSeconds;
    }

    public void setSamplingDataUploadSeconds(long samplingDataUploadSeconds) {
        this.samplingDataUploadSeconds = samplingDataUploadSeconds;
    }

    public void setTlWakeUpIntervalSeconds(long tlWakeUpIntervalSeconds) {
        this.tlWakeUpIntervalSeconds = tlWakeUpIntervalSeconds;
    }

    public void setIdleStatusUploadSeconds(long idleStatusUploadSeconds) {
        this.idleStatusUploadSeconds = idleStatusUploadSeconds;
    }

    public void setAlcoholLimit(long alcoholLimit) {
        this.alcoholLimit = alcoholLimit;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }


    public static Terminal findByTerminalId(String terminalId) {
        List terminals = Hibernate.currentSession().
                createCriteria(Terminal.class).
                add(Restrictions.eq("terminalId", terminalId)).list();
        if (terminals == null || terminals.size() == 0)
            throw new RuntimeException("Can't find terminal " + terminalId + " in database.");
        return (Terminal)terminals.get(0);
    }
    
    public static List<Terminal> findAll() {
    	return (List<Terminal> )Hibernate.currentSession().createCriteria(Terminal.class).list();
    }
}
