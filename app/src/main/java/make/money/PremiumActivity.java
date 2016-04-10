package make.money;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import utils.SendEmail;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Created by alexgenadinik on 1/3/16.
 */
public class PremiumActivity extends BaseActivity
{
    /**
     * Called when this activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    // TODO: start here
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.premium);


        Button books = (Button)findViewById(R.id.books);
        books.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(PremiumActivity.this, BooksActivity.class);
                PremiumActivity.this.startActivity(myIntent);
            }
        });


        Button courses = (Button)findViewById(R.id.courses);
        courses.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(PremiumActivity.this, CoursesActivity.class);
                PremiumActivity.this.startActivity(myIntent);
            }
        });

        Button coaching = (Button)findViewById(R.id.coaching);
        coaching.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(PremiumActivity.this, CoachingActivity.class);
                PremiumActivity.this.startActivity(myIntent);
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

    @Override
    public void onStop()
    {
        super.onStop();
        // your code
    }

    public void sendFeedback( String name , String email , String phone )
    {
        String[] params = new String[] { "https://www.problemio.com/problems/professional_help.php",
                name , email , phone };

        DownloadPageTask task = new DownloadPageTask();
        task.execute(params);
    }


    private class DownloadPageTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... theParams)
        {
            String myUrl = theParams[0];
            final String name = theParams[1];
            final String email = theParams[2];
            final String phone = theParams[3];

            String charset = "UTF-8";
            String response = null;

            try
            {
                String query = String.format("platform=android&name=%s&email=%s&phone=%s",
                        URLEncoder.encode(name, charset) ,
                        URLEncoder.encode(email + "", charset) ,
                        URLEncoder.encode(phone + "", charset)
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
