package anna.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;

import annab.mytweetActivity.R;


public class mytweetActivity extends Activity implements OnClickListener {

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
        textCount = (TextView) findViewById(R.id.textCount);
        editStatus = (EditText) findViewById(R.id.editStatus);
        sent_date = (TextView) findViewById(R.id.sent_date);
        selectContact = (Button) findViewById(R.id.selectContact);
        sendEmail = (Button) findViewById(R.id.sendEmail);

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        String dateString = sdf.format(date);
        sent_date.setText(dateString);

        buttonTweet.setOnClickListener(this);

        editStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                int count = 140 - editStatus.length();
                textCount.setText(Integer.toString(count));
                textCount.setTextColor(Color.GREEN);
                if (count < 10 & count > 0)
                    textCount.setTextColor(Color.YELLOW);
                else if (count == 0)
                    textCount.setTextColor(Color.RED);
                else
                    textCount.setTextColor(Color.GREEN);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
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

    @Override
    public void onClick(View v) {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        String dateString = sdf.format(date);
        sent_date.setText(dateString);
        Toast toast = Toast.makeText(this, "Message Sent at " + dateString, Toast.LENGTH_SHORT);
        toast.show();
    }
}
