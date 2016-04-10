package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.json.JSONArray;
import org.json.JSONObject;

//import com.google.android.gcm.GCMRegistrar;

import utils.SendEmail;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends BaseActivity 
{
	Dialog dialog;

	static final String SENDER_ID = "1039266385602";
	
	// api key AIzaSyANcg1QJ9g8OUgy-1zPXQrlWogtHf1NoS4
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        try
//        {
//	        if ( android.os.Build.VERSION.SDK_INT >= 8 )
//	        {
//		        GCMRegistrar.checkDevice(this);	
//		        GCMRegistrar.checkManifest(this);
//	        
//		        final String regId = GCMRegistrar.getRegistrationId(this);
//		        if (regId.equals("")) 
//		        {
//		        	GCMRegistrar.register(this, SENDER_ID); 
//		        }        
//	        }
//        }
//        catch ( Exception e )
//        {
//           //sendEmail ("Problemio Home Reg Exception" , e.getMessage() + "");	
//        }           
        
        // Get Shared Preferences.
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( MainActivity.this);
	    
        
//        prefs.edit().putString("user_id", null).commit();	
//        prefs.edit().putString("first_time_cookie", null).commit();	
//        prefs.edit().putString("first_name", null).commit();	
//        prefs.edit().putString("email", null).commit();	        
        
        // For checking if the person is logged in.        
        //first_time_check();
         


        
        
        
        
	    String user_id = prefs.getString( "user_id" , null );		
	   	//sendEmail("FUNDRAISINGApp loading", "user id: " + user_id);
	    
        //Button questions_i_asked = (Button)findViewById(R.id.questions_i_asked);        
        //Button ask_direct_question = (Button)findViewById(R.id.ask_direct_question);  
        Button learn = (Button)findViewById(R.id.learn); 
        Button settings = (Button)findViewById(R.id.settings); 

        
        // ARTICLES
        Button funding_grants = (Button)findViewById(R.id.funding_grants); 
        Button funding_loans = (Button)findViewById(R.id.funding_loans); 
        Button funding_investment = (Button)findViewById(R.id.funding_investment); 
        Button funding_donation = (Button)findViewById(R.id.funding_donations); 
        Button creative_fundriaisng = (Button)findViewById(R.id.creative_fundriaisng); 
        Button plan_fundraising = (Button)findViewById(R.id.plan_fundraising); 
        Button more_apps_button = (Button)findViewById(R.id.more_apps_button); 
        Button get_investment = (Button)findViewById(R.id.get_investment); 
        Button premium_button = (Button)findViewById(R.id.premium_button);
        Button video_button = (Button)findViewById(R.id.video_button);
        Button support_app = (Button)findViewById(R.id.support_app);
        Button job = (Button)findViewById(R.id.job);
        //Button financial_resources = (Button)findViewById(R.id.financial_resources);
        Button money_online = (Button)findViewById(R.id.money_online);
        //Button books = (Button)findViewById(R.id.books);
        Button community = (Button)findViewById(R.id.community);
        

        community.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	
                Intent myIntent = new Intent(MainActivity.this, MotivationActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });                         
        
//        books.setOnClickListener(new Button.OnClickListener()
//        {
//            public void onClick(View v)
//            {
//                Intent myIntent = new Intent(MainActivity.this, BooksActivity.class);
//                MainActivity.this.startActivity(myIntent);
//            }
//        });
        
        
        money_online.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, PremiumIdeasActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });        
        
//        financial_resources.setOnClickListener(new Button.OnClickListener()
//        {
//            @Override
//			public void onClick(View v)
//            {
//              Intent myIntent = new Intent(MainActivity.this, ResourcesActivity.class);
//              MainActivity.this.startActivity(myIntent);
//            }
//        });
        
        job.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, JobActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });        

        support_app.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, SupportActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });                           
        
        video_button.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, VideoChannelActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });

        premium_button.setOnClickListener(new Button.OnClickListener()
        {
            @Override
			public void onClick(View v)
            {
              Intent myIntent = new Intent(MainActivity.this, PremiumActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });
        
        get_investment.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, HowToGetInvestmentActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });           

        more_apps_button.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, ExtraHelpActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });   
        
