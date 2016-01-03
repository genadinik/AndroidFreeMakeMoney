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
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class MyPlansActivity extends BaseListActivity
{
	ArrayAdapter<FundraisingPlan> adapter;
	TextView problem_loading_prompt = null;
	
	ArrayList<FundraisingPlan> problems = new ArrayList <FundraisingPlan>( );
	
	private Dialog dialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.my_plans_page);
        
        // Setup the text to inform the person that their businesses are retrieved.
        problem_loading_prompt = (TextView) findViewById(R.id.problem_loading_prompt);
                
        adapter = new ArrayAdapter<FundraisingPlan>(this, R.layout.my_plans, problems);

        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // When clicked, show a toast with the TextView text
              Toast.makeText(getApplicationContext(), (( TextView ) view).getText(),
                  Toast.LENGTH_SHORT).show();

              String plan_name = problems.get( position ).getPlanName();
              String plan_id = problems.get( position ).getPlanId();           

              // Now put this stuff into the SharedPreferences
		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( MyPlansActivity.this);
		      prefs.edit().putString("recent_problem_id", plan_id ).commit();              
		      prefs.edit().putString("recent_plan_name", plan_name ).commit(); 
		      
		      //sendEmail("Plan id: " + plan_id , "Plan nam: " + plan_name );
		      
              // And go to problem intent
	          Intent myIntent = new Intent(MyPlansActivity.this, PlanActivity.class);
	          MyPlansActivity.this.startActivity(myIntent);
            }
          });        
        
        // Check if person is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( MyPlansActivity.this);
        final String user_id = prefs.getString( "user_id" , null ); 
        final String session_name = prefs.getString( "first_name" , null );
        final String session_email = prefs.getString( "email" , null ); 
        
        //sendEmail("My Businesses Loading", "Name: " + session_name + ", User_id: " + user_id + " , email: " + session_email );   	        

        
        // If the user is not logged in, send them to log in
        if ( user_id == null )
        {
            //sendEmail("My Businesses NULL ERROR Loading", "Name: " + session_name + ", User_id: " + user_id + " , email: " + session_email );   	        

		    Toast.makeText(getApplicationContext(), "Not logged in.  Please login or create an account.", Toast.LENGTH_LONG).show();	
            
	        Intent loginIntent = new Intent( MyPlansActivity.this, LoginActivity.class);
	        MyPlansActivity.this.startActivity(loginIntent);        	
        }        
        
        
        

        

        
        Button add_problem = (Button)findViewById(R.id.add_business);  
        add_problem.setOnClickListener(new Button.OnClickListener() 
        {  
           public void onClick(View v) 
            {
                //sendEmail("Add Problem From MyProblems", "From my problems page, user clicked on " );               		
                
                Intent myIntent = new Intent(MyPlansActivity.this, AddPlanActivity.class);
                MyPlansActivity.this.startActivity(myIntent);
            }
        });                
       
        Button enter_invite_code = (Button)findViewById(R.id.enter_invite_code);  
        enter_invite_code.setOnClickListener(new Button.OnClickListener() 
        {  
           public void onClick(View v) 
            {                
                Intent myIntent = new Intent(MyPlansActivity.this, EnterInviteCodeActivity.class);
                MyPlansActivity.this.startActivity(myIntent);
            }
        });                        
        
        
        
        // Go to the database and display a list of problems, all clickable.
        if ( user_id != null )
        {
        	sendFeedback( user_id );   
        }
    }	
    
    public void sendFeedback(String user_id) 
    {  
        String[] params = new String[] { "http://www.problemio.com/problems/get_users_fundraising_plans.php", user_id };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }              

    
    public void onItemClick(AdapterView<?> items, View v, int x, long y)
    {
        Log.d( "onItemClick: " , "In the onItemClick method of MyProblemsActivity" );
    }
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(MyPlansActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		        dialog.setTitle("Loading Your Fundraising Plans");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		        text.setText("Please wait while your plans load...");
		      dialog.show();
		 }     	
    	
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
		        	     URLEncoder.encode(user_id, charset));

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
			     //sendEmail ( "MyProblemsActivity Network Error" , "Error: " + e.getMessage() );
		
			     connectionError = true;
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{	      
            try 
            {
                dialog.dismiss();
            } catch (Exception e) {
                // nothing
            }
            
			if ( connectionError == true || result == null )
			{
			     Toast.makeText(getApplicationContext(), "Please try again. Possible Internet connection error.", Toast.LENGTH_LONG).show();	 				
			
			        // Clear current page messaging
		        	problems.clear();
		        	
		        	FundraisingPlan p = new FundraisingPlan ();
		            p.setPlanName( "Possible Internet connection error. Please reload the page." );
		            
		            problems.add(p);
		            
		        	adapter.notifyDataSetChanged();		        			        	
			}
			else
	        if ( result.equals( "no_plans_for_user" ))
	        {
		        // Clear current page messaging
	        	problems.clear();
	        	
	        	FundraisingPlan p = new FundraisingPlan ();
	            p.setPlanName( "You have not started any plans." );
	            
	            problems.add(p);
	            
	        	adapter.notifyDataSetChanged();		        			        	
	        	
	        	problem_loading_prompt.setVisibility(View.GONE);       	
	        		        	
		        // No problems.  Display message that there are no problems.
	            //final TextView solution_title  = (TextView) findViewById(R.id.no_problems);
	        }
	        else
	        if ( result != null && result.equals("no_such_user" ))
	        {	            
		        // Show the user a message that they did not enter the right login
		        
		        Toast.makeText(getApplicationContext(), "We could not get your plans.", Toast.LENGTH_LONG).show();	
		     		        
	        }
	        else
	        {
		        String plan_title = null;
		        String plan_id = null;
		        
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{
				        problems.clear();
		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);

				            plan_title = o.getString("name");
				            plan_id = o.getString("plan_id");
				            String has_new_comments = o.getString("has_new_comments");
				            String is_private = o.getString("privacy");
				            
				            FundraisingPlan p = new FundraisingPlan ( );
				            p.setPlanId(plan_id);				            
				            p.setPlanName(plan_title);
				            p.setIsPrivate(is_private);
				            p.setHasNewComments(has_new_comments);
				            
				            problems.add( p );				      					        					        
		        		}
		        		
		        		problem_loading_prompt.setText("");
		        		
				        adapter.notifyDataSetChanged();		        		
		        	}			        
		        }
		        catch ( Exception e )
		        {
		            //sendEmail( "MyPlansError" , "Result: " + result + " and exception: " + e.getMessage() );
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
}
