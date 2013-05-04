package com.example.myapp;

import java.util.List;

public class Data {
	static public String login_username = null;
	static public String login_password = null;
	static public String host = "loki.eng.unsw.edu.au";
	//static public byte[] hostBytes = new byte[] { (byte) 149, (byte) 171, (byte) 93, (byte) 198 };// 149.171.93.198
	static public int port = 80;
	static public int SurveyLen;
	static public String keyWord = "tele3118project\0";
	static public String privacy;
	static public int privacyLevel;
	static public List<QuestionData> dataList;
	static public List<SurveyData> surveyList;
}
