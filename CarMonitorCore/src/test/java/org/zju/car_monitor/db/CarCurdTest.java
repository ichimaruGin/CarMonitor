package org.zju.car_monitor.db;

import org.junit.Ignore;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.ReadWriteTask;

/**
 * @author jiezhen 7/17/13
 */
public class CarCurdTest{


    @Ignore
    public void testCarInsert() {
        final Terminal terminal = new Terminal();
        terminal.setTerminalId("00001");
        terminal.setAlcoholLimit(200);
        terminal.setWaterTempLimit(100);
        terminal.setSpeedLimit(200);
        terminal.setTiredDriveLimitMinutes(200);
        terminal.setSamplingIntervalSeconds(5);
        terminal.setSamplingDataUploadSeconds(5);
        terminal.setTlWakeUpIntervalSeconds(10);
        terminal.setIdleStatusUploadSeconds(100);
        
        Department department = (Department) Hibernate.readOnly(new ReadOnlyTask<Department>(){
			public Department doWork() {
				return Department.findAllDepartments().get(0);
			}
        });
        
        final Car car = new Car();
        car.setRegNumber("浙AC687R");
        car.setType("福克斯");
        car.setDriverName("张三");
        car.setDriverPhone("1367500000");
        car.setDepartment(department);
        car.setTerminal(terminal);

        Hibernate.readWrite(new ReadWriteTask() {
            public void doWork() {
                terminal.save();
                car.save();
            }
        });
    }

}
