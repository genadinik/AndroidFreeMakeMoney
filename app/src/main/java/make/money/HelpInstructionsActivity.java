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

public class HelpInstructionsActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.content_help_instructions);
	    	    
				
		

	}
	
    // Subject , body
    public void sendEmail( String subject , String body )
    {
        String[] params = new String[] { "http://www.problemio.com/problems/send_email_mobile.php", subject, body };

        SendEmail task = new SendEmail();
        task.execute(params);            	
    }   	
}
