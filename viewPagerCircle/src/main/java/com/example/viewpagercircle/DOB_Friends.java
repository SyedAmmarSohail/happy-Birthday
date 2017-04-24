package com.example.viewpagercircle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DOB_Friends extends Activity implements OnClickListener {

	Button yourBirth, yourFriends, viewFriends;
	DatePickerDialog datePickerDialog;
	SimpleDateFormat dateFormate;
	SharedPreferences sharedPreferences;
	int login  = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dob_friends);

		sharedPreferences = this
				.getSharedPreferences("Options",
						MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences
				.edit();
		editor.putInt("year", login);
		editor.commit();

		dateFormate = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

		Initialize();
		yourBirth.setOnClickListener(this);
		yourFriends.setOnClickListener(this);
		viewFriends.setOnClickListener(this);
	}

	private void Initialize() {
		// TODO Auto-generated method stub
		yourBirth = (Button) findViewById(R.id.yourBirth);
		yourFriends = (Button) findViewById(R.id.yourFriends);
		viewFriends = (Button) findViewById(R.id.viewFriend);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.yourBirth:
			Calendar newCalendar = Calendar.getInstance();
			datePickerDialog = new DatePickerDialog(this,
					new OnDateSetListener() {

						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							Calendar newDate = Calendar.getInstance();
							newDate.set(year, monthOfYear, dayOfMonth);
							Log.d("date", year + " " + monthOfYear + " "
									+ dayOfMonth);

							sharedPreferences = getApplicationContext()
									.getSharedPreferences("Options",
											MODE_PRIVATE);
							SharedPreferences.Editor editor = sharedPreferences
									.edit();
							editor.putInt("year", year);
							editor.putInt("month", monthOfYear);
							editor.putInt("day", dayOfMonth);
							editor.commit();

							startActivity(new Intent(DOB_Friends.this,
									DOB.class));
							overridePendingTransition(R.anim.enter, R.anim.exit);
						}

					}, newCalendar.get(Calendar.YEAR),
					newCalendar.get(Calendar.MONTH),
					newCalendar.get(Calendar.DAY_OF_MONTH));

			datePickerDialog.show();
			break;

		case R.id.yourFriends:
			startActivity(new Intent(DOB_Friends.this, AddFriends.class));
			overridePendingTransition(R.anim.enter, R.anim.exit);
			break;
			
		case R.id.viewFriend:
			startActivity(new Intent(DOB_Friends.this, FriendsBirthday.class));
			overridePendingTransition(R.anim.enter, R.anim.exit);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this, R.style.CustomDialog)
				.setTitle("Really Exit?")
				.setMessage("Are you sure you want to exit?")
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								/* finish(); */
								moveTaskToBack(true);
							}
						}).create().show();
	}
}
