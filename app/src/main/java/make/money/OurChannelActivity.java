package make.money;

import utils.SendEmail;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class OurChannelActivity extends BaseActivity
{
	WebView webview = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

	    webview = new WebView(this);
	    setContentView(webview);	    
        
	    webview.getSettings().setAppCacheEnabled(false);
	    webview.getSettings().setJavaScriptEnabled(true);
	    webview.setInitialScale(1);
	    webview.getSettings().setPluginState(PluginState.ON);

	    WebSettings webSettings = webview.getSettings();
	    
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);        
        //webSettings.setAllowContentAccess(true);
        //webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
                
	    webview.setWebViewClient(new WebViewClient() {
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	            view.loadUrl(url);	            
	            return false;
	        }
	    });        
        
	    
	    webview.setWebChromeClient(new WebChromeClient(){});
	    
	    webSettings.setJavaScriptEnabled(true);
	    webSettings.setDomStorageEnabled(true);
	    webSettings.setAppCacheEnabled(true);
	    webSettings.setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
	    webSettings.setDatabaseEnabled(true);
	    webSettings.setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");
	    
	    
		webview.loadUrl("http://www.youtube.com/user/Okudjavavich");   

	    
    }
    

    
    

    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }        
    
    @Override
	public void onPause()
    {
       super.onPause();
       // your code
       
       webview.clearCache(true); 
       webview.getSettings().setAppCacheEnabled(false);
       webview.clearView();
       webview.stopLoading();
       webview.destroy();
       this.finish();
    }
    
    
    
    @Override
	public void onDestroy()
    {
       super.onDestroy();
       // your code
       
       try
       {
    	   webview.getSettings().setBuiltInZoomControls(true);
      
       }
       catch ( Exception e )
       {
    	   //sendEmail ("Podcast error" , e.getMessage() + "" );
       }	
    }
    
    @Override
	public void onStop()
    {
       super.onStop();
       // your code
       
       //webview.goBack();
       try
       {
    	   webview.clearView();
           webview.getSettings().setAppCacheEnabled(false);
           webview.stopLoading();
           webview.destroy();
       }
       catch ( Exception e )
       {
    	   //sendEmail ("Podcast error" , e.getMessage() + "" );
       }
       
       this.finish();
    }	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) 
	    {
	        webview.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}	
	
	//@Override 
	public void onPageFinished(WebView view, String url) 
	{ 
		//super.onPageFinished(view, url); 
		view.clearCache(true); 
	}
	
	public void onBackPressed ( )
	{
		//Class.forName("com.***.HTML5WebView").getMethod("onPause", (Class[]) null).
		//invoke(html5WebView, (Object[]) null);
		webview.clearView();
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
