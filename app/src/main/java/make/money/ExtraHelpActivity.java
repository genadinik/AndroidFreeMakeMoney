package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class ExtraHelpActivity extends BaseActivity
{
	   @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.extra_help);

		   	Button business_ideas_app = (Button)findViewById(R.id.business_ideas_app);  // DONE
	        Button business_plan_app = (Button)findViewById(R.id.business_plan_app);     // DONE   
	       	Button marketing_app = (Button)findViewById(R.id.marketing_app); 
	       	Button fundraising_app = (Button)findViewById(R.id.fundraising_app); 
	        
	       	Button free_plan_app = (Button)findViewById(R.id.free_plan_app); 
	       	Button free_ideas_app = (Button)findViewById(R.id.free_ideas_app); 
	       	
	       	free_plan_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=com.problemio"));
	            	startActivity(intent);
	            }
	        });         
	       	
	       	free_ideas_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=business.ideas"));
	            	startActivity(intent);
	            }
	        });         	       	
	       	
	       	
	       	fundraising_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=com.fundraising"));
	            	startActivity(intent);
	            }
	        });         
	       	
	       	business_ideas_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=com.businessideas"));
	            	startActivity(intent);
	            }
	        });         
	        

	       	business_plan_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=business.premium"));
	            	startActivity(intent);
	            }
	        });   
	        
	       	marketing_app.setOnClickListener(new Button.OnClickListener() 
	        {  
	            @Override
				public void onClick(View v) 
	            {            	
	                Intent intent = new Intent(Intent.ACTION_VIEW);
	            	intent.setData(Uri.parse("market://details?id=com.marketingpremium"));
	            	startActivity(intent); 
	            }
	        });           
	        
	        
//	        Button give_review = (Button)findViewById(R.id.give_review);
//	        give_review.setOnClickListener(new Button.OnClickListener() 
//	        {  
//	            @Override
//				public void onClick(View v) 
//	            {            	
//	                Intent intent = new Intent(Intent.ACTION_VIEW);
//	            	intent.setData(Uri.parse("market://details?id=com.fundraising"));
//	            	startActivity(intent); 
//	            }
//	        });          	        
	    }

	    // Subject , body
	    public void sendEmail( String subject , String body )
	    {
	        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

	        SendEmail task = new SendEmail();
	        task.execute(params);            	
	    }    
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_main, menu);
	        return true;
	    }
}
