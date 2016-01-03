package make.money;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends Activity
{
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);		
    {
    	Button home_header = (Button)findViewById(R.id.home_header);
    	Button questions_header = (Button)findViewById(R.id.questions_header);
//    	Button businesses_header = (Button)findViewById(R.id.businesses_header);
    	Button learn_header = (Button)findViewById(R.id.learn_header);
    	Button extra_help_header = (Button)findViewById(R.id.extra_help_header);
//    	
    	home_header.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	
              Intent myIntent = new Intent( BaseActivity.this , MainActivity.class);
              BaseActivity.this.startActivity(myIntent);
            }
        });    		    	
    	
    	questions_header.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	
              Intent myIntent = new Intent( BaseActivity.this , MyPlansActivity.class);
              BaseActivity.this.startActivity(myIntent);
            }
        });	    	
    	
    	learn_header.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	
              Intent myIntent = new Intent( BaseActivity.this , ContentLearnActivity.class);
              BaseActivity.this.startActivity(myIntent);
            }
        });	    
    	
    	extra_help_header.setOnClickListener(new Button.OnClickListener() 
        {  
            public void onClick(View v) 
            {	            	
              Intent myIntent = new Intent( BaseActivity.this , ExtraHelpActivity.class);
              BaseActivity.this.startActivity(myIntent);
            }
        });	    	
	}
}
}
