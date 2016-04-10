package make.money;

import utils.SendEmail;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


//import com.google.android.gcm.GCMBaseIntentService;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class GCMIntentService //extends GCMBaseIntentService
{
	public GCMIntentService() 
	{
		  //  super(MainActivity.SENDER_ID);
	}
	
//	@Override
//	  protected void onRegistered(Context ctxt, String regId) {
//	  }
//
//	  @Override
//	  protected void onUnregistered(Context ctxt, String regId) {
//	  }

	 // @Override
	  protected void onMessage(Context ctxt, Intent message) 
	  {
	    Bundle extras=message.getExtras();

	    for (String key : extras.keySet()) 
	    {
	    	//sendEmail( "Key: " + key , "value: " + extras.getString(key) );
	    	
//	      Log.d(getClass().getSimpleName(),
//	            String.format("onMessage: %s=%s", key,
//	                          extras.getString(key)));
	    }
	    
	    try
	    {
	    	String notification_type = extras.getString("notification_type");
	    	if ( notification_type != null && notification_type.equals("question"))
	    	{
		    	String question_id = extras.getString("question_id");
		    	String recent_question = extras.getString("question");

//		    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
//
//			    prefs.edit()
//		        .putString("recent_question_id", question_id)
//		        .putString("recent_question", recent_question)
//		        .commit();
	    	}
	    	else
	    	if ( notification_type != null && notification_type.equals("plan"))
	    	{
		    	String recent_problem_id = extras.getString("plan_id");
		    	String recent_topic_id = extras.getString("topic_id");
		    	String recent_topic_name = extras.getString("topic_name");
		    	
		    	
				//$t_data['plan_name'] = $plan_name;
//				$t_data['plan_id'] = $plan_id;
//				$t_data['topic_id'] = $topic_id;
//				$t_data['topic_name'] = $section_name;
		    	
//		    	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
//
//			    prefs.edit()
//		        .putString("recent_problem_id", recent_problem_id)
//		        .putString("recent_topic_id", recent_topic_id)
//		        .putString("recent_topic_name", recent_topic_name)
//		        .commit();
	    	}
	    	
	    	generateNotification(ctxt, extras.getString("message"), "New Message" ,  extras);
	    	
	    	//sendEmail( "Creating Notification" , "SDK: " + Build.VERSION.SDK_INT );
	    }
	    catch ( Exception e )
	    {
	    	//sendEmail( "Creating Notification Caught Exception " , "SDK: " + Build.VERSION.SDK_INT + " and Exception: " + e.getMessage() );
	    }
	  }
	  
	    // Subject , body
	    public void sendEmail( String subject , String body )
	    {
	        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

	        SendEmail task = new SendEmail();
	        task.execute(params);            	
	    }    
	  
//	  @Override
//	  protected void onError(Context ctxt, String errorMsg) {
//	  }
//
//	  @Override
//	  protected boolean onRecoverableError(Context ctxt, String errorMsg) {
//
//	    return(true);
//	  }
	  
	  private static void generateNotification(Context context, String message, String title ,
			  Bundle extras ) 
	  {
	    	String notification_type = extras.getString("notification_type");

	    	
		
	        int icon = R.drawable.ic_launcher;
	        long when = System.currentTimeMillis(); // can change this to a future time if desired
	        
	        NotificationManager notificationManager = 
	        		(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	        
	        Intent notificationIntent = new Intent(context, MainActivity.class);
	        if ( notification_type != null && notification_type.equals("question"))
	        {
	        	notificationIntent = new Intent(context, QuestionActivity.class);
	        }
	        else
		    if ( notification_type != null && notification_type.equals("plan"))
		    {
	        	notificationIntent = new Intent(context, TopicActivity.class);		    	
		    }
	        
	        // set intent so it does not start a new activity
	        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);        
	        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);            

	        Notification notification = new NotificationCompat.Builder(context)
	         .setContentTitle(title)
	         .setContentText(message)
	         .setContentIntent(intent)
	         .setSmallIcon(icon)
	         .setLights(Color.YELLOW, 1, 2)
	         .setAutoCancel(true)
	         .setSound(defaultSound)
	         .build();

	        notificationManager.notify(0, notification);
	}

	TrustManager tm = new X509TrustManager()  {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		try {
			chain[0].checkValidity();
		} catch (Exception e) {
			throw new CertificateException("Certificate not valid or trusted.");
		}
	}
}
