package anna.models;

import java.util.ArrayList;
import java.util.UUID;

import android.util.Log;

import static anna.android.helpers.LogHelpers.info;

public class Timeline

{
    public  ArrayList<Message> messages;
    private TimelineSerializer   serializer;

    // timeline is refactored so it includes the serializer now to make sure the tweets are saved when we leave the app
    public Timeline(TimelineSerializer serializer)
    {
        this.serializer = serializer;
        try {
            messages = serializer.loadMessages();
        }
        catch (Exception e)
        {
            info(this, "Error loading messages: " + e.getMessage());
            messages = new ArrayList<Message>();
        }
    }
    //tweets saved through the setializer
    public boolean saveMessages()
    {
        try
        {
            serializer.saveMessages(messages);
            info(this, "Messages saved to file");
            return true;
        }
        catch (Exception e)
        {
            info(this, "Error saving messages: " + e.getMessage());
            return false;
        }
    }

    public void addMessage(Message message)
    {
        messages.add(message);
    }

    public Message getMessage(UUID id)
    {
        Log.i(this.getClass().getSimpleName(), "UUID parameter id: " + id);

        for (Message mes : messages)
        {
            if(id.equals(mes.id))
            {
                return mes;
            }
        }
        info(this, "failed to find message. returning first element array to avoid crash");
        return null;
    }

    private void generateTestData()
    {
        for(int i = 0; i < 3; i += 1)
        {
            Message m = new Message();
            m.editStatus = "I'm just saying hello";
            messages.add(m);
        }
    }

    //to delete all tweets
    public void deleteAllMessages()
    {
        messages = new ArrayList<Message>();
    }

    //to delete a single tweet
    public void deleteMessage(Message m)
    {
        messages.remove(m);
    }
}