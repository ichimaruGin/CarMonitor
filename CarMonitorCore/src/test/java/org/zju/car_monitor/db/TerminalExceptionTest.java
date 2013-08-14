package org.zju.car_monitor.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.client.Constants;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;

@RunWith(JUnit4.class)
public class TerminalExceptionTest {
	
	@Test
	public void testGetUnProcessed() {
		Hibernate.readOnly(new ReadOnlyTask(){

			public Object doWork() {
				TerminalException ex = TerminalException.findUnProcessEventsByCode("00001", Constants.EXCEPTION_CODE_HIGH_SPEED);
				return null;
			}
			
		});
	}

}
