package com.example.myapp;

public class ReadJson {
	private String question;
	private int id;
	public ReadJson(){
		
	}
	public ReadJson(String question, int id){
		this.question=question;
		this.id=id;
	}
	public String getQuestion(){
		return question;
	}
	public int getID(){
		return id;
	}
}
