package com.example.demo;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.daoimpl.EmployeeDaoImpl;
import com.example.entity.Employee;
import com.example.serviceimpl.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JunitTests
{
	private ApplicationContext con=new ClassPathXmlApplicationContext("Beans.xml");
	
	private Employee employee;
	
	private List<Employee> empList;
	
	@Mock
	private EmployeeDaoImpl empDao;
	
	@InjectMocks
	private EmployeeServiceImpl empService=con.getBean("empService",EmployeeServiceImpl.class);;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		empList=new ArrayList<>();
		employee=new Employee();
		employee.setUsername("kamal");
		employee.setPassword("Kamal");
		employee.setEmailId("kamal@gmail.com");
		employee.setFullName("Kamal");
		employee.setDob("2/12/1990");
		employee.setGender("Male");
		employee.setSecurityQuestion("Which place you like most?");
		employee.setSecurityAnswer("temple");
		empList.add(employee);
		System.out.println(empList);
	}
	
	@Test
	public void testSelect()
	{
		try {
			org.mockito.Mockito.when(empDao.displayAll()).thenReturn(empList);
			System.out.println(empDao.displayAll().get(0));
			assertEquals("kamal@gmail.com",empService.displayAll(con).get(0).getEmailId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
