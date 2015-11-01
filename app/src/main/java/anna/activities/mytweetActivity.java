package anna.activities;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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

import anna.app.MyTweetApp;
import anna.models.Message;
import anna.models.Timeline;
import annab.mytweetActivity.R;

import static anna.android.helpers.IntentHelper.navigateUp;

public class mytweetActivity extends FragmentActivity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        setTitle(R.string.mytweet_activity);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null)
        {
            fragment = new MessageFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}


