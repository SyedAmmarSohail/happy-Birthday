package com.example.viewpagercircle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service{

	List<SelectUser_AddOmniTracker> list;
	int i = 0;
	int minute = 60 * 1000;
	private static Timer timer = new Timer();

	@Override
	public void onCreate() {
		super.onCreate();
		//startService();
		/*mTimer = new Timer();
		mTimer.schedule(timerTask, minute, 3000);*/

	}
	/*private void startService()
	{
		timer.scheduleAtFixedRate(new mainTask(), 0, 60000);
	}
	private class mainTask extends TimerTask
	{
		public void run()
		{
			toastHandler.sendEmptyMessage(0);
		}
	}*/
	
	/*private Timer mTimer;

	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			stopSelf();
			String a = "ammar";
			Log.e("Log", "Running "  + a);
			
		}
	};*/
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	   public int onStartCommand(Intent intent, int flags, int startId) {
	      // Let it continue running until it is stopped.
	      /*Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
	      Calendar c = Calendar.getInstance();
	      System.out.println("Current time => " + c.getTime());

	      SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
	      String formattedDate = df.format(c.getTime());
	      Log.d("current date", formattedDate);
	     
	      if (AddFriends.list != null) {
	    	  Toast.makeText(this, "list is here", Toast.LENGTH_LONG).show();
	    	  Log.d("List Size", AddFriends.list.size()+ "");
	    	  
	    	  for (int i = 0; (i <= (AddFriends.list.size()-1)); i++) {
	    		  Log.d("equal or not",  "equal");
	    		  Log.d("birthday", AddFriends.list.get(i).getDOB());
	    		  if ((formattedDate.trim().toLowerCase()).compareTo(AddFriends.list.get(i).getDOB().trim().toLowerCase()) == 0 ) {
					
	    			  Toast.makeText(this, "birthday", Toast.LENGTH_SHORT).show();*/
	    			  
	    			 
	    			  Intent intent1 = new Intent("com.android.techtrainner");
	    				intent1.putExtra("date", list.get(i).getDOB().trim().toLowerCase());
	    				sendBroadcast(intent1);
	    			/*  Intent intentN = new Intent(this, NotificationReceiver.class);
	    			// use System.currentTimeMillis() to have a unique ID for the pending intent
	    			PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intentN, 0);*/

	    			// build notification
	    			// the addAction re-use the same intent to keep the example short
	    			/*Notification n  = new Notification.Builder(this)
	    			        .setContentTitle("New mail from " + "test@gmail.com")
	    			        .setContentText("Subject")
	    			        .setSmallIcon(R.drawable.ic_launcher)
	    			        .setContentIntent(pIntent)
	    			        .setAutoCancel(true)
	    			        .addAction(R.drawable.ic_launcher, "Call", pIntent)
	    			        .addAction(R.drawable.ic_launcher, "More", pIntent)
	    			        .addAction(R.drawable.ic_launcher, "And more", pIntent).build();
	    			    
	    			  
	    			NotificationManager notificationManager = 
	    			  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

	    			notificationManager.notify(0, n); */
	    			
	    			//////////////////////////////////////////////
	    			
	    			/* NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    	            Notification notify=new Notification(R.drawable.noti,tittle,System.currentTimeMillis());
	    	            PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
	    	            
	    	            notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
	    	            notif.notify(0, notify);*/
	    			
	    			//////////////////////////////////////////
	    			
	    			/*PowerManager pm = (PowerManager) this
	    	                .getSystemService(Context.POWER_SERVICE);
	    	        PowerManager.WakeLock wakeLock = pm.newWakeLock(
	    	                PowerManager.PARTIAL_WAKE_LOCK, "");
	    	        wakeLock.acquire();
	    	 
	    	        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
	    	                this).setSmallIcon(R.drawable.ic_launcher)
	    	                .setContentTitle("your Friends birthday")
	    	                .setContentText("Ammar")
	    	                .setDefaults(Notification.DEFAULT_ALL).setAutoCancel(false);
	    	 
	    	        NotificationManager notificationManager = (NotificationManager) this
	    	                .getSystemService(Context.NOTIFICATION_SERVICE);
	    	        notificationManager.notify(0, notificationBuilder.getNotification());
	    	 
	    	        wakeLock.release();*/
	    			
	    			//////////////////////////////////////////////////
	    			
	    			
	    			
	    			
				/*}else {
					
				}
			}
		}else {
			 Toast.makeText(this, "list is  NOT here", Toast.LENGTH_LONG).show();
		}*/
	      
	    /* Log.d("List index", list.get(i).getDOB());*/
	      
	      /*if (condition) {
			
		}*/
	      return Service.START_STICKY;
	      /*return super.onStartCommand(intent, flags, startId);*/
	   }
	   
	public void onDestroy() {
		try {
			/*mTimer.cancel();
			timerTask.cancel();*/
			Toast.makeText(this, "destroy Test", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent intent = new Intent("com.android.techtrainner");
		intent.putExtra("date", list.get(i).getDOB().trim().toLowerCase());
		sendBroadcast(intent);
	}
	
}
