package com.example.viewpagercircle;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {

    List<SelectUser_AddOmniTracker> list;
    String formattedDate;
    String userBirthdate;

    public MyIntentService() {
        super("MyIntentService");
    }
    int i=0;
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
        }
//Toast.makeText(getApplicationContext(), "Service", Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String formattedDate = df.format(c.getTime());
        Log.d("current date", formattedDate);

        list = loadSharedPreferencesLogList(this);

        Log.e("serviceDateSystem", formattedDate.trim().toLowerCase() );
        Log.e("serviceDateApp",  list.get(i).getDOB().trim().toLowerCase());

        formattedDate = formattedDate.trim().toLowerCase();
        formattedDate = formattedDate.substring(0, 6);

        for (int i = 0; (i <= (list.size()-1)); i++) {
            Log.d("equal or not",  "equal");
            Log.d("birthday", list.get(i).getDOB());
            userBirthdate = list.get(i).getDOB().trim().toLowerCase();
            userBirthdate = userBirthdate.substring(0, 6);
   //         if ((formattedDate.trim().toLowerCase()).compareTo(list.get(i).getDOB().trim().toLowerCase()) == 0 ) {
            if (formattedDate.compareTo(userBirthdate) == 0 ) {
				/*Toast.makeText(this, "birthday", Toast.LENGTH_SHORT).show();*/
                Log.e("serviceDateSystem", formattedDate.trim().toLowerCase() );
                Log.e("serviceDateApp",  list.get(i).getDOB().trim().toLowerCase());
                Toast.makeText(getApplicationContext(), "birthday", Toast.LENGTH_SHORT).show();
                Notification notification = new NotificationCompat.Builder(this)
                        .setTicker("Birthday")
                        .setSmallIcon(R.drawable.birthdayicon)
                        .setContentTitle("Say Happy Birthday")
                        .setContentText(list.get(i).getUser() + " have birthday today")
                        .setContentIntent(PendingIntent.getActivity(this, 0,
                                new Intent(this, NotificationReceiver.class), 0))
                        .setAutoCancel(true)
                        .build();
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(i, notification);
            }
        }
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
}