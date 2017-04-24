package com.example.viewpagercircle;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

public class FriendsBirthday extends Activity{
	
	ListView friendList;
	List<SelectUser_AddOmniTracker> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends_birthday);
		
		friendList = (ListView) findViewById(R.id.friendList);
		if (list != null) {
			////////////change//////////////
			list = loadSharedPreferencesLogList(this);
			//////////change//////////////

			friendList.setAdapter(new Custom_AddOmniTracker(this, list));
		}else {
			list = loadSharedPreferencesLogList(this);
			friendList.setAdapter(new Custom_AddOmniTracker(this, list));
		//	Toast.makeText(this, "FriendsList Empty", Toast.LENGTH_SHORT).show();
		}
		/*list = new ArrayList<SelectUser_AddOmniTracker>();*/
		/*list.add(new SelectUser_AddOmniTracker("abc"));*/
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
	}

	////////////////change/////////////
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
	/////////////////change///////////

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
