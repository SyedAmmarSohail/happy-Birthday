package com.example.viewpagercircle;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class DOB extends Activity {

	TextView dateofBirth, yearT, monthsT, daysT, nextBirth;

	SharedPreferences sharedPreferences;
	/*
	 * private int yearC; private int monthC; private int dayC; private int
	 * year; private int month; private int day; private int yearA; private int
	 * monthA; private int dayA; private int monthN; private int dayN;
	 */

	// ////////////////////////////////////////

	private int startYear;
	private int startMonth;
	private int startDay;
	private int endYear;
	private int endMonth;
	private int endDay;
	private int resYear;
	private int resMonth;
	private int resDay;
	private int monthN;
	private int dayN;
	private Calendar start;
	private Calendar end;

	// ////////////////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dob);

		dateofBirth = (TextView) findViewById(R.id.dateOfBirth);
		yearT = (TextView) findViewById(R.id.year);
		monthsT = (TextView) findViewById(R.id.months);
		daysT = (TextView) findViewById(R.id.days);
		nextBirth = (TextView) findViewById(R.id.nextBirth);

		currentDate();

		sharedPreferences = getApplication().getSharedPreferences("Options",
				MODE_PRIVATE);
		startYear = sharedPreferences.getInt("year", 0);
		startMonth = sharedPreferences.getInt("month", 0);
		startDay = sharedPreferences.getInt("day", 0);

		dateofBirth.setText(new StringBuilder().append(startDay).append("-")
				.append(theMonth(startMonth)).append("-").append(startYear)
				.append(" "));

		calcualteYear();
		calcualteMonth();
		calcualteDay();
		nextDOB();
		CalculateAge();

		getActionBar().setDisplayHomeAsUpEnabled(true);
		/* getActionBar().setHomeAsUpIndicator(R.drawable.back_arrow); */
	}

	private void nextDOB() {
		// TODO Auto-generated method stub
		monthN = endMonth - startMonth;
		Log.d("month", monthN + "");
		if (monthN < 0) {
			dayN = endDay - startDay;
			if (endDay <= startDay) {
				dayN = Math.abs(dayN);
				monthN = Math.abs(monthN);
				Log.d("month", monthN + "");
			} else {
				monthN = Math.abs(monthN + 1);
				dayN = 30 - dayN;

			}

		} else if (monthN > 0) {
			monthN = 12 - monthN;
			dayN = endDay - startDay;
			if (endDay > startDay) {
				monthN = monthN - 1;
				dayN = 30 - dayN;
			} else if (endDay == startDay) {
				dayN = 29;

			} else {
				dayN = Math.abs(dayN);
			}
		} else if (monthN == 0) {
			dayN = endDay - startDay;
			if (dayN > 0) {
				monthN = 11;

			}
			if (dayN < 0) {
				monthN = 0;
				dayN = Math.abs(dayN);
			}
		}
	}

	private void CalculateAge() {
		/*
		 * // TODO Auto-generated method stub yearA = yearC - year; if (month >
		 * monthC) { yearA--; } if (month == monthC) { if (day > dayC) {
		 * yearA--; } }
		 * 
		 * monthA = monthC - month; dayA = dayC - day; if (monthA < 0) { monthN
		 * = monthA * -1; monthA = 12 + monthA;
		 * 
		 * }else { monthN = 12 -monthA; } if (dayA < 0) { monthA--;
		 * 
		 * dayA = dayC; }
		 */

		// /////////////////////////////////////////

		// ////////////////////////////////////////////////

		yearT.setText(resYear + "");
		monthsT.setText(resMonth + "");
		daysT.setText(resDay + "");
		nextBirth.setText(monthN + "  " + dayN);
	}

	private void currentDate() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		endYear = c.get(Calendar.YEAR);
		endMonth = c.get(Calendar.MONTH);
		endDay = c.get(Calendar.DAY_OF_MONTH);
	}

	public static String theMonth(int month) {
		String[] monthNames = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "Oct", "November",
				"December" };
		return monthNames[month];
	}

	public void calcualteYear() {
		resYear = endYear - startYear;

	}

	public void calcualteMonth() {
		if (endMonth >= startMonth) {
			resMonth = endMonth - startMonth;
		} else {
			resMonth = endMonth - startMonth;
			resMonth = 12 + resMonth;
			resYear--;
		}

	}

	public void calcualteDay() {

		if (endDay >= startDay) {
			resDay = endDay - startDay;
		} else {
			resDay = endDay - startDay;
			resDay = 30 + resDay;
			if (resMonth == 0) {
				resMonth = 11;
				resYear--;
			} else {
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
