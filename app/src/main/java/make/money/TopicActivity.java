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

import data.DiscussionMessage;

import utils.SendEmail;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TopicActivity extends BaseListActivity
{
	ArrayAdapter<DiscussionMessage> adapter;			
	ArrayList<DiscussionMessage> discussion = new ArrayList <DiscussionMessage>( );	
	Dialog dialog;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.discussion);
        
        DiscussionMessage d = new DiscussionMessage ();
        d.setMessage( "Please wait while the discussion loads..." );
        
        discussion.add(d);
        adapter = new ArrayAdapter<DiscussionMessage>( 
        		this,R.layout.discussion_comments, 
        		discussion);

        setListAdapter(adapter);
        
        
        
        
//    	ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
//    	    	
//        // My data
//        fillMaps = new ArrayList<HashMap<String, String>>();
//    	
//    	simple_adapter = new SimpleAdapter(this, fillMaps, R.layout.comment_list,
//                new String[] {"train", "from"}, 
//                new int[] {R.id.TRAIN_CELL,  R.id.FROM_BUTTON });
    	
    	// This was the middle item R.id.FROM_BUTTON,
      
    	//final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) 
            {     
               final String author_id = discussion.get( position ).getAuthorId();
               final String message_id = discussion.get( position ).getMessageId();
               
               //Toast.makeText(getApplicationContext(), "Click, authorid: " + author_id + " and message_id: " + message_id  ,
               //        Toast.LENGTH_SHORT).show();
              
               if ( author_id != null && !author_id.equals("1") )
               {
                   // Now put this stuff into the SharedPreferences
     		      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( TopicActivity.this);
     		      prefs.edit().putString("recent_topic_message_id", message_id ).commit();              

     		      final String user_id = prefs.getString( "user_id" , null ); 

     		      
//     		        final String problem_id = prefs.getString( "recent_problem_id" , null ); 
//     		        final String recent_topic_id = prefs.getString( "recent_topic_id" , null ); 
//     		        final String recent_topic_name = prefs.getString( "recent_topic_name" , null );
     		      

//                   Toast.makeText(getApplicationContext(), "User id: " + user_id + " and author_id: " + author_id,
//                           Toast.LENGTH_SHORT).show();
     		      
     		      
                   // Show dialog if to delete or edit.    		       
//                   builder.setMessage("Edit?")
//         		              .setCancelable(true)
//         		              .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
//         		                  public void onClick(DialogInterface dialog, int id) 
//         		                  {
//         		                	  if ( user_id != null && author_id != null && user_id.equals(author_id))
//         		                	  {
//         		                		  // Go to edit message activity.
//         		                		  Intent myIntent = new Intent(TopicActivity.this, TopicEditActivity.class);
//         		            	          TopicActivity.this.startActivity(myIntent);
//         		                	  }
//         		                	  else
//         		                	  {
//         		                		  Toast.makeText(getApplicationContext(), "Can not edit a post which you did not write",
//           		                                Toast.LENGTH_SHORT).show();
//         		                	  }
//         		                  }
//         		              })
//         		              .setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
//         		              {
//         		                  public void onClick(DialogInterface dialog, int id) 
//         		                  {
//         		                	  dialog.cancel();
//         		                  }
//         		              });
//         		       
//         		       AlertDialog alert = builder.create();
//         		       alert.show();		       		       
               }

         	  
		      //prefs.edit().putString("recent_problem_id", problem_id ).commit();              
              
//              // And go to problem intent
//	          Intent myIntent = new Intent(MyProblemsActivity.this, ProblemActivity.class);
//	          MyProblemsActivity.this.startActivity(myIntent);
            }
          });        
        
                  		
        
        
        
        
        
        

           
        
        // Check if person is logged in
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( TopicActivity.this);
        final String user_id = prefs.getString( "user_id" , null ); 
        final String problem_id = prefs.getString( "recent_problem_id" , null ); 
        final String recent_topic_id = prefs.getString( "recent_topic_id" , null ); 
        final String recent_topic_name = prefs.getString( "recent_topic_name" , null ); 


        
        
        
        
        
        // If the user is not logged in, send them to log in
