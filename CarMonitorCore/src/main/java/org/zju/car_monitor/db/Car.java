package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.*;

import org.zju.car_monitor.util.Hibernate;

/**
 * @author jiezhen 7/17/13
 */
@Entity
@Table(name = "cars")
public class Car extends DbObject {

    public String getRegNumber() {
		return regNumber;
	}

	public String getType() {
		return type;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public Map getMap() {
		return map;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department = null;

    
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

    
    public void setMap(Map map) {
        this.map = map;
    }

    public static List<Car> findAllCars() {
    	return Hibernate.currentSession().createCriteria(Car.class).list();
    }

}
