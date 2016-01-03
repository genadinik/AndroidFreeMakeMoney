package make.money;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PremiumIdeasActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.content_premium_ideas);

        Button jungle = (Button)findViewById(R.id.jungle);          
        jungle.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
              Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
            		  Uri.parse("http://www.startupjungle.com/sign-up1.html"));
              
              startActivity(browserIntent);
            }
        }); 	    
	    
	    
//        Button shoppingcart_button = (Button)findViewById(R.id.shoppingcart_button); 
//        shoppingcart_button.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {            	
//  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//    	          		  Uri.parse("http://www.kqzyfj.com/click-7252177-11134782"));
//    	            startActivity(browserIntent);
//            }
//        }); 	    
	    
        Button website_setup = (Button)findViewById(R.id.website_setup); 
        website_setup.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(PremiumIdeasActivity.this, WebsiteSetupActivity.class);
              PremiumIdeasActivity.this.startActivity(myIntent);
            }
        }); 

        Button website_promotion = (Button)findViewById(R.id.website_promotion); 
        website_promotion.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	
              Intent myIntent = new Intent(PremiumIdeasActivity.this, KeepInTouchActivity.class);
              PremiumIdeasActivity.this.startActivity(myIntent);
            }
        });        
        
	    
	    
	    Button premium_marketing_app = (Button)findViewById(R.id.premium_marketing_app);   
	    premium_marketing_app.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                	intent.setData(Uri.parse("market://details?id=com.marketingpremium"));
                	            	 
                	 try 
                	 {
                	        startActivity(intent);
                	 } 
                	 catch (ActivityNotFoundException anfe) 
                	 {                		
                         try
                         {
                        	 Uri uri = Uri.parse("market://search?q=pname:com.marketingpremium");
                        	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                        	 startActivity(next_intent);  
                         }
                         catch ( Exception e)
                         {
                             // Now try to redirect them to the web version:
                             Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.marketingpremium");
                             try
                             {
                            	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
                            	 startActivity(webintent);
                             }
                             catch ( Exception except )
                             {
                                 //sendEmail("Give Review (2) ERROR", "User clicked on web version of give review and this is the exception: " + except.getMessage() );   	                    	 
                             }
                         }
                	 }           	
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
       // your code
    }
    
    @Override
    public void onResume ()
    {
    	super.onResume();
    }    
}
