package com.example.listview;

public class Model_Category_with_number {
	private String text;
	private String number;
	
	public Model_Category_with_number(String name, String number)
	{
		text=name;
		this.number  = number;
	}
	public String getName()
	{
		return text;
	}
	
	public String getNumber()
	{
		return number;
	}

}
