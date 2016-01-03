package make.money;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class BaseListActivity extends ListActivity
{
	  @Override
	public ListView getListView() {
		    return (ListView)findViewById(android.R.id.list);
		  }

		  @Override
		public ListAdapter getListAdapter() {
		    return getListView().getAdapter();
		  }	
	
@Override
public void setContentView(int layoutResID) {
    super.setContentView(layoutResID);		
{
	setListAdapter(this.getListAdapter());
	
	Button home_header = (Button)findViewById(R.id.home_header);
	Button questions_header = (Button)findViewById(R.id.questions_header);
	Button learn_header = (Button)findViewById(R.id.learn_header);
	Button extra_help_header = (Button)findViewById(R.id.extra_help_header);
	
	home_header.setOnClickListener(new Button.OnClickListener() 
    {  
        public void onClick(View v) 
        {	            	
          Intent myIntent = new Intent( BaseListActivity.this , MainActivity.class);
          BaseListActivity.this.startActivity(myIntent);
        }
    });    		    	
	
	questions_header.setOnClickListener(new Button.OnClickListener() 
    {  
        public void onClick(View v) 
        {	            	
          Intent myIntent = new Intent( BaseListActivity.this , MyPlansActivity.class);
          BaseListActivity.this.startActivity(myIntent);
        }
    });	    	
	
	learn_header.setOnClickListener(new Button.OnClickListener() 
    {  
        public void onClick(View v) 
        {	            	
          Intent myIntent = new Intent( BaseListActivity.this , ContentLearnActivity.class);
          BaseListActivity.this.startActivity(myIntent);
        }
    });
	
 
	
	extra_help_header.setOnClickListener(new Button.OnClickListener() 
    {  
        public void onClick(View v) 
        {	            	
          Intent myIntent = new Intent( BaseListActivity.this , ExtraHelpActivity.class);
          BaseListActivity.this.startActivity(myIntent);
        }
    });	    	
	}
}
}
