package make.money;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

@ReportsCrashes(formKey = "dEd5VS02NUN6NE11R0JzYk1hanpHMWc6MQ")
public class MyApplication extends Application
{
	@Override
    public void onCreate() 
	{
        // The following line triggers the initialization of ACRA
        ACRA.init(this);
        super.onCreate();            
    }	
}
 