//        if ( user_id == null )
//        { 	
//	        Intent loginIntent = new Intent( TopicActivity.this, LoginActivity.class);
//	        TopicActivity.this.startActivity(loginIntent);        	
//        }        
        
        // Show field for making a comment  
    	final EditText comment = (EditText) findViewById(R.id.discussion_comment);  
    	String text = comment.getText().toString();      
       
        Button topic_learn = (Button)findViewById(R.id.topic_learn);   
        
        if ( recent_topic_id != null && recent_topic_id.equals( "3" ) )
        	topic_learn.setText( "Learn About Donations" );
        	
        if ( recent_topic_id != null && recent_topic_id.equals( "4" ) )
        	topic_learn.setText( "Learn About Loans" );      

        if ( recent_topic_id != null && ( recent_topic_id.equals( "1" ) ) )
        	topic_learn.setVisibility(View.GONE);        

        if ( recent_topic_id != null && ( recent_topic_id.equals( "2" ) ) )
        	topic_learn.setVisibility(View.GONE);            
        
        if ( recent_topic_id != null && recent_topic_id.equals( "5" ) )
        	topic_learn.setText( "Learn About Grants" );         
        
        if ( recent_topic_id != null && recent_topic_id.equals( "6" ) )
        	topic_learn.setText( "Learn About Investments" );       
        
        
        topic_learn.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {
     		  SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( 
     				  TopicActivity.this);
     		  
     	      final String recent_topic_id = prefs.getString( "recent_topic_id" , null ); 
     		   
     	     Intent myIntent = null;
     	                   
     	     if ( recent_topic_id == null )
     	     {
     	    	 // Do something
     	     }
     	     else
        	 if ( recent_topic_id.equals( "3" ) )
               	  myIntent = new Intent(TopicActivity.this, ContentDonationActivity.class);
             else
     	     if ( recent_topic_id.equals( "4" ) )
            	  myIntent = new Intent(TopicActivity.this, ContentLoansActivity.class);
             else
             if ( recent_topic_id.equals( "5" ) )
            	 myIntent = new Intent(TopicActivity.this, ContentGrantsActivity.class);      
             else
             if ( recent_topic_id.equals( "6" ) )	 
            	 myIntent = new Intent(TopicActivity.this, ContentInvestmentActivity.class); 
              
             TopicActivity.this.startActivity(myIntent);
     	   }
        }); 
   		        
        
        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {      		               
		        Intent loginIntent = new Intent( TopicActivity.this, PlanActivity.class);
		        TopicActivity.this.startActivity(loginIntent);                  	
     		}
        });        
        
	    //dialog = new Dialog(this);
        Button submit = (Button)findViewById(R.id.submit_comment);   
        submit.setOnClickListener(new Button.OnClickListener() 
        {  
     	   public void onClick(View v) 
     	   {     		   
     	      String c = comment.getText().toString();

              if ( c == null || c.length() == 0 )
              {				    
     	         Toast.makeText(getApplicationContext(), "Please make sure the comment is not empty.", Toast.LENGTH_LONG).show();			     		        
              }
              else
              {     	    
    	      	  sendFeedback( c , user_id , problem_id , recent_topic_id );   
              }
     	    
     		// To add a new comment, I need these fields:
     	    // problem_id , suggested_solution_id , commenter_id , comment , solution_section
     	   }
        });
        
        
        TextView discussion_message = (TextView) findViewById(R.id.discussion_message);
        discussion_message.setText( "Discuss: " + recent_topic_name );

