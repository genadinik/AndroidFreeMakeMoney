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

import utils.SendEmail;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class UpdateProfileActivity extends BaseActivity
{
	EditText email;
	TextView email_ask;
	EditText name;
	TextView name_ask;	
	
	EditText pass1;
	EditText pass2;
	
	Dialog dialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.update_profile);

        // Get Shared Preferences.
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( UpdateProfileActivity.this);
	    String user_id = prefs.getString( "user_id" , null );  
	    String user_email = prefs.getString( "email" , null );  
	    String first_name = prefs.getString( "first_name" , null );  	    
	    String saved_password = prefs.getString( "password", null );
	    
	    email_ask = (TextView) findViewById(R.id.email_ask);
	    email = (EditText) findViewById(R.id.email);
	    name_ask = (TextView) findViewById(R.id.name_ask);
	    name = (EditText) findViewById(R.id.name);	    
	   
	    pass1 = (EditText) findViewById ( R.id.pass1 );
	    pass2 = (EditText) findViewById ( R.id.pass2 );
	    
	    if ( user_email != null )
	    {
	    	email.setText(user_email);
	    }
	    
	    if ( first_name != null )
	    {
	    	name.setText( first_name );
	    }
	    
	    if ( pass1 != null )
	    {
	    	pass1.setText( saved_password );
	    	pass2.setText( saved_password );
	    }
	    
		//sendEmail("Update Profile Loaded", "Update profile page loaded for name: " + first_name + " + user_id + " + user_id + " , email: " + user_email);   

        final TextView password_ask = (TextView) findViewById(R.id.password_ask);
    	final EditText password = (EditText) findViewById(R.id.password);  

        final TextView password_confirm_ask = (TextView) findViewById(R.id.password_confirm_ask);
    	final EditText confirm_password = (EditText) findViewById(R.id.confirm_password);  		
		
        Button submit = (Button)findViewById(R.id.submit);  
	    
        if ( user_id == null )
        {
    		//sendEmail("Update Profile Error", "User id is null: " + user_id + " so how do I proceed now? lol");     
        }
        
        //dialog = new Dialog(this);        
        submit.setOnClickListener(new Button.OnClickListener() 
        {  
           public void onClick(View v) 
           {   
//               TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
//               text.setText("Please wait while account details are processed...");
               
           
        	   
        	   //sendEmail("Update Submitted", "Profile update submitted." );   

   	           // Get the params.
     	       String person_email = email.getText().toString().trim();
     	       String person_name = name.getText().toString().trim();   			   
   			   
     	       String new_pass_1 = pass1.getText().toString().trim();
     	       String new_pass_2 = pass2.getText().toString().trim(); 
     	      
    	       // Put the persons SharepPrefs email+name in there.
    	       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
    	    		  UpdateProfileActivity.this);

    	       
    		   String user_id = prefs.getString( "user_id" , null ); 
 
    		   
    		   
    		   if ( user_id == null )
    		   {
    			   //dialog.dismiss();
    			   // Send me email alert that this should not happen.
    			   //sendEmail ( "Null UserId UpdateProfile " , "After update profile was clicked, user id was null :(  User_id : " + user_id + " ,name: " + person_name + " ,email: " + person_email );
    		   }
    		   else
    		   {
    			  if ( person_name == null || person_name.length() == 0 )
    			  {
       			      //dialog.dismiss();
		    		  Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();	    	    		      				  
    			  }
    			  else
    			  if ( ( new_pass_1 == null || new_pass_2 == null ) || !new_pass_1.trim().equals( new_pass_2.trim() ) || new_pass_1.trim().length() < 5 )
    			  {
       			      //dialog.dismiss();
    				  // The passwords are empty or did not match. Give an error.
		    		  Toast.makeText(getApplicationContext(), "Passwords must be 5 characters or longer and have to match. Please try again.", Toast.LENGTH_LONG).show();	    	    		  
    			  }
    			  else
    			  {   
       			      //dialog.dismiss();
    				  // TODO: set passwords in memory  
    				  prefs.edit().putString( "password", new_pass_1 ).commit();
    				  
		     	 	  //Set the email pattern string
		     		  Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
		     		  //Match the given string with the pattern
		     		  Matcher m = pattern.matcher(person_email);
		     		  //check whether match is found
		     		  boolean matchFound = m.matches(); 
		     		  
		     		  if ( !matchFound )
			          {
		    			  //dialog.dismiss();
			    		  Toast.makeText(getApplicationContext(), "The email address is not properly formed.", Toast.LENGTH_LONG).show();	    	    		  
			          }
			          else
			          {
		    			  //dialog.dismiss();
			 		      Toast.makeText(getApplicationContext(), "Updating your information...", 
					    		  Toast.LENGTH_LONG).show();			        	  

			        	  sendFeedback( user_id , person_name , person_email , new_pass_1);
			 		      
			              //sendEmail("Updating Profile", "User_id : " + user_id + " ,name: " + person_name + " ,email: " + person_email );       	        	  		
			          }     		  
    			  }
    		   }
           }
       });
       
       Button home = (Button)findViewById(R.id.home);  
       Button settings = (Button)findViewById(R.id.settings);  

       home.setOnClickListener(new Button.OnClickListener() 
       {  
           public void onClick(View v) 
           {            	        	   
   			   //sendEmail("Update To Home", "Home page chosen." );   
           
               Intent myIntent = new Intent( UpdateProfileActivity.this, MainActivity.class);
               UpdateProfileActivity.this.startActivity(myIntent);              
           }
       });       

       settings.setOnClickListener(new Button.OnClickListener() 
       {  
           public void onClick(View v) 
           {            	        	      			   
               Intent myIntent = new Intent( UpdateProfileActivity.this, SettingsActivity.class);
               UpdateProfileActivity.this.startActivity(myIntent);   			   
           }
       });    
    }
    
    
    
    
    public void sendFeedback( String user_id , String person_name , String person_email , String new_password) 
    {  
        String[] params = new String[] { "https://www.problemio.com/auth/update_user_mobile.php",
        		user_id , person_name , person_email , new_password };

        DownloadPageTask task = new DownloadPageTask();
        task.execute(params);        
    }       
    
    
    public class DownloadPageTask extends AsyncTask<String, Void, String> 
    {
    	private boolean connectionError = false;
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];	        
	        final String user_id = theParams[1];
	        final String person_name = theParams[2];
	        final String person_email = theParams[3];
	        final String new_password = theParams[4];
	        	     
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s&person_name=%s&person_email=%s&pass=%s", 
		        	     URLEncoder.encode(user_id, charset) ,
		        	     URLEncoder.encode(person_name + "", charset) ,
		        	     URLEncoder.encode(person_email + "", charset) ,
		        	     URLEncoder.encode(new_password + "", charset)
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
				connectionError = true;
	    	    //dialog.dismiss();
			}
			
			return response;
		}
		
		 @Override
		 protected void onPreExecute( ) 
		 {
			 dialog = new Dialog(UpdateProfileActivity.this);

		     dialog.setContentView(R.layout.please_wait);
             dialog.setTitle("Updating account details...");
             
             dialog.show();		 
		 }

		@Override
		protected void onPostExecute(String result) 
		{	 
		    try {
		        dialog.dismiss();
		    } catch (Exception ee) {
		        // nothing
		    }
			
			if ( connectionError == true )
			{
				Toast.makeText(getApplicationContext(), "Possible Internet connection error. Please try again. ", Toast.LENGTH_LONG).show();	 
			}
			
	        if ( result == null )
	        {		        
                //sendEmail("UpdateProfile Error 0", "Result came back as empty ...?" );
                
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  Please try again in a few minutes. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();                	        	
	        }
	        else 
	        if ( result.length() <= 1 )
	        {		        
                //sendEmail("UpdateProfile Error", "Result came back as empty ...?" );
                
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  We are aware of this, and sorry this happened. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();                	        	
	        }
	        else
	        if ( result.equals("no_password") )
	        {
                //sendEmail("Password Error", "Something was wrong with resetting the passwod" );
                
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  We are aware of this, and sorry this happened. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();     	        	
	        }
	        else
	        if ( result.equals("update_database_error") )
	        {
                //sendEmail("UpdateProfile DB Error", "Syntax or random error updating the db when trying to update user." );
                
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  We are aware of this and fixing it. Sorry this happened. If the problem persists, please conact us for help.", 
		    		  Toast.LENGTH_LONG).show();     	        	
	        }
	        else
	        if ( result.equals("error_duplicate_email") )
	        {
	  		     Toast.makeText(getApplicationContext(), "This email is used in another account.  Log back into your older account to change the details of that account. If the problem persists, please contact us.", 
			    		  Toast.LENGTH_LONG).show();     	
	  		     
	               // sendEmail("UpdateProfile DB Error", "Error: " + result );

	        }
	        else
	        if ( result.equals( "no_member_id" ) )
	        {                   
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  We are aware of this, and sorry this happened. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();                
	        }	      
	        else	        	
	        if ( result.equals( "no_name" ) )
	        {		        
                //sendEmail("UpdateProfile Error", "Name was empty when sending " );
                
  		        Toast.makeText(getApplicationContext(), "Could not identify the name you are trying to update. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();                
	        }	      
	        else
	        if ( result.equals( "error_getting_user" ) )
	        {    
                //sendEmail("UpdateProfile Error", "Could not get user when querying for them." );
                
  		        Toast.makeText(getApplicationContext(), "Error updating your profile.  We are aware of this, and sorry this happened. If the problem persists, please let us know about it.", 
		    		  Toast.LENGTH_LONG).show();                  		        		        
	        }
	        else
	        {   
  		        Toast.makeText(getApplicationContext(), "Successfully updated your profile. ", 
		    		  Toast.LENGTH_LONG).show();                		        		        		        

		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
		        		UpdateProfileActivity.this);
  		        
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{	
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);
		        	
				            String name = o.getString("first_name");
				            String email = o.getString("email");

					        prefs.edit().putString("email", email ).commit();
					        prefs.edit().putString("first_name", name ).commit();					        
		        		}  // End of for loop		       
		        	} // End of if object is not null
		        	else
		        	{
		        		// TODO: what to do if object is null?
		        		//sendEmail ( "UpdateProfileError" , "On server return object is null");
		        	}
		        }
		        catch ( Exception e )
		        {
		        	e.printStackTrace();
		        }
	        } // End of if/elses    
		}
	}        
    
    
    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }      
    
    @Override
	public void onStop()
    {
       super.onStop();
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
