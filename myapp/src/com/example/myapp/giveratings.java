package com.example.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class giveratings extends Activity{
	private TextView tv;

	private Gallery myGallery;
	private RatingBar rb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratepage);
		findViews();
		setListener();
		SpinnerAdapter spinnerAdapter = new SimpleAdapter(this,getData(), android.R.layout.simple_gallery_item, 
	            new String[] {"mydata"},new int[] { android.R.id.text1 });                
	    myGallery.setAdapter(spinnerAdapter); 
	}
	private void findViews() {
	    myGallery = (Gallery) findViewById(R.id.mygallery); 
	    tv=(TextView) findViewById(R.id.text1);
		rb=(RatingBar)findViewById(R.id.ratingBar1);
	}

	private void setListener() {
		tv.setMovementMethod(new ScrollingMovementMethod());
        rb.setNumStars(5);
        rb.setRating(3);   
        rb.setStepSize((float)1.0);
        rb.setOnRatingBarChangeListener(rbLis);
	}
    public String f2Int2Str(float f){
  	  return Integer.toString((int)f);
    }
	  private OnRatingBarChangeListener rbLis=new OnRatingBarChangeListener(){
		  
			public void onRatingChanged(RatingBar ratingBar, float rating,boolean fromUser) {
				String rate;
				rate=f2Int2Str(rating);

				
				
			}
	 
	    };
	public List<Map<String,String>> getData(){ 
	       List<Map<String,String>> myList = new ArrayList<Map<String,String>>(); 
	       Map myMap = new HashMap<String,String>(); 
	       myMap.put("mydata","aa\na\n\nasdf\n\n\n\n\njopsdf"); 
	       myList.add(myMap); 
	       
	       myMap = new HashMap<String,String>(); 
	       myMap.put("mydata","bbb"); 
	       myList.add(myMap); 
	       
	       myMap = new HashMap<String,String>(); 
	       myMap.put("mydata","ccc"); 
	       myList.add(myMap); 
	       
	       myMap = new HashMap<String,String>(); 
	       myMap.put("mydata","ddd"); 
	       myList.add(myMap); 
	       
	       myMap = new HashMap<String,String>(); 
	       myMap.put("mydata","eee"); 
	       myList.add(myMap); 
	       
	       myList.add(myMap); 
	       return myList; 
	   }
}
	