//        if ( problem_id != null && user_id != null && !user_id.equals("1") && 
//        		( problem_id.equals("3404") || problem_id.equals("3403") || problem_id.equals("3402")
//        				|| problem_id.equals("3401") || problem_id.equals("3400") 
//        		) )
//        {
//        	// Hide form and the two buttons.
//        	comment.setVisibility(View.GONE);
//        	submit.setVisibility(View.GONE);
//        	back.setVisibility(View.GONE);
//        }
        
        // Go to the database and display a list of problems, all clickable.
	    
        sendFeedback( problem_id , recent_topic_id );   
    }		
    
    
    public void sendFeedback(String problem_id , String recent_topic_id) 
    {  

        String[] params = new String[] 
        		{ "https://www.problemio.com/problems/get_fundraising_discussion_comments.php",
        		problem_id , recent_topic_id };
        

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(params);        
    }                  
    
    public void sendFeedback( String c , String user_id , String problem_id , 
    		String recent_topic_id )
    {  
        String[] params = new String[] 
        		{ "https://www.problemio.com/problems/add_fundraising_comment.php",
        		 c , user_id, problem_id , recent_topic_id };

        AddComment task = new AddComment();
        task.execute(params);        
    }   
    
    public void onItemClick(AdapterView<?> items, View v, int x, long y)
    {
        Log.d( "onItemClick: " , "In the onItemClick method of ViewSolutionsActivity" );
    }
    
    public class DownloadWebPageTask extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
	    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(TopicActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		      dialog.setTitle("Getting Current Comments");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		      text.setText("Please wait while comments load... ");
		      dialog.show();
		 }     	
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String problem_id = theParams[1];
	        final String solution_section = theParams[2];
	        	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        	        
		        String query = String.format("plan_id=%s&section=%s", 
		        	     URLEncoder.encode( problem_id, charset),
		        	     URLEncoder.encode( solution_section, charset));

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
			
			if ( connectionError == true )
			{
				Toast.makeText(getApplicationContext(), "Please try again. Possible Internet connection error.", Toast.LENGTH_LONG).show();	 
			}
						
			if ( result == null )
			{					
		        Toast.makeText(getApplicationContext(), "Could not get the discussion. Please try again in a few minutes.", Toast.LENGTH_LONG).show();			     		        
			}
			else
	        if ( result.equals("no_suggested_solution_id") || result.equals("no_topic_id" )  || result.equals("no_plan_id" ) )
	        {	        			        
		        Toast.makeText(getApplicationContext(), "Could not get the discussion. Please try again in a few minutes.", Toast.LENGTH_LONG).show();			     		        
	        }
	        else
	        { 
		        if ( result.length() == 0 )
		        {
			        discussion.clear();
			        			    	
		        	DiscussionMessage message = new DiscussionMessage ( );
		            message.setMessage("This section is empty.");
		            
		            discussion.add( message );			        
		        }
		        else
		        {
			        try
			        {	
			        	JSONArray obj = new JSONArray(result);
				        			        
			        	if ( obj != null )
			        	{
					        discussion.clear();
			        
			        		for ( int i = 0; i < obj.length(); i++ )
			        		{
			        			JSONObject o = obj.getJSONObject(i);
			    		    	//HashMap<String, String> map = new HashMap<String, String>();
	
					            String comment = o.getString("comment");
					            String commenter_id = o.getString("commenter_id");
					            String comment_id = o.getString("comment_id");
					            String first_name = o.getString("first_name");
					            String is_private = o.getString("privacy");
					            
					            DiscussionMessage d = new DiscussionMessage ( );
					            d.setMessage(comment);
					            d.setAuthorId(commenter_id);
					            d.setMessageId(comment_id);
					            
					            if ( first_name != null )
					            {					            	
					            	d.setAuthorName( first_name.trim( ) );
					            }
					            else
					            {
					            	d.setAuthorName( "User" );
					            }
					            
					            if ( is_private != null && is_private.trim().equals("1") )
					            {
					            	d.setIsPrivate( true );
					            }
					            else
					            {
					            	d.setIsPrivate( false );
					            }
					            
				            	//map.put("train", comment);
				            	//map.put("from", "Edit");
				            	//map.put("to", "Delete");
				            	
				            	//fillMaps.add(map);
					          
					            discussion.add( d );
					        }
			        		
			                TextView topic_instructions = (TextView) findViewById(R.id.topic_instructions);

		        		} 
					    adapter.notifyDataSetChanged();		        		
			        }			        
			        catch ( Exception e )
			        {

			        }
		        }
		        
		        adapter.notifyDataSetChanged();		        		
		    }
		}        
    }    


    
    
    
    public class AddComment extends AsyncTask<String, Void, String> 
    {
		 private boolean connectionError = false;
    	
		 @Override
		 protected void onPreExecute( ) 
		 {
			  dialog = new Dialog(TopicActivity.this);

		      dialog.setContentView(R.layout.please_wait);
		      dialog.setTitle("Submitting Your Comment");

		      TextView text = (TextView) dialog.findViewById(R.id.please_wait_text);
		      text.setText("Please wait while your comment is processed... ");
		      dialog.show();
		 }    	
    	
		@Override
		protected String doInBackground(String... theParams) 
		{
	        String myUrl = theParams[0];
	        final String comment = theParams[1];
	        final String user_id = theParams[2];
	        final String problem_id = theParams[3];
	        final String recent_topic_id = theParams[4];
	        
	        String charset = "UTF-8";	        
	        String response = null;
	        
			try 
			{		        	        
		        String query = String.format("comment=%s&user_id=%s&plan_id=%s&topic_id=%s", 
		        	     URLEncoder.encode( comment, charset), 
		        	     URLEncoder.encode( user_id, charset), 
		        	     URLEncoder.encode( problem_id, charset), 
		        	     URLEncoder.encode( recent_topic_id, charset)
		        	     );		        
		        
		        final URL url = new URL( myUrl + "?" + query );
		        
		        final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		        
		        conn.setDoOutput(true); 
		        conn.setRequestMethod("POST");
		        
		        conn.setDoOutput(true);
		        conn.setUseCaches(false);
		        
		        conn.connect();
		        
		        InputStream stream = conn.getInputStream();
		        byte[] stream_buffer = new byte[8196];
		        int readCount;
		        StringBuilder stream_builder = new StringBuilder();
		        while ((readCount = stream.read(stream_buffer)) > -1) 
		        {
		            stream_builder.append(new String(stream_buffer, 0, readCount));
		        }

		        response = stream_builder.toString();		
			} 
			catch (Exception e) 
			{
				connectionError = true;
			}
			
			return response;
		}
		
		// TODO: 
//		 protected void onPreExecute(Long result) 
//		 {
//		     dialog = new Dialog(TopicActivity.this);
//		     //your initiation
//		     dialog.show();
//		 }

		@Override
		protected void onPostExecute(String result) 
		{
		    try {
		        dialog.dismiss();
		    } catch (Exception ee) {
		        // nothing
		    }			
			
			if ( connectionError == true )		 		      
			{
				Toast.makeText(getApplicationContext(), "Possible Internet connection error. Please try again. ", Toast.LENGTH_LONG).show();	 
			}
			
	        if ( result == null )
	        {			    
		        Toast.makeText(getApplicationContext(), "We experienced a server error. Please try again, or in a few minutes.", Toast.LENGTH_LONG).show();			     		        

	        }
	        else
	        if ( result.equals("no_problem_id") || 
	        	 result.equals("error_adding_suggested_solution_comment") || 
	        	 result.equals("no_recent_topic_id") || result.equals( "no_comment") || 
	        	 result.equals("no_member_id") ||  result.equals("no_plan_id") ||
	        	 result.equals("error_duplicate_topic_comment") 
	        	 )
	        {		        
		        Toast.makeText(getApplicationContext(), "Could not get the current discussion.", Toast.LENGTH_LONG).show();			     		        
	        }
	        else
	        {		    
	        	// FIRST CHECK FOR ERRORS
		        try
		        {
		        	JSONObject obj = new JSONObject(result);
		        	
		        	if ( obj != null )
		        	{
				        discussion.clear();
		    	        
				        if ( obj.length() == 0 )
		        		{
				            DiscussionMessage message = new DiscussionMessage ( );
				            message.setMessage("No messages in this discussion.");
				            
				            discussion.add( message );
		        		}
				        else
				        {
				        	// 	echo json_encode(array("result" => "error", "data" : "error_duplicate_topic_comment"));

				        	String json_result = obj.get("result") + "";
				        	
				        	if ( json_result != null && json_result.equals("ok"))
				        	{
				        		try
				        		{
				        			JSONArray list = (JSONArray)obj.get("data");
				        			
					        		for ( int i = 0; i < list.length(); i++ )
					        		{
					        			JSONObject o = list.getJSONObject(i);
			
							            //String suggested_solution_id = o.getString("suggested_solution_id");
					        			String comment = o.getString("comment");
							            String commenter_id = o.getString("commenter_id");
							            String comment_id = o.getString("comment_id");
							            String first_name = o.getString("first_name");
							            String is_private = o.getString("privacy");
							            
							            DiscussionMessage d = new DiscussionMessage ( );
							            d.setMessage(comment);
							            d.setAuthorId(commenter_id);
							            d.setMessageId(comment_id);	
							            d.setAuthorName(first_name);
		
							            if ( is_private != null && is_private.trim().equals("1") )
							            {
							            	d.setIsPrivate( true );
							            }
							            else
							            {
							            	d.setIsPrivate( false );
							            }					            
							            					            
							            discussion.add( d );
							        }
				        		}
				        		catch ( Exception e )
				        		{

				        		}				        		
				        	}
				        	else
				        	if ( json_result != null && json_result.equals("error")) 
				        	{
				        		JSONArray list = (JSONArray)obj.get("data");
			        			JSONObject o = list.getJSONObject(0);
			        			String error = o.getString("data");

					        	if( error != null && 
					        			( error.equals("no_problem_id") || 
					        			  error.equals("error_adding_suggested_solution_comment") || 
					        			  error.equals("no_recent_topic_id") ||
					        			  error.equals("no_comment") ||
					        			  error.equals("no_member_id") ||
					        			  error.equals("no_plan_id") ||
					        			  error.equals("error_duplicate_topic_comment") ) 
					        	  )
					        	{

					       	        {		        
					       		        Toast.makeText(getApplicationContext(), "Could not get the current discussion.", Toast.LENGTH_LONG).show();			     		        
					       	        }
					        	}
					        	else
					        	if ( error != null &&  error.equals("no_email_in_public_plan"))
					        	{
					        		Toast.makeText(getApplicationContext(),"Unexpected error. Please let us know about this" , Toast.LENGTH_LONG).show();	
					        	}				        	
				        	}
				        }
				        adapter.notifyDataSetChanged();	
				        
				        // Now clear the text area of text.
				    	EditText comment = (EditText) findViewById(R.id.discussion_comment);  
				    	comment.setText( "" );  
		        	}
		        }
		        catch ( Exception e )
		        {   		        	
		        	// End of input at character 0 of
		        }
		    }
		}        
    }        
    
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