//        Button new_question_comment = (Button)findViewById(R.id.new_question_comment);
//        new_question_comment.setOnClickListener(new Button.OnClickListener() 
//        {  
//            @Override
//			public void onClick(View v) 
//            {            	
//              Intent myIntent = new Intent(MainActivity.this, MyQuestionsActivity.class);
//              MainActivity.this.startActivity(myIntent);
//            }
//        });          
                
        plan_fundraising.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(MainActivity.this, MyPlansActivity.class);
              MainActivity.this.startActivity(myIntent);
            }
        });          
        
        creative_fundriaisng.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, CreativeFundraisingActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    });         
        
        funding_grants.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, ContentGrantsActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    }); 

        funding_loans.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, ContentLoansActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    }); 
        
        funding_investment.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, ContentInvestmentActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    }); 
        
        funding_donation.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, ContentDonationActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    });         
        
	    settings.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
	            MainActivity.this.startActivity(myIntent);
	        }
	    });	

//	    ask_direct_question.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {
//                //sendEmail("Ask Question Chosen", "From home page, user clicked on ask question" );   	
//            	
//                Intent myIntent = new Intent(MainActivity.this, AskQuestionActivity.class);
//                MainActivity.this.startActivity(myIntent);
//            }
//        });         
    
	    learn.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	                
                Intent myIntent = new Intent(MainActivity.this, ContentLearnActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });   	    

        Button marketing = (Button)findViewById(R.id.marketing);
        marketing.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {                
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=com.marketing"));
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {
                     try
                     {
                    	 Uri uri = Uri.parse("market://search?q=pname:com.marketing");
                    	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                    	 startActivity(next_intent);  
                     }
                     catch ( Exception e)
                     {
                         // Now try to redirect them to the web version:
                         Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.marketing");
                         try
                         {
                        	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
                        	 startActivity(webintent);
                         }
                         catch ( Exception except )
                         {

                         }
                     }
            	 }           	            	
            }
        });   		                   
        
        Button ideas = (Button)findViewById(R.id.ideas);
        ideas.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {                
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=business.ideas"));
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {
                     try
                     {
                    	 Uri uri = Uri.parse("market://search?q=pname:business.ideas");
                    	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                    	 startActivity(next_intent);  
                     }
                     catch ( Exception e)
                     {
                         // Now try to redirect them to the web version:
                         Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=business.ideas");
                         try
                         {
                        	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
                        	 startActivity(webintent);
                         }
                         catch ( Exception except )
                         {

                         }
                     }
            	 }           	            	
            }
        });   		           
        
        Button plan = (Button)findViewById(R.id.plan);
        plan.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {                
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=com.problemio"));
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {
                     try
                     {
                    	 Uri uri = Uri.parse("market://search?q=pname:com.problemio");
                    	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                    	 startActivity(next_intent);  
                     }
                     catch ( Exception e)
                     {
                         // Now try to redirect them to the web version:
                         Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.problemio");
                         try
                         {
                        	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
                        	 startActivity(webintent);
                         }
                         catch ( Exception except )
                         {

                         }
                     }
            	 }           	            	
            }
        });   		                               
    }
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }
    
    public void first_time_check()
    {	
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( MainActivity.this);
        
        String user_id = prefs.getString( "user_id", null ); // First arg is name and second is if not found.
        String first_time_cookie = prefs.getString( "first_time_cookie" , null );			    
        String first_name = prefs.getString( "first_name" , null );			    
        String email = prefs.getString( "email" , null );			    
        
        //sendEmail ( "In FirstTimeCheck" , "Name: " + first_name + " email: " + email + " and user_id: " + user_id + " and cookie: " + first_time_cookie );
        
	    // About setting cookie.  Check for first time cookie
	    if ( first_time_cookie == null )
	    {	        
	    	// This user is a first-time visitor, 1) Create a cookie for them
	    	first_time_cookie = "1";
	    	
	    	// 2) Set it in the application session.
	    	prefs.edit().putString("first_time_cookie", first_time_cookie ).commit(); 
	    
	    	// Make a remote call to the database to increment downloads number
	    	// AND send me an email that it was a new user.
	    	sendFeedback ( ); 		    	
	    }        
	    else
	    {
	    	// If not first time, get their id.
	    	// If user_id is empty, make them an account.  
	    	// If id not empty, call the update login date and be done.	    	    	
	    	if ( user_id == null )
	    	{
	    		// User id is null so they must have logged out.	    		
//	            sendEmail("Not new, user NULL (3)", "Name: " + first_name + " email: " + 
//	            email + " and user_id: " + user_id + "....redirecting them to login activity.  " );   	
	    		
	            if ( email == null || email.trim().equals(""))
	            {
	            	//sendEmail ("3, email null" , "Doing nothing");
		    	    //Intent myIntent = new Intent(ProblemioActivity.this, LoginActivity.class);
		            //ProblemioActivity.this.startActivity(myIntent);
	            }
	            else
	            {	            	
	            	//sendEmail ("User id empty, email NOT null" , "Logging person in. Email is this: " + email);

	            	// Send it to get the user id from the email.
	                logUserIn( email );
	            }
	        }
	    	else
	    	{	    		
		    	// Make a remote call to the database to increment downloads number
		    	sendFeedback ( user_id );	    	    		
	    	}
	    }
       
    	return;
    }        
    
 
    public void sendFeedback( String user_id ) 
    {  
        String[] params = new String[] { "https://www.problemio.com/auth/update_last_login_mobile.php",
        		 user_id };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }         
    
    public void sendFeedback( ) 
    {  
        String[] params = new String[] { "https://www.problemio.com/problems/increment_downloads.php" };

        DownloadsPageTask task = new DownloadsPageTask();
        task.execute(params);        
    }       

    public void logUserIn( String email ) 
    {  
        String[] params = new String[] 
        		{ "https://www.problemio.com/auth/log_user_in_from_email_mobile.php" , email };

        LogUserFromEmailTask task = new LogUserFromEmailTask();
        task.execute(params);        
    }          
    
    
    
    public class LogUserFromEmailTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String email = theParams[1];
	      
	        String charset = "UTF-8";	        
	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("email=%s", 
		        	     URLEncoder.encode(email, charset)  
		        		);

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setDoOutput(true);
		        
		        conn.setUseCaches(false);
		        
		        conn.connect();
		        
		        final InputStream is = conn.getInputStream();
		        final byte[] buffer = new byte[8196];
		        int readCount;
		        final StringBuilder builder = new StringBuilder();
		        while ((readCount = is.read(buffer)) > -1) 
		        {
		            builder.append(new String(buffer, 0, readCount));
		        }

		        response = builder.toString();		
			} 
			catch (Exception e) 
			{
					e.printStackTrace();
			}
			
			return response;
		}
    
		@Override
		protected void onPostExecute(String result) 
		{	     
			  SharedPreferences prefs = 
		        		PreferenceManager.getDefaultSharedPreferences( MainActivity.this);

			  String temp_email = prefs.getString("email", null);
      	
						
        	if ( result == null )
        	{
        		//sendEmail ("Error in problemio" , "Null was returned after logging in user from email.");
        	
	    	    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
	    	    MainActivity.this.startActivity(myIntent);        		
        	}
        	else
            if ( result.equals ( "no_email ") )
            {
	        	//sendEmail ( "Eror in problemio - no email" , "Error getting the email after logging in user from email, redirecting to log in." );
	    	    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
	    	    MainActivity.this.startActivity(myIntent);
            }
        	else
	        if ( result.equals( "error_getting_user") )
	        {	        
	        	//sendEmail ( "Eror in problemio" , "Error getting the user after logging in user from email" );
	        		
	    	    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
	    	    MainActivity.this.startActivity(myIntent);
	        }	        
	        else
	        if ( result.equals( "email_error") )
	        {
	        	//sendEmail ( "Error in problemio" , "Error, no email sent....after logging in user from email");
	        
	    	    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
	    	    MainActivity.this.startActivity(myIntent);
	        }
	        else
	        if ( result.equals( "no_user" ) )
	        {
	        	//sendEmail( "Error in problemio" , "no_user for email: " + temp_email );
	        		
	    	    Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
	    	    MainActivity.this.startActivity(myIntent);
	        }
	        else
	        {
	        	// Unwrap the stuff from the JSON string
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);
		        	
				            String user_id = o.getString("user_id");
				        		
				        		// If ok, put this id into the user's SharedPrefs
					
						        prefs.edit()        
						        .putString("user_id", user_id)
						        .commit();
						        
						        //sendEmail ( "Logged them in" , "With this user id: " + user_id );
		        		}		        		
		        	}			        
		        }
		        catch ( Exception e )
		        {
		        	//sendEmail ( "ProblemioError *" , "error logging in existing user from email.  Email was " + temp_email + ", and Message: " + e.getMessage() );
		        }
	        }
		}    
    }




		
		
       
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String user_id = theParams[1];
//	        final String firstName = theParams[2];
//	        final String lastName = theParams[3];
//	        final String email = theParams[4];
	      
	        String charset = "UTF-8";	        
	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s", 
		        	     URLEncoder.encode(user_id, charset)  
		        		);

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setDoOutput(true);
		        
		        conn.setUseCaches(false);
		        
		        conn.connect();
		        
		        final InputStream is = conn.getInputStream();
		        final byte[] buffer = new byte[8196];
		        int readCount;
		        final StringBuilder builder = new StringBuilder();
		        while ((readCount = is.read(buffer)) > -1) 
		        {
		            builder.append(new String(buffer, 0, readCount));
		        }

		        response = builder.toString();		
			} 
			catch (Exception e) 
			{
					//e.printStackTrace();
			}
			
			return response;
		}
    
		@Override
		protected void onPostExecute(String result) 
		{	        
			if ( result == null || result.trim().equals(""))
			{
				
			}
			else
	        if ( result.equals( "error_updating") )
	        {	        

	        }	        
	        else
	        if ( result.equals( "no_member_id") )
	        {

	        }
	        else
	        {
	        	// PARSE JSON
	        	try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	if ( obj != null )
		        	{		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);
		        	
				            String has_question_comment = o.getString("has_question_comment");
				   				            
//				            if ( has_question_comment != null && has_question_comment.equals("1") )
//				            {
//				                Button new_question_comment = (Button)findViewById(R.id.new_question_comment);  
//				                new_question_comment.setVisibility(View.VISIBLE);				            	
//				            }				            
		        		}		        		
		        	}
		        }
		        catch ( Exception e )
		        {
			        //Log.d( "ProblemsActivity: " , "some crap happened " + e.getMessage() );
		        }	        	
	        }
		}    
    }





    public class DownloadsPageTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(MainActivity.this);

		        dialog.setContentView(R.layout.please_wait);
		        dialog.setTitle("Setting Up App Configurations...");
		        dialog.show();
		 }    	    	
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	      
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		     
		        final URL url = new URL( myUrl );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setDoOutput(true);
		        
		        conn.setUseCaches(false);
		        
		        conn.connect();
		        
		        final InputStream is = conn.getInputStream();
		        final byte[] buffer = new byte[8196];
		        int readCount;
		        final StringBuilder builder = new StringBuilder();
		        while ((readCount = is.read(buffer)) > -1) 
		        {
		            builder.append(new String(buffer, 0, readCount));
		        }

		        response = builder.toString();		
			} 
			catch (Exception e) 
			{
					//sendEmail( "Exception creating user" , "Exception:  " + e );

					e.printStackTrace();
					
					//dialog.dismiss();
			}
			
			return response;
		}
    
		@Override
		protected void onPostExecute(String result_id) 
		{		
	        //sendEmail( "ProblemioActivity New Member" , "Returned result: " + result_id );
			
            try 
            {
                dialog.dismiss();
            } 
            catch (Exception e) 
            {
                // nothing
            }
	        
	        // Need to get the person's new user id that was just returned from the db and store it
	        if ( result_id != null )
	        {	        
	        	try
	        	{
	        		Integer i = Integer.valueOf( result_id );
	        		
	        		// If ok, put this id into the user's SharedPrefs
			        SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( MainActivity.this);

			        prefs.edit()        
			        .putString("user_id", result_id)
			        .commit();	        			        		
	        	}
	        	catch ( Exception e )
	        	{
	        		//sendEmail ( "Error creating user id " ,  e.toString() ) ;        		
	        	}
	        }		        
	        else
	        {		        
		        // Email me this problem.
        		//sendEmail ( "Error creating new user" ,  "Here is the result: " + result_id) ;
	        }
		}    
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
