package com.ibodb.ibodbpredictor;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	EditText name1, occupancy, screen_count;
	Spinner type, target, response;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickResult(View view) {
		name1 = (EditText)findViewById(R.id.name);
		occupancy = (EditText)findViewById(R.id.occupancy);
		screen_count = (EditText)findViewById(R.id.screen);
		String occupancy_percentage = occupancy.getText().toString();
		String screen = screen_count.getText().toString();
		int op = Integer.parseInt(occupancy_percentage);
		int scr = Integer.parseInt(screen);
		String movie_name = name1.getText().toString();
		
		type = (Spinner)findViewById(R.id.type_release);
		target = (Spinner)findViewById(R.id.target);
		response = (Spinner)findViewById(R.id.response);
		String release_type = String.valueOf(type.getSelectedItem());
		String target_audience = String.valueOf(target.getSelectedItem());
		String people_response = String.valueOf(response.getSelectedItem());
		Intent i = new Intent(MainActivity.this,result.class);
		Bundle extras = new Bundle();
		extras.putString("movie_name", movie_name);
		extras.putInt("op", op);
		extras.putInt("scr", scr);
		extras.putString("target", target_audience);
		extras.putString("response", people_response);
		extras.putString("type", release_type);
		i.putExtras(extras);
		Log.d("!!!","Working upto here");
		startActivity(i);
	}

	public void onClickSelectType(View view) {

	}

	public void onClickResponse(View view) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
