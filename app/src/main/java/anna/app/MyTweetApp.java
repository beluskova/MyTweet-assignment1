package anna.app;

import android.app.Application;
import anna.models.Timeline;
import anna.models.TimelineSerializer;

import static anna.android.helpers.LogHelpers.info;

public class MyTweetApp extends Application
{
    //adding new file where tweets are saved to
    private static final String FILENAME = "portfolio.json";
    public Timeline timeline;

    @Override
    public void onCreate()
    {
        super.onCreate();
        //adding serializer to make sure the tweets are saved when we leave the app
        TimelineSerializer serializer = new TimelineSerializer(this, FILENAME);
        timeline = new Timeline(serializer);

        info(this, "MyTweet app launched");
    }

}
