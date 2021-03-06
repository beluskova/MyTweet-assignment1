package anna.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import anna.android.helpers.IntentHelper;
import anna.app.MyTweetApp;
import anna.models.Message;
import anna.models.Timeline;
import annab.mytweetActivity.R;
import android.view.ActionMode;


public class TimelineListFragment extends ListFragment
        implements AdapterView.OnItemClickListener,
        AbsListView.MultiChoiceModeListener
{
    private ArrayList<Message> messages;
    private Timeline timeline;
    private MessageAdapter adapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        MyTweetApp app = (MyTweetApp) getActivity().getApplication();
        timeline = app.timeline;
        messages = timeline.messages;

        adapter = new MessageAdapter(getActivity(), messages);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        listView = (ListView) v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        return v;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Message mes = ((MessageAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), MytweetPagerActivity.class);
        i.putExtra(MessageFragment.EXTRA_MESSAGE_ID, mes.id);
        startActivityForResult(i, 0);
    }
    //saving new messages
    @Override
    public void onResume()
    {
        super.onResume();
        ((MessageAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_timeline, menu);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_settings :
                IntentHelper.startActivity(getActivity(), SettingsActivity.class);
                break;
            case R.id.new_tweet:
                Message message = new Message();
        timeline.addMessage(message);
                Intent i = new Intent(getActivity(), MytweetPagerActivity.class);
                i.putExtra(MessageFragment.EXTRA_MESSAGE_ID, message.id);
                startActivityForResult(i, 0);
                return true;
                default:
                return super.onOptionsItemSelected(item);
            case R.id.action_clear :
                IntentHelper.startActivity(getActivity(), TimelineListActivity.class);
                timeline.deleteAllMessages();
                break;
        }
        return true;
    }
    //to edit an individual tweet
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Message message = adapter.getItem(position);
        IntentHelper.startActivityWithData(getActivity(), MytweetPagerActivity.class, "MESSAGE_ID", message.id);
    }

    /* ************ MultiChoiceModeListener methods (begin) *********** */
    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.message_list_context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_delete_message:
                deleteMessage(mode);
                return true;
            default:
                return false;
        }
    }

    private void deleteMessage(ActionMode actionMode)
    {
        for (int i = adapter.getCount() - 1; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                timeline.deleteMessage(adapter.getItem(i));
            }
        }
        actionMode.finish();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
    /* ************ MultiChoiceModeListener methods (end) *********** */
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


