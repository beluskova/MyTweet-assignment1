package anna.models;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Message
{
    public UUID id;
    public String  editStatus;
    public Date    date;


    public Message()
    {
        id = UUID.randomUUID();
        this.date = new Date();
    }

    public void setMessage(String editStatus)
    {
        this.editStatus = editStatus;
    }

    public String getDateString()
    {
        return DateFormat.getDateTimeInstance().format(date);
    }
}
