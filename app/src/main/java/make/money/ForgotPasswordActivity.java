package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import utils.SendEmail;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ForgotPasswordActivity extends BaseActivity
{
	EditText emailask;

    @Override
	public void onCreate(Bundle savedInstanceState) 
    {
	    super.onCreate(savedInstanceState);	    
	    setContentView(R.layout.forgot_password);        
    
	    TextView emailtext = (TextView) findViewById(R.id.email_ask);
	    emailask = (EditText) findViewById(R.id.login_email);

	    Button submit = (Button)findViewById(R.id.submit);   
            
	    submit.setOnClickListener(new Button.OnClickListener() 
	    {  
	 	   public void onClick(View v) 
	 	   {
			  Toast.makeText(getApplicationContext(), "Sending your new password. Please wait...", Toast.LENGTH_LONG).show();	
	 		  
			  String email = emailask.getText().toString(); 			  

              if ( email == null || email.length() < 2 )
              {
    			  Toast.makeText(getApplicationContext(), "Please enter the email address that is used for your login.", Toast.LENGTH_LONG).show();	            	  
              }
              else
              {
            	  sendFeedback(email);   
              }
	 	   }
	    });        
    }
    
    public void sendFeedback(String email) 
    {  
        String[] params = new String[] { "https://www.problemio.com/auth/forgot_password_mobile.php", email };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }          
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String myEmail = theParams[1];
	      
	        String charset = "UTF-8";	        
	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("email=%s", 
		        	     URLEncoder.encode(myEmail, charset) );

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setRequestProperty("login", myEmail);
		        conn.setDoOutput(true);
		        
		        conn.setUseCaches(false);
		        
		        conn.connect();
		        
		        final InputStream is = conn.getInputStream();
		        final byte[] buffer = new byte[8196];
		        int readCount;
		        final StringBuilder builder = new StringBuilder();
		        while ((readCount = is.read(buffer)) > -1) 
		        {
		            builder.append(new String(buffer, 0, readCount));
		        }

		        response = builder.toString();		
			} 
			catch (Exception e) 
			{
	 		      //sendEmail ( "Forgot Password Network Error" , "Error: " + e.getMessage() );
			}
			
			return response;
		}
    
		@Override
		protected void onPostExecute(String result) 
		{	        	        
	        if ( result != null && result.equals( "error_querying_for_email") )
	        {	        
		        Toast.makeText(getApplicationContext(), "The email you entered does not match out records. Please try again or create and account.", Toast.LENGTH_LONG).show();		        
	        }	        
	        else
	        if ( result != null && result.equals( "email_does_not_match_record") )
	        {	        
		        Toast.makeText(getApplicationContext(), "The email you entered does not match out records. Please try again or create and account.", Toast.LENGTH_LONG).show();		        
	        }
	        else
	        if ( result != null && result.equals( "no_such_email") )
	        {		        
		        Toast.makeText(getApplicationContext(), "The email you entered does not match out records. Please try again or create and account.", Toast.LENGTH_LONG).show();	
		    }
	        else
	        {		        
		        Toast.makeText(getApplicationContext(), "We have successfully reset your password.  Please check your email for your new password.", Toast.LENGTH_LONG).show();	
	        }
		}    
    }		
		
    @Override
	public void onStop()
    {
       super.onStop();
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
