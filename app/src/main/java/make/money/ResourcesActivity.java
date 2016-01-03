package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ResourcesActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);	    
	    setContentView(R.layout.resources);
	    	 
        Button lendio_button = (Button)findViewById(R.id.lendio_button);
        lendio_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("https://www.lendio.com/sign_up/affiliate?affId=93697065"));
  	            startActivity(browserIntent);
            }
        });
        
        Button curadebt_button = (Button)findViewById(R.id.curadebt_button);
        curadebt_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://traktum.com/?a=66786&c=213962&s1="));
  	            startActivity(browserIntent);
            }
        });
        
        
        Button beyond_button = (Button)findViewById(R.id.beyond_button);
        beyond_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://www.kqzyfj.com/click-7252177-10386766"));
  	            startActivity(browserIntent);
            }
        });
        
        Button jungle = (Button)findViewById(R.id.jungle);          
        jungle.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
              Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
            		  Uri.parse("http://www.startupjungle.com/sign-up1.html"));
              
              startActivity(browserIntent);
            }
        }); 
        
        
        Button shopping_cart_button = (Button)findViewById(R.id.shopping_cart_button);
        shopping_cart_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://traktum.com/?a=66786&c=305146&s1="));
  	            startActivity(browserIntent);
            }
        });
        
        
        Button bluehost_button = (Button)findViewById(R.id.bluehost_button);
        bluehost_button.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	          	
  	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
  	          		  Uri.parse("http://www.kqzyfj.com/click-7252177-10383360"));
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

    @Override
    public void onResume ()
    {
    	super.onResume();
    }
}
