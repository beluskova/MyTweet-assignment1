package anna.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import anna.models.Message;
import annab.mytweetActivity.R;


public class timelineActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.timeline_activity);
        setContentView(R.layout.activity_timeline);

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
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast toast = Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.new_tweet:
                startActivity(new Intent(this, mytweetActivity.class));
                break;
            case R.id.action_clear:
                Toast toast1 = Toast.makeText(this, "Clear Selected", Toast.LENGTH_SHORT);
                toast1.show();
                break;
        }
        return true;
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
            this.messages = messages;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
            {
                convertView = inflater.inflate(R.layout.list_item_message, null);
            }
            Message mes = getItem(position);

            TextView editStatus = (TextView) convertView.findViewById(R.id.list_item_tweet_message);
            editStatus.setText(mes.editStatus);

            TextView sent_date = (TextView) convertView.findViewById(R.id.list_item_dateTextView);
            sent_date.setText(mes.getDateString());

           return convertView;
        }

        @Override
        public int getCount()
        {
            return messages.size();
        }
}
