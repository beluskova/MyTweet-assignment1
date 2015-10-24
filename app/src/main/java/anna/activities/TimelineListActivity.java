package anna.activities;

        import android.content.Intent;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.AdapterView;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.app.Activity;
        import android.os.Bundle;

        import anna.android.helpers.IntentHelper;
        import anna.app.MyTweetApp;
        import anna.models.Message;
        import anna.models.Timeline;
        import annab.mytweetActivity.R;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

public class TimelineListActivity extends Activity implements View.OnClickListener
{
    private ListView listView;
    private Timeline timeline;
    private MessageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTitle(R.string.timeline_activity);
        setContentView(R.layout.activity_timeline);

        listView = (ListView) findViewById(R.id.messageList);

        MyTweetApp app = (MyTweetApp) getApplication();
        timeline = app.timeline;

        adapter = new MessageAdapter(this, timeline.messages);
        listView.setAdapter(adapter);
    }
    //saving new messages
    @Override
    public void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings : Toast toast = Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.new_tweet:
                Message message = new Message();
                timeline.addMessage(message);
                IntentHelper.startActivityWithDataForResult(this, mytweetActivity.class, "MESSAGE_ID", message.id, 0);
                return true;
            case R.id.action_clear :  Toast toast1 = Toast.makeText(this, "Clear Selected", Toast.LENGTH_SHORT);
                toast1.show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}

class MessageAdapter extends ArrayAdapter<Message>
{
    private Context context;
    public List<Message> messages;

    public MessageAdapter(Context context, List<Message> messages)
    {
        super(context, R.layout.list_item_message, messages);
        this.context   = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item_message, null);
        }

        Message mes   = getItem(position);

        TextView editStatus = (TextView) convertView.findViewById(R.id.list_item_tweet_message);
        editStatus.setText(mes.editStatus);

        TextView dateTextView = (TextView) convertView.findViewById(R.id.list_item_dateTextView);
        dateTextView.setText(mes.getDateString());
        return convertView;
    }
}


