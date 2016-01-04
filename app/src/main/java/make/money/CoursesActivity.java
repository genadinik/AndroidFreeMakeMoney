package make.money;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.flurry.android.FlurryAgent;

import utils.SendEmail;

/**
 * Created by alexgenadinik on 1/3/16.
 */
public class CoursesActivity extends BaseActivity
{
    /**
     * Called when this activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.courses);

        Button niche = (Button)findViewById(R.id.niche);
        niche.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/how-to-find-your-business-niche/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button startbusiness = (Button)findViewById(R.id.startbusiness);
        startbusiness.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/how-to-start-a-business-go-from-business-idea-to-a-business/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button appbook = (Button)findViewById(R.id.appbook);
        appbook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/how-to-create-grow-a-mobile-app-iphone-android-business/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button card = (Button)findViewById(R.id.card);
        card.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/business-card-marketing-business-networking/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button flier = (Button)findViewById(R.id.flier);
        flier.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/flyers-marketing-design-the-perfect-flier-and-get-clients/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button twitter = (Button)findViewById(R.id.twitter);
        twitter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/twitter-marketing-course/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button facebook = (Button)findViewById(R.id.facebook);
        facebook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/facebook-marketing-course/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button strategiesbook = (Button)findViewById(R.id.strategiesbook);
        strategiesbook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/marketing-plan-strategy-become-a-great-marketer/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });



//
        Button businessplan = (Button)findViewById(R.id.businessplan);
        businessplan.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/how-to-write-a-business-plan/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });

        Button marketingplan = (Button)findViewById(R.id.marketingplan);
        marketingplan.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/how-to-write-a-marketing-plan/?couponCode=androidapp"));

                startActivity(browserIntent);
            }
        });
        //
        //
        // https://www.udemy.com/startup-small-business-law-business-registration/?couponCode=androidapp
        //
        // https://www.udemy.com/learn-about-trademarks-and-how-to-look-up-trademarks/?couponCode=androidapp
        //
        //
        //
        //
        // https://www.udemy.com/how-to-get-great-domains-start-a-business-selling-domains/?couponCode=androidapp
        //

//        Button marketingplan = (Button)findViewById(R.id.marketingplan);
//        marketingplan.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.udemy.com/how-to-write-a-marketing-plan/?couponCode=androidapp"));
//
//                startActivity(browserIntent);
//            }
//        });
//
//        Button marketingplan = (Button)findViewById(R.id.marketingplan);
//        marketingplan.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.udemy.com/how-to-write-a-marketing-plan/?couponCode=androidapp"));
//
//                startActivity(browserIntent);
//            }
//        });
//
//        Button marketingplan = (Button)findViewById(R.id.marketingplan);
//        marketingplan.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://www.udemy.com/how-to-write-a-marketing-plan/?couponCode=androidapp"));
//
//                startActivity(browserIntent);
//            }
//        });

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
