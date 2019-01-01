package com.example.employeemanagement;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.Employee;
import com.example.entity.Login;
import com.example.serviceimpl.EmployeeServiceImpl;
import com.example.serviceimpl.LoginServiceImpl;

@RestController
public class Controller 
{
	ApplicationContext con = new ClassPathXmlApplicationContext("Beans.xml");
	LoginServiceImpl loginService = con.getBean("loginService",LoginServiceImpl.class);
	EmployeeServiceImpl employeeService = con.getBean("empService",EmployeeServiceImpl.class);
	
	@RequestMapping("/EmpMgt/checkLogin")
	public ResponseEntity<String> func3(@RequestBody Login obj)							//Validate the login details
	{
		int num = loginService.validate(obj);
		return new ResponseEntity<String>( ((num > 0)? ( (num==1)? "Admin" : "Employee has authenticated successfully") : "Invalid Username and Password"),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/EmpMgt/InsertData")
	public ResponseEntity<String> func4(@RequestBody Employee u)						//Get the values entered by user using @RequestBody
	{
		try {
			employeeService.addEmployee(u,con);											//Insert the empoyee details into DB
			Login login = Config.getLogin();
			login.setUsername(u.getUsername());
			login.setPassword(u.getPassword());
			
			System.out.println(loginService.add(login));
			return new ResponseEntity<String> ("Employee data inserted successfully.",HttpStatus.OK);
		} 
		catch (Exception e) 
		{
				return new ResponseEntity<String> ("Input data mismatch",HttpStatus.OK);
		}
	}
	
	@RequestMapping("/EmpMgt/getAllEmpDetails")											//Get all employee Details from DB
	public ResponseEntity<List<Employee>> func4()
	{
		List<Employee> list = employeeService.displayAll(con);
		return new ResponseEntity<List<Employee>> (list,HttpStatus.OK);
	}
	
	
	@RequestMapping("/EmpMgt/getByEmpId/{empId}")										//Get an employee using employeeId from DB
	public  ResponseEntity<Employee> func5(@PathVariable String empId)
	{
		return new ResponseEntity<Employee>(employeeService.displayById(empId,con),HttpStatus.OK);
	}

	
	@RequestMapping("/EmpMgt/deleteEmp/{empId}")
	public  ResponseEntity<String> func6(@PathVariable String empId)
	{
		return new ResponseEntity<String>(employeeService.deleteEmployee(empId,con),HttpStatus.OK);
	}
}