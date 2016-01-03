package make.money;

import java.lang.reflect.InvocationTargetException;

import utils.SendEmail;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;


public class YoutubeActivity extends Activity
{
	private WebView webview = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    
	    webview = new WebView(this);

//	    if(webview != null)
//	    {
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
		    
		    webSettings.setDomStorageEnabled(true);
		    webSettings.setAppCacheEnabled(true);
		    
		    // OLD
		    webSettings.setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
		    
		    
		    
		    webSettings.setDatabaseEnabled(true);
		    
		    // OLD
		    webSettings.setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");
		 
		    
		    //webSettings.setDatabasePath(
		    	//	getDatabasePath("WeJustNeedANameHereForAMoment").getParentFile());
		    
		    //webSettings.setDatabasePath(getDatabasePath("WeJustNeedANameHereForAMoment").getParentFile());
		    
		    
		    //2 BELOW LINES WERE GOOD
		    //webSettings.setAppCachePath(getApplicationContext().getCacheDir().getPath());
		    //webSettings.setAppCacheEnabled(true);
		    
		    
//		    And get rid of the empty WebChromeClient,
		    //the opposite-and-redundant setAppCacheEnabled() calls,
		    
		    
      	    SharedPreferences prefs = 
      	    		PreferenceManager.getDefaultSharedPreferences( YoutubeActivity.this);
            String url_to_watch = prefs.getString( "url_to_watch" , null );	
            
		    
            webview.loadUrl(url_to_watch);
            
			//webview.loadUrl("http://www.youtube.com/user/Okudjavavich");    	    	
//	    } 
//	    else
//	    {
//	     	final AlertDialog.Builder linkedin_builder = new AlertDialog.Builder(this);
//           	
//	            	 linkedin_builder.setMessage("" +
//	            	 		"Note: Something went wrong and your device and/or Android version is having difficuties. Please watch our YouTube channel on the web at this url: http://www.youtube.com/user/Okudjavavich")
//	 	            .setCancelable(false)            
//	 	            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//	 	                public void onClick(DialogInterface dialog, int id) {
//	 	                                     
//	 	    	        //    Intent myIntent = new Intent(MainActivity.this, YoutubeActivity.class);
//	 	    	        //    MainActivity.this.startActivity(myIntent);
//	 	                }
//	 	            })
//	 ;
//	 	     AlertDialog alert = linkedin_builder.create();
//	 	     alert.show();	        	
//	  
//	    }	    
	    
	    
	    
	    

	    
//	    long timeout = ViewConfiguration.getZoomControlsTimeout();
//	    new Timer().schedule(new TimerTask() {
//	        @Override
//	        public void run() {
//	            webview.destroy();
//	        }
//	    }, timeout);
	    
	    

	    
	}
	
   
    
//    @Override
//	public void onPause()
//    {
//       super.onPause();
//       // your code
//       
//       try
//       {
//    	   if ( webview != null )
//    	   {
//    	       webview.clearCache(true); 
//    	       webview.getSettings().setAppCacheEnabled(false);
//    	       webview.stopLoading();
//    	       webview.destroy();    		
//
//    	       //webview = new WebView(this);
//    	   }
//
//	       this.finish();
//       }
//       catch ( Exception e )
//       {
//
//       }
//    }
    

    @Override
    public void onPause() {
        super.onPause();

        try {
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                                .invoke(webview, (Object[]) null);

        } 
        catch(ClassNotFoundException cnfe) 
        {
           // ...
        } catch(NoSuchMethodException nsme) {
            //...
        } catch(InvocationTargetException ite) {
           // ...
        } catch (IllegalAccessException iae) {
           // ...
        }
    }    
    
    
    
    @Override
	public void onDestroy()
    {
       super.onDestroy();
       // your code
       
       try
       {
    	   if ( webview != null )
    	   {
    		   //webview = new WebView(this);
    		   
    		   
    		   //webview.getSettings().setBuiltInZoomControls(true);
    	   }
       }
       catch ( Exception e )
       {

       }	
    }
    
    
	// onDestroy - yes
	// onStop - yes
	// onPause - yes
	
	// onCreate - initial method
    
	// onStart
	// onRestart
	// onResume
    
    @Override
	public void onStop()
    {
       super.onStop();
       // your code
              
       //webview.goBack();
       try
       {
    	   if ( webview != null )
    	   {
	    	   //webview.clearView();
	           webview.getSettings().setAppCacheEnabled(false);
	           webview.stopLoading();
	           webview.destroy();
	           
    	   }
       }
       catch ( Exception e )
       {

       }
       
       this.finish();
    }	
	
    @Override
    protected void onResume() 
    {
        super.onResume();
        
//        try
//        {
//        	webview.onResume();
//        }
//        catch ( Exception  e )
//        {
//        	
//        }
    }
    
    @Override
    protected void onRestart() 
    {
        super.onRestart();
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
	
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }  
	
	
	//@Override 
	public void onPageFinished(WebView view, String url) 
	{ 
		
		view.clearCache(true);
		view.stopLoading();
		view.removeAllViews();
	}
	
	public void onBackPressed ( )
	{
		//Class.forName("com.***.HTML5WebView").getMethod("onPause", (Class[]) null).
		//invoke(html5WebView, (Object[]) null);
		//webview.clearView();
		//sendEmail ("back pressed" , "");
		
     	final AlertDialog.Builder linkedin_builder = new AlertDialog.Builder(this);
   	
        	 linkedin_builder.setMessage("" +
        	 		"Go back to the home screen?")
	            .setCancelable(false)            
	            .setNegativeButton("YES", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) 
	                {
	                	if ( webview != null )
	                	{
	                		//webview.onPause();
	                		webview.loadUrl("file:///android_asset/nonexistent.html");
	                		//webview.destroy();
	                	}
	                	
	    	            Intent myIntent = new Intent(YoutubeActivity.this, MainActivity.class);
	    	            YoutubeActivity.this.startActivity(myIntent);
	                }
	            })	            
	            .setPositiveButton("NO", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) 
	                {
	                	dialog.cancel();                  
	                }
	            })
;
	     AlertDialog alert = linkedin_builder.create();
	     alert.show();				
	}

    
    @Override
    protected void onStart()
    {
    	super.onStart();
    }    
}
