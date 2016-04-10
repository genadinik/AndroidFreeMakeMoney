package make.money;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.SendEmail;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//import com.google.android.gcm.GCMRegistrar;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import data.FundingTopic;

public class PlanActivity extends BaseListActivity
{
	int num_of_asynch_calls = 2;
	boolean isPrivate;
		
	TextView business_privacy = null;
	TextView view_name; 
		
	ArrayAdapter<FundingTopic> adapter;
	//private SimpleAdapter adapter;

	Dialog dialog;
	ArrayList<FundingTopic> problems = new ArrayList <FundingTopic>( );	 	
	
	static final String SENDER_ID = "1039266385602";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
	    
        setContentView(R.layout.plan);
        
	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
	    		  PlanActivity.this);              
	        
	    final String user_id = prefs.getString( "user_id" , null );
	    final String recent_problem_id = prefs.getString( "recent_problem_id" , null ); 
	    
//	      try
//	      {
//		        if ( android.os.Build.VERSION.SDK_INT >= 8 )
//		        {
//			        GCMRegistrar.checkDevice(this);	
//			        GCMRegistrar.checkManifest(this);
//		        
//			        final String regId = GCMRegistrar.getRegistrationId(this);
//			        if (regId.equals("")) 
//			        {
//			        	GCMRegistrar.register(getApplicationContext(), SENDER_ID); 
//			        	
//		//	        	// Automatically registers application on startup. 
//		//	        	
//		//		        //GCMRegistrar.register(this, SENDER_ID); // google register 
//		//
//		//	        	
//		//	        	String newRegId = GCMRegistrar.getRegistrationId(this);
//		//
//		//	        	setRegistrationId ( user_id , newRegId );
//		
//			    		//GCMRegistrar.setRegisteredOnServer(this, true); //Tell GCM: this device is registered on my server	        
//				    	
//			        	
//			        } 
//			        else 
//			        {	     
//			        	// Device is already registered on GCM, check server. 
//			        	if (GCMRegistrar.isRegisteredOnServer(getApplicationContext())) 
//			        	{ 
//			        		// Not sure what to do here :)
//			        	} 
//			        	else 
//			        	{
//					    	if ( user_id != null )
//					    	{	
//						        GCMRegistrar.register(this, SENDER_ID); // google register 
//					    		//GCMRegistrar.setRegisteredOnServer(this, true); //Tell GCM: this device is registered on my server	        
//						    	setRegistrationId ( user_id , regId );
//					    	}
//					    	else
//					    	{
//
//					    	}
//			        	}
//			        }	        
//		        }
//	      }
//	      catch ( Exception e )
//	      {

//	      }
	        
	        
	    FundingTopic s = new FundingTopic ();
        s.setTopicName( "Loading plan sections..." );        
        problems.add(s);        
        
        
