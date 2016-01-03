package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import utils.SendEmail;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class InviteFriendsActivity extends BaseActivity
{
	String problem_id = null;
	TextView view_name; 
	
	Dialog dialog;
	Dialog send_dialog;
	
	EditText email;
	TextView email_ask;
	
	EditText friend_name;
	TextView friend_name_ask;

	EditText your_name;
	TextView your_name_ask;
	
	String promo_code = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
	    
        setContentView(R.layout.invite_friends_to_business);
                
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.please_wait);
        dialog.setTitle("Generating Your Invite Code");

        TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
        text.setText("Please wait...");
        dialog.show();        

	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
	    		  InviteFriendsActivity.this);         
        
	    // display a loading message before problem loads.
        final String recent_problem_id = prefs.getString( "recent_problem_id" , null );
                
        if ( recent_problem_id == null )
        {
        	// TODO: figure out a good way to quit
            //sendEmail("Problem Page Error (2)", "No problem id and program is about to crash :)" ); 
            
            dialog.dismiss();
        }
        else
        {
        	getInviteCode( recent_problem_id );	
        }
        
        view_name = (TextView) findViewById(R.id.invite_code_snippet);
        
        
	    email_ask = (TextView) findViewById(R.id.friend_email_ask);
	    email = (EditText) findViewById(R.id.friend_email);
	    friend_name_ask = (TextView) findViewById(R.id.friend_name_ask);
	    friend_name = (EditText) findViewById(R.id.friend_name);        

	    your_name_ask = (TextView) findViewById(R.id.your_name_ask);
	    your_name = (EditText) findViewById(R.id.your_name); 
	    
        TextView think = (TextView) findViewById(R.id.instructions);  

        // Now create the form
        // 1) From name
        // 2) from email
        // 3) to name 
        // 4) to email
        // 5) body
        // 6) submit button
 
        
        Button invite_to_business = (Button)findViewById(R.id.invite_to_business);
        Button send_invite = (Button)findViewById(R.id.send_invite);
        //Button like_home_page = (Button)findViewById(R.id.like_home_page);
        
