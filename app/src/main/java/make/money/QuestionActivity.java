package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.android.gcm.GCMRegistrar;

import utils.SendEmail;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import data.QuestionComment;

public class QuestionActivity extends BaseListActivity 
{
	ArrayAdapter<QuestionComment> adapter;		
	TextView question_label = null;
	TextView please_wait = null;
		
	ArrayList<QuestionComment> comments = new ArrayList <QuestionComment>( );		
	
	static final String SENDER_ID = "1039266385602";
	
    @Override
	public void onCreate(Bundle savedInstanceState) 
    {   
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.question_page);
                 
	    // Make sure the user is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( QuestionActivity.this);
        final String user_id = prefs.getString( "user_id" , null );	    
        final String recent_question_id = prefs.getString( "recent_question_id" , null );	    
        final String recent_question = prefs.getString( "recent_question" , null );	 	         
        
        // If the user is not logged in, send them to log in
        if ( user_id == null )
        {              
	        Intent loginIntent = new Intent( QuestionActivity.this, LoginActivity.class);
	        QuestionActivity.this.startActivity(loginIntent);        	
        }          
       
        
        
        if ( android.os.Build.VERSION.SDK_INT >= 8 )
        {
	        GCMRegistrar.checkDevice(this);	
	        GCMRegistrar.checkManifest(this);
	    
	        final String regId = GCMRegistrar.getRegistrationId(this);
	        if (regId.equals("")) 
	        {
	        	// Automatically registers application on startup. 
	        	GCMRegistrar.register(getApplicationContext(), SENDER_ID); 
	        } 
	        else 
	        {	     
	        	// Device is already registered on GCM, check server. 
	        	if (GCMRegistrar.isRegisteredOnServer(getApplicationContext())) 
	        	{ 
	        		// Not sure what to do here :)
	        	} 
	        	else 
	        	{
			    	if ( user_id != null )
			    	{	
				        GCMRegistrar.register(this, SENDER_ID); // google register 
			    		//GCMRegistrar.setRegisteredOnServer(this, true); //Tell GCM: this device is registered on my server	        
				    	setRegistrationId ( user_id , regId );
			    	}
	        	}
	        }	        
        }        
        
        
        
        
        // Now lets output some of the stuff on the page.
        question_label = (TextView) findViewById(R.id.question_label);
	    question_label.setText("Question: " + recent_question);
        question_label.setMovementMethod(new ScrollingMovementMethod());
	    
        
        //TextView question = (TextView) findViewById(R.id.question);
	    
        
        // Need to display a simple message:
	    TextView please_wait = (TextView) findViewById(R.id.please_wait);
	    
	    // Now make the remote call to display the existing comments in the question.
	    // TODO: ERROR HERE
	    
  	    sendFeedback( user_id , recent_question_id );   

        adapter = new ArrayAdapter<QuestionComment>(this, R.layout.question_comment_list, 
        		R.id.label,  comments);
        
        setListAdapter ( adapter );
        
	    // Now put the text box here so that people can comment more.
        final EditText commentText = (EditText) findViewById(R.id.question_text);  

	    
	    Button submit = (Button)findViewById(R.id.submit);
	    
