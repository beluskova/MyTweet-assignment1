package anna.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.UUID;

import anna.android.helpers.ContactHelper;
import anna.android.helpers.IntentHelper;
import anna.app.MyTweetApp;
import anna.models.Message;
import anna.models.Timeline;
import annab.mytweetActivity.R;


import static android.provider.ContactsContract.Contacts.CONTENT_URI;
import static anna.android.helpers.IntentHelper.navigateUp;

public class MessageFragment extends Fragment implements View.OnClickListener, TextWatcher {

    public static   final String  EXTRA_MESSAGE_ID = "mytweet.MESSAGE_ID";
    private static final int REQUEST_CONTACT = 1;

    private Button buttonTweet;
    private TextView textCount;
    private EditText editStatus;
    private TextView sent_date;
    private Button selectContact;
    private Button sendEmail;
    private Message message;
    private Timeline timeline;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID mesId = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_MESSAGE_ID);

        MyTweetApp app = (MyTweetApp) getActivity().getApplication();
        timeline = app.timeline;
        message = timeline.getMessage(mesId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_message, parent, false);

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        addListeners(v);
        updateControls(message);

        return v;
    }

    private void addListeners(View v) {
        buttonTweet = (Button) v.findViewById(R.id.buttonTweet);
        textCount = (TextView) v.findViewById(R.id.textCount);
        editStatus = (EditText) v.findViewById(R.id.editStatus);
        sent_date = (TextView) v.findViewById(R.id.sent_date);
        selectContact = (Button) v.findViewById(R.id.selectContact);
        sendEmail = (Button) v.findViewById(R.id.sendEmail);

        long date = System.currentTimeMillis();

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        String dateString = sdf.format(date);
        sent_date.setText(dateString);
        buttonTweet.setOnClickListener(this);
        editStatus.addTextChangedListener(this);
        selectContact.setOnClickListener(this);
        sendEmail.setOnClickListener(this);

    }

    public void updateControls(Message message)
    {
        editStatus.setText(message.editStatus);
        sent_date.setText(message.getDateString());
    }

    public void afterTextChanged (Editable s)
    {
        int count = 140 - editStatus.length();
        textCount.setText(Integer.toString(count));
        textCount.setTextColor(Color.GREEN);
        if (count < 10 & count > 0)
            textCount.setTextColor(Color.YELLOW);
        else if (count == 0)
            textCount.setTextColor(Color.RED);
        else
            textCount.setTextColor(Color.GREEN);

        String thisMessage = s.toString();
        Log.i(this.getClass().getSimpleName(), "tweeted: " + thisMessage);
        message.editStatus = thisMessage;
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy h:mm:ss a");
        String dateString = sdf.format(date);
        sent_date.setText(dateString);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateUp(getActivity());
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    // @Override
    public void onClick (View v) {
        switch  (v.getId())
        {
            case R.id.buttonTweet:
                Toast toast = Toast.makeText(getActivity(), "Message Sent ", Toast.LENGTH_SHORT);
                toast.show();
                //to return to Timeline when tweet is sent: no more editing of tweet is prevented
                IntentHelper.startActivity(getActivity(), TimelineListActivity.class);
                break;

            case R.id.selectContact:
                Intent i = new Intent(Intent.ACTION_PICK, CONTENT_URI);
                startActivityForResult(i, REQUEST_CONTACT);
                if (message.selectContact != null)
                {
                    selectContact.setText("Contact: "+ message.selectContact);
                }
                break;

            case R.id.sendEmail:
                IntentHelper.sendEmail(getActivity(), "", getString(R.string.tweet_report_subject), message.getTweetEmailed(getActivity()));
                break;
        }
    }
    //to make sure the tweets are saved when we leave the app
    public void onPause()
    {
        super.onPause();
        timeline.saveMessages();
    }

    //a helping method to display selected person's email when Select Contact button is clicked
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        else
        if (requestCode == REQUEST_CONTACT)
        {
            String name = ContactHelper.getEmail(getActivity(), data);
            message.selectContact = name;
            selectContact.setText(name);
        }
    }
}