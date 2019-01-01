package com.example.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EmployeeDao;
import com.example.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao
{
	HibernateTemplate template;
	
	public EmployeeDaoImpl() {
		super();
	}

	public EmployeeDaoImpl(HibernateTemplate template) {
		super();
		this.template = template;
	}


	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public String addEmployee(Employee employee) throws Exception 
	{
		try {
			Session s = template.getSessionFactory().openSession();     //Open a new session using sessionFactory object
			s.save(employee);											//Add the employee details to DB using hibernate template
			s.beginTransaction().commit();
			s.close();
			return "Inserted Successfully";
		}
		catch(Exception e)
		{
			throw new Exception();
		}
		
	}

	public String deleteEmployee(String empId) 
	{	
		Session s = template.getSessionFactory().openSession();			
		s.delete(template.get(Employee.class, empId));					//Get and delete an employeeById from DB 
		s.beginTransaction().commit();
		s.close();
		return "Employee Data Deleted Successfully";								
	}

	public List<Employee> displayAll() 
	{
		return template.loadAll(Employee.class);						//Getting an list of employees from DB								
	}

	
	@SuppressWarnings("deprecation")
	public String updateEmployee(Employee employee) 
	{
		template.bulkUpdate("update employee set username = ? , password=? ", employee.getUsername(),employee.getPassword()); 	//Updating an employee detail in DB
		return "Updated Succesfully";								
	}

	@Transactional
	public Employee displayById(String empId) 
	{
		return template.get(Employee.class, empId);								
	}	

}
