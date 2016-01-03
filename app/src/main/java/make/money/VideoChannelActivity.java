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

public class VideoChannelActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.video_channel);
	    

	    
	    Button make_money_video = (Button)findViewById(R.id.make_money_video); 	    
	    make_money_video.setOnClickListener(new Button.OnClickListener() 
        {  
            @Override
			public void onClick(View v) 
            {            	
//              Intent myIntent = new Intent(VideoChannelActivity.this, MakeMoneyVideoActivity.class);
//              VideoChannelActivity.this.startActivity(myIntent);
            	
	        	  // Add url_to_watch in local storage and go to video screen
	      	    SharedPreferences prefs = 
	      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

		        prefs.edit()        
		        .putString("url_to_watch", "http://www.youtube.com/watch?v=eHZ1b6KSryk")
		        .commit();

	              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
	              VideoChannelActivity.this.startActivity(myIntent);            	
            }
        }); 
        
	      Button sub_button = (Button)findViewById(R.id.sub_button);
	      sub_button.setOnClickListener(new Button.OnClickListener() 
	      {  
	          public void onClick(View v) 
	          {	            	 
		      	    SharedPreferences prefs = 
		      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

			        prefs.edit()        
			        .putString("url_to_watch", "https://www.youtube.com/user/Okudjavavich/videos")
			        .commit();
// https://www.youtube.com/user/Okudjavavich/videos
		              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
		              VideoChannelActivity.this.startActivity(myIntent); 
	        	  
//	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//	  	          		  Uri.parse("http://www.youtube.com/user/Okudjavavich"));
//	  	            startActivity(browserIntent);
	          }
	      });	    
	      
	      
	      
	    	  
		      Button recurring = (Button)findViewById(R.id.recurring);
	          recurring.setOnClickListener(new Button.OnClickListener() 
		      {  
		          public void onClick(View v) 
		          {	            	          	
		        	  // Add url_to_watch in local storage and go to video screen
		      	    SharedPreferences prefs = 
		      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

			        prefs.edit()        
			        .putString("url_to_watch", "http://youtu.be/0vNbAvo14iI")
			        .commit();

		              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
		              VideoChannelActivity.this.startActivity(myIntent);
		          }
		      });	       

		      Button rev_biz = (Button)findViewById(R.id.rev_biz);
		      rev_biz.setOnClickListener(new Button.OnClickListener() 
		      {  
		          public void onClick(View v) 
		          {	            	          	
		        	  // Add url_to_watch in local storage and go to video screen
		      	    SharedPreferences prefs = 
		      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

			        prefs.edit()        
			        .putString("url_to_watch", "http://youtu.be/gxeXrasXy6Q")
			        .commit();

		              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
		              VideoChannelActivity.this.startActivity(myIntent);
		          }
		      });	          
	          
		      
		    	  
			      Button creative = (Button)findViewById(R.id.creative);
		          creative.setOnClickListener(new Button.OnClickListener() 
			      {  
			          public void onClick(View v) 
			          {	            	          	
			        	  // Add url_to_watch in local storage and go to video screen
			      	    SharedPreferences prefs = 
			      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

				        prefs.edit()        
				        .putString("url_to_watch", "http://youtu.be/tsA1VrJskD0")
				        .commit();

			              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
			              VideoChannelActivity.this.startActivity(myIntent);
			          }
			      });	          
		          
		          
		          
		        	  
				  Button crowdfunding = (Button)findViewById(R.id.crowdfunding);
		          crowdfunding.setOnClickListener(new Button.OnClickListener() 
			      {  
			          public void onClick(View v) 
			          {	            	          	
			        	  // Add url_to_watch in local storage and go to video screen
			      	    SharedPreferences prefs = 
			      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

				        prefs.edit()        
				        .putString("url_to_watch", "http://youtu.be/xKn0ts74fRo")
				        .commit();

			              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
			              VideoChannelActivity.this.startActivity(myIntent);
			          }
			      });	
		          
				  Button grants = (Button)findViewById(R.id.grants);
				  grants.setOnClickListener(new Button.OnClickListener() 
			      {  
			          public void onClick(View v) 
			          {	            	          	
			        	  // Add url_to_watch in local storage and go to video screen
			      	    SharedPreferences prefs = 
			      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

				        prefs.edit()        
				        .putString("url_to_watch", "http://youtu.be/Uq4in_GLnAU")
				        .commit();

			              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
			              VideoChannelActivity.this.startActivity(myIntent);
			          }
			      });			          
		          
				  Button ideas = (Button)findViewById(R.id.ideas);
				  ideas.setOnClickListener(new Button.OnClickListener() 
			      {  
			          public void onClick(View v) 
			          {	            	          	
			        	  // Add url_to_watch in local storage and go to video screen
			      	    SharedPreferences prefs = 
			      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

				        prefs.edit()        
				        .putString("url_to_watch", "http://youtu.be/0lGgUtF4JjU")
				        .commit();

			              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
			              VideoChannelActivity.this.startActivity(myIntent);
			          }
			      });	
				  
				  
					  
					  Button loans = (Button)findViewById(R.id.loans);
				      loans.setOnClickListener(new Button.OnClickListener() 
				      {  
				          public void onClick(View v) 
				          {	            	          	
				        	  // Add url_to_watch in local storage and go to video screen
				      	    SharedPreferences prefs = 
				      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

					        prefs.edit()        
					        .putString("url_to_watch", "http://youtu.be/PH7Q2nuYZ1o")
					        .commit();

				              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
				              VideoChannelActivity.this.startActivity(myIntent);
				          }
				      });
					  
					  Button freemium = (Button)findViewById(R.id.freemium);
					  freemium.setOnClickListener(new Button.OnClickListener() 
				      {  
				          public void onClick(View v) 
				          {	            	          	
				        	  // Add url_to_watch in local storage and go to video screen
				      	    SharedPreferences prefs = 
				      	    		PreferenceManager.getDefaultSharedPreferences( VideoChannelActivity.this);

					        prefs.edit()        
					        .putString("url_to_watch", "http://youtu.be/8IqWwNLHIsU")
					        .commit();

				              Intent myIntent = new Intent(VideoChannelActivity.this, YoutubeActivity.class);
				              VideoChannelActivity.this.startActivity(myIntent);
				          }
				      });				      
				      
				    	  
					  
					  //	Button give_review = (Button)findViewById(R.id.give_review);   
//	give_review.setOnClickListener(new Button.OnClickListener() 
//	{  
//	    public void onClick(View v) 
//	    {	    	
//	        Intent intent = new Intent(Intent.ACTION_VIEW);
//	    	intent.setData(Uri.parse("market://details?id=make.money"));
//	    	startActivity(intent);                
//	    }
//	});
    
} 


	// Subject , body
	public void sendEmail( String subject , String body )
	{
	    String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", 
	    		subject, body };
	
	    SendEmail task = new SendEmail();
	    task.execute(params);            	
	}  
}
