package make.money;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import utils.SendEmail;

/**
 * Created by alexgenadinik on 1/3/16.
 */
public class CoachingActivity extends BaseActivity
{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setContentView(R.layout.coaching);

    Button coachingask = (Button)findViewById(R.id.coachingask);
    coachingask.setOnClickListener(new Button.OnClickListener() {
        public void onClick(View v) {
            Intent myIntent = new Intent(CoachingActivity.this, ExtraHelpActivity.class);
            CoachingActivity.this.startActivity(myIntent);
        }
    });

    Button coaching = (Button)findViewById(R.id.coachingskype);
    coaching.setOnClickListener(new Button.OnClickListener() {
    public void onClick(View v) {



        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.fiverr.com/genadinik/give-you-a-business-coaching-and-business-plan-skype-session-for-30-minutes"));

        startActivity(browserIntent);
    }
});

    // https://www.fiverr.com/genadinik/give-you-a-business-coaching-and-business-plan-skype-session-for-30-minutes
    // https://www.fiverr.com/genadinik/help-you-plan-an-seo-strategy

    Button me = (Button)findViewById(R.id.me);
    me.setOnClickListener(new Button.OnClickListener() {
    public void onClick(View v) {
        Intent myIntent = new Intent(CoachingActivity.this, AboutActivity.class);
        CoachingActivity.this.startActivity(myIntent);
    }
});
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