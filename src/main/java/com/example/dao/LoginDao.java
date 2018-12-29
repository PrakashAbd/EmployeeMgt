package com.example.dao;

import java.util.List;

import com.example.entity.Login;

public interface LoginDao 
{
	int validate(Login login);
	List<Login> display();
	String add(Login login);
}
