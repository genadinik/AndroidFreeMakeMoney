package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BooksActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.business_book);

	    

        
//        Button fiverr = (Button)findViewById(R.id.fiverr);          
//        fiverr.setOnClickListener(new Button.OnClickListener() 
//        {  
//            public void onClick(View v) 
//            {        	
//              Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
//            		  Uri.parse("http://fiverr.com/genadinik/send-you-a-60-page-book-on-business-ideas"));
//              
//              startActivity(browserIntent);
//            }
//        });

        Button amazon = (Button)findViewById(R.id.amazon);          
        amazon.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {        	
              Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
            		  Uri.parse("http://www.amazon.com/Business-Start-up-Ideas-Comprehensive-entrepreneurs/dp/1495261840"));
              
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
}
