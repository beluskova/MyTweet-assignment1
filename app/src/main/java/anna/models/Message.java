package anna.models;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import annab.mytweetActivity.R;

public class Message
{
    public UUID id;
    public String  editStatus;
    public Date    date;
    public String  selectContact;

    private static final String JSON_ID             = "id"            ;
    private static final String JSON_EDITSTATUS    = "editStatus"   ;
    private static final String JSON_DATE           = "date"          ;
    private static final String JSON_CONTACT        = "selectContact";

    public Message()
    {
        this.id = UUID.randomUUID();
        this.date = new Date();

        this.editStatus = editStatus;
        selectContact = "no contact";
    }

    public Message(JSONObject json) throws JSONException
    {
        id = UUID.fromString(json.getString(JSON_ID));
        editStatus   = json.getString(JSON_EDITSTATUS);
        date          = new Date(json.getLong(JSON_DATE));
        selectContact        = json.getString(JSON_CONTACT);
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID            , id.toString());
        json.put(JSON_EDITSTATUS    , editStatus);
        json.put(JSON_DATE          , date.getTime());
        json.put(JSON_CONTACT       , selectContact);
        return json;
    }

    public void setMessage(String editStatus)
    {
        this.editStatus = editStatus;
    }

    public String getDateString()
    {
        return DateFormat.getDateTimeInstance().format(date);
    }

    public String getTweetEmailed(Context context)
    {
        String dateFormat = "EEE, MMM dd";
        String dateString = android.text.format.DateFormat.format(dateFormat, date).toString();
        String report =  "Tweet " + editStatus + " Date: " + dateString + " ";
        return report;
    }
}