//        fillMaps = new ArrayList<HashMap<String, String>>();
//    	
//    	adapter = new SimpleAdapter(this, fillMaps, R.layout.solution_topic_list,
//                new String[] {"train", "to"}, 
//                new int[] {R.id.TRAIN_CELL,  R.id.TO_CELL});
    
        
        
        adapter = new ArrayAdapter<FundingTopic>(this, R.layout.topic_list, 
        		R.id.label,  problems);        
        
        setListAdapter (adapter);
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        
        
        
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {
              // Now put this stuff into the SharedPreferences
		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
		    		  PlanActivity.this);              
		        
		      final String user_id = prefs.getString( "user_id" , null ); 
		      final String email = prefs.getString( "email" , null ); 
		        
		        // If the user is not logged in, send them to log in
		        if ( user_id == null )
		        {
//		            sendEmail("Plan user_id Error", "User id is empty when browsing a problem. Here is " +
//		            		"their user_id: " + user_id + ", and email: " + email );   	        	
		        	
			        //Intent loginIntent = new Intent( ProblemActivity.this, LoginActivity.class);
			        //ProblemActivity.this.startActivity(loginIntent);        	
		        }   
		      
              String topic_name = problems.get( position ).getTopicName();
              String topic_id = problems.get( position ).getTopicId();   
              
              //sendEmail("Problem Page, Solution Topic Clicked", "A user was on the problem page and they chose one of the " +
              //		" discussion topics.  Here is the topic name: " + topic_name + " and topic_id: " + topic_id + " and " +
              //				"here is the user id: " + prefs.getString("user_id", null ) + " and " +
              //						"here is the problem name: " + prefs.getString("recent_problem_name", null ) );
              		      
		      prefs.edit().putString("recent_topic_id", topic_id ).commit();              
		      prefs.edit().putString("recent_topic_name", topic_name ).commit();              
              
              // And go to problem intent
	          Intent myIntent = new Intent(PlanActivity.this, TopicActivity.class);
	          PlanActivity.this.startActivity(myIntent);
            }
          });                
        
		// Now show button to add solution and see suggested solutions
        view_name  = (TextView) findViewById(R.id.view_name);
        view_name.setMovementMethod(new ScrollingMovementMethod());
        
        //problemName = (TextView) findViewById(R.id.problem_name);  
        business_privacy = (TextView) findViewById(R.id.business_privacy);  


        
//	    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
//	    		  ProblemActivity.this);         
//        
//	    // display a loading message before problem loads.
//        final String recent_problem_id = prefs.getString( "recent_problem_id" , null );
//        
//        
        //String recentProblemId = getIntent().getStringExtra("RecentProblemId");
        
        if ( recent_problem_id == null )
        {        
            // TODO: ASK THEM HOW THIS HAPPENED
         	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
         	
         	builder.setMessage("Notice: We could not locate the plan you are loading. Please reload the page. If the problem persists, please let us know about this.")
            .setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                     
                	dialog.cancel();
    
                }
            })
            .setNegativeButton("Tell Us About This", new DialogInterface.OnClickListener() 
            {
                public void onClick(DialogInterface dialog, int id) 
                {
                     // GO TO FEEDBACK CONTROLLER
                    Intent myIntent = new Intent(PlanActivity.this, FeedbackActivity.class);
                    PlanActivity.this.startActivity(myIntent);
                }
            });
         	
		     AlertDialog alert = builder.create();
		     alert.show();         	
        }
        else
        {
            // Now have to 
            sendFeedback( recent_problem_id );
     
            sendFeedback ( recent_problem_id , null );              	
        }
                

//        Button learn = (Button)findViewById(R.id.learn);           
//        learn.setOnClickListener(new Button.OnClickListener() 
//        {  
//     	   public void onClick(View v) 
//     	   {      		   
//               // Now put this stuff into the SharedPreferences
// 		       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
// 		    		  ProblemActivity.this);              
// 		        
// 		       String user_id = prefs.getString( "user_id" , null );      		   
// 		       String email = prefs.getString( "email" , null );      		   
// 		       String name = prefs.getString( "first_name" , null );      		   
//
//     		   sendEmail ( "Problem --> Learn" , "Person: " + name + " email: " + email + " and user_id: " + user_id );
// 		       
//               Intent myIntent = new Intent(ProblemActivity.this, LearnActivity.class);
//               ProblemActivity.this.startActivity(myIntent);
//     	   }
//        }); 
        
//     	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        
//        Button delete_business = (Button)findViewById(R.id.delete_business);
//        delete_business.setEnabled(false);
//        
//        Button edit_business = (Button)findViewById(R.id.edit_business);
//        edit_business.setEnabled(false);
        
