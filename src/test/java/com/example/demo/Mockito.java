package com.example.demo;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.daoimpl.EmployeeDaoImpl;
import com.example.entity.Employee;
import com.example.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class Mockito 
{	
		ApplicationContext con = new ClassPathXmlApplicationContext("Beans.xml");
		@Mock
		EmployeeDaoImpl employeeDao;
		
		@Rule public MockitoRule rule = MockitoJUnit.rule();
		
		@InjectMocks
		EmployeeServiceImpl employeeService = con.getBean("empService",EmployeeServiceImpl.class); 
		
		List<Employee> empList = new ArrayList<Employee>();
		Employee employee = new Employee();
		
		@Before
		public void setUp()
		{
			MockitoAnnotations.initMocks(this);	
			employee.setEmpId("101");
			employee.setUsername("Dinesh");
			employee.setPassword("Dinesh@123");
			employee.setDob("10.09/1998");
			employee.setEmailId("dini@gmail.com");
			employee.setFullName("suresh");
			employee.setGender("Male");
			employee.setSecurityAnswer("ani");
			employee.setSecurityQuestion("Mothers name?");
			empList.add(employee);

		}
		
		@Test
		public void testDisplayAll() 
		{	
			org.mockito.Mockito.when(employeeDao.displayAll()).thenReturn(empList);
			String name = employeeService.displayAll(con).get(0).getUsername();
			System.out.println(name);
			assertEquals(empList.get(0).getUsername(),name);
		}
}