package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends BaseActivity
{
	EditText feedbackText;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.feedback);
 
        // Get Shared Preferences.
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( FeedbackActivity.this);
	    String user_id = prefs.getString( "user_id" , null );		
	    String first_name = prefs.getString( "first_name" , null );			    
	    String email = prefs.getString( "email" , null );			    
	    
		//sendEmail("Feedback Loaded", "Name: " + first_name + " and email: " + email + ", and User id: " + user_id );   
    
        TextView explanation = (TextView) findViewById(R.id.explanation);
    
    	final EditText feedbackText = (EditText) findViewById(R.id.feedbackText);  
    
        Button home = (Button)findViewById(R.id.home);  
        home.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	        	       
                Intent myIntent = new Intent( FeedbackActivity.this, MainActivity.class);
                FeedbackActivity.this.startActivity(myIntent);              
            }
        });       	
    	
		Button button_send_feedback = (Button)findViewById(R.id.button_send_feedback);
		button_send_feedback.setOnClickListener(new Button.OnClickListener()
		{  
		   public void onClick(View v) 
		   {
		      Toast.makeText(getApplicationContext(), "Thank you! Your feedback is received.", Toast.LENGTH_LONG).show();	
	
		      String feedback = feedbackText.getText().toString();
	
		      // Put the persons SHarepPrefs email in there.
		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
		    		  FeedbackActivity.this);
		      
		      String user_id = prefs.getString( "user_id" , null );		
			  String first_name = prefs.getString( "first_name" , null );			    
			  String email = prefs.getString( "email" , null );			    
			    
			  sendEmail("Feedback Android Business Ideas", "User id: " + user_id + " name: " + first_name + " and email: " + email  + " ....with this feedback: " + feedback );   
		    }
		});
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
    }        
}
