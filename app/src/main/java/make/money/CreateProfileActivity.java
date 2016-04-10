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

public class CreateProfileActivity extends BaseActivity
{

	Dialog dialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);	        
	        setContentView(R.layout.create_profile);
	        	        
   	        final TextView nameask = (TextView) findViewById(R.id.name_ask);
	    	final EditText name = (EditText) findViewById(R.id.name);  
	        
	        final TextView emailask = (TextView) findViewById(R.id.email_ask);
	    	final EditText email = (EditText) findViewById(R.id.login_email);  

	    	
	        final TextView password_ask = (TextView) findViewById(R.id.password_ask);
	        
	    	final EditText password = (EditText) findViewById(R.id.password);  

	        final TextView password_confirm_ask = (TextView) findViewById(R.id.password_confirm_ask);
	    	final EditText confirm_password = (EditText) findViewById(R.id.confirm_password);  

	        // Show button for submit
	        Button submit = (Button)findViewById(R.id.submit);  
	        
	    
	        

	        // Show button for login
	        Button login = (Button)findViewById(R.id.login);  	        
	        	        
	        final TextView got_account_question = (TextView) findViewById(R.id.got_account_question);

	        
	        login.setOnClickListener(new Button.OnClickListener() 
	        {  
	     	   public void onClick(View v) 
	     	   {	     		   
	      		  // And go to login intent
		          Intent myIntent = new Intent( CreateProfileActivity.this, LoginActivity.class);
		          CreateProfileActivity.this.startActivity(myIntent); 
	     	   }
	        });     
	            
	        
	        submit.setOnClickListener(new Button.OnClickListener() 
	        {  
	     	   public void onClick(View v) 
	     	   {  		   	      		  
	     		  boolean validate_form_errors = validate_form ( name.getText().toString() , 
	     				  email.getText().toString() , 
	     				  password.getText().toString() , confirm_password.getText().toString() ); 

//	     	      String email = loginEmail.getText().toString();
//	     	      String pass = password.getText().toString(); 

	     		  
	     		  String n = name.getText().toString().trim();
	     		  String p = password.getText().toString().trim();
	     		  String pc = confirm_password.getText().toString().trim();
	     		  String e = email.getText().toString().trim();
	     		  
		          if ( validate_form_errors == false )
		          {
				      Toast.makeText(getApplicationContext(), "Submitting your form...", Toast.LENGTH_LONG).show();	
		      		  
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", p );
				        
		      		  
		        	  sendFeedback(n , e , p);   
	     	      }
		          else
		          {


		          }
	     	   }
	        });        	    	
	   }
	   
	   public boolean validate_form ( String name , String email , String password , String confirm_password )
	   {
	    	boolean error = false;
		   
    		 //Set the email pattern string
    		 Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
    		 //Match the given string with the pattern
    		 Matcher m = pattern.matcher(email);
    		 //check whether match is found
    		 boolean matchFound = m.matches();
    		 
    		 if (matchFound)
    		 {
    			 
	   		 }
    		 else
    		 {
 		        error = true;
    			 
 		        Toast.makeText(getApplicationContext(), "The email: " + email + " has invalid format.  Please try again." , Toast.LENGTH_LONG).show();  		        
			 }	
	    	
	    	// Now validate the form fields
	        if ( name == null || name.length() < 2 )
	        {
	        	// Make a toast message showing the error
		        Toast.makeText(getApplicationContext(), "You must enter a name. " +
		        		"Please try again.", Toast.LENGTH_LONG).show();	
		        
		        error = true;
		        
	        }

	        if ( email == null || email.length() < 3 )
	        {
	        	// Make a toast message showing the error
		        Toast.makeText(getApplicationContext(), "You must enter a valid email. " +
		        		"Please try again.", Toast.LENGTH_LONG).show();	

		        error = true;	        		        
	        }	 
	        
	        if ( password == null || password.length() < 5 || confirm_password == null || 
	        		confirm_password.length() < 5 || !confirm_password.equals(password)  )
	        {
	        	// Make a toast message showing the error
		        Toast.makeText(getApplicationContext(), "The passwords must match and be 5 characters or longer. " +
		        		"Please try again.", Toast.LENGTH_LONG).show();	
	        
		        error = true;		        
	        }		   
	        
	        return error;
	   }
	   
	   
	   public void sendFeedback(String name , String email , String password ) 
	   {  
	        String[] params = new String[] { "https://www.problemio.com/auth/create_profile_mobile.php", name , email , password };

	        DownloadWebPageTask task = new DownloadWebPageTask();
	        task.execute(params);        
	   }	   
	   
	   public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
	   {	   
			 private boolean connectionError = false;
		    	
			 @Override
			 protected void onPreExecute( ) 
			 {
				  dialog = new Dialog(CreateProfileActivity.this);

			      dialog.setContentView(R.layout.please_wait);
			      dialog.setTitle("Creating Profile");

			      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
			      text.setText("Please wait while your profile is created... ");
			      dialog.show();
			 }   		   
		   
			@Override
			protected String doInBackground(String... theParams) 
			{
		        String myUrl = theParams[0];
		        final String name = theParams[1];
		        final String email = theParams[2];
		        final String password = theParams[3];
		        
		        String charset = "UTF-8";	        	        
		        String response = null;
		        
				try 
				{		        
			        String query = String.format("name=%s&email=%s&password=%s", 
			        	     URLEncoder.encode(name, charset), 
			        		 URLEncoder.encode(email, charset), 
			        	     URLEncoder.encode(password, charset));

			        final URL url = new URL( myUrl + "?" + query );
			        		        
			        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			        
			        conn.setDoOutput(true); 
			        conn.setRequestMethod("POST");
			        
			        //conn.setRequestProperty("login", myEmail);
			        //conn.setRequestProperty("password", myPassword);
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
				try {
			        dialog.dismiss();
			    } catch (Exception ee) {
			        // nothing
			    }
				
				if ( connectionError == true )
				{
			        Toast.makeText(getApplicationContext(), "Please check your Internet connection.", 
			        		Toast.LENGTH_LONG).show();					
				}
				
		        if ( result == null )
		        {
			        Toast.makeText(getApplicationContext(), "An error happened while creating your profile. Please try again or let us know about this.", 
			        		Toast.LENGTH_LONG).show();
			        
		        	// Reset the password to null.
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", null );			        
			        
		        }
		        else
		        if ( result == "no_name" || result.equals("no_name") )
		        {			        
			        Toast.makeText(getApplicationContext(), "You did not enter a name. Please try again.", 
			        		Toast.LENGTH_LONG).show();				        
			        
		        	// Reset the password to null.
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", null );			        
		        }
		        else
		        if ( result == "no_email" || result.equals("no_email") )
		        {
		        	Toast.makeText(getApplicationContext(), "You did not enter an email. Please try again.", 
			        		Toast.LENGTH_LONG).show();
		        	
		        	// Reset the password to null.
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", null );		        			        	
		        }
		        else
		        if ( result == "no_password" || result.equals( "no_password" ) )
		        {
		        	Toast.makeText(getApplicationContext(), "You did not enter a correctly formed password. Please try again.", 
			        		Toast.LENGTH_LONG).show();
		        	
		        	// Reset the password to null.
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", null );		        			        	
		        }
		        else
		        if ( result == "duplicate_email" || result.equals( "duplicate_email" ) )
		        {
		        	Toast.makeText(getApplicationContext(), "There is already an account under the email you are using. Simply use it to log in.", 
			        		Toast.LENGTH_LONG).show();
		        	
		        	// Reset the password to null.
		      		SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

				        prefs.edit().putString("password", null );		        			        	
		        }
		        else
		        {			        
			        String user_id = null;
			        String firstName = null;
			        String lastName = null;
			        String email = null;
			       
			        try
			        {
			        	JSONArray obj = new JSONArray(result);
			        
			        	JSONObject o = obj.getJSONObject(0);
			            
			            firstName = o.getString("first_name");
			            lastName = o.getString("last_name");
			            
			            user_id = o.getString("user_id");
			            email = o.getString("email");				            					        			        	
			        }
			        catch ( Exception e )
			        {
			        	// Reset the password to null.
			      		SharedPreferences prefs = 
				        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

					        prefs.edit().putString("password", null );			        	
			        }
			        
			        // 1) First, write to whatever local session file that the person is logged in
			        // - I just really need user id and name and email. And store that.
			        SharedPreferences prefs = 
			        		PreferenceManager.getDefaultSharedPreferences( CreateProfileActivity.this);

			        if ( user_id != null && user_id.trim().length() > 0 && !user_id.trim().equals("null") ) 
			        {
				        prefs.edit()
				        .putString("first_name", firstName)
				        .putString("last_name", lastName)
				        .putString("email", email)		        
				        .putString("user_id", user_id)
				        .putString("has_account", "true")
				        .commit();
			        }
			        
			        // Make an intent to go to the home screen
		            Intent myIntent = new Intent(CreateProfileActivity.this, MainActivity.class);
		            CreateProfileActivity.this.startActivity(myIntent);
		        }
			}    
	    }
	    
//			public void readWebpage(View view) 
//			{
//		        Log.d( "Read webpage: " , "In the read webpage method" );
//				DownloadWebPageTask task = new DownloadWebPageTask();
//				task.execute(new String[] { "http://www.problemio.com/auth/mobile_login.php" });
//			}    
		
	    // Subject , body
	    public void sendEmail( String subject , String body )
	    {
	        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

	        SendEmail task = new SendEmail();
	        task.execute(params);            	
	    }    	   

//	    @Override
//	    public boolean onKeyUp(int keyCode, KeyEvent event) 
//	    {
//	        Log.d( "CreateProfileActivity" , "In key event" );
//
//		    if(keyCode == KeyEvent.KEYCODE_BACK)
//		    {
//		        Log.d( "CreateProfileActivity" , "In key back" );
//
//		    	//do your stuff here
//		        return true;
//		    }
//		    else
//		    {
//		        Log.d( "CreateProfileActivity" , "NOT In key back" );
//		    	return super.onKeyUp(keyCode, event);
//		    }
//	    }    
	    
	    
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
