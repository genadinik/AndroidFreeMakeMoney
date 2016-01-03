package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import data.FundraisingPlan;

import utils.SendEmail;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EnterInviteCodeActivity extends BaseListActivity
{
	EditText invite_code;
	
	ArrayAdapter<FundraisingPlan> adapter;
	ArrayList<FundraisingPlan> problems = new ArrayList <FundraisingPlan>( );	
	private Dialog loading_businesses_dialog;		
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.enter_invite_code);

        // Check if person is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( EnterInviteCodeActivity.this);
        final String user_id = prefs.getString( "user_id" , null ); // First arg is name and second is if not found.
        final String session_name = prefs.getString( "first_name" , null );
        final String session_email = prefs.getString( "email" , null );        
                
        // If the user is not logged in, send them to log in
        if ( user_id == null )
        {
            //sendEmail("InviteCode - Null UserId", "Redirecting to login page, user had null user id. Here is the user id: " + user_id );   	        	
        	
	        Intent loginIntent = new Intent( EnterInviteCodeActivity.this, LoginActivity.class);
	        EnterInviteCodeActivity.this.startActivity(loginIntent);        	
        }
           
        TextView prompt_to_enter_code = (TextView) findViewById(R.id.prompt_to_enter_code);
	    invite_code = (EditText) findViewById(R.id.invite_code);
        
        adapter = new ArrayAdapter<FundraisingPlan>(this, R.layout.my_plans, problems);

        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            @Override
			public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
              Toast.makeText(getApplicationContext(), (( TextView ) view).getText(),
                  Toast.LENGTH_SHORT).show();

              String problem_name = problems.get( position ).getPlanName();
              String problem_id = problems.get( position ).getPlanId();
              
              // Now put this stuff into the SharedPreferences
		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( EnterInviteCodeActivity.this);
		      prefs.edit().putString("recent_problem_id", problem_id ).commit();              
              
              // And go to problem intent
	          Intent myIntent = new Intent(EnterInviteCodeActivity.this, PlanActivity.class);
	          EnterInviteCodeActivity.this.startActivity(myIntent);
            }
          });                	    
    	
    	    	
    	
    	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	Button submit_invite_code = (Button)findViewById(R.id.submit_invite_code);
    	submit_invite_code.setOnClickListener(new Button.OnClickListener()
    	{  
    	   @Override
		public void onClick(View v) 
    	   {
 		      Toast.makeText(getApplicationContext(), "Submitting...", Toast.LENGTH_LONG).show();	 
    		   
              // Get the code.
    	      String invite_code_input = invite_code.getText().toString().trim();

    	      if ( user_id == null )
    	      {
       		      Toast.makeText(getApplicationContext(), "Error: please create a profile first.", Toast.LENGTH_LONG).show();	    	    		      	    	  
    	      }
    	      else
    	      if ( invite_code_input == null )
    	      {
    	    	  // Show error message
     		      Toast.makeText(getApplicationContext(), "Please enter an invite code.", Toast.LENGTH_LONG).show();	 

    	      }
    	      else
    	      {
    	    	  // Send to check in the database.
    	    	  matchInviteCode ( invite_code_input.trim() , user_id);
    	    	  
               	  //sendEmail ("Submitting android fundraising invite code" , "User: " + user_id + " and code: " + invite_code_input.trim() );

    	      }
    	    }
    	});
    	
        if ( user_id == null )
        {
   		      Toast.makeText(getApplicationContext(), "Please, create an account or log in.", Toast.LENGTH_LONG).show();	    	    		  
        	
	          Intent myIntent = new Intent(EnterInviteCodeActivity.this, CreateProfileActivity.class);
	          EnterInviteCodeActivity.this.startActivity(myIntent);        	
        }
        else
        {
        	getInvitedBusinesses ( user_id );
        }    	    	
    }
    
    public void getInvitedBusinesses(String user_id) 
    {
        String[] params = new String[] { 
        		"http://www.problemio.com/problems/get_users_fundraising_invites.php", 
        		user_id };

        GetInvitedBusinesses task = new GetInvitedBusinesses();
        task.execute(params);        
    }    

    public void matchInviteCode(String invite_code_input , String user_id) 
    {   
	        String[] params = new String[] 
	        		{ "http://www.problemio.com/problems/enter_invite_code_fundraising.php", 
	        		invite_code_input , user_id};
	             
	        DownloadWebPageTask task = new DownloadWebPageTask();
	        task.execute(params); 	        	                
    }      
    
    private class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];	        
	        final String invite_code = theParams[1];
	        final String user_id = theParams[2];
	        	     
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("invite_code=%s&user_id=%s", 
		        	     URLEncoder.encode( invite_code , charset) , 
		        	     URLEncoder.encode( user_id , charset)
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
			if ( result == null )
			{
				Toast.makeText(getApplicationContext(), "Could not connect to the server. Please try again in a few minutes.", 
			    		  Toast.LENGTH_LONG).show();
			}
			else
	        if ( result.equals( "no_matching_problem" ) )
	        {		        
                //sendEmail("Add Problem Submitted Error", "From add-problem page, after submitted a business. " +
                	//	"There was no problem name, no matching business" );
                
  		        Toast.makeText(getApplicationContext(), "No matching plans found. Are you sure your code is entered correcty?", 
		    		  Toast.LENGTH_LONG).show();                
	        }
	        else
	        if ( result.equals( "no_invite_code" ) )
	        {		        
                //sendEmail("Add Problem Submitted Error", "From add-problem page, no invite code" );
              
  		        Toast.makeText(getApplicationContext(), "Error submitting the business.  " +
  		        		"We are aware working to fix this.", 
		    		  Toast.LENGTH_LONG).show();                		        		        
	        }
	        else
	        {                
  		        //sendEmail ("Invite Code (Android fundraising) entered correctly ","Redirecting to business id: " + result);
		       
		        // 1) First, write to whatever local session file that the person is logged in
		        // - I just really need user id and name and email. And store that.
		        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( EnterInviteCodeActivity.this);
		        prefs.edit().putString("recent_problem_id", result ).commit();
		        
		        String name = prefs.getString("first_name", null);
		        String email = prefs.getString("email", null);
		        
		        if ( name == null || email == null || name.trim().equals("") || email.trim().equals("") )
		        {
	  		        Toast.makeText(getApplicationContext(), "Now, please fill out your profil information.  " , 
	  		    		  Toast.LENGTH_LONG).show();		        	

		            Intent myIntent = new Intent(EnterInviteCodeActivity.this, UpdateProfileActivity.class);
		            EnterInviteCodeActivity.this.startActivity(myIntent);		  		        
		        }
		        else
		        {
	  		        Toast.makeText(getApplicationContext(), "Success, redirecting...  " , 
	  		    		  Toast.LENGTH_LONG).show();

			        // 2) Make an intent to go to the home screen
		            Intent myIntent = new Intent(EnterInviteCodeActivity.this, PlanActivity.class);
		            EnterInviteCodeActivity.this.startActivity(myIntent);	  		        
		        }
	        }
		}    
    }    
    
    private class GetInvitedBusinesses extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];	        
	        final String user_id = theParams[1];
	        	     
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s", 
		        	     URLEncoder.encode( user_id , charset)
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
			//sendEmail ("result" , "r: " + result);
			
			if ( result == null )
			{
				Toast.makeText(getApplicationContext(), "Could not connect to the server. Please try again in a few minutes.", 
			    		  Toast.LENGTH_LONG).show();
			}
			else
	        if ( result.equals( "no_problems_for_user" ) )
	        {		        
//                sendEmail("Add Problem Submitted Error", "From add-problem page, after submitted a business. " +
//                		"There was no problem name, no matching business" );
                
                TextView invites_loading_prompt = (TextView) findViewById(R.id.invites_loading_prompt);
                invites_loading_prompt.setText("You have not entered any correct invite codes");

	        }
	        else
	        if ( result.equals( "not_logged_in" ) )
	        {		                      
  		        Toast.makeText(getApplicationContext(), "Could not get your account. Please log in first." +
  		        		"We are aware working to fix this.", 
		    		  Toast.LENGTH_LONG).show();                		        		        
	        }
	        else
	        {                 		        
		        String problem_title = null;
		        String problem_id = null;
		        
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{
				        problems.clear();
		        		if ( obj == null || obj.length() < 1 )
		        		{
			                TextView invites_loading_prompt = (TextView) findViewById(R.id.invites_loading_prompt);
			                invites_loading_prompt.setText("You have not entered any correct invite codes");

		        		}
		        		else
		        		{
			        		for ( int i = 0; i < obj.length(); i++ )
			        		{
			        			JSONObject o = obj.getJSONObject(i);
	
					            problem_title = o.getString("name");
					            problem_id = o.getString("plan_id");
	
					            FundraisingPlan p = new FundraisingPlan ( );
					            p.setPlanId(problem_id);				            
					            p.setPlanName(problem_title);
					            
					            problems.add( p );
			        		}
			        		
			                TextView invites_loading_prompt = (TextView) findViewById(R.id.invites_loading_prompt);
			                invites_loading_prompt.setVisibility(View.GONE);		        			
			        		
					        adapter.notifyDataSetChanged();		        		
		        		}  // END OF ELSE
		        	}  // END OF IF OBJ == NULL
		        }
		        catch ( Exception e )
		        {
		            //sendEmail ( "Fundraising App Invited Problems Error" , "error: " + e.getMessage() + " and result: " + result );
		            
	                TextView invites_loading_prompt = (TextView) findViewById(R.id.invites_loading_prompt);
	                invites_loading_prompt.setText("We are experiencing technical difficuties. Please let us know about this or check back in an hour.");
		        }
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
    
    @Override
	public void onStop()
    {
       super.onStop();
       // your code
    }     
}