//        final AlertDialog.Builder share_builder = new AlertDialog.Builder(this);
//        like_home_page.setOnClickListener(new Button.OnClickListener() 
//        {  
//     	   public void onClick(View v) 
//     	   {      		
//             share_builder.setMessage("You will be taken to our home page from which you can press the Facebook Like button. It helps the app grow so thank you!")
//             .setCancelable(false)
//             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                 public void onClick(DialogInterface dialog, int id) {
//                      
//                     sendEmail("Invite ~ Yes Share", "From business page, user clicked on share button" );   	        		       
//                 
//                     Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//                   		  Uri.parse("http://www.problemio.com"));
//                     startActivity(browserIntent);
//                 }
//             })
//             .setNegativeButton("No", new DialogInterface.OnClickListener() 
//             {
//                 public void onClick(DialogInterface dialog, int id) 
//                 {
//                      dialog.cancel();
//                 }
//             });
//      AlertDialog alert = share_builder.create();
//      alert.show();
//     	   }
//        });         
        
        invite_to_business.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {      		
               Intent myIntent = new Intent(InviteFriendsActivity.this, PlanActivity.class);
               InviteFriendsActivity.this.startActivity(myIntent);
     	   }
        }); 
        
     
     	final AlertDialog.Builder builder = new AlertDialog.Builder(this);

	    send_dialog = new Dialog(this);
        send_invite.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {      		        		   
     		   // Get the parameters 
     	      String email_input = email.getText().toString().trim();
     	      String friend_name_input = friend_name.getText().toString().trim();
     	      String your_name_input = your_name.getText().toString().trim();
     	           	
     	      //sendEmail("Submitting invite friend" , "From: " + email_input + " to: " + friend_name_input + " from name: " + your_name_input);
     	      
//     	      if ( user_id == null )
//     	      {
//     		      sendEmail("Add Biz Err Validating USER ID (1). " , "Person tried to submit business, but their user id was empty....very bad." );
//     	    	
//     		      Toast.makeText(getApplicationContext(), "You do not have an active session.  Please Log in or create an account.", Toast.LENGTH_LONG).show();	
//     		     
//     		      Intent loginIntent = new Intent( AddProblemActivity.this, LoginActivity.class);
//     		      AddProblemActivity.this.startActivity(loginIntent);  
//     	      }
     	      
     	  
 	    	  if ( friend_name_input == null || friend_name_input.length() < 1 )
 	    	  {
 	    		  Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();	    	    		  
 	    	  
 	    		 try 
 	        	 {
 	    			send_dialog.dismiss();
 	             } 
 	        	 catch (Exception e) 
 	        	 {
 	                // nothing
 	             }
 	    	  }
 	    	  else
 	          if ( your_name_input == null )
 	          {
 	    		  Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_LONG).show();	    	    		  
 	          
 	    		 try 
 	        	 {
 	    			send_dialog.dismiss();
 	             } 
 	        	 catch (Exception e) 
 	        	 {
 	                // nothing
 	             }
 	          }
 	          else
 	          if ( email_input == null)
 	          {
 	        	 try 
 	        	 {
 	                dialog.dismiss();
 	             } 
 	        	 catch (Exception e) 
 	        	 {
 	                // nothing
 	             }
 	        	  
 	    		  Toast.makeText(getApplicationContext(), "Please enter the email address(es) of your friends.", Toast.LENGTH_LONG).show();	    	    		  
 	          }
 	          else
 	          if ( promo_code == null )
 	          {
 	        	 try 
 	        	 {
 	        		send_dialog.dismiss();
 	             } 
 	        	 catch (Exception e) 
 	        	 {
 	                // nothing
 	             }
 	        	  
 	    		  Toast.makeText(getApplicationContext(), "Please wait while the promo code is generated. Try again in a few seconds", Toast.LENGTH_LONG).show();	    	    		  
 	          }
     	      else
     	      {    	   
         	      //sendEmail("Sending invite friend (fundraising android)" , "From: " + email_input + " to: " + friend_name_input + " from name: " + your_name_input + " and code: " + promo_code);
     	    	  
     	          send_dialog.setContentView(R.layout.please_wait);
     	          send_dialog.setTitle("Sending invite...");

     	          TextView text = (TextView) send_dialog.findViewById(R.id.please_wait_text);
     	          text.setText("Please wait...");
     	          send_dialog.show();  
     	    	  
     	    	  sendInvite( friend_name_input , your_name_input , email_input , recent_problem_id , promo_code );
     	      
       	    	 builder.setMessage("Your invite has been sent. Tell your friend to check their junk folder for the invite because sometimes auto-generated email goes there.")
	              .setCancelable(false)
	              .setPositiveButton("I understand", new DialogInterface.OnClickListener() {
	                  public void onClick(DialogInterface dialog, int id) {
	                       
	                  }
	              });

			       AlertDialog alert = builder.create();
			       alert.show();     	           	      
     	      }
     	   }
        });   
    }
    
    public void getInviteCode(String problem_id) 
    {
        String[] params = new String[] { 
        		"http://www.problemio.com/problems/get_invite_code_fundraising.php", 
        		problem_id };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }              
    
    public void sendInvite( String friend_name , String your_name , String to_email , 
    		String problem_id , String promo_code ) 
    {
    		String[] params = new String[] 
        		{ "http://www.problemio.com/problems/invite_friends_fundraising.php" , friend_name , 
    				your_name , to_email, problem_id , promo_code};
    		
    		InviteFriendsTask task = new InviteFriendsTask();
	        task.execute(params);
    }            
        
      
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String business_id = theParams[1];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("business_id=%s", 
		        	     URLEncoder.encode(business_id, charset));

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
	 		      //sendEmail ( "InviteFriendsActivity Network Error" , "Error: " + e.getMessage() );
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			try 
        	{
                dialog.dismiss();
                
            } 
        	catch (Exception ee) 
        	{
                // nothing
            }
			
			if ( result == null )
			{
		        Toast.makeText(getApplicationContext(), "Could not connect to server. Please try again in a few minutes.", Toast.LENGTH_LONG).show();	
			}
			else
	        if ( result.equals("update_error"))
	        {
		        // Show the user a message that they did not enter the right login
		        
		        Toast.makeText(getApplicationContext(), "We could not get your problem. Please let us know about this issue.", Toast.LENGTH_LONG).show();	
	        }
	        else
	        if ( result.equals("no_business_id"))
	        {
	        	//sendEmail ("InviteFriendsActivityError" , "No business id");
	        }
	        else
	        {
	        	view_name.setText("Invite Code: " + result);
	        	
	        	promo_code = result;
		    }
		}        
    }    
    
    


   
    
    public class InviteFriendsTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        String from_name = theParams[1];
	        String to_person_name = theParams[2];
	        String to_email = theParams[3];
	        String problem_id = theParams[4];
	        String promo_code = theParams[5];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("problem_id=%s&from_name=%s&to_name=%s&to_email=%s&promo_code=%s", 
		        	     URLEncoder.encode(problem_id, charset) , 
		        	     URLEncoder.encode(from_name, charset) ,
		        	     URLEncoder.encode(to_person_name, charset) ,
		        	     URLEncoder.encode(to_email, charset) ,
		        	     URLEncoder.encode(promo_code, charset)
		        		);

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
	        Toast.makeText(getApplicationContext(), "Success! Now you can go back to the business page.", Toast.LENGTH_LONG).show();
		
		
	        try 
        	 {
        		send_dialog.dismiss();
             } 
        	 catch (Exception e) 
        	 {
                // nothing
             }
		}        
    }        

    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }  
}
