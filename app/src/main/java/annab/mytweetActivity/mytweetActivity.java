package annab.mytweetActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class mytweetActivity extends AppCompatActivity {

    private Button buttonTweet;
    private TextView textCount;
    private EditText editStatus;
    private TextView sent_date;
    private Button selectContact;
    private Button sendEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);

        buttonTweet = (Button) findViewById(R.id.buttonTweet);
        textCount = (TextView)findViewById(R.id.textCount);
        editStatus = (EditText) findViewById(R.id.editStatus);
        sent_date = (TextView) findViewById(R.id.sent_date);
        selectContact = (Button) findViewById(R.id.selectContact);
        sendEmail = (Button) findViewById(R.id.sendEmail);

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        String dateString = sdf.format(date);
        sent_date.setText(dateString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_twitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
