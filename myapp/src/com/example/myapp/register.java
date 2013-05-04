package com.example.myapp;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.android.uti.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class register extends Activity {
	private EditText registerUsername;
	private TextView text_view;
	private Button registerSubmitButton;
	private Button registerBackButton;
	private String username = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpage);
		findViews();
		setListener();
		//text_view.setText("1");
	}

	private void findViews() {
		registerSubmitButton = (Button) findViewById(R.id.register_submit_button);
		registerBackButton = (Button) findViewById(R.id.register_back_button);
		registerUsername = (EditText) findViewById(R.id.register_username);
		text_view = (TextView) findViewById(R.id.textView12);

	}

	private void setListener() {
		registerSubmitButton.setOnClickListener(submitAll);
		registerBackButton.setOnClickListener(goBack);
	}

	private OnClickListener goBack = new OnClickListener() {
		public void onClick(View v) {
			finish();
		}
	};

	private OnClickListener submitAll = new OnClickListener() {
		public void onClick(View v) {

			text_view.setText("");
			NetWork nw = new NetWork();
			int errorCode = 0;
			String password;
			String errorString;
			byte[] c = null;
			byte[] d = null;
			byte[] f = null;

			username = registerUsername.getText().toString();
			username = username + "\0";

			/*
			 * nw.disable_policy();
			 * nw.setUDPSend(nw.concat(nw.str2bytes(Data.keyWord
			 * ),nw.str2bytes(username)));
			 * 
			 * text_view.setText("2"); byte[] rec_data=nw.setUDPReceive();
			 * 
			 * System.arraycopy(rec_data,0,c,0,4);
			 * System.arraycopy(rec_data,4,d,0,16);
			 * System.arraycopy(rec_data,20,f,0,108); errorCode=nw.byteToInt(c);
			 * password=nw.bytes2str(d); errorString=nw.bytes2str(f);
			 * 
			 * text_view.setText(Data.keyWord.length()+"first:"+errorCode+"..."+
			 * password+"..."+errorString);
			 */
			// Intent intent = new Intent();
			// intent.setClass(register.this, MainActivity.class);
			// startActivity(intent);
			// finish();

		}
	};
}
