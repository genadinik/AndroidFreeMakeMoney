package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class KeepInTouchActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.keep_in_touch);
	    
	 // YouTube sub
	 // Twitter
	 	
	 // Buy apps
	    
        Button problemio_button = (Button)findViewById(R.id.problemio_button);
        problemio_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://www.twitter.com/problemio"));
  	            startActivity(browserIntent);
            }
        });

        Button youtube_button = (Button)findViewById(R.id.youtube_button);
        youtube_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	                   	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://www.youtube.com/user/Okudjavavich"));
  	            startActivity(browserIntent);
            }
        });	    	        
        
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
                Intent myIntent = new Intent(KeepInTouchActivity.this, MainActivity.class);
                KeepInTouchActivity.this.startActivity(myIntent);
            }
        });
	    
//	    
//	    Button give_review = (Button)findViewById(R.id.give_review);   
//	    give_review.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {
//            	
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//            	intent.setData(Uri.parse("market://details?id=business.ideas"));
//            	//startActivity(intent);
//            	            	 
//            	 try 
//            	 {
//            	        startActivity(intent);
//            	 } 
//            	 catch (ActivityNotFoundException anfe) 
//            	 {            		
//                     try
//                     {
//                    	 Uri uri = Uri.parse("market://search?q=pname:com.marketing");
//                    	 Intent next_intent = new Intent(Intent.ACTION_VIEW, uri);
//                    	 startActivity(next_intent);  
//                     }
//                     catch ( Exception e)
//                     {
//                         // Now try to redirect them to the web version:
//                         Uri weburi = Uri.parse("https://play.google.com/store/apps/details?id=com.marketing");
//                         try
//                         {
//                        	 Intent webintent = new Intent(Intent.ACTION_VIEW, weburi);
//                        	 startActivity(webintent);
//                         }
//                         catch ( Exception except )
//                         {
//
//                         }
//                     }
//            	 }           	            	
//            }
//        });

	    
	    
//      Button godaddy = (Button)findViewById(R.id.godaddy);          
//      godaddy.setOnClickListener(new Button.OnClickListener() 
//      {  
//          public void onClick(View v) 
//          {	            	
//               //sendEmail("Free Ideas -> GoDaddy Check domain", "" );   	
//        	
//	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//	          		  Uri.parse("http://www.jdoqocy.com/click-7252177-10985823"));
//	            startActivity(browserIntent);
//          }
//      });
	    
	    
//        Button support_app = (Button)findViewById(R.id.support_app);          
//        support_app.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {	            	
//                Intent myIntent = new Intent(ChooseDomainNameActivity.this, SupportAppActivity.class);
//                ChooseDomainNameActivity.this.startActivity(myIntent);
//            }
//        });	 
	    
	    
	    // http://www.jdoqocy.com/click-7252177-10985823
	    
	}

    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
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
