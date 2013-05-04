package com.example.myapp;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.android.uti.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText loginUsername;
	private EditText loginPassword;
	private Button loginButton;
	private Button registerButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*
		 * if (android.os.Build.VERSION.SDK_INT > 9) { StrictMode.ThreadPolicy
		 * policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		 * StrictMode.setThreadPolicy(policy); }
		 */
		findViews();
		setListener();
	}

	private void findViews() {
		loginUsername = (EditText) findViewById(R.id.login_username);
		loginPassword = (EditText) findViewById(R.id.login_password);
		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.register_button);

	}

	private void setListener() {
		loginButton.setOnClickListener(login_process);
		registerButton.setOnClickListener(register_process);
	}

	private OnClickListener login_process = new OnClickListener() {
		public void onClick(View v) {
			String [] receivedLines;

			Data.login_username = loginUsername.getText().toString();
			Data.login_password = loginPassword.getText().toString();
			// then tcp connection to server
			NetWork nw = new NetWork();
			nw.disable_policy();
			receivedLines=nw.http10TcpSendAndRec(nw.http_request(Data.host, "/survey/8/"));
			
			//Data.jsonData=receivedLines[7];
			//Data.jsonData=Data.jsonData.replaceAll("\"","'");
			//nw.getJsonData();
			Gson gson= new Gson();
			Type type = new TypeToken<List<ReadJson>>(){}.getType();  
			Data.dataList=gson.fromJson(receivedLines[7], type);


			//********

			//ReadJson rj=new ReadJson();
			//rj=list.get(0);

			
			//nw.http10TcpSendOnly("username:" + Data.login_username + ",password:"+ Data.login_password);
			privacy_dialogue();

		}
	};

	private OnClickListener register_process = new OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, register.class);
			startActivity(intent);
		}
	};

	public void privacy_dialogue() {
		final CharSequence[] items = { "None", "Low", "Medium", "High" };

		AlertDialog.Builder listBuilder = new AlertDialog.Builder(
				MainActivity.this);
		listBuilder.setTitle("Select A Privacy Level");
		listBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Toast.makeText(getApplicationContext(), items[item],
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,
						TestGalleryActivity.class);
				intent.putExtra("privacy", item);
				startActivity(intent);
			}
		});
		AlertDialog alertList = listBuilder.create();
		alertList.show();

	}

}
