package com.example.viewpagercircle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class ReceiverCall extends BroadcastReceiver {

	List<SelectUser_AddOmniTracker> list;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Service Stops", "Ohhhhhhh");
		
		String Bdate = intent.getStringExtra("date");
		
		Calendar c = Calendar.getInstance();
		 SimpleDateFormat df = new SimpleDateFormat("dd-MMMMM-yyyy", Locale.US);
	      String formattedDate = df.format(c.getTime());
	      Log.d("current date", formattedDate);
	     /* if (list.size() != 0) {
	    	  Log.d("List Size", list.size()+ "");
		}*/
	      if (list != null) {
	    	  Toast.makeText(context, "list is here", Toast.LENGTH_LONG).show();
	    	  Log.d("List Size", list.size()+ "");
	    	  
	    	  for (int i = 0; (i <= (list.size()-1)); i++) {
	    		  Log.d("equal or not",  "equal");
	    		  Log.d("Listbirthday", list.get(i).getDOB().trim().toLowerCase());
				  Log.d("Datebirthday", formattedDate.trim().toLowerCase());
	    		  if ((formattedDate.trim().toLowerCase()).compareTo(list.get(i).getDOB().trim().toLowerCase()) == 0 ) {
					/*Toast.makeText(this, "birthday", Toast.LENGTH_SHORT).show();*/
	    			  Toast.makeText(context, "birthday", Toast.LENGTH_SHORT).show();
	    			  PowerManager pm = (PowerManager) context
	    		                .getSystemService(Context.POWER_SERVICE);
	    		        PowerManager.WakeLock wakeLock = pm.newWakeLock(
	    		                PowerManager.PARTIAL_WAKE_LOCK, "");
	    		        wakeLock.acquire();

	    		        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
	    		                context).setSmallIcon(R.drawable.birthdayicon)
	    		                .setContentTitle("your Friends birthday")
	    		                .setContentText(list.get(i).getUser())
								.setDefaults(Notification.DEFAULT_ALL).setAutoCancel(false);
	    		 
	    		        NotificationManager notificationManager = (NotificationManager) context
	    		                .getSystemService(Context.NOTIFICATION_SERVICE);
	    		        notificationManager.notify(0, notificationBuilder.getNotification());
	    		 
	    		        wakeLock.release();
	    			  
	    		        
	    		        
	    		  }else {
						
					}
				}
			}else {
				 Toast.makeText(context, "list is  NOT here", Toast.LENGTH_LONG).show();
			}
		
		
		/*context.startService(new Intent(context, MyService.class));;*/
	}

}
