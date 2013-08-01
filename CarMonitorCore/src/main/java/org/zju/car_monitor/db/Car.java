package org.zju.car_monitor.db;

import java.util.List;

import javax.persistence.*;

import org.hibernate.criterion.Restrictions;
import org.zju.car_monitor.client.CarDto;
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
    
    public static void updateCar(Car car, CarDto carDto) {
    	carSetter(car, carDto);
    	car.saveOrUpdate();
    }
    
    private static void carSetter(Car car, CarDto carDto) {
    	car.setRegNumber(carDto.getCarRegNumber());
		car.setType(carDto.getCarType());
		car.setDriverName(carDto.getDriverName());
		car.setDriverPhone(carDto.getDriverPhone());
		Department department = (Department) Hibernate.currentSession().get(Department.class, carDto.getDepartmentId());
		Terminal terminal = (Terminal) Hibernate.currentSession().get(Terminal.class, carDto.getTerminalId());
		car.setDepartment(department);
		car.setTerminal(terminal);
    }
    public static void saveCar(CarDto carDto) {
    	Car car = new Car();
    	carSetter(car, carDto);
    	car.save();
    }
    
    public void setMap(Map map) {
        this.map = map;
    }

    public static List<Car> findAllCars() {
    	return Hibernate.currentSession().createCriteria(Car.class).list();
    }
    
    public static Car findCarByTerminalUUId(String terminalId) {
    	List<?> list =  Hibernate.currentSession().createCriteria(Car.class).createAlias("terminal", "terminal")
    			.add(Restrictions.eq("terminal.id", terminalId)).list();
    	if (list == null || list.size() == 0) return null;
    		else return (Car) list.get(0);
    }

}
