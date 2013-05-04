package com.example.myapp;

public class SurveyData {
	private int id;
	private String name;
	public SurveyData(){
		
	}
	public SurveyData(int id,String name){
		this.id=id;
		this.name=name;
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
}
