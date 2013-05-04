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
			Commands cmd = new Commands();
			Gson gson = new Gson();
			Type type = new TypeToken<List<SurveyData>>() {
			}.getType();
			Data.login_username = loginUsername.getText().toString();
			Data.login_password = loginPassword.getText().toString();
			// then tcp connection to server
			Data.surveyList = gson.fromJson(cmd.getSurveys(), type);
			//Data.SurveyLen = Data.surveyList.size();
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
		AlertDialog alertList;
		AlertDialog.Builder listBuilder = new AlertDialog.Builder(
				MainActivity.this);
		listBuilder.setTitle("Select A Privacy Level");
		listBuilder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Toast.makeText(getApplicationContext(), items[item],
						Toast.LENGTH_SHORT).show();
				Data.privacyLevel=item;
				Intent intent = new Intent(MainActivity.this,
						chooseSurvey.class);
				//intent.putExtra("privacy", item);
				startActivity(intent);

			}
		});
		alertList = listBuilder.create();
		alertList.show();
		

	}

}
