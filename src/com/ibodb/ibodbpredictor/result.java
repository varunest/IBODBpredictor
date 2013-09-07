package com.ibodb.ibodbpredictor;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class result extends Activity {

	TextView view1, view2, view3,view4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_layout);
		Bundle bundle = getIntent().getExtras();
		String target = bundle.getString("target");
		String type = bundle.getString("type");
		String response = bundle.getString("response");
		String movie = bundle.getString("movie_name");
		double op = bundle.getInt("op");
		double scr = bundle.getInt("scr");
		view4 = (TextView) findViewById(R.id.movie_name);
		view1 = (TextView) findViewById(R.id.first_line);
		view2 = (TextView) findViewById(R.id.second_line);
		view3 = (TextView) findViewById(R.id.third_line);
		view4.setText(movie);
		Log.d("op",String.valueOf(op));
		Log.d("scr",String.valueOf(scr));


		double multiplication_factor = (target.equals("Both(Single Screen and Multiplex)")) ? 0.76 : 0.85;
		double collection_lower_limit = (op * (scr - 145.00) * multiplication_factor)/10000.00;
		//collection_lower_limit = (int)collection_lower_limit;
		Log.d("collection_lower_limit",String.valueOf(collection_lower_limit));
		double collection_upper_limit = (op * (scr + 125.00) * multiplication_factor)/10000.00;
		//collection_upper_limit = (int)collection_upper_limit;
		
		
		if (type.equals("Major Holiday Release"))
		{
			collection_lower_limit += (0.18*collection_lower_limit);
			collection_upper_limit += (0.22*collection_upper_limit);
		}
		else if(type.equals("Minor Holiday Release"))
		{
			collection_lower_limit += (0.10*collection_lower_limit);
			collection_upper_limit += (0.14*collection_upper_limit);		
		}
		
		//Initialize the 3 Variables as Integers....
		double first_week_collections = 0.0;
		double weekly_factor = 0.0;
		double lifetime_factor = 0.0;
		
		if (response.equals("Positive"))
		{
			if (type.equals("Major Holiday Release")) 
			{
				weekly_factor = 4.5;
				lifetime_factor = 8.7;
			}
				
			else if (type.equals("Minor Holiday Release"))
			{
				weekly_factor = 4.0;
				lifetime_factor = 8.5;
			}
			else //Positive Response and Not a Holiday
			{
				weekly_factor = 5.0;
				lifetime_factor = 7.75;
			}
			
		}
		
		else if (response.equals("Mixed"))
		{
				weekly_factor = 3.75;
				lifetime_factor = 6;	
		}
		
		else if (response.equals("Negative"))
		{
			if ( type.equals("Major Holiday Release") || type.equals("Minor Holiday Release"))
			{
				weekly_factor = 4.5;
				lifetime_factor = 5.2;
			}
			else //Negative Response and Not a Holiday
			{
				weekly_factor = 3.5;
				lifetime_factor = 5.0;
			}
			
		}
		
		//Finalizing Figures
		double lifetime_total = collection_lower_limit * lifetime_factor;
		double week_total =   collection_lower_limit * weekly_factor;
		double lifetime_total_up = lifetime_total + (lifetime_total*0.15);
		double week_total_up = week_total + (week_total*0.15);
		String firstDayLow = String.valueOf(collection_lower_limit).substring(0,5);
		String firstDayUp = String.valueOf(collection_upper_limit).substring(0,5);
		String weekTotalUp = String.valueOf(week_total_up).substring(0,5);
		String weekTotalLow = String.valueOf(week_total).substring(0,5);
		String lifeTotalLow = String.valueOf(lifetime_total).substring(0,5);
		String lifeTotalUp = String.valueOf(lifetime_total_up).substring(0,5);
		
		view1.setText("First Day Collections:     "+ firstDayLow+ " to "+firstDayUp +" crores. ");
		view2.setText("Week Day Collections:     "+ weekTotalLow+ " to "+weekTotalUp +" crores. ");
		view3.setText("Week Day Collections:     "+ lifeTotalLow+ " to "+lifeTotalUp +" crores. ");

	}
	
}
