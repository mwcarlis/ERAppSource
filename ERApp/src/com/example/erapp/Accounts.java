package com.example.erapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class Accounts implements Comparable
{

	private LinkedList<String> Items = new LinkedList<String>();
	private String UserName;
	private String password;
	
	public Accounts(String username2, String password2) 
	{
		UserName = username2;
		password = password2;
	}

	@Override
	public int compareTo(Object o) 
	{
		Accounts object = (Accounts)o;
		return UserName.compareTo(object.getUsername());
	}
	
	public String getUsername()
	{
		return UserName;
	}
	
	public String getPassword()
	{
		return password;
	}

		
}
