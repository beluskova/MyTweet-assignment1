package anna.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import annab.mytweetActivity.R;


public class TimelineActivity extends Activity {
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
                startActivity(new Intent(this, MytweetActivity.class));
                break;
            case R.id.action_clear:
                Toast toast1 = Toast.makeText(this, "Clear Selected", Toast.LENGTH_SHORT);
                toast1.show();
                break;
        }
        return true;
    }
}

