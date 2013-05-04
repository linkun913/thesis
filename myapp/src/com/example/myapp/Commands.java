package com.example.myapp;

import com.android.uti.NetWork;

public class Commands {
	private NetWork nw;

	public Commands() {
		nw = new NetWork();
		nw.disable_policy();
	}
	public String getSurveys(){
		String[] s=nw.http10TcpSendAndRec(nw.http_request(Data.host, "/survey/"));
		return s[s.length-1];
	}

	public String getSurveyQue(String s) {
		String[] s1=nw.http10TcpSendAndRec(nw.http_request(Data.host, "/survey/"+s+"/"));
		return s1[s1.length-1];
	}

	public void createSessionAndSetPrivacyLevel() {
		nw.http10TcpSendAndRec(
			nw.http_request(
				Data.host, 
				"/survey/8/"
				+ 
				Data.login_username 
				+ 
				"/" 
				+ 
				Data.login_password 
				+ 
				"/"
				+ 
				Data.privacy 
				+ 
				"/session/"
			)
		);
	}

	public void sendSurveyResponse() {
		for (int i = 0; i < Data.SurveyLen; i++) {
			nw.http10TcpSendAndRec (
				nw.http_request (
					Data.host, 
					"/survey/"
					+ 
					Integer.toString(Data.dataList.get(i).getID()) 
					+ 
					"/"
					+ 
					Double.toString(Data.dataList.get(i).getPrivateRate())
					+ 
					"/" 
					+ 
					Data.login_username
					+ 
					"/"
					+ 
					Data.login_password
					+ 
					"/response/"
				)
			);
		}
	}
}
