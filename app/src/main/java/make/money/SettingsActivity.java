package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SettingsActivity extends BaseActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.settings);

        // Get Shared Preferences.
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( SettingsActivity.this);
	    String user_id = prefs.getString( "user_id" , null );        
        
     	Button create_profile = (Button)findViewById(R.id.create_profile);  
        Button login = (Button)findViewById(R.id.login);  
        Button fill_out_info = (Button)findViewById(R.id.fill_out_info);  
        Button different_user = (Button)findViewById(R.id.different_user);
        
        if ( user_id == null )
        {
        	fill_out_info.setVisibility(View.GONE);        
        	different_user.setVisibility(View.GONE);                     	
        	
	           login.setOnClickListener(new Button.OnClickListener() 
	           {  
	               public void onClick(View v) 
	               {            	       	        
	                 Intent myIntent = new Intent(SettingsActivity.this, LoginActivity.class);
	                 SettingsActivity.this.startActivity(myIntent);
	               }
	           });   	    
	           
	           create_profile.setOnClickListener(new Button.OnClickListener() 
	           {  
	               public void onClick(View v) 
	               {            	       	        
	                 Intent myIntent = new Intent(SettingsActivity.this, CreateProfileActivity.class);
	                 SettingsActivity.this.startActivity(myIntent);
	               }
	           }); 	           
        }
        else
        {
        	// Not logged in
	       	create_profile.setVisibility(View.GONE);        
 	        login.setVisibility(View.GONE);        

 	        fill_out_info.setOnClickListener(new Button.OnClickListener() 
            {  
               public void onClick(View v) 
               {                 	
                    Intent myIntent = new Intent(SettingsActivity.this, UpdateProfileActivity.class);
                    SettingsActivity.this.startActivity(myIntent);
               }
            });  	        

	           Button diff_user = (Button)findViewById(R.id.different_user);  
	           diff_user.setOnClickListener(new Button.OnClickListener() 
	           {  
	               public void onClick(View v) 
	               {            	        	   	               
	                   Intent myIntent = new Intent( SettingsActivity.this, LoginActivity.class);
	                   SettingsActivity.this.startActivity(myIntent);              
	               }
	           }); 	            	        
        }

        
         
        Button give_feedback = (Button)findViewById(R.id.give_feedback);  
        give_feedback.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	        	               
                Intent myIntent = new Intent( SettingsActivity.this, FeedbackActivity.class);
                SettingsActivity.this.startActivity(myIntent);              
            }
        });           
        
        Button home = (Button)findViewById(R.id.home);  
        home.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {            	        	               
                Intent myIntent = new Intent( SettingsActivity.this, MainActivity.class);
                SettingsActivity.this.startActivity(myIntent);              
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

    @Override
	public void onStop()
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
