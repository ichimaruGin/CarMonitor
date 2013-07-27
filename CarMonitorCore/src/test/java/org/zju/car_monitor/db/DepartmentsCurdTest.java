package org.zju.car_monitor.db;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;

@RunWith(JUnit4.class)
public class DepartmentsCurdTest{
	
	@Test
	public void testGetAllDepartments() {
		Object object = null;
		List<Department> departments = (List<Department>) Hibernate.readOnly(new ReadOnlyTask<List<Department>>(){

			public List<Department> doWork() {
				List<Department> departments = Department.findAllDepartments();
				return departments;
			}
			
		});
		
		for (Department department: departments) {
			System.out.println(department.name);
		}
		
	}

}
