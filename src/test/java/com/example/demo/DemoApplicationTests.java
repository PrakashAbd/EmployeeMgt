package com.example.demo;

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
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)

public class DemoApplicationTests extends TestCase
{
	ApplicationContext con = new ClassPathXmlApplicationContext("Beans.xml");
	@Mock
	EmployeeDaoImpl employeeDao;
	
	@Rule public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	EmployeeServiceImpl employeeService = con.getBean("empService",EmployeeServiceImpl.class); 
	
	List<Employee> empList = new ArrayList<Employee>();
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		Employee employee = new Employee();
		employee.setEmpId("101");
		employee.setUsername("Dinesh");
		employee.setPassword("Dinesh@123");
		empList.add(employee);
		employee = new Employee();
		employee.setEmpId("102");
		employee.setUsername("Ram");
		employee.setPassword("Rama@123");
		empList.add(employee);
		org.mockito.Mockito.when(employeeDao.displayAll()).thenReturn(empList);
	}
	
	/*@Test
	public void testAddEmployee() throws Exception
	{	
		org.mockito.Mockito.when(employeeDao.addEmployee(empList.get(0))).thenReturn("Inserted Successfully");
		assertEquals(employeeService.addEmployee(empList.get(0)), "Inserted Successfully");
	}*/
	
	@Test
	public void testDisplayById() 
	{
		setUp();
		org.mockito.Mockito.when(employeeDao.displayById("101")).thenReturn(empList.get(0));
		assertEquals("Dinesh", employeeService.displayById("101",con).getUsername());
	}
	
	@Test
	public void testDisplayAll() 
	{	
		org.mockito.Mockito.when(employeeDao.displayAll()).thenReturn(empList);
		
		assertEquals(empList.get(0).getEmailId(),employeeService.displayAll(con).get(0).getEmailId());
	}
}