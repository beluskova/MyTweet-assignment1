package anna.app;

import android.app.Application;
import android.os.Bundle;

import anna.models.Timeline;
import annab.mytweetActivity.R;

public class MyTweetApp extends Application {
    public Timeline timeline;

    @Override
    public void onCreate() {
        super.onCreate();
        timeline = new Timeline();
    }

}
