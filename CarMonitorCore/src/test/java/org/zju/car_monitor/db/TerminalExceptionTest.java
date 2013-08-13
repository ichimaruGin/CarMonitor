package org.zju.car_monitor.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;

@RunWith(JUnit4.class)
public class TerminalExceptionTest {
	
	@Test
	public void testGetUnProcessed() {
		Hibernate.readOnly(new ReadOnlyTask(){

			public Object doWork() {
				List<TerminalException> list = TerminalException.findUnProcessEvents("00001");
				System.out.println(list.size());
				return null;
			}
			
		});
	}

}
