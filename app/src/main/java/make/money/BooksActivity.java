package make.money;

import utils.SendEmail;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class BooksActivity extends BaseActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.business_book);


        Button niche = (Button)findViewById(R.id.niche);
        niche.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/How-find-business-niche-Ideas-ebook/dp/B0197T77CS"));

                startActivity(browserIntent);
            }
        });

        Button startbusiness = (Button)findViewById(R.id.startbusiness);
        startbusiness.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Business-Start-up-Ideas-Comprehensive-successful/dp/1495261840/"));

                startActivity(browserIntent);
            }
        });

        Button appbook = (Button)findViewById(R.id.appbook);
        appbook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Mobile-App-Marketing-Monetization-downloads/dp/1502383829"));

                startActivity(browserIntent);
            }
        });

        Button card = (Button)findViewById(R.id.card);
        card.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Business-Card-Marketing-Networking-ebook/dp/B018BD6T02"));

                startActivity(browserIntent);
            }
        });

        Button flier = (Button)findViewById(R.id.flier);
        flier.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Flier-Marketing-Flyers-Promote-Business-ebook/dp/B018HU86SM"));

                startActivity(browserIntent);
            }
        });

        Button twitter = (Button)findViewById(R.id.twitter);
        twitter.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Advanced-Twitter-Marketing-Automation-strategies-ebook/dp/B017T1T4FO"));

                startActivity(browserIntent);
            }
        });







        Button facebook = (Button)findViewById(R.id.facebook);
        facebook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Facebook-Marketing-Business-Expect-Promoting-ebook/dp/B00LMGPDIK"));

                startActivity(browserIntent);
            }
        });

        Button strategiesbook = (Button)findViewById(R.id.strategiesbook);
        strategiesbook.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Marketing-Advertising-Strategy-Reach-People/dp/1495453588"));

                startActivity(browserIntent);
            }
        });



//
        Button businessplan = (Button)findViewById(R.id.businessplan);
        businessplan.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Business-plan-template-example-business-ebook/dp/B0193L53IU"));

                startActivity(browserIntent);
            }
        });

        Button marketingplan = (Button)findViewById(R.id.marketingplan);
        marketingplan.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Marketing-Plan-Template-Example-marketing/dp/1519712952"));

                startActivity(browserIntent);
            }
        });

        Button amznauthor = (Button)findViewById(R.id.amznauthor);
        amznauthor.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Alex-Genadinik/e/B00I114WEU"));

                startActivity(browserIntent);
            }
        });
           
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
