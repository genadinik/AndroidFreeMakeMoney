package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import data.DiscussionMessage;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class AddPlanActivity extends BaseActivity
{
	String business_name = null;
	
//	EditText email;
//	TextView email_ask;
//	EditText name;
//	TextView name_ask;
	
	private RadioGroup radioPrivacyGroup;
	RadioButton open;
	RadioButton closed;
	
    // VAR TO DETERMINE HELP
    boolean helping = true;
    
    TextView premium_text;
	Button premiumAppButton;
    
    
	TextView error_message;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.add_plan);
        
        // Check if person is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AddPlanActivity.this);
        final String user_id = prefs.getString( "user_id" , null ); // First arg is name and second is if not found.
        final String session_name = prefs.getString( "first_name" , null );
        final String session_email = prefs.getString( "email" , null );     
        
    	final Button addBusinessButton = (Button)findViewById(R.id.button_send_feedback);

        

        
        // If the user is not logged in, send them to log in
        if ( user_id == null )
        {
        	Toast.makeText(getApplicationContext(), "Please log in.", Toast.LENGTH_LONG).show();	            	              
            
	        Intent loginIntent = new Intent( AddPlanActivity.this, LoginActivity.class);
	        AddPlanActivity.this.startActivity(loginIntent);        	
        }

        
    	premiumAppButton = (Button)findViewById(R.id.premium_button);
    	premiumAppButton.setVisibility(View.GONE);

    	premium_text = (TextView) findViewById(R.id.premium_text);
    	premium_text.setVisibility(View.GONE);        
        
        
        
        //Button help_instructions = (Button)findViewById(R.id.help_instructions);

        
//        if ( helping == false )
//        {
//        	// SET A FEW FILDS TO GONE        	
//        	//help_instructions.setVisibility(View.GONE);
//        }

//	    email_ask = (TextView) findViewById(R.id.email_ask);
//	    email = (EditText) findViewById(R.id.email);
//	    name_ask = (TextView) findViewById(R.id.name_ask);
//	    name = (EditText) findViewById(R.id.name);
//	    
//	    email.setVisibility(View.GONE);
//	    email_ask.setVisibility(View.GONE);
//	    name.setVisibility(View.GONE);
//	    name_ask.setVisibility(View.GONE);
//
//
//
//	    
//	    // Now pre-fill the entries
//	    if ( session_email != null && session_email.trim().length() > 2)
//	    {
//	    	email.setText(session_email);
//	    	email.setEnabled(false);
//
//	    	email_ask.setText("This is the email associated with your account.  If you wish to use a different email, please update your profile.");
//	    }
//	    
//	    if ( session_name != null )
//	    {
//	    	name.setText(session_name);
//	    }  	    
	    
    	final EditText problemName = (EditText) findViewById(R.id.problem_explanation);  
    	business_name = problemName.getText().toString();  
    	
    	radioPrivacyGroup = (RadioGroup) findViewById(R.id.radioPrivacy);
    	radioPrivacyGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
    	{
            public void onCheckedChanged(RadioGroup group, int checkedId) 
            { 
                RadioButton radioButton = (RadioButton) findViewById(checkedId);
                String temp = radioButton.getText() + "";
                
                if ( temp.equals("I want help planning"))
                {            	
//                   if ( helping == true )
//                   {
//	       			   email.setVisibility(View.VISIBLE);
//	       			   email_ask.setVisibility(View.VISIBLE);
//	       			   name.setVisibility ( View.VISIBLE );
//	       			   name_ask.setVisibility ( View.VISIBLE );
                	   
	                   	premiumAppButton.setVisibility ( View.VISIBLE );
	                   	premium_text.setVisibility ( View.VISIBLE );
	                   	
	                	   addBusinessButton.setVisibility(View.GONE);
//                   }
//                   else
//                   {
//                	   // DISPLAY LINK TO PREMIUM APP
//                	   premium_text.setVisibility(View.GONE);
//                	   premiumAppButton.setVisibility(View.GONE);
//                	   
//                	   addBusinessButton.setVisibility(View.VISIBLE);
//                   }
                }
                else
                {  
                 	premiumAppButton.setVisibility ( View.GONE );
                   	premium_text.setVisibility ( View.GONE );
                   	
                	   addBusinessButton.setVisibility(View.VISIBLE);
                }
            }
    	});    	
    	
    	
    	
	  
    	
    	
//	    Button business_instructions = (Button)findViewById(R.id.help_instructions);
//	    business_instructions.setOnClickListener(new Button.OnClickListener() 
//        {   
//            public void onClick(View v) 
//            {	              
//              Intent myIntent = new Intent(AddPlanActivity.this, HelpInstructionsActivity.class);
//              AddPlanActivity.this.startActivity(myIntent);
//          }
//        });	    	
    	
