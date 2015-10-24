package anna.app;

import android.app.Application;
import anna.models.Timeline;
import static anna.android.helpers.LogHelpers.info;

public class MyTweetApp extends Application {
    public Timeline timeline;

    @Override
    public void onCreate() {
        super.onCreate();
        timeline = new Timeline();

        info(this, "MyTweet app launched");
    }

}
