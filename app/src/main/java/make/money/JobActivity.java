package make.money;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.jobs);
	    
	    Button beyond_jobs = (Button)findViewById(R.id.beyond_jobs);          
	    beyond_jobs.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	      
                //sendEmail("Money -> Beyond.com", "" );   	
            	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
              		  Uri.parse("http://www.kqzyfj.com/click-7252177-10386766"));
                startActivity(browserIntent);
	        	
	        }
	    }); 

//	    Button rabbit = (Button)findViewById(R.id.snag);          
//	    rabbit.setOnClickListener(new Button.OnClickListener() 
//	    {  
//	        public void onClick(View v) 
//	        {	      
//            	
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//              		  Uri.parse("http://www.anrdoezrs.net/click-7252177-10435412"));
//                startActivity(browserIntent);
//	        	
//	        }
//	    });	    
	    
	    Button eresume = (Button)findViewById(R.id.eresume);          
	    eresume.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	      
            	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
              		  Uri.parse("http://traktum.com/?a=66786&c=213962&s1="));
                startActivity(browserIntent);
	        	
	        }
	    });	    	    
	    
    
//	    Button biz_app = (Button)findViewById(R.id.biz_app);          
//	    biz_app.setOnClickListener(new Button.OnClickListener() 
//	    {
//	          public void onClick(View v) 
//	          {	 		              
//	            Intent intent = new Intent(Intent.ACTION_VIEW);
//	        	intent.setData(Uri.parse("market://details?id=com.problemio"));
//	        	            	 
//	        	 try 
//	        	 {
//	        	        startActivity(intent);
//	        	 } 
//	        	 catch (ActivityNotFoundException anfe) 
//	        	 {
//	                 try
//	                 {
//	                	 Uri uri = Uri.parse("market://search?q=pname:com.problemio");
//	                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
//	                	 startActivity(next_intent);  
//	                 }
//	                 catch ( Exception e)
//	                 {
//	                     // Now try to redirect them to the web version:
//	                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.problemio");
//	                     try
//	                     {
//	                    	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
//	                    	 startActivity(webintent);
//	                     }
//	                     catch ( Exception except )
//	                     {
//
//	                     }
//	                 }
//	        	 }           	            	
//	        }
//	    });  	    
//
//	
//	Button give_review = (Button)findViewById(R.id.give_review);   
//	give_review.setOnClickListener(new Button.OnClickListener() 
//	{  
//	    public void onClick(View v) 
//	    {
//	        //sendEmail("Learn --> Give Review", "From learn page, user clicked on give review" );   	
//	    	
//	        Intent intent = new Intent(Intent.ACTION_VIEW);
//	    	intent.setData(Uri.parse("market://details?id=make.money"));
//	    	startActivity(intent);                
//	    }
//	});

     
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