//        delete_business.setOnClickListener(new Button.OnClickListener() 
//        {  
//     	   public void onClick(View v) 
//     	   {      		   		       
//		       builder.setMessage("Are you sure you want to delete?")
//		              .setCancelable(false)
//		              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//		                  public void onClick(DialogInterface dialog, int id) {
//		                       
//		        		       // Need user_id and business_id
//		         		       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
//		         		    		   ProblemActivity.this);              
//		  		        
//				  		       String user_id = prefs.getString( "user_id" , null );  		   
//				  		       String business_id = prefs.getString( "recent_problem_id" , null );		        		       
//		        		       
//		        		       
//		        		       sendDeleteFeedback ( user_id , business_id );
//		                  }
//		              })
//		              .setNegativeButton("No", new DialogInterface.OnClickListener() 
//		              {
//		                  public void onClick(DialogInterface dialog, int id) 
//		                  {
//		                       dialog.cancel();
//		                  }
//		              });
//		       AlertDialog alert = builder.create();
//		       alert.show();		       		       
//     	   }
//        });         
//        
//        edit_business.setOnClickListener(new Button.OnClickListener() 
//        {  
//     	   public void onClick(View v) 
//     	   {      		        		   
//               Intent myIntent = new Intent(ProblemActivity.this, EditBusinessActivity.class);
//               ProblemActivity.this.startActivity(myIntent);
//     	   }
//        });         
        
        
        
//        Button send_business_plan = (Button)findViewById(R.id.send_business_plan);
//        send_business_plan.setEnabled(false);
//        send_business_plan.setOnClickListener(new Button.OnClickListener() 
//        {  
//     	   public void onClick(View v) 
//     	   {      		   
//     		   // Now put this stuff into the SharedPreferences
// 		       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
// 		    		  ProblemActivity.this);              
// 		        
// 		       String user_id = prefs.getString( "user_id" , null );      		   
// 		       String email = prefs.getString( "email" , null );      		   
// 		       String name = prefs.getString( "first_name" , null );      		   
//
// 		       if ( email == null )
// 		       {
// 			       Toast.makeText(getApplicationContext(), "We do not have your email on record.  Please update your profile (Home->Settings->Update Profile).", Toast.LENGTH_LONG).show();	
// 		       }
// 		       else
// 		       {
// 			       Toast.makeText(getApplicationContext(), "Generating and emailing your business plan. Make sure to check in your spam folder as email with attachments can end up there.", Toast.LENGTH_LONG).show();	
// 		    	   
// 		    	   //sendEmail ( "Email Biz Plan" , "Person: " + name + " email: " + email + " and user_id: " + user_id + " and problem_id: " + recent_problem_id  );
// 		       
// 		    	   sendFeedback( recent_problem_id , "export_as_text");
//     		   
//               //Intent myIntent = new Intent(ProblemActivity.this, LearnActivity.class);
//               //ProblemActivity.this.startActivity(myIntent);
// 		       }
//     	   }
//        });         
                
        Button business_invite_friends = (Button)findViewById(R.id.business_invite_friends);        
        business_invite_friends.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {
               Intent myIntent = new Intent(PlanActivity.this,  InviteFriendsActivity.class);
               PlanActivity.this.startActivity(myIntent);
     	   }
        }); 
        

        Button review = (Button)findViewById(R.id.plan_review);   
        review.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {    	    	
    	        Intent intent = new Intent(Intent.ACTION_VIEW);
    	    	intent.setData(Uri.parse("market://details?id=make.money"));
    	    	startActivity(intent);               
            }
        });   
    }
    
    
    
    
    public void setRegistrationId(String user_id , String regId ) 
    {  
        String[] params = new String[] { "https://www.problemio.com/problems/update_user_reg_mobile.php", user_id , regId };

        UpdateRedId task = new UpdateRedId();
        task.execute(params);        
    } 
    
    
    
    public void sendFeedback(String problem_id) 
    {  
        String[] params = new String[] { "https://www.problemio.com/problems/get_fundraising_plan.php", problem_id };

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }              
    
    public void sendFeedback( String problem_id , String dummyParam ) 
    {  
    	if ( dummyParam != null)
    	{
    		String[] params = new String[] 
          		{ "https://www.problemio.com/problems/generate_doc.php" , problem_id};
    		
//    		GenerateTextDocTask task = new GenerateTextDocTask();
//            task.execute(params);
    	}
    	else
    	{
    		String[] params = new String[] 
        		{ "https://www.problemio.com/problems/get_fundraising_plan_topics.php" , problem_id};

	        GetSolutionTopicsTask task = new GetSolutionTopicsTask();
	        task.execute(params);
    	}
    }            
        
