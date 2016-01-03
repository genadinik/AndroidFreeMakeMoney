package make.money;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.SendEmail;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends BaseActivity
{

	private TextView textView;
	private Dialog dialog;
	
    @Override
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.login);        
        
        // Subject , body
	    //sendEmail("Login shown", "Someone was shown the login screen");   
        
	    //dialog = new Dialog(this);
        
        final TextView emailask = (TextView) findViewById(R.id.email_ask);
        
        // Show form for login_email
    	final EditText loginEmail = (EditText) findViewById(R.id.login_email);  
    	String name = loginEmail.getText().toString();  

    	// Show field for password  
    	final EditText password = (EditText) findViewById(R.id.password);  
    	String text = password.getText().toString();                  
        
        // Show button for submit
        Button submit = (Button)findViewById(R.id.submit);   
                
        submit.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {
     	      String email = loginEmail.getText().toString();
     	      String pass = password.getText().toString();
     	      
     		  //sendEmail("Login attempted", "This is the login email: " + email);   

     		  // TODO: VALIDATE!!!
     		  if ( email == null || email.trim().length() < 2 )
     		  {
	    		  Toast.makeText(getApplicationContext(), "Please enter a valid email address.", Toast.LENGTH_LONG).show();	    	    		  
     		  }
     		  else
     		  if ( pass == null || pass.trim().length() < 2 )
     		  {
     			  Toast.makeText(getApplicationContext(), "Please enter a correct password.", Toast.LENGTH_LONG).show();	    	    		  
     		  }
     		  else
     		  {
     			 sendFeedback(pass, email); 
     		  }  
     	   }
        });        

        // Show button for submit
        Button forgot_password = (Button)findViewById(R.id.forgot_password);   
                
        forgot_password.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {
			  Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_LONG).show();	
     				  
	          Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
	          LoginActivity.this.startActivity(intent);			  
     	   }
        });                
        
        // Now add messaging for creating a profile
        final TextView create_profile_message = (TextView) findViewById(R.id.create_profile_message);
        
        Button create_profile = (Button)findViewById(R.id.create_profile);   

        create_profile.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {
      		    //sendEmail("Create Profile Clicked", "From Login screen, someone clicked on the create profile button" );   
     		   
     	        Intent myIntent = new Intent(LoginActivity.this, CreateProfileActivity.class);
     	        LoginActivity.this.startActivity(myIntent);
     	   }
        });        
        
    }
    
    public void sendFeedback(String pass , String email) 
    {  
        String[] params = new String[] { "http://www.problemio.com/auth/mobile_login.php", email, pass };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }          
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(LoginActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		        dialog.setTitle("Logging You In");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		        text.setText("Please wait while you are being logged in...");
		      dialog.show();
		 }   
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String myEmail = theParams[1];
	        final String myPassword = theParams[2];
	      
	        String charset = "UTF-8";	        
	        
	        Authenticator.setDefault(new Authenticator()
	        {
	            @Override
				protected PasswordAuthentication getPasswordAuthentication() 
	            {
	                return new PasswordAuthentication( myEmail, myPassword.toCharArray());
	            }
	        });	        
	        
        
	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("login=%s&password=%s", 
		        	     URLEncoder.encode(myEmail, charset), 
		        	     URLEncoder.encode(myPassword, charset));

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setRequestProperty("login", myEmail);
		        conn.setRequestProperty("password", myPassword);
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
	 		      //sendEmail ( "Login Activity 1 Network Error" , "Error: " + e.getMessage() );
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
				Toast.makeText(getApplicationContext(), "Please try again. Possible Internet connection error.", Toast.LENGTH_LONG).show();	 
			}			
			
	        if ( result != null && result.equals( "no_such_user") )
	        {		        
		        Toast.makeText(getApplicationContext(), "Your email and password do not match out records. Please try again or create and account.", Toast.LENGTH_LONG).show();	
		        
		        //final TextView login_error = (TextView) findViewById(R.id.login_error);
	        }
	        else
	        {		        
		        String firstName = null;
		        String email = null;
		        String user_id = null;
		        
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        
		        	JSONObject o = obj.getJSONObject(0);
		            
		            firstName = o.getString("first_name");
		            email = o.getString("email");
		            user_id = o.getString("user_id");
		            
			        // Write to whatever local session file that the person is logged in
			        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
			        		LoginActivity.this);
			        
			        if ( user_id != null && user_id.trim().length() > 0 
			        		&& !user_id.trim().equals("null") )
			        {
				        prefs.edit()
				        .putString("first_name", firstName)
				        .putString("email", email)		        
				        .putString("user_id", user_id)
				        .commit();
			        }
			        // 2) Make an intent to go to the home screen
		            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
		            LoginActivity.this.startActivity(myIntent);		            
		            
		        }
		        catch ( Exception e )
		        {
			        Log.d( "JSON ERRORS: " , "some crap happened " + e.getMessage() );
		        }
	        }
		}    
    }

    // TODO: see if I can get rid of this
	public void readWebpage(View view) 
	{
		DownloadWebPageTask task = new DownloadWebPageTask();
		task.execute(new String[] { "http://www.problemio.com/auth/mobile_login.php" });
	}     	
	
    @Override
	public void onStop()
    {
       super.onStop();
    }    	
}
