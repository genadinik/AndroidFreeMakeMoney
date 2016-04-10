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

public class MotivationActivity extends BaseActivity
{	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.motivation);
	    

        Button fb = (Button)findViewById(R.id.fb);          
        fb.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
            	 // 
            	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                		  Uri.parse("https://www.facebook.com/groups/problemio/"));
                  startActivity(browserIntent);

            }
        });
        
        Button twitter = (Button)findViewById(R.id.twitter);          
        twitter.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                		  Uri.parse("http://www.twitter.com/problemio"));
                  startActivity(browserIntent);

            }
        });
        
        Button newsletter = (Button)findViewById(R.id.newsletter);          
        newsletter.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                		  Uri.parse("http://glowingstart.com/email-subscribe/"));
                  startActivity(browserIntent);

            }
        });
	    
        Button youtube = (Button)findViewById(R.id.youtube);          
        youtube.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
                		  Uri.parse("http://www.youtube.com/user/Okudjavavich"));
                  startActivity(browserIntent);

            }
        });    
       
	}
	
	
	
	

	
	
	
	

		
	@Override
	public void onDestroy() 
	{
	    super.onDestroy();
//	    if (mService != null) 
//	    {
//	        unbindService(mServiceConn);
//	    }   
	}
	

    
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
