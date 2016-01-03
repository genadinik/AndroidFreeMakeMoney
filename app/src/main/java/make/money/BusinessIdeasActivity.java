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

public class BusinessIdeasActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.business_ideas);
	    
	    Button plan_fundraising = (Button)findViewById(R.id.plan_fundraising);          
	    plan_fundraising.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	        	
	            Intent myIntent = new Intent(BusinessIdeasActivity.this, AddPlanActivity.class);
	            BusinessIdeasActivity.this.startActivity(myIntent);
	        }
	    });     

	    
	    
	    Button business_ideas_app = (Button)findViewById(R.id.business_ideas_app);
	    business_ideas_app.setOnClickListener(new Button.OnClickListener() 
	    {
	          public void onClick(View v) 
	          {	 		              
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=business.ideas"));
	        	            	 
	        	 try 
	        	 {
	        	        startActivity(intent);
	        	 } 
	        	 catch (ActivityNotFoundException anfe) 
	        	 {
	                 try
	                 {
	                	 Uri uri = Uri.parse("market://search?q=pname:business.ideas");
	                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
	                	 startActivity(next_intent);  
	                 }
	                 catch ( Exception e)
	                 {
	                     // Now try to redirect them to the web version:
	                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=business.ideas");
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
	    
	    
	    Button premium_apps = (Button)findViewById(R.id.premium_apps);
	    premium_apps.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(BusinessIdeasActivity.this, ExtraHelpActivity.class);
	            BusinessIdeasActivity.this.startActivity(myIntent);
	        }
	    }); 
      
	    Button poll = (Button)findViewById(R.id.poll);
	    poll.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                		  Uri.parse("http://traktum.com/?a=66786&c=305146&s1="));
                  startActivity(browserIntent);
	        }
	    }); 	    
	    
	    
	    

	    Button business_plan_app = (Button)findViewById(R.id.business_plan_app);
	    business_plan_app.setOnClickListener(new Button.OnClickListener() 
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
	    
	    
//        Button donations_button = (Button)findViewById(R.id.donations_button);          
//        Button grants_button = (Button)findViewById(R.id.grants_button);          
//        Button loans_button = (Button)findViewById(R.id.loans_button);          
//        Button investments_button = (Button)findViewById(R.id.investments_button);          
//        
//        
//        grants_button.setOnClickListener(new Button.OnClickListener() 
//	    {  
//	        public void onClick(View v) 
//	        {		        			        	
//	            Intent myIntent = new Intent(BusinessIdeasActivity.this, ContentGrantsActivity.class);
//	            BusinessIdeasActivity.this.startActivity(myIntent);
//	        }
//	    }); 
//

//        investments_button.setOnClickListener(new Button.OnClickListener() 
//	    {  
//	        public void onClick(View v) 
//	        {		        			        	
//	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentInvestmentActivity.class);
//	            ContentLearnActivity.this.startActivity(myIntent);
//	        }
//	    }); 
//        
//        donations_button.setOnClickListener(new Button.OnClickListener() 
//	    {  
//	        public void onClick(View v) 
//	        {		        			        	
//	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentDonationActivity.class);
//	            ContentLearnActivity.this.startActivity(myIntent);
//	        }
//	    });         
//        
        
        
//    TextView squarespace_text = (TextView)findViewById(R.id.squarespace_text);           
//    Button squarespace_button = (Button)findViewById(R.id.squarespace_button);          
//    squarespace_button.setOnClickListener(new Button.OnClickListener() 
//    {  
//        public void onClick(View v) 
//        {
//          sendEmail("Marketing Learn -> Squarespace", "From learn page, user clicked on squarespace button" );   	
//        	
//          Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//        		  Uri.parse("http://squarespace.7eer.net/c/35378/38409/1291"));
//          
//          startActivity(browserIntent);
//        }
//    });            
       
   
    
    


	Button give_review = (Button)findViewById(R.id.give_review);   
	give_review.setOnClickListener(new Button.OnClickListener() 
	{  
	    public void onClick(View v) 
	    {	    	
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
