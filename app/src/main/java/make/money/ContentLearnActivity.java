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
import android.widget.TextView;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ContentLearnActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.learn);
	        
        Button donations_button = (Button)findViewById(R.id.donations_button);          
        Button grants_button = (Button)findViewById(R.id.grants_button);          
        Button loans_button = (Button)findViewById(R.id.loans_button);          
        Button investments_button = (Button)findViewById(R.id.investments_button);          
        
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
        
        grants_button.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentGrantsActivity.class);
	            ContentLearnActivity.this.startActivity(myIntent);
	        }
	    }); 

        loans_button.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentLoansActivity.class);
	            ContentLearnActivity.this.startActivity(myIntent);
	        }
	    }); 
        
        investments_button.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentInvestmentActivity.class);
	            ContentLearnActivity.this.startActivity(myIntent);
	        }
	    }); 
        
        donations_button.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {		        			        	
	            Intent myIntent = new Intent(ContentLearnActivity.this, ContentDonationActivity.class);
	            ContentLearnActivity.this.startActivity(myIntent);
	        }
	    });         
        
        
        
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
       
	    Button plan_fundraising = (Button)findViewById(R.id.plan_fundraising);          
	    plan_fundraising.setOnClickListener(new Button.OnClickListener() 
	    {  
	        public void onClick(View v) 
	        {	        	
	            Intent myIntent = new Intent(ContentLearnActivity.this, AddPlanActivity.class);
	            ContentLearnActivity.this.startActivity(myIntent);
	        }
	    });         
    
    


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
	    String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php",
	    		subject, body };
	
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
