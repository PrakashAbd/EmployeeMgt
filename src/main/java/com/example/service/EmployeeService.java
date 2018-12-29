package com.example.service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import com.example.entity.Employee;

public interface EmployeeService
{
	String addEmployee(Employee employee,ApplicationContext con) throws Exception;
	String deleteEmployee(String empId,ApplicationContext con);
	List<Employee> displayAll(ApplicationContext con);
	String updateEmployee(Employee employee,ApplicationContext con);
	Employee displayById(String empId,ApplicationContext con);
}
