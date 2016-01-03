package make.money;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class ContentGrantsActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.grants);
	    
    
	    Button plan_fundraising = (Button)findViewById(R.id.plan_fundraising);          
	    plan_fundraising.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	        	
	            Intent myIntent = new Intent(ContentGrantsActivity.this, AddPlanActivity.class);
	            ContentGrantsActivity.this.startActivity(myIntent);
	        }
	    });     
    
	    Button biz_app = (Button)findViewById(R.id.biz_app);          
	    biz_app.setOnClickListener(new Button.OnClickListener() 
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

	
	Button give_review = (Button)findViewById(R.id.give_review);   
	give_review.setOnClickListener(new Button.OnClickListener() 
	{  
	    public void onClick(View v) 
	    {
	        //sendEmail("Learn --> Give Review", "From learn page, user clicked on give review" );   	
	    	
	        Intent intent = new Intent(Intent.ACTION_VIEW);
	    	intent.setData(Uri.parse("market://details?id=make.money"));
	    	startActivity(intent);                
	    }
	});

     
} 


	// Subject , body
	public void sendEmail( String subject , String body )
	{
	    String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", 
	    		subject, body };
	
	    SendEmail task = new SendEmail();
	    task.execute(params);            	
	}  
}