//    public void sendDeleteFeedback( String user_id , String business_id ) 
//    {  
//        String[] params = new String[] { 
//        		"http://www.problemio.com/problems/delete_business_mobile.php", 
//        		user_id , business_id };
//
//        DeleteBusinessTask task = new DeleteBusinessTask();
//        task.execute(params);        
//    }    
    
    public class UpdateRedId extends AsyncTask<String, Void, String> 
    {
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String user_id = theParams[1];
	        final String reg_id = theParams[2];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("user_id=%s&reg_id=%s", 
		        	     URLEncoder.encode(user_id, charset) , 
		        	     URLEncoder.encode(reg_id, charset)
		        		);

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
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
		        // Show the user a message that they did not enter the right login
		        
		        //sendEmail ( "Device reg error" , "No response from server." );	
	        }
	        else
	        if ( result.equals("error_updating_user") )
	        {
		        //sendEmail ( "Device reg error" , "Result: " + result );		        	
	        }
	        else
	        {
		        //sendEmail ( "Device reg success" , "Success, that is it :)" );
		        
		        // TODO:
//		        if ( android.os.Build.VERSION.SDK_INT >= 8 )
//		        {
//		        	GCMRegistrar.setRegisteredOnServer(getApplicationContext(), true);
//		        }
		    }
		}        
    }        
    
    
    
    
    
    
    
    
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(PlanActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		      dialog.setTitle("Loading Plan Details");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		      text.setText("Please wait while plan details and topics load...");
		      dialog.show();
		 }      	

		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String problem_id = theParams[1];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("plan_id=%s", 
		        	     URLEncoder.encode(problem_id, charset));

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
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
				connectionError = true;
			     //sendEmail ( "ProblemActivity v.94 Network Error" , "Error: " + e.getMessage() );
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{	
		    try {
		        dialog.dismiss();
		    } catch (Exception ee) {
		        // nothing
		    }
			
			if ( connectionError == true || result == null  )
			{
			     Toast.makeText(getApplicationContext(), "Possible Internet connection error. Please try again. ", Toast.LENGTH_LONG).show();	 
			}
			else
	        if ( result.trim().length() == 0 || result == "no_such_user")
	        {
		        Toast.makeText(getApplicationContext(), "We could not get your plan. Please let us know about this issue.", Toast.LENGTH_LONG).show();	
	        }
	        else
	        if ( result.trim().length() == 0 || result == "no_plan_id")
	        {
		        Toast.makeText(getApplicationContext(), "We could not get your plan. Please let us know about this issue.", Toast.LENGTH_LONG).show();	
	        }	
	        else
	        if ( result.trim().length() == 0 || result == "no_plan_for_id")
	        {
		        Toast.makeText(getApplicationContext(), "We could not get your plan. Please let us know about this issue.", Toast.LENGTH_LONG).show();	
	        }	        	
	        else
	        {   
		        String problem_title = null;
		        String is_private = null;
		        String creator_member_id = null;
		        
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			JSONObject o = obj.getJSONObject(i);
		        	
				            problem_title = o.getString("name");
				            is_private = o.getString("privacy");
				            creator_member_id = o.getString("member_id");

				            //   ,  , platform , has_new_comments , 
				            
				            if ( creator_member_id != null && !creator_member_id.equals("1"))
				            { 
//				            	 Button delete_business = (Button)findViewById(R.id.delete_business);
//				                 delete_business.setEnabled(true);
//				                 
//				                 Button edit_business = (Button)findViewById(R.id.edit_business);
//				                 edit_business.setEnabled(true);
//				                 
//				                 Button business_invite_friends = (Button)findViewById(R.id.business_invite_friends);
//				                 business_invite_friends.setEnabled(true);
//				                 
//				                 Button send_business_plan = (Button)findViewById(R.id.send_business_plan);
//				                 send_business_plan.setEnabled(true);
				            }
				            
				            // business_privacy
				            if ( is_private != null && is_private.equals( "1" ) )
				            {
				            	business_privacy.setText ( "Plan: Private" );
				            	isPrivate = true;
				            	
//				            	 Button delete_business = (Button)findViewById(R.id.delete_business);
//				                 delete_business.setEnabled(true);
//				                 
//				                 Button edit_business = (Button)findViewById(R.id.edit_business);
//				                 edit_business.setEnabled(true);
//				                 
//				                 Button business_invite_friends = (Button)findViewById(R.id.business_invite_friends);
//				                 business_invite_friends.setEnabled(true);					                 
				            }
				            else
				            {
					            business_privacy.setText ( "Privacy setting: Collaborative" );
					            isPrivate = false;
				            }

					        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( PlanActivity.this);
					        
					        prefs.edit()
					        .putString("recent_problem_name", problem_title)
					        .putString("recent_problem_is_private" , is_private )
					        .commit();
					        
					        view_name.setText("Plan: " + problem_title);
				        }
		        	}			        
		        }
		        catch ( Exception e )
		        {

		        }
		        
		        num_of_asynch_calls = num_of_asynch_calls - 1;
		        
		        if ( num_of_asynch_calls == 0 )
		        {
		        	updateAdapter ( );
		        }
		    }
		}        
    }    
    
    
    public void onItemClick(AdapterView<?> items, View v, int x, long y)
    {
        Log.d( "onItemClick: " , "In the onItemClick method of MyProblemsActivity" );
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
	{
		String item = (String) getListAdapter().getItem(position);
		Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	}    
    
    public class GetSolutionTopicsTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	     private Dialog testDialog;	
		 
		 @Override
		 protected void onPreExecute( ) 
		 {
			  testDialog = new Dialog(PlanActivity.this);

			  testDialog.setContentView(R.layout.please_wait);
			  testDialog.setTitle("Getting plan topics...");

		      //TextView text = (TextView) testDialog.findViewById(R.id.please_wait_text);
		      //text.setText("Please wait while your comment is processed... ");
			  try
			  {
		          testDialog.show(); 
			  }
			  catch ( Exception e )
			  {
		      
			  }
		 }
		
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        String problem_id = theParams[1];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        
		        String query = String.format("plan_id=%s", 
		        	     URLEncoder.encode(problem_id, charset));

		        final URL url = new URL( myUrl + "?" + query );
		        		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
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
				 //connectionError = true;
			     //sendEmail ( "ProblemActivity v.93 Network Error" , "Error: " + e.getMessage() );
			}
			
			return response;
		}

		@Override
		protected void onPostExecute(String result) 
		{	 
            try 
            {
            	testDialog.dismiss();
            } catch (Exception e) {
                // nothing
            }			
			
			if ( result == null || result.trim().equals(""))
			{   
		        // Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "We could not get the next steps. " +
		        		"Please reload the plan. If the problem persits, please let us know about it.", Toast.LENGTH_LONG).show();	
		        
		        //sendEmail("Problem Activity Error null", "Tried to make a remote call to get the solution topics for a problem, but the server returned: " + result );   	        				
			}
			else
		    if ( result.equals( "no_plan_id" ) || result.equals( "no_problem_id" ))
		    {	            
			        // Show the user a message that they did not enter the right login
			        Toast.makeText(getApplicationContext(), "Error getting the plan topics. Please reload the plan.", Toast.LENGTH_LONG).show();	
			        
//			        sendEmail("Problem Activity Error 0", "Tried to make a remote call to get the solution topics for a problem, but the server returned no_solution_topics" );   	        
			}
			else
	        if ( result.equals( "no_solution_topics") )
	        {	            
		        // Show the user a message that they did not enter the right login
		        Toast.makeText(getApplicationContext(), "We could not get the next steps. " +
		        		"Please let us know about this.", Toast.LENGTH_LONG).show();	
		        
//		        sendEmail("Problem Activity Error", "Tried to make a remote call to get the solution topics for a problem, but the server returned no_solution_topics" );   	        
		    }
	        else
	        {  		        
		        // Unwrap the stuff from the JSON string
		        try
		        {
		        	JSONArray obj = new JSONArray(result);
		        	
		        	if ( obj != null )
		        	{
				        problems.clear();
		        		
		        		for ( int i = 0; i < obj.length(); i++ )
		        		{
		        			//HashMap<String, String> map = new HashMap<String, String>();
		        			JSONObject o = obj.getJSONObject(i);

				            String section_name = o.getString("section_name");
				            String section_id = o.getString("section_id");
				            String display_order = o.getString("display_order");
				            String comment_count = o.getString("comment_count");
				            
				            FundingTopic st = new FundingTopic ( );
				            st.setTopicName(section_name);
				            st.setTopicId( section_id );
				            st.setCommentCount( comment_count );
				            st.setDisplayOrder(display_order);
				            
			            	//map.put("train", section_name);			            	
			            	//map.put("to", ">");
				            
			            	//fillMaps.add(map);
				            problems.add( st );				            					        
		        		}
		        
		        		
		        		
//		        		for ( int i = 0; i < obj.length(); i++ )
//		        		{
//		    		    	HashMap<String, String> map = new HashMap<String, String>();
//		        			JSONObject o = obj.getJSONObject(i);
//		        			
//				            question_id = o.getString("question_id");
//				            question = o.getString("question");
//				            question_by_member_id = o.getString("member_id");
//		        			
//				            Question q = new Question ( );
//				            q.setQuestion( question );			            
//				            q.setQuestionId( question_id );
//				            q.setQuestionByMemberId(question_by_member_id);
//
//			            	map.put("train", question);
//			            	
//			            	//map.put("from", ">");
//			            	map.put("to", ">");
//			            	
////			            	R.id.loading_questions
////						    ImageView imView = (ImageView) row.findViewById(R.id.TO_CELL); 
//
//			            	//map.put("to", imView);
//			            	
//			            	fillMaps.add(map);
//			            	questions.add( q );					            
//				        }
//		        		
//		        		adapter.notifyDataSetChanged();		        		
		        		
		        		
		        		
		        		
		        		
		        		
		        		
		        		
				        //adapter.notifyDataSetChanged();					        
		        	}			        
		        }
		        catch ( Exception e )
		        {
			        //sendEmail( "Exception in problem activity ", "Problem activity error: " + e.getMessage() + " , and result was: " + result);
		        }
		    }  // End of else
	        
	        num_of_asynch_calls = num_of_asynch_calls - 1;
	        
	        if ( num_of_asynch_calls == 0 )
	        {
	        	updateAdapter ( );
	        }
		}        
    }        

    
    public void updateAdapter ( )
    {	
    	if ( isPrivate == true )
    	{
    		ArrayList<FundingTopic> temp_problems = new ArrayList <FundingTopic>( );
    		
    		if ( problems != null )
    		{
    			for ( int i = 0; i < problems.size(); i++)
    			{
    				//	ArrayList<SolutionTopic> problems = new ArrayList <SolutionTopic>( );	 	
    				FundingTopic topic = problems.get(i);
    				String topicId = topic.getTopicId();

    				if ( topicId != null && topicId.equals("15"))
    				{
    				    // ? :)    			        
    				}
    				else
    				{
    					temp_problems.add(topic);
    				}
    			}
    		}
    		
    		problems.clear();
    		problems.addAll(temp_problems);
    		
    	}
    	
    	
    	adapter.notifyDataSetChanged();	
    }
    
    
    
    
    
    
    
    
    
    
