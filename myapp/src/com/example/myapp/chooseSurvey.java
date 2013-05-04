package com.example.myapp;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class chooseSurvey extends ListActivity{
	 //private ListView listview;
	 //private ArrayAdapter<String> listAdapter ;
	 public void onCreate(Bundle savedInstanceState) {  
		    super.onCreate(savedInstanceState);  
			String[] choose=new String[Data.surveyList.size()];
		    for (int i=0;i<Data.surveyList.size();i++){
		    	choose[i]=Data.surveyList.get(i).getName();
		    }
		    setListAdapter(new ArrayAdapter<String>(this,
		              android.R.layout.simple_list_item_1, choose));
		    
		    //listview = getListView();

		      //lv.setTextFilterEnabled(true);
		    
	 }
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	Commands cmd=new Commands();
			Gson gson = new Gson();
			Type type = new TypeToken<List<QuestionData>>() {}.getType();
	        Intent intent = new Intent(this, TestGalleryActivity.class);        
			Data.dataList = gson.fromJson(cmd.getSurveyQue(Integer.toString(Data.surveyList.get(position).getID())), type);
			Data.SurveyLen = Data.dataList.size();
	        startActivity(intent);
	        finish();
	    }
	 
	 
}
