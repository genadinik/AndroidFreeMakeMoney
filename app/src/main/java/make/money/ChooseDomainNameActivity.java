package make.money;

import utils.SendEmail;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ChooseDomainNameActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.content_domain_name);
	    
	    Button give_review = (Button)findViewById(R.id.give_review);   
	    give_review.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {
            	
                Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(Uri.parse("market://details?id=make.money"));
            	//startActivity(intent);
            	            	 
            	 try 
            	 {
            	        startActivity(intent);
            	 } 
            	 catch (ActivityNotFoundException anfe) 
            	 {            		
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

                         }
                     }
            	 }           	            	
            }
        });

	    
	    
      Button godaddy = (Button)findViewById(R.id.godaddy);          
      godaddy.setOnClickListener(new Button.OnClickListener() 
      {  
          public void onClick(View v) 
          {	            	
               //sendEmail("Free Ideas -> GoDaddy Check domain", "" );   	
        	
	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
	          		  Uri.parse("http://www.jdoqocy.com/click-7252177-10985823"));
	            startActivity(browserIntent);
          }
      });   
	}

    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

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
}
