package com.example.service;

import java.util.List;

import com.example.entity.Login;

public interface LoginService 
{
	int validate(Login login);
	String add(Login login);
	List<Login> display();
}
