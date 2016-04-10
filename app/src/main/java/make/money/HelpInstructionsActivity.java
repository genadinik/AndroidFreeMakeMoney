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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HelpInstructionsActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.content_help_instructions);
	    	    
				
		

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
