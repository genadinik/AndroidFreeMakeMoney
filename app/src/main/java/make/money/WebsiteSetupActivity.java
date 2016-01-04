package make.money;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.pm.PackageManager;
import android.content.Context;


public class WebsiteSetupActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
        //setTheme(R.style.Theme_Sherlock_Light);
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.content_web_setup);
	    
//        mSelected = (TextView)findViewById(R.id.text);
//        mLocations = getResources().getStringArray(R.array.locations);
//                
//        Context context = getSupportActionBar().getThemedContext();
//        
//        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(
//        		context, R.array.locations, R.layout.sherlock_spinner_item);
//        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
//
//        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//        getSupportActionBar().setListNavigationCallbacks(list, this);        	    
	    
        
        
		Button choosing_domain = (Button)findViewById(R.id.choosing_domain);
		choosing_domain.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	        	          	
	            Intent myIntent = new Intent(WebsiteSetupActivity.this, ChooseDomainNameActivity.class);
	            WebsiteSetupActivity.this.startActivity(myIntent);

	        }
	    }); 	    

		
		
	    
	    
//	    Button web_marketing = (Button)findViewById(R.id.web_marketing);
//	    web_marketing.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {	                  	        
//	            Intent myIntent = new Intent(WebsiteSetupActivity.this, WebAdvertisingActivity.class);
//	            WebsiteSetupActivity.this.startActivity(myIntent);	
//            }
//        });	    
	    
	    
	    
	    
	    
	      Button plan_button = (Button)findViewById(R.id.plan_app); 
	      plan_button.setOnClickListener(new Button.OnClickListener() 
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

	      
	      
	    
	    Button bluehost = (Button)findViewById(R.id.bluehost);
	    bluehost.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	            	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
              		  Uri.parse("http://www.kqzyfj.com/click-7252177-10383360"));

				boolean isChromeInstalled = isPackageInstalled("com.android.chrome", WebsiteSetupActivity.this);
				if (isChromeInstalled) {
					browserIntent.setPackage("com.android.chrome");
					startActivity(browserIntent);
				}else{
					Intent chooserIntent = Intent.createChooser(browserIntent, "Select Application");
					startActivity(chooserIntent);
				}

                startActivity(browserIntent);
            }
        });




//	  web_content_squarespace.setOnClickListener(new Button.OnClickListener() 
//	  {  
//	      public void onClick(View v) 
//	      {	      	          	
//              Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//            		  Uri.parse("http://squarespace.7eer.net/c/35378/38409/1291"));
//              
//              startActivity(browserIntent);
//	      }
//	  });
	  
   
	    
	    
	    
	  
	    Button give_review = (Button)findViewById(R.id.give_review);   
	    give_review.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {
            	
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=make.money"));
            	//startActivity(intent);
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {            		
                     try
                     {
                    	 Uri uri = Uri.parse("market://search?q=pname:make.money");
                    	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                    	 startActivity(next_intent);  
                     }
                     catch ( Exception e)
                     {
                         // Now try to redirect them to the web version:
                         Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=make.money");
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
        
	    Button premium_marketing = (Button)findViewById(R.id.premium_marketing);
	    premium_marketing.setOnClickListener(new Button.OnClickListener() 
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

	private boolean isPackageInstalled(String packagename, Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	@Override
    protected void onStart()
    {
    	super.onStart();
    }
     
    @Override
    protected void onStop()
    {
    	super.onStop();		
    }   
    
}