//    public class GenerateTextDocTask extends AsyncTask<String, Void, String> 
//    {
//		@Override
//		protected String doInBackground(String... theParams) 
//		{
//	        String myUrl = theParams[0];
//	        String problem_id = theParams[1];
//	        
//	        String charset = "UTF-8";	        
//	        String response = null;
//	        
//			try 
//			{		        
//		        String query = String.format("problem_id=%s", 
//		        	     URLEncoder.encode(problem_id, charset));
//
//		        final URL url = new URL( myUrl + "?" + query );
//		        		        
//		        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		        
//		        conn.setDoOutput(true); 
//		        conn.setRequestMethod("POST");
//		        
//		        conn.setDoOutput(true);
//		        conn.setUseCaches(false);
//		        
//		        conn.connect();
//		        
//		        final InputStream is = conn.getInputStream();
//		        final byte[] buffer = new byte[8196];
//		        int readCount;
//		        final StringBuilder builder = new StringBuilder();
//		        while ((readCount = is.read(buffer)) > -1) 
//		        {
//		            builder.append(new String(buffer, 0, readCount));
//		        }
//
//		        response = builder.toString();		
//			} 
//			catch (Exception e) 
//			{
//			     sendEmail ( "ProblemActivity 3 Network Error" , "Error: " + e.getMessage() );			}
//			
//			return response;
//		}
//
//		@Override
//		protected void onPostExecute(String result) 
//		{	        
//	        if ( result != null && result.equals( "no_solution_topics") )
//	        {		        
//		        Toast.makeText(getApplicationContext(), "We could not get the next steps. " +
//		        		"Please let us know about this.", Toast.LENGTH_LONG).show();	
//		        
//		        sendEmail("Problem Activity Error", "Tried to make a remote call to get the solution topics for a problem, but the server returned no_solution_topics" );   	        
//		    }
//	        else
//	        {		        
//		        // Unwrap the stuff from the JSON string
//		        try
//		        {
//		        	JSONArray obj = new JSONArray(result);
//		        	
//		        	if ( obj != null )
//		        	{
//				        problems.clear();
//		        		
//		        		for ( int i = 0; i < obj.length(); i++ )
//		        		{
//		        			JSONObject o = obj.getJSONObject(i);
//
//				            //Log.d( "Title: " , "" + o.getString("section_name") );	      
//				            //Log.d( "id: " , "" + o.getString("solution_section_id") );	   		        			
//		        	
//				            String section_name = o.getString("section_name");
//				            String section_id = o.getString("section_id");
//				            String display_order = o.getString("display_order");
//				            String comment_count = o.getString("comment_count");
//				            
//				            
//				            FundingTopic st = new FundingTopic ( );
//				            st.setTopicName(section_name);
//				            st.setTopicId( section_id );
//				            st.setCommentCount( comment_count );
//				            st.setDisplayOrder(display_order);
//				            
//				            problems.add( st );				            					        
//		        		}
//		        		
//				        adapter.notifyDataSetChanged();					        
//		        	}			        
//		        	
//		        }
//		        catch ( Exception e )
//		        {
//
//		        }
//		    }
//		}        
//    }    
    
    
    
    
    
