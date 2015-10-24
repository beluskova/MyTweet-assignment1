package anna.models;

import java.util.ArrayList;
import java.util.UUID;

import android.util.Log;

public class Timeline

{
    public  ArrayList<Message> messages;

    public Timeline()
    {
        messages = new ArrayList<Message>();
        //this.generateTestData();
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
}