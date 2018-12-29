package com.example.dao;

import java.util.List;

import com.example.entity.Employee;

public interface EmployeeDao 
{
	String addEmployee(Employee employee) throws Exception;
	String deleteEmployee(String empId);
	List<Employee> displayAll();
	String updateEmployee(Employee employee);
	Employee displayById(String empId);
}
