package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.SendEmail;
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


public class AskQuestionActivity extends BaseActivity
{
	EditText question;
	EditText email;
	EditText name;
	
	TextView error_message;

    @Override
	public void onCreate(Bundle savedInstanceState) 
    {
	    super.onCreate(savedInstanceState);	    
	    setContentView(R.layout.ask_question);        
    
	    // Make sure the user is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AskQuestionActivity.this);
        final String user_id = prefs.getString( "user_id" , null );
        final String session_name = prefs.getString( "first_name" , null );
        final String session_email = prefs.getString( "email" , null );  
        
        
        if ( user_id == null )
		{
        	  Toast.makeText(getApplicationContext(), "Please log in.", Toast.LENGTH_LONG).show();	            	  

	          Intent myIntent = new Intent(AskQuestionActivity.this, LoginActivity.class);
	          AskQuestionActivity.this.startActivity(myIntent);        	  
		}
	    
	    TextView question_ask = (TextView) findViewById(R.id.question_ask);
	    question = (EditText) findViewById(R.id.question);

	    TextView email_ask = (TextView) findViewById(R.id.email_ask);
	    email = (EditText) findViewById(R.id.email);
	    TextView name_ask = (TextView) findViewById(R.id.name_ask);
	    name = (EditText) findViewById(R.id.name);	    
	    
	    // Now pre-fill the entries
	    if ( session_email != null && session_email.trim().length() > 2 )
	    {	
	    	email.setText(session_email);
	    	email.setEnabled(false);
	    	
	    	email_ask.setText("This is the email associated with your account.  If you wish to use a different email, please update your profile.");
	    }
	    
	    if ( session_name != null )
	    {
	    	name.setText(session_name);
	    }
	    
	    
	    
	    Button submit = (Button)findViewById(R.id.submit);   
            
	    submit.setOnClickListener(new Button.OnClickListener() 
	    {  
	 	   public void onClick(View v) 
	 	   {
			  Toast.makeText(getApplicationContext(), "Submitting your question. Please wait...", Toast.LENGTH_LONG).show();	
	 		  
			  String q = question.getText().toString(); 			  
			  String e = email.getText().toString(); 			  
			  String n = name.getText().toString(); 
	
			  if ( e != null )
			  {
				  e = e.trim();
			  }
			  
              //sendEmail("A Question Has Been Submitted", "A question is submitted and here it is: " + q );

    	      // Put the persons SHarepPrefs email in there.
    	      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
    	    		  AskQuestionActivity.this);

    	      String existing_email = prefs.getString( "email" , null );
    	      
    	      if ( existing_email == null || existing_email.trim().length() < 2 )
    	      {
        	     prefs.edit()        
  		        .putString("email", e)
  		        .putString("recent_question", q )
  		        .putString( "first_name", n )
  		        .commit();    	    	  
    	      }
    	      else
    	      {
        	     prefs.edit()        
  		        .putString("recent_question", q )
  		        .putString( "first_name", n )
  		        .commit();     	    	  
    	      }
				  
			  
			  
    	 	  //Set the email pattern string
    		  Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
    		  //Match the given string with the pattern
    		  Matcher m = pattern.matcher(e);
    		  //check whether match is found
    		  boolean matchFound = m.matches();
    		 
	    	
			  if ( user_id == null )
			  {
				  // TODO: please enter user id.  
    		      //sendEmail("Ask Question Err Validating USER ID. " , "Person tried to submit a question, but their user id was empty....very bad." );
				  
			  }
			  else
			  if ( n == null || n.length() < 2 )
              {
            	  Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();	            	  
                                  
              }
              else
              if ( e == null || !matchFound )
              { 
    	 		   Toast.makeText(getApplicationContext(), "The email: " + e + " has invalid format.  Please try again." , Toast.LENGTH_LONG).show();     	 		        
    		  }				  
              else
              if ( q == null || q.length() < 5 )
              {
    			  Toast.makeText(getApplicationContext(), "Please enter a real business question that you need help with.", Toast.LENGTH_LONG).show();	            	                
              }
              else
              {
            	  // Add the question to prefs
    		      //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AskQuestionActivity.this);
            	      		      
            	  sendFeedback( q , user_id , e , n);   
              }
	 	   }
	    });        
    }

    public void sendFeedback( String question , String user_id , String email , String name ) 
    {  
        String[] params = new String[] { "http://www.problemio.com/problems/add_question_mobile.php?platform=android_fundraising", 
        		question , user_id , email , name };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }          
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String question = theParams[1];
	        final String user_id = theParams[2];
	        final String email = theParams[3];	        
	        final String name = theParams[4];	      
	        
	        // TODO: ADD THE PERSONS EMAIL AND NAME TO APPROPRIATE OBJECTS
	        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AskQuestionActivity.this);
	        prefs.edit().putString("email", email ).commit();
	        prefs.edit().putString("last_name", name ).commit();
	        
	        
	        
	        String charset = "UTF-8";	        
	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("question=%s&user_id=%s&email=%s&name=%s", 
		        	     URLEncoder.encode(question, charset) ,
		        	     URLEncoder.encode(user_id, charset) ,
		        	     URLEncoder.encode(email, charset) ,
		        	     URLEncoder.encode(name, charset) 
		        		);

		        final URL url = new URL( myUrl + "&" + query );
		        		        
		        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        
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

			}
			
			return response;
		}
    
		@Override
		protected void onPostExecute(String result) 
		{	        
			if ( result == null )
			{
		        Toast.makeText(getApplicationContext(), "Network error. Please try again.  If the error persists, please let us know about it.", Toast.LENGTH_LONG).show();		        				
			}
			else
	        if ( result.equals( "error_adding_question") )
	        {	        	        	
		        Toast.makeText(getApplicationContext(), "We encountered an error while adding your question.  We are aware of the error and it will be fixed in the next update of the app.", Toast.LENGTH_LONG).show();		        
	        }	        
	        else
	        if ( result.equals( "email_in_diff_account") )
	        {	        	        	
	        	error_message = (TextView) findViewById(R.id.error_message);
	        	error_message.setVisibility(View.VISIBLE);
	        	
		        Toast.makeText(getApplicationContext(), "Error: This email already belongs to an account. Please sign in to that account, or recover your password.", Toast.LENGTH_LONG).show();		        
	        }	
	        else	
	        if ( result.equals( "no_question" ) )
	        {	        	        	
		        Toast.makeText(getApplicationContext(), "We encountered an error while adding your question.  We are aware of the error and it will be fixed in the next update of the app.", Toast.LENGTH_LONG).show();		        
	        }
	        else
	        if ( result.equals( "no_member_id" ) )
	        {		        
		        Toast.makeText(getApplicationContext(), "We encountered an error while adding your question.  We are aware of the error and it will be fixed in the next update of the app.", Toast.LENGTH_LONG).show();	
		    }
	        else
	        {		        
		        // Add recent question id to prefs.
  		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( AskQuestionActivity.this);
  		        prefs.edit().putString("recent_question_id", result ).commit();         		        
		        
		        Toast.makeText(getApplicationContext(), "You successfully asked your question.", Toast.LENGTH_LONG).show();	
	        
		        // Now go to question page
		        Intent myIntent = new Intent(AskQuestionActivity.this, QuestionActivity.class);
	            AskQuestionActivity.this.startActivity(myIntent);		        
	        }
		}    
    }		    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }        
    
    
    @Override
	public void onStop()
    {
       super.onStop();
       // your code
    }        
}
