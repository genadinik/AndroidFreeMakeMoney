package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import data.Question;

import utils.SendEmail;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class MyQuestionsActivity extends BaseListActivity
{
	ArrayAdapter<Question> adapter;		
	
	ArrayList<Question> questions = new ArrayList <Question>( );		

	private Dialog dialog;
	
    @Override
	public void onCreate(Bundle savedInstanceState) 
    {   
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.users_questions);
        
	    // Make sure the user is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( MyQuestionsActivity.this);
        final String user_id = prefs.getString( "user_id" , null );	    
        final String email = prefs.getString( "email" , null );	    
        final String first_name = prefs.getString( "first_name" , null );	    
        
        // If the user is not logged in, send them to log in
        if ( user_id == null )
        {
            //sendEmail("MyQuestions USER_ID NULL, REDIRECTING", "Redirecting them to be logged in before they see this button. ");   	
        	
		    Toast.makeText(getApplicationContext(), "Not logged in.  Please login or create an account.", Toast.LENGTH_LONG).show();	
            
	        Intent loginIntent = new Intent( MyQuestionsActivity.this, LoginActivity.class);
	        MyQuestionsActivity.this.startActivity(loginIntent);        	
        }          

        final TextView question_label = (TextView) findViewById(R.id.question_label);
	           
//        Question q = new Question ();
//        q.setQuestion( "" );
//        
//        questions.add(q);	    
	    
        //adapter = new ArrayAdapter<Question>(this, R.layout.user_question_list, 
        //		R.id.label,  questions);
        
        adapter = new ArrayAdapter<Question>(this, R.layout.user_question_list, questions);
        
        setListAdapter ( adapter );
                
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
              Toast.makeText(getApplicationContext(), (( TextView ) view).getText(),
                  Toast.LENGTH_SHORT).show();
              
              String question_name = questions.get( position ).getQuestion() + "";
              String question_id = questions.get( position ).getQuestionId() + "";
              
              // Now put this stuff into the SharedPreferences
		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
		    		  MyQuestionsActivity.this);
		      		      
		      prefs.edit().putString( "recent_question_id" , question_id ).commit();
		      prefs.edit().putString( "recent_question" , question_name ).commit();
		      		      
              // And go to problem intent
	          Intent myIntent = new Intent(MyQuestionsActivity.this, QuestionActivity.class);
	          MyQuestionsActivity.this.startActivity(myIntent);
            }
          });                
               
	    Button add_question = (Button)findViewById(R.id.add_question);              
	    add_question.setOnClickListener(new Button.OnClickListener() 
	    {  
	 	   public void onClick(View v) 
	 	   {	 		   
	 		   // Send me an email that a comment was submitted on a question. 
	 	      //sendEmail("My Questions -> Add Question", "Someone clicked on add-question from my questions.  User id: " + user_id  );   
	 		   
			  //Toast.makeText(getApplicationContext(), "Going to the question page. Please wait...", Toast.LENGTH_LONG).show();	
	 		  
              Intent myIntent = new Intent(MyQuestionsActivity.this, AskQuestionActivity.class);
              MyQuestionsActivity.this.startActivity(myIntent);    	 	  
	 	   }
	    });        
	    
	    // Now make a call to server to get the questions for user.
	    
	    if ( user_id == null )
	    {
	    	//sendEmail ( "MyQuestionsError NULL ID" ,  "User id is null.  here is the email: " + email + " and name: + first_name.  Lets see what happens next....migth crash :)");
	    }
	    else
	    {
	    	sendFeedback( user_id );
	    }
    }

    public void sendFeedback( String user_id ) 
    {  
        String[] params = new String[] 
        		{ "http://www.problemio.com/problems/get_questions_by_user_mobile.php", 
        		user_id };
        
        DownloadWebPageTask task = new DownloadWebPageTask();
        
        task.execute(params);        
    }  
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {	
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(MyQuestionsActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		        dialog.setTitle("Loading Your Questions");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		        text.setText("Please wait while your questions load...");
		      dialog.show();
		 }   	
    	
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String user_id = theParams[1];
	        	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        	        
		        String query = String.format("user_id=%s", 
		        	     URLEncoder.encode( user_id, charset) );

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
			     //sendEmail ( "MyQuestionsActivity Network Error" , "Error: " + e.getMessage() );
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
			
			if ( connectionError == true || result == null || result.trim().equals(""))
			{
			     Toast.makeText(getApplicationContext(), "Please try again. Possible Internet connection error.", Toast.LENGTH_LONG).show();	 
			}
			else
	        if ( result.trim().equals( "no_user_id" ) )
	        {
		        Toast.makeText(getApplicationContext(), "Could not get your questions. We are working to fix this. ", Toast.LENGTH_LONG).show();			     		        
	        
                //sendEmail("Error in My Questions", "The questions by user did not load because on the server the validation for user id said the user id was empty." );   	
	        }
	        else
	        if ( result.trim().equals( "database_error" ) )
	        {		        
		        Toast.makeText(getApplicationContext(), "Could not get your questions. We are working to fix this. ", Toast.LENGTH_LONG).show();			     		        
	         		        
	        }
	        else
	        if ( result.trim().equals( "no_questions_by_user" ) )
	        {		        
		        //Toast.makeText(getApplicationContext(), "You have not asked any questions.  Ask your business questions! ", Toast.LENGTH_LONG).show();			     		        
	        
                //sendEmail("No Questions so far in My Questions", "User got message that they do not have questions." );	        
	        
                TextView question_label = (TextView) findViewById(R.id.question_label);
                question_label.setText("You have not asked any business questions.  Ask a business question and an experienced entrepreneur will get back to you." );
	        }
	        else
	        {	        	
		            try
			        {	
			        	JSONArray obj = new JSONArray(result);
				        			        
			        	{
					        questions.clear();
			        
			        		for ( int i = 0; i < obj.length(); i++ )
			        		{
			        			JSONObject o = obj.getJSONObject(i);
	
					            String question_id = o.getString("question_id");
					            String question = o.getString("question");
					            String questioner_id = o.getString("member_id");
					            String first_name = o.getString("first_name");
					            String has_new_comments = o.getString("has_new_comments");			        						            
					            Question q = new Question ( );
					            q.setQuestion(question);
					            q.setQuestionId(question_id);
					            q.setQuestionByMemberId( questioner_id );
					            q.setHasNewComments(has_new_comments);
					            q.setAuthorName(first_name);
					            
					            questions.add( q );
					        }
		        		}
					        
					    adapter.notifyDataSetChanged();
					    
				        TextView question_label = (TextView) findViewById(R.id.question_label);
				        question_label.setText("The questions you asked are below. Choose one or ask a new question.");
			        }			        
			        catch ( Exception e )
			        {
			        	//sendEmail ("Exeption in getting qestions" , "Exception: " + e.getMessage());
				        e.printStackTrace();					
			        }
		        }
		        
		        //adapter.notifyDataSetChanged();		        		
		    }
		}        
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }        
}