	    submit.setOnClickListener(new Button.OnClickListener() 
	    {  
	 	   public void onClick(View v) 
	 	   {
			  String comment = commentText.getText().toString(); 			  
	 		   
	 		   // Send me an email that a comment was submitted on a question. 
	 	      sendEmail("Someone Submitted Comment on Question", "Someone submitted a comment on a " +
	 	      		"question. This is the comment: " + comment + " and here is the user id: " + 
	 	    		  user_id );   
	 		   
			  Toast.makeText(getApplicationContext(), "Submitting your comment. Please wait...", 
					  Toast.LENGTH_LONG).show();	
	 		  
              if ( comment == null || comment.length() < 2 )
              {
    			  Toast.makeText(getApplicationContext(), "Please enter a comment before you submitting.", Toast.LENGTH_LONG).show();	            	  
              
//    	 	      sendEmail("Someone Submitted EMPTY Comment on Question", "Someone submitted a comment on a " +
//    		 	      		"question. This is the comment: " + comment + " and here is the user id: " + 
//    		 	    		  user_id + " RESULT: not submitting the question and showing error.");               
              }
              else
              {
            	  // Add the question to prefs
    		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( QuestionActivity.this);
    		      prefs.edit().putString( "recent_comment", comment ).commit();                	  
            	  
            	  sendFeedback( comment , user_id , recent_question_id );   
              }
	 	   }
	    });        
    }
    
    public void sendFeedback( String comment , String user_id , String recent_question_id ) 
    {  
        String[] params = new String[] { "http://www.problemio.com/problems/add_question_comment_mobile.php", 
        		comment , user_id , recent_question_id };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }            
    
    public void sendFeedback( String user_id , String recent_question_id ) 
    {  
        String[] params = new String[] { "http://www.problemio.com/problems/get_question_comments_mobile.php", 
        		user_id , recent_question_id };

        LoadQuestionCommentsTask task = new LoadQuestionCommentsTask();
        task.execute(params);        
    }      
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", 
        		subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }
    
    public void setRegistrationId(String user_id , String regId ) 
    {  
        String[] params = new String[] { "http://www.problemio.com/problems/update_user_reg_mobile.php", user_id , regId };

        UpdateRedId task = new UpdateRedId();
        task.execute(params);        
    } 
    

    
    
    
    
    public class UpdateRedId extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String user_id = theParams[1];
	        final String reg_id = theParams[2];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s&reg_id=%s", 
		        	     URLEncoder.encode(user_id, charset) , 
		        	     URLEncoder.encode(reg_id, charset)
		        		);

		        final URL url = new URL( myUrl + "?" + query );
		        		        
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
					e.printStackTrace();
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{	        
	        if ( result == null )
	        {
		        // Show the user a message that they did not enter the right login
		        
	        }
	        else
	        if ( result.equals("error_updating_user") )
	        {
	        	
	        }
	        else
	        {		        
		        // TODO:
		        if ( android.os.Build.VERSION.SDK_INT >= 8 )
		        {
		        	GCMRegistrar.setRegisteredOnServer(getApplicationContext(), true);
		        }

		    }
		}        
    }            
    
    
    
    public class LoadQuestionCommentsTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String user_id = theParams[1];
	        final String recent_question_id = theParams[2];
	        	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s&question_id=%s", 
		        	     URLEncoder.encode(user_id, charset),
		        	     URLEncoder.encode(recent_question_id, charset) );

		        final URL url = new URL( myUrl + "?" + query );
		        		        
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
			     //sendEmail ( "QuestionsActivity 1 Network Error" , "Error: " + e.getMessage() );			
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{			
	        if ( result == null )
	        {
	          	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error loading the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	
		    
//	            sendEmail("Question - Error Loading Question Comments", 
//	            		"Output returned from server says " +
//	            		"no_question_id. Result was: " + result ); 
	        }
	        else
	        if ( result.equals("no_question_id") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error loading the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	  		        
	        }
	        else	        	        	        	        
	        if ( result.equals("error_getting_question_comments") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error getting the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();			    
	        }
	        else	        	        
	        if ( result.equals("no_question_comments") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        // Toast.makeText(getApplicationContext(), "There was an error adding the comment for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	

	    	    TextView please_wait = (TextView) findViewById(R.id.please_wait);
	    	    please_wait.setText("So far there are no answers to this question. Someone will get back to you with an answer. ");		        
	        }
	        else	        
	        if ( result.equals("no_member_id") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error getting the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();			    
	        }
	        else
	        {		        		        
	    	    please_wait = (TextView) findViewById(R.id.please_wait);
	    	    please_wait.setVisibility(View.GONE);

		        // Unwrap the stuff from the JSON string
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{
				        comments.clear();
		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);
				            
				            String first_name = o.getString("first_name");
				            String last_name = o.getString("last_name");
				            String comment = o.getString("comment");
				            String user_id = o.getString("user_id");
				            
				            QuestionComment qc = new QuestionComment ( );

				            qc.setComment(comment);
				          
				            if ( last_name == null || last_name.equals("null") || 
				            		last_name.trim().equals("") )
				            {
					            qc.setAuthorName( first_name + "" );
				            }
				            else
				            {
				            	qc.setAuthorName( first_name + " " + last_name );
				            }				          
				          				            
				            qc.setCommentBy(user_id);
				            
				            comments.add( qc );
				    	}
		        		
				        adapter.notifyDataSetChanged();
		        	}			        
		        }
		        catch ( Exception e )
		        {			        
//		            sendEmail("Exception parsing question comments", 
//		            		"Exception parsing the JSON returned by the question comments. " +
//		            		"Here is the exception: " + e.getMessage() + " and here is the returned " +
//		            				"string: " + result );   		        		        
		        }
		        
		        // Now should reset the text in the form.
		        
		    }
		}        
    }            
    
    

    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String comment = theParams[1];
	        final String user_id = theParams[2];
	        final String recent_question_id = theParams[3];
	        	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s&comment=%s&question_id=%s", 
		        	     URLEncoder.encode(user_id, charset),
		        	     URLEncoder.encode(comment, charset),
		        	     URLEncoder.encode(recent_question_id, charset) );

		        final URL url = new URL( myUrl + "?" + query );
		        		        
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
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an unexpected problem getting the comments for this question. Please try again. If the problem persists, please let us know about it.", Toast.LENGTH_LONG).show();			    
			}
			else
	        if ( result.equals("no_question_id") )
	        {   
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error adding your comment for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	 		        
	        }
	        else	        	        	        	        
	        if ( result.equals("error_adding_question_comment") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error adding your comment for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	
		    	        
	        }
	        else	        	        	        
	        if ( result.equals("error_getting_question_comments") )
	        {
		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error getting the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	
		      		        
	        }
	        else	        	        
	        if ( result.equals("no_comment") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There are no answers yet to this question. What do you think?" 
		        	,	Toast.LENGTH_LONG).show();	  		        	        
	        }
	        else	        
	        if ( result.equals("no_question_comments") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error getting the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();	
	        }
	        else
	        if ( result.equals("no_member_id") )
	        {		        
	        	// Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "There was an error getting the comments for this question. We are aware of this and it will be fixed with the next update of the app.", Toast.LENGTH_LONG).show();			    
	        }
	        else
	        if ( result.equals("error_duplicate_problem") )
	        {
		        Toast.makeText(getApplicationContext(), "This comment has already been submitted. Thank you.", Toast.LENGTH_LONG).show();	
	        }
	        else
	        {		        
	            //sendEmail("Add Question Comment Success", "Output returned from server says question added." );   		        		        
		        
	    	    //please_wait = (TextView) findViewById(R.id.please_wait);
	    	    //please_wait.setText("test?");
	    	    //please_wait.setVisibility(View.GONE);
	      
	    	    
		        // Unwrap the stuff from the JSON string
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{
				        comments.clear();
		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);      
				            
				            String first_name = o.getString("first_name");
				            String last_name = o.getString("last_name");
				            String comment = o.getString("comment");
				            String user_id = o.getString("user_id");
				            
				            QuestionComment qc = new QuestionComment ( );

				            qc.setComment(comment);
				
				            if ( last_name == null || last_name.equals("null") || 
				            		last_name.trim().equals("") )
				            {
					            qc.setAuthorName( first_name + "" );
				            }
				            else
				            {
				            	qc.setAuthorName( first_name + " " + last_name );
				            }
				            
				            qc.setCommentBy(user_id);
				            
				            comments.add( qc );
				    	}
		        		
				        adapter.notifyDataSetChanged();
				        
				        // Now clear the input form.
				        EditText commentText = (EditText) findViewById(R.id.question_text);
				        commentText.setText("");
				        
				        TextView please_wait = (TextView) findViewById(R.id.please_wait);
			    	    please_wait.setVisibility(View.GONE);
		        	}			        
		        }
		        catch ( Exception e )
		        {			        
   		        		        
		        }		        
		    }
		}        
    }        

    @Override
	public void onStop()
    {
       super.onStop();
    }    
}
