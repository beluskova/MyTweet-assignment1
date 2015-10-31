//a new class to support swipe activity
package anna.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.UUID;

import anna.app.MyTweetApp;
import anna.models.Message;
import anna.models.Timeline;
import annab.mytweetActivity.R;

import static anna.android.helpers.LogHelpers.info;


public class MytweetPagerActivity extends FragmentActivity  implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private ArrayList<Message> messages;
    private Timeline timeline;
    private PagerAdapter pagerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.mytweet_activity);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);
        setMessageList();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), messages);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(this);
        setCurrentItem();
    }

    private void setMessageList() {
        MyTweetApp app = (MyTweetApp) getApplication();
        timeline = app.timeline;
        messages = timeline.messages;
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        info(this, "onPageScrolled: arg0 " + arg0 + " arg1 " + arg1 + " arg2 " + arg2);
        Message message = messages.get(arg0);
        if (message.editStatus != null) {
            setTitle(message.editStatus);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    //Ensure selected residence is shown in details view
    private void setCurrentItem() {
        UUID res = (UUID) getIntent().getSerializableExtra(MessageFragment.EXTRA_MESSAGE_ID);
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).id.toString().equals(res.toString())) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Message> messages;

        public PagerAdapter(FragmentManager fm, ArrayList<Message> messages) {
            super(fm);
            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Fragment getItem(int pos) {
            Message message = messages.get(pos);
            Bundle args = new Bundle();
            args.putSerializable(MessageFragment.EXTRA_MESSAGE_ID, message.id);
            MessageFragment fragment = new MessageFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }
}


