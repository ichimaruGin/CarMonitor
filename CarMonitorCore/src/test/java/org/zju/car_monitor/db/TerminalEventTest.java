package org.zju.car_monitor.db;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;
import org.zju.car_monitor.util.ReadWriteTask;


public class TerminalEventTest {
	
	@Ignore
	public void test718EventGet(){
		Hibernate.readWrite(new ReadWriteTask(){

			public void doWork() {
				Terminal terminal = Terminal.findByTerminalId("00001");
				CAT718TerminalEvent newEvent = new CAT718TerminalEvent();
				newEvent.setTerminal(terminal);
				newEvent.save();
				CAT718TerminalEvent event = CAT718TerminalEvent.findLatestEventByTerminalId("00001");
				if (event == null) {
					System.out.println("event is null");
				}
				System.out.println(event.id);
			}
			
		});
	}

}
