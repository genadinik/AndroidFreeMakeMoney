package utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import android.os.AsyncTask;
import android.util.Log;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SendEmail extends AsyncTask<String, Void, String> 
{
	@Override
	protected String doInBackground(String... theParams) 
	{
        String myUrl = theParams[0];
        final String subject = theParams[1];
        final String body = theParams[2];
      
        String charset = "UTF-8";	        
        String response = null;
        
		try 
		{		        
	        String query = String.format("subject=%s&body=%s", 
	        	     URLEncoder.encode(subject, charset), 
	        	     URLEncoder.encode(body, charset));

	        final URL url = new URL( myUrl + "?" + query );
	        		        
	        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        conn.setDoOutput(true); 
	        conn.setRequestMethod("POST");
	        
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
				e.printStackTrace();
		}
		
		return response;
	}

	
	
	@Override
	protected void onPostExecute(String result) 
	{
        //Log.d( "SendEmail" , "After sending email: " + result );
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
