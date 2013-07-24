package org.zju.car_monitor.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.ReadWriteTask;

/**
 * @author jiezhen 7/17/13
 */
@RunWith(JUnit4.class)
public class CarCurdTest{


    @Test
    public void testCarInsert() {

        final Terminal terminal = new Terminal();
        terminal.setAlcoholLimit(200);
        terminal.setWaterTempLimit(100);
        terminal.setSpeedLimit(200);
        terminal.setTiredDriveLimitMinutes(200);
        terminal.setSamplingIntervalSeconds(5);
        terminal.setSamplingDataUploadSeconds(5);
        terminal.setTlWakeUpIntervalSeconds(10);
        terminal.setIdleStatusUploadSeconds(100);

        final Car car = new Car();
        car.setRegNumber("浙AC687R");
        car.setType("福克斯");
        car.setDriverName("张三");
        car.setDriverPhone("1367500000");
        car.setDepartment("浙江武警");
        car.setDivision("杭州中队");
        car.setBranch("西湖大队");
        car.setTerminal(terminal);

        Hibernate.readWrite(new ReadWriteTask() {
            @Override
            public void doWork() {
                terminal.save();
                car.save();
            }
        });
    }

}
