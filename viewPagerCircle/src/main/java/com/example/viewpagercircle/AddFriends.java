package com.example.viewpagercircle;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

public class AddFriends extends Activity implements OnClickListener{

	EditText name;
	TextView friendDOB, yearT, monthT, dayT;
	Button save;
	List<SelectUser_AddOmniTracker> list;
	
	private int startYear;
	private int startMonth;
	private int startDay;
	private int endYear;
	private int endMonth;
	private int endDay;
	private int resYear;
	private int resMonth;
	private int resDay;
	private Calendar start;
	private Calendar end;

/*	AlarmManager alarmManager ;
	PendingIntent alarmIntent ;*/

	int startTime = 10 * 1000;
	int nextTime = 6 * 60 * 60 * 1000;
	

	DatePickerDialog datePickerDialog;
	SimpleDateFormat dateFormate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_friends);
		
		dateFormate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
		
		initialize();
		
		currentDate();

		final AlarmManager alarmManager = (AlarmManager) AddFriends.this.getSystemService(ALARM_SERVICE);
		final PendingIntent alarmIntent = PendingIntent.getService(AddFriends.this, 0,
				new Intent(AddFriends.this, MyIntentService.class), 0);
		
		friendDOB.setOnClickListener(this);
		//save.setOnClickListener(this);

		save.setOnClickListener(new View.OnClickListener(){

									@Override
									public void onClick(View v) {
										if (name.getText().toString().compareTo("") != 0
												&& friendDOB.getText().toString().compareTo("") != 0){
											//	if (list == null) {
											list = loadSharedPreferencesLogList(AddFriends.this);
											if (list == null) {
												list = new ArrayList<SelectUser_AddOmniTracker>();
												Log.d("listttttt", list + "");
												list.add(new SelectUser_AddOmniTracker(name.getText().toString(), friendDOB.getText().toString()));
												saveSharedPreferencesLogList(AddFriends.this, list);
											}else {
												list.add(new SelectUser_AddOmniTracker(name.getText().toString(), friendDOB.getText().toString()));
												saveSharedPreferencesLogList(AddFriends.this, list);
											}
											Log.d("List Size", list.size()+"");
											// startService(new Intent(getBaseContext(), MyService.class));
											alarmManager.setInexactRepeating(AlarmManager.RTC,
													System.currentTimeMillis() +  10 * 1000,
													10 * 1000,
													alarmIntent);
											startActivity(new Intent(AddFriends.this, FriendsBirthday.class));
											overridePendingTransition(R.anim.enter, R.anim.exit);
										}
										else {
											Toast.makeText(AddFriends.this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
										}
									}
								}
		);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		name = (EditText)findViewById(R.id.friendName);
		friendDOB = (TextView) findViewById(R.id.friendDOB);
		yearT = (TextView)findViewById(R.id.year);
		monthT = (TextView)findViewById(R.id.months);
		dayT = (TextView)findViewById(R.id.days);
		save = (Button) findViewById(R.id.save);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.friendDOB:
			Calendar newCalendar = Calendar.getInstance();
		       datePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
		 
		            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		                Calendar newDate = Calendar.getInstance();
		                newDate.set(year, monthOfYear, dayOfMonth);
		                Log.d("date", year + " " + monthOfYear + " "  + dayOfMonth );
		                
		                friendDOB.setText(new StringBuilder().append(dayOfMonth).append("-")
		        				.append(theMonth(monthOfYear)).append("-").append(year).append(" "));
		                
		                startYear = year;
		                startMonth = monthOfYear;
		                startDay = dayOfMonth;
		                
		                calcualteYear();
		        		calcualteMonth();
		        		calcualteDay();
		                calculateAge();
		                
		                /*sharedPreferences = getApplicationContext().getSharedPreferences(
		    					"Options", MODE_PRIVATE);
		    			SharedPreferences.Editor editor = sharedPreferences.edit();
		    			editor.putInt("year", year);
		    			editor.putInt("month", monthOfYear);
		    			editor.putInt("day", dayOfMonth);
		    			editor.commit();
		                
		                startActivity(new Intent(DOB_Friends.this, DOB.class));*/
		            }
		 
		        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
		       
		       datePickerDialog.show();
			break;

		case R.id.save:
			/*if (name.getText().toString().compareTo("") != 0
			&& friendDOB.getText().toString().compareTo("") != 0){
			//	if (list == null) {
				list = loadSharedPreferencesLogList(this);
				if (list == null) {
					list = new ArrayList<SelectUser_AddOmniTracker>();
					Log.d("listttttt", list + "");
					list.add(new SelectUser_AddOmniTracker(name.getText().toString(), friendDOB.getText().toString()));
					saveSharedPreferencesLogList(this, list);
				}else {
					list.add(new SelectUser_AddOmniTracker(name.getText().toString(), friendDOB.getText().toString()));
					saveSharedPreferencesLogList(this, list);
				}
				Log.d("List Size", list.size()+"");
				// startService(new Intent(getBaseContext(), MyService.class));
				alarmManager.setInexactRepeating(AlarmManager.RTC,
						System.currentTimeMillis() +  1000,
						6 * 60 * 60 * 1000,
						alarmIntent);
			startActivity(new Intent(this, FriendsBirthday.class));
			overridePendingTransition(R.anim.enter, R.anim.exit);
			}
			else {
				Toast.makeText(this, "Please fill the required fields", Toast.LENGTH_SHORT).show();
			}*/
			break;
		}
		
	}



	/////////////////////change////
	public static void saveSharedPreferencesLogList(Context context, List<SelectUser_AddOmniTracker> callLog) {
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor prefsEditor = mPrefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(callLog);
		prefsEditor.putString("myJson", json);
		prefsEditor.commit();
	}

	public static List<SelectUser_AddOmniTracker> loadSharedPreferencesLogList(Context context) {
		List<SelectUser_AddOmniTracker> list = new ArrayList<SelectUser_AddOmniTracker>();
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		Gson gson = new Gson();
		String json = mPrefs.getString("myJson", "");
		if (json.isEmpty()) {
			list = new ArrayList<SelectUser_AddOmniTracker>();
		} else {
			Type type = new TypeToken<List<SelectUser_AddOmniTracker>>() {
			}.getType();
			list = gson.fromJson(json, type);
		}
		return list;
	}

	//////////////change////////////

	protected void calculateAge() {
		// TODO Auto-generated method stub
			yearT.setText(resYear +"");
			monthT.setText(resMonth + "");
			dayT.setText(resDay +"");
			/*nextBirth.setText(monthN + "  "  + dayA);*/
		
	}
	
	private void currentDate() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		endYear = c.get(Calendar.YEAR);
		endMonth = c.get(Calendar.MONTH);
		endDay = c.get(Calendar.DAY_OF_MONTH);
	}

	/*public static String theMonth(int month) {
		String[] monthNames = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "Oct", "November",
				"December" };
		return monthNames[month];
	}*/

	public static String theMonth(int month) {
		String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May",
				"Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
				"Dec" };
		return monthNames[month];
	}

	public void calcualteYear()
	{
	resYear=endYear-startYear;

	}

	public void calcualteMonth()
	{
	if(endMonth>=startMonth)
	{
	resMonth= endMonth-startMonth;
	}
	else
	{
	resMonth=endMonth-startMonth;
	resMonth=12+resMonth;
	resYear--;
	}

	}
	public void calcualteDay()
	{

	if(endDay>=startDay)
	{
	resDay= endDay-startDay;
	}
	else
	{
	resDay=endDay-startDay;
	resDay=30+resDay;
	if(resMonth==0)
	{
	resMonth=11;
	resYear--;
	}
	else
	{
	resMonth--;
	}

	}
	}
	
	private void animatedBack() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, DOB_Friends.class));
		overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		animatedBack();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			
			animatedBack();
			finish();
		}
		return true;
	}
}