//    public class DeleteBusinessTask extends AsyncTask<String, Void, String> 
//    {
//		@Override
//		protected String doInBackground(String... theParams) 
//		{
//	        String myUrl = theParams[0];
//	        final String user_id = theParams[1];
//	        final String business_id = theParams[2];
//	        
//	        String charset = "UTF-8";	        
//	        String response = null;
//	        
//			try 
//			{		        
//		        String query = String.format("user_id=%s&business_id=%s", 
//		        	     URLEncoder.encode(user_id, charset),
//		        	     URLEncoder.encode(business_id, charset)
//		        	     );
//
//		        final URL url = new URL( myUrl + "?" + query );
//		        		        
//		        final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		        
//		        conn.setDoOutput(true); 
//		        conn.setRequestMethod("POST");
//		        
//		        conn.setDoOutput(true);
//		        conn.setUseCaches(false);
//		        
//		        conn.connect();
//		        
//		        final InputStream is = conn.getInputStream();
//		        final byte[] buffer = new byte[8196];
//		        int readCount;
//		        final StringBuilder builder = new StringBuilder();
//		        while ((readCount = is.read(buffer)) > -1) 
//		        {
//		            builder.append(new String(buffer, 0, readCount));
//		        }
//
//		        response = builder.toString();		
//			} 
//			catch (Exception e) 
//			{
//			     //sendEmail ( "ProblemActivity 4 Network Error" , "Error: " + e.getMessage() );			
//			}
//			
//			return response;
//		}
//
//		@Override
//		protected void onPostExecute(String result) 
//		{	        
//		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
//		    		  PlanActivity.this);              
//		        
//		      String temp_user_id = prefs.getString( "user_id" , null ); 
//		      String temp_business_id = prefs.getString( "recent_problem_id" , null );	        
//	        
//	        if ( result == null || result.trim().length() == 0 )
//	        {
//			     Toast.makeText(getApplicationContext(), "Please try again. Possible Internet connection error.", Toast.LENGTH_LONG).show();	 				
//
//	        	sendEmail ( "Deleting business error"  , "No result came back. problem_id: " + temp_business_id + " user_id: " + temp_user_id );
//	        }
//	        else 
//	        if ( result.equals("no_user_id") )
//	        {
//	        	sendEmail ("Delete error" , "no_user_id, problem_id: " + temp_business_id + " user_id: " + temp_user_id);
//	        }
//	        else
//	        if ( result.equals("no_business_id") )
//	        {
//	        	sendEmail ("Delete error" , "no_business_id, problem_id: " + temp_business_id + " user_id: " + temp_user_id);
//	        }
//	        else
//	        if ( result.equals("error_getting_business") ) 
//	        {
//	        	sendEmail ("Delete error" , "error getting business, problem_id: " + temp_business_id + " user_id: " + temp_user_id);
//	        }
//	        else
//	        if ( result.equals("business_not_found") )
//	        {	        	
//	        	sendEmail ("Delete error" , "business_not_found, problem_id: " + temp_business_id + " user_id: " + temp_user_id);
//	        }
//	        else
//	        if ( result.equals("error_deleting_business") )
//	        {
//	        	sendEmail ("Delete error" , "error_deleting_business, problem_id: " + temp_business_id + " user_id: " + temp_user_id);
//	        }
//	        else
//	        if ( result.equals( "success" ) )
//	        {
//	 		    Toast.makeText(getApplicationContext(), "Deleted business successfully. Redirecting to your business-home page.", Toast.LENGTH_LONG).show();
//	        		        			        
//		        prefs.edit()
//		        .putString("recent_problem_name", null )
//		        .putString("recent_problem_id", null )
//		        .putString("recent_problem_is_private" , null )
//		        .commit();
//		        
//		          Intent myIntent = new Intent(PlanActivity.this, MyPlanActivity.class);
//		          PlanActivity.this.startActivity(myIntent);		        		        
//	        }
//	        else
//	        {
//	        	// WTF not supposed to be here.  Email me result.
//	        	sendEmail ( "Deleting plan - weird" , "Got to spot which is not accounted for...result: " + result );
//		    }
//		}        
//    }        
    
    
    
    
    
    
    
    
    
    
    
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
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
