package org.zju.car_monitor.db;

import java.util.List;

import org.junit.Ignore;
import org.zju.car_monitor.util.Hibernate;
import org.zju.car_monitor.util.ReadOnlyTask;

public class DepartmentsCurdTest{
	
	@Ignore
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