//    	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    	Button button_help_expectation = (Button)findViewById(R.id.button_help_expectation);
//    	button_help_expectation.setOnClickListener(new Button.OnClickListener()
//    	{  
//    	   public void onClick(View v) 
//    	   {
//    		   builder.setMessage("Within 12 hours (often much less) of completing this form, another entrepreneur will interact with you within the app, and help you plan various parts of your business right on the app.  You will be alerted about this via email. \n\nWhat to expect: Do not expect a full completed business plan document. DO expect help planning, feedback, and to have your overall plan and strategy improved.")
//	              .setCancelable(false)
//	              .setPositiveButton("Ok", new DialogInterface.OnClickListener() 
//	              {
//	                  public void onClick(DialogInterface dialog, int id) 
//	                  {
//	                 
//	                  }
//	              });
//    		   
//			       AlertDialog alert = builder.create();
//			       alert.show();
//    	    }
//    	});
    	
    	
    	
	    premiumAppButton.setOnClickListener(new Button.OnClickListener() 
	      {  
	          public void onClick(View v) 
	          {		              
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=business.premium"));
	        	            	 
	        	 try 
	        	 {
	        	        startActivity(intent);
	        	 } 
	        	 catch (ActivityNotFoundException anfe) 
	        	 {
	                 try
	                 {
	                	 Uri uri = Uri.parse("market://search?q=pname:business.premium");
	                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
	                	 startActivity(next_intent);  
	                 }
	                 catch ( Exception e)
	                 {
	                     // Now try to redirect them to the web version:
	                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=business.premium");
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
    	
    	
    	
    	addBusinessButton.setOnClickListener(new Button.OnClickListener()
    	{  
    	   public void onClick(View v) 
    	   {
		      Toast.makeText(getApplicationContext(), "Adding the plan. Please wait...", Toast.LENGTH_LONG).show();	

    	      String business_name = problemName.getText().toString().trim();
    	      
    	      
    	      // Put the persons SHarepPrefs email in there.
    	      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
    	    		  AddPlanActivity.this);
    	      
    	      prefs.edit()        
		        .putString("temp_business_name", business_name )
		        .commit();	 
    	      
    	      open = (RadioButton)findViewById(R.id.radioOpen);
    	      closed = (RadioButton)findViewById(R.id.radioPrivate);
    	  
    	      if ( user_id == null )
    	      {    	    	
    		      Toast.makeText(getApplicationContext(), "You do not have an active session.  Please Log in or create an account.", Toast.LENGTH_LONG).show();	
    		     
    		      Intent loginIntent = new Intent( AddPlanActivity.this, LoginActivity.class);
    		      AddPlanActivity.this.startActivity(loginIntent);  
    	      }
    	      
    	      if(!open.isChecked() && !closed.isChecked())
    	      {    	    	  
    		      Toast.makeText(getApplicationContext(), "Please choose whether you want help or privacy.", Toast.LENGTH_LONG).show();	
    	      }              
    	      else 	  
    	      if ( business_name == null || business_name.length() < 1 )
    	      {
    		      Toast.makeText(getApplicationContext(), "The plan name field can not be empty. Please try again.", 
    		    		  Toast.LENGTH_LONG).show();	      		      
    	      }
    	      else
//    	      if ( open.isChecked() )
//    	      {
//    	    	  if ( person_name == null || person_name.length() < 1 )
//    	    	  {    	    		  
//    	    		  Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();	    	    		  
//    	    	  }
//    	    	  else
//    	          if ( person_email == null || !matchFound )
//    	          {
//    	    		  Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_LONG).show();
//    	          }
//    	          else
//    	          {    		 	     
////    		 	     String business = prefs.getString( "business" , null );
//    		 	       
//
//    			     sendFeedback( business_name.trim() , user_id , person_name , person_email , "0");    	        	  
//    			  }    		      
//    	      }    	      
//    	      else
    	      {    	    	  
        	      sendFeedback( business_name.trim() , user_id , null , null , "1");
    	      }
    	    }
    	});
    }
    


    public void sendFeedback(String business_name , String user_id , String person_name , 
    		String person_email , String privacy) 
    {          
        // Now that I have these items lets put them into the database
        
        if ( business_name != null && business_name.length() > 0 )
        {	
	        String[] params = new String[] 
	        		{ "https://www.problemio.com/problems/add_fundraising_plan.php?platform=android_fundraising&",
	        		business_name , user_id , person_name , person_email , privacy};
	             
	        DownloadWebPageTask task = new DownloadWebPageTask();
	        task.execute(params); 	        	        
        }
        else
        {
        	// Display error message
        	TextView errorMessage = (TextView) findViewById(R.id.add_problem_validation_error);
        	//errorMessage.setText(getResources().getString(R.string.add_problem_validation_error));          	
        }    	
    }      
    
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		private boolean connectionError = false;
		 
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];	        
	        final String business_name = theParams[1];
	        final String user_id = theParams[2];
	        final String person_name = theParams[3];
	        final String person_email = theParams[4];
	        final String privacy = theParams[5];
	        	     
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("name=%s&user_id=%s&person_name=%s&email=%s&privacy=%s", 
		        	     URLEncoder.encode( business_name , charset), 
		        	     URLEncoder.encode(user_id, charset) ,
		        	     URLEncoder.encode(person_name + "", charset) ,
		        	     URLEncoder.encode(person_email + "", charset) ,
		        	     URLEncoder.encode(privacy, charset)  
		        		);

		        final URL url = new URL( myUrl + query );
		        		        
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
					connectionError = true;
					
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{	   			
			if ( connectionError == true )
			{
		        Toast.makeText(getApplicationContext(), "Error: Internet conection problem. Please try again and make sure your phone is connected to the Internet." , Toast.LENGTH_LONG).show();
			}
			
			if ( result != null )
			{        
	        	try
	        	{
	        		Integer i = Integer.valueOf( result );
	        		
	        		// If ok, put this id into the user's SharedPrefs
			        SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( AddPlanActivity.this);

			        prefs.edit()        
			        .putString("recent_problem_id", result)
			        .commit();	        			       

			        // 2) Make an intent to go to the home screen
			        Intent myIntent = new Intent(AddPlanActivity.this, PlanActivity.class);
			        AddPlanActivity.this.startActivity(myIntent);			        
	        	}
	        	catch ( Exception e )
	        	{

	        	}
			}
			
			
			
			if ( result == null )
			{
		        Toast.makeText(getApplicationContext(), "Error: Internet conection problem. Please try again. If the error persists, please let us know about it." , Toast.LENGTH_LONG).show();
			}
			else
			if ( result.equals("email_in_diff_account"))
			{
	        	error_message = (TextView) findViewById(R.id.error_message);
	        	error_message.setVisibility(View.VISIBLE);
	        	
		        Toast.makeText(getApplicationContext(), 
		        		"Error: This email already belongs to an account." +
		        		" Please sign in to that account, or recover your password.", 
		        		Toast.LENGTH_LONG).show();		        				
			}
			else
	        if ( result.equals( "no_problem_name" ) )
	        {		       
  		        Toast.makeText(getApplicationContext(), "Error submitting the business.  We are aware of this problem and it will be fixed in the next update of the app.  We sincerely apologize.", 
		    		  Toast.LENGTH_LONG).show();                
	        }        
	        else
	        if ( result.equals( "error_adding_problem" ) )
	        {		        
                //		"There was a database error adding the problem" );
                
  		        Toast.makeText(getApplicationContext(), "Error submitting the business.  We are aware of this problem and it will be fixed in the next update of the app.  We sincerely apologize.", 
		    		  Toast.LENGTH_LONG).show();                		        		        
	        }
	        else
	        if ( result.equals("error_duplicate_problem") )
	        {
		        //Log.d( "AddProblemActivity" , "Error - duplicate problem" );		        
            }
	        else
	        if ( result.equals( "not_logged_in") )
	        {	        	
  		        Toast.makeText(getApplicationContext(), "Error submitting the business.  We are aware of this problem and it will be fixed in the next update of the app.  We sincerely apologize.", 
		    		  Toast.LENGTH_LONG).show();                		        		        	        	
		        
	        	// TODO take this user to the login screen.
     	        Intent myIntent = new Intent(AddPlanActivity.this, LoginActivity.class);
     	        AddPlanActivity.this.startActivity(myIntent);	        	
		    }
	        else
	        {   
	        	try
		        {
		        	JSONObject obj = new JSONObject(result);

		        	if ( obj != null )
		        	{		    	        
			        	String error = obj.getString("error");

			            if ( error != null && error.trim().equals("email_in_diff_account") )
			            {
			            	// SHOW ERROR
				        	error_message = (TextView) findViewById(R.id.error_message);
				        	error_message.setVisibility(View.VISIBLE);
				        	
					        Toast.makeText(getApplicationContext(), "Error: This email already belongs to an account. Please sign in to that account, or recover your password.", Toast.LENGTH_LONG).show();		        									            	
			            }
			            else
			            {

			            }					            					            					            
		        	}
		        }
		        catch ( Exception e )
		        {   

		        }
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
	        	
//  		        Toast.makeText(getApplicationContext(), "Successfully submitted the business.  " +
//  		        		"Please wait to be redirected. ", 
//		    		  Toast.LENGTH_LONG).show();                		        		        		        
//		       
//		        // 1) First, write to whatever local session file that the person is logged in
//		        // - I just really need user id and name and email. And store that.
//		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AddPlanActivity.this);
//		        prefs.edit().putString("recent_problem_id", result ).commit();
//		        
//		        // 2) Make an intent to go to the home screen
//	            Intent myIntent = new Intent(AddPlanActivity.this, PlanActivity.class);
//	            AddPlanActivity.this.startActivity(myIntent);
	        }
		}    
    }    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
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
