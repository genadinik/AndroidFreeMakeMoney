package make.money;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by alexgenadinik on 1/3/16.
 */
public class AboutActivity extends BaseActivity
{
    // TODO: start here
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about);

        Button books = (Button)findViewById(R.id.books);
        books.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.amazon.com/Alex-Genadinik/e/B00I114WEU"));

                startActivity(browserIntent);
            }
        });

        Button courses = (Button)findViewById(R.id.courses);
        courses.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.udemy.com/user/alexgenadinik"));

                startActivity(browserIntent);
            }
        });
    }
}
