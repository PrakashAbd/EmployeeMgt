package com.example.daoimpl;

import java.util.List;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import com.example.dao.LoginDao;
import com.example.employeemanagement.Config;
import com.example.entity.Login;

public class LoginDaoImpl implements LoginDao
{
	HibernateTemplate logintemplate;
	
	
	public LoginDaoImpl() {
		super();
	}
	
	public LoginDaoImpl(HibernateTemplate logintemplate) {
		super();
		this.logintemplate = logintemplate;
	}
	
	public HibernateTemplate getLogintemplate() {
		return logintemplate;
	}

	public void setLogintemplate(HibernateTemplate logintemplate) {
		this.logintemplate = logintemplate;
	}

	public int validate(Login login) 
	{
		try 
		{		
			if(display().stream().
			   filter(i -> i.getUsername().equals(login.getUsername()) && i.getPassword().equals(login.getPassword())).
				  toArray().length!=0)
			{
				if(Config.getEmployeeDao().displayAll().stream().filter(e -> e.getUsername().equals(login.getUsername()) && e.getPassword().equals(login.getPassword())).toArray().length!=0)
				{			return 2;			}
				else
				{			return 1;			}
			}
			else	{			return 0;			}
		
		}
		catch(Exception e){		return 0;		}	
	}
	

	public String add(Login login) 
	{		
		Session session = logintemplate.getSessionFactory().openSession();
		session.save(login);
		session.beginTransaction().commit();
		return "Added Successfully";			
	}
	
	public List<Login> display() 
	{		return logintemplate.loadAll(Login.class);			}

}
