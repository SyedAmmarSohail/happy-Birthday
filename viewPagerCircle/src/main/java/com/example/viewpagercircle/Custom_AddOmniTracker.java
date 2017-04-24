package com.example.viewpagercircle;

import java.util.Calendar;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class Custom_AddOmniTracker extends
		ArrayAdapter<SelectUser_AddOmniTracker> {

	private final Context context;
	List<SelectUser_AddOmniTracker> list;
	DatePickerDialog datePickerDialog;

	public Custom_AddOmniTracker(Context context,
			List<SelectUser_AddOmniTracker> list) {
		super(context, R.layout.custom_friend_list, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.custom_friend_list, parent,
					false);
			holder.name = (TextView) convertView.findViewById(R.id.friendName);
			holder.friendDOB = (TextView) convertView
					.findViewById(R.id.friendDOB);
			holder.edit = (ImageButton) convertView.findViewById(R.id.edit);
			holder.edit.setTag(position);
			holder.delete = (ImageButton) convertView.findViewById(R.id.delete);
			holder.delete.setTag(position);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		SelectUser_AddOmniTracker selectUser = (SelectUser_AddOmniTracker) this.list
				.get(position);
		holder.name.setText(selectUser.getUser());
		holder.friendDOB.setText(selectUser.getDOB());

		holder.edit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				LayoutInflater factory = LayoutInflater.from(getContext());
				final View dialogView = factory.inflate(R.layout.custom_dialog,
						null);
				final EditText dialogName = (EditText) dialogView
						.findViewById(R.id.dialogName);
				final TextView dialogDOB = (TextView) dialogView
						.findViewById(R.id.dialogDOB);
				final AlertDialog.Builder dialog = new AlertDialog.Builder(
						getContext(), R.style.CustomDialog);

				dialog.setView(dialogView);
				dialogName.setText(list.get((int)v.getTag()).getUser());
				dialogDOB.setText(list.get((int)v.getTag()).getDOB());
				dialog.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								if (dialogName.getText().toString()
										.compareTo("") != 0
										|| dialogDOB.getText().toString()
												.compareTo("") != 0) {
									Toast.makeText(getContext(), "ok",
											Toast.LENGTH_SHORT).show();

									int pos = (int) v.getTag();
									list.set(pos,
											new SelectUser_AddOmniTracker(
													dialogName.getText()
															.toString(),
													dialogDOB.getText()
															.toString()));
									saveSharedPreferencesLogList(getContext(), list);

									Custom_AddOmniTracker.this
											.notifyDataSetChanged();

								} else {
									Toast.makeText(getContext(),
											"Please Fill the required fields",
											Toast.LENGTH_SHORT).show();
								}

							}
						});

				dialog.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
							}
						});
				
				dialogView.findViewById(R.id.dialogName).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// your business logic

							}
						});
				dialogView.findViewById(R.id.dialogDOB).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								Calendar newCalendar = Calendar.getInstance();
								datePickerDialog = new DatePickerDialog(
										getContext(),
										new OnDateSetListener() {

											public void onDateSet(
													DatePicker view, int year,
													int monthOfYear,
													int dayOfMonth) {
												Calendar newDate = Calendar
														.getInstance();
												newDate.set(year, monthOfYear,
														dayOfMonth);
												Log.d("date", year + " "
														+ monthOfYear + " "
														+ dayOfMonth);

												dialogDOB
														.setText(new StringBuilder()
																.append(dayOfMonth)
																.append("-")
																.append(theMonth(monthOfYear))
																.append("-")
																.append(year)
																.append(" "));
											}

											private Object theMonth(int month) {
												// TODO Auto-generated method
												// stub
												String[] monthNames = {
														"January", "February",
														"March", "April",
														"May", "June", "July",
														"August", "September",
														"Oct", "November",
														"December" };
												return monthNames[month];
											}

										}, newCalendar.get(Calendar.YEAR),
										newCalendar.get(Calendar.MONTH),
										newCalendar.get(Calendar.DAY_OF_MONTH));

								datePickerDialog.show();

							}
						});

				dialog.show();
				/*
				 * dialog.getWindow().setBackgroundDrawable(new
				 * ColorDrawable(android.graphics.Color.TRANSPARENT));
				 */

			}
		});
		holder.delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				int pos = (int) v.getTag();
				list.remove(pos);
				saveSharedPreferencesLogList(getContext(), list);
				Custom_AddOmniTracker.this.notifyDataSetChanged();
			}
		});

		return convertView;
	}

	///////////////change/////
	public static void saveSharedPreferencesLogList(Context context, List<SelectUser_AddOmniTracker> callLog) {
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor prefsEditor = mPrefs.edit();
		Gson gson = new Gson();
		String json = gson.toJson(callLog);
		prefsEditor.putString("myJson", json);
		prefsEditor.commit();
	}
	/////////////change//////

	static class ViewHolder {
		TextView name, friendDOB;
		ImageButton edit, delete;
		ImageView trackerPic;
	}

}
