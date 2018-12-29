package com.example.serviceimpl;

import java.util.List;
import com.example.employeemanagement.Config;
import com.example.entity.Login;
import com.example.service.LoginService;

public class LoginServiceImpl implements LoginService
{

	public int validate(Login login) 
	{			return Config.getLoginDao().validate(login);			}

	
	public String add(Login login) 
	{			return Config.getLoginDao().add(login);					}

	
	public List<Login> display() 
	{			return Config.getLoginDao().display();					}

}
