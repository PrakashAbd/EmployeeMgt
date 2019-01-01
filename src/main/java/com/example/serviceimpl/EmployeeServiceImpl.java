package com.example.serviceimpl;

import java.util.List;
import org.springframework.context.ApplicationContext;
import com.example.daoimpl.EmployeeDaoImpl;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
public class EmployeeServiceImpl implements EmployeeService
{
	
	public String addEmployee(Employee employee,ApplicationContext con) throws Exception 	//Call the addEmployee Function in DAO Layer
	{
		EmployeeDaoImpl employeeDao = con.getBean("empDao",EmployeeDaoImpl.class);
		return employeeDao.addEmployee(employee);		
	}

	public String deleteEmployee(String empId,ApplicationContext con) 					
	{
		EmployeeDaoImpl employeeDao = con.getBean("empDao",EmployeeDaoImpl.class);			//Creating an object for EmployeeDaoImpl class
		return employeeDao.deleteEmployee(empId);											//Call the delete function by passing employeeID
	}

	
	public List<Employee> displayAll(ApplicationContext con) 
	{
		EmployeeDaoImpl employeeDao = con.getBean("empDao",EmployeeDaoImpl.class);			// Call the display function in DAO Layer
		return employeeDao.displayAll();				
	}

	
	public String updateEmployee(Employee employee,ApplicationContext con) 
	{
		EmployeeDaoImpl employeeDao = con.getBean("empDao",EmployeeDaoImpl.class);	
		return employeeDao.updateEmployee(employee);										
	}

	
	public Employee displayById(String empId,ApplicationContext con) 
	{
		EmployeeDaoImpl employeeDao = con.getBean("empDao",EmployeeDaoImpl.class);
		return employeeDao.displayById(empId);
	}
}
