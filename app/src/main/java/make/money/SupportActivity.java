package make.money;


import utils.SendEmail;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class SupportActivity extends BaseActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.give_back);
                 
	    Button marketing_app = (Button)findViewById(R.id.marketing_app);
	    Button fundraising = (Button)findViewById(R.id.fundraising_app);
	    
		fundraising.setOnClickListener(new Button.OnClickListener() 
 		{  
 		          public void onClick(View v) 
 		          {	 		              
 		            Intent intent = new Intent(Intent.ACTION_VIEW);
 		        	intent.setData(Uri.parse("market://details?id=com.fundraising"));
 		        	            	 
 		        	 try 
 		        	 {
 		        	        startActivity(intent);
 		        	 } 
 		        	 catch (ActivityNotFoundException anfe) 
 		        	 {
 		                 try
 		                 {
 		                	 Uri uri = Uri.parse("market://search?q=pname:com.fundraising");
 		                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
 		                	 startActivity(next_intent);  
 		                 }
 		                 catch ( Exception e)
 		                 {
 		                     // Now try to redirect them to the web version:
 		                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.fundraising");
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
 		    
 		       		      
 		      
 		      marketing_app.setOnClickListener(new Button.OnClickListener() 
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
	    	      
	    Button business_idea_app = (Button)findViewById(R.id.business_idea_app);
	    business_idea_app.setOnClickListener(new Button.OnClickListener() 
	      {  
	          public void onClick(View v) 
	          {		              
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	        	intent.setData(Uri.parse("market://details?id=com.businessideas"));
	        	            	 
	        	 try 
	        	 {
	        	        startActivity(intent);
	        	 } 
	        	 catch (ActivityNotFoundException anfe) 
	        	 {
	                 try
	                 {
	                	 Uri uri = Uri.parse("market://search?q=pname:com.businessideas");
	                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
	                	 startActivity(next_intent);  
	                 }
	                 catch ( Exception e)
	                 {
	                     // Now try to redirect them to the web version:
	                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.businessideas");
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
	    
	    Button paid_app = (Button)findViewById(R.id.paid_app);   
	    
	    
	    
	    
     	final AlertDialog.Builder t_builder = new AlertDialog.Builder(this);
//	    Button follow_on_twitter = (Button)findViewById(R.id.follow_on_twitter);
//	    follow_on_twitter.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {          	
//            t_builder.setMessage("You will be taken to our Twitter page from which you can follow us, say hello, and ask for retweet any time.")
//            .setCancelable(false)
//            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                                     
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//                  		  Uri.parse("http://www.twitter.com/problemio"));
//                    startActivity(browserIntent);
//                }
//            })
//            .setNegativeButton("No", new DialogInterface.OnClickListener() 
//            {
//                public void onClick(DialogInterface dialog, int id) 
//                {
//                     dialog.cancel();
//                }
//            });
//     AlertDialog alert = t_builder.create();
//     alert.show();
//
//          }
//      });  	    	    
	    
	    
	    
     	final AlertDialog.Builder builder = new AlertDialog.Builder(this);

	    
	    
	    //Button starting_mistakes = (Button)findViewById(R.id.starting_mistakes);   
	    //Button psychology_of_entrepreneurship = (Button)findViewById(R.id.psychology_of_entrepreneurship);   
	    //Button business_plan_examples = (Button)findViewById(R.id.business_plan_examples);   
	    //Button community_chat = (Button)findViewById(R.id.community_chat);   	    
//	    Button we_promote = (Button)findViewById(R.id.we_promote); 
	    
	    
//	    we_promote.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {	              
//              Intent myIntent = new Intent(GiveBackActivity.this, WePromoteActivity.class);
//              GiveBackActivity.this.startActivity(myIntent);
//          }
//        });	    
	    
//	    community_chat.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {	              
//              Intent myIntent = new Intent(GiveBackActivity.this, CommunityActivity.class);
//              GiveBackActivity.this.startActivity(myIntent);
//          }
//        });		    
	    
//	    business_plan_examples.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {	              
//              Intent myIntent = new Intent(GiveBackActivity.this, PlanExamplesActivity.class);
//              GiveBackActivity.this.startActivity(myIntent);
//          }
//        });	
	    
//	    psychology_of_entrepreneurship.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {	              
//              Intent myIntent = new Intent(GiveBackActivity.this, PsychologyActivity.class);
//              GiveBackActivity.this.startActivity(myIntent);
//          }
//        });	    

//	    starting_mistakes.setOnClickListener(new Button.OnClickListener() 
//        {  
//          public void onClick(View v) 
//          {	              
//              Intent myIntent = new Intent(GiveBackActivity.this, TopMistakesActivity.class);
//              GiveBackActivity.this.startActivity(myIntent);
//          }
//        });		    
	    
	    
	    
	    
//     	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//	  see_ads.setOnClickListener(new Button.OnClickListener() 
//      {  
//          public void onClick(View v) 
//          {	
//          	sendEmail("Give Back To See Ads", "" );   	
//          	
//          	// Here show instructions.
//		       builder.setMessage("Instructions: a screen with ads will appear. Please consider signing up for one of their interesting free offerings.  To get out of the takeover screen, just press the back button. Try it?")
//	              .setCancelable(false)
//	              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//	                  public void onClick(DialogInterface dialog, int id) {
//	       
//	                      Intent myIntent = new Intent(GiveBackActivity.this, AdsActivity.class);
//	                      GiveBackActivity.this.startActivity(myIntent);
//	                      
//	                      sendEmail( "See ads YES" , ":)");
//	                  }
//	              })
//	              .setNegativeButton("No", new DialogInterface.OnClickListener() 
//	              {
//	                  public void onClick(DialogInterface dialog, int id) 
//	                  {
//	                       dialog.cancel();
//	                       
//		                      sendEmail( "See ads NO" , ":)");
//	                  }
//	              });
//	       AlertDialog alert = builder.create();
//	       alert.show();		       		         
//          }
//      });	 	     
	    
	    
      paid_app.setOnClickListener(new Button.OnClickListener() 
      {  
          public void onClick(View v) 
          {	              
            Intent intent = new Intent(Intent.ACTION_VIEW);
        	intent.setData(Uri.parse("market://details?id=business.premium"));
        	            	 
        	 try 
        	 {
        	        startActivity(intent);
        	 } 
        	 catch (ActivityNotFoundException anfe) 
        	 {
                 try
                 {
                	 Uri uri = Uri.parse("market://search?q=pname:business.premium");
                	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
                	 startActivity(next_intent);  
                 }
                 catch ( Exception e)
                 {
                     // Now try to redirect them to the web version:
                     Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=business.premium");
                     try
                     {
                    	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
                    	 startActivity(webintent);
                     }
                     catch ( Exception except )
                     {
                        // sendEmail("Give Review (2) ERROR", "From advertising page, user clicked on web version of give review and this is the exception: " + except.getMessage() );   	                    	 
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
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=make.money"));
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {
                     //sendEmail("Give Review ERROR", "From show thanks page, user clicked on give review and this is the exception: " + anfe.getMessage() );   	
            		
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
                            // sendEmail("Give Review (2) ERROR", "From advertising page, user clicked on web version of give review and this is the exception: " + except.getMessage() );   	                    	 
                         }
                     }
            	 }           	            	
            }
        });        
        
    }
 
    
    

    
    
    
    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }

	TrustManager tm = new X509TrustManager()  {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		try {
			chain[0].checkValidity();
		} catch (Exception e) {
			throw new CertificateException("Certificate not valid or trusted.");
		}
	}
}
