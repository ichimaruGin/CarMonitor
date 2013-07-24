package org.zju.car_monitor.db;

import org.zju.car_monitor.util.Hibernate;

import javax.persistence.*;

/**
 * @author jiezhen 7/17/13
 */
@Entity
@Table(name = "cars")
public class Car extends DbObject {

    @Column(name = "reg_number")
    String regNumber = null;

    @Column(name = "type")
    String type = null;

    @Column(name = "driver_name")
    String driverName = null;

    @Column(name = "driver_phone")
    String driverPhone = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    Terminal terminal = null;

    @Column(name = "department")
    String department = null;

    @Column(name = "division")
    String division = null;

    @Column(name = "branch")
    String branch = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_id")
    Map map = null;

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setMap(Map map) {
        this.map = map;
    }



}
