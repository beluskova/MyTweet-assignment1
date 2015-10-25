package anna.android.helpers;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.provider.ContactsContract;


public class IntentHelper
{
    public static void startActivity (Activity parent, Class classname)
    {
        Intent intent = new Intent(parent, classname);
        parent.startActivity(intent);
    }

    public static void startActivityWithData (Activity parent, Class classname, String extraID, Serializable extraData)
    {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivity(intent);
    }

    public static void startActivityWithDataForResult (Activity parent, Class classname, String extraID, Serializable extraData, int idForResult)
    {
        Intent intent = new Intent(parent, classname);
        intent.putExtra(extraID, extraData);
        parent.startActivityForResult(intent, idForResult);
    }

    public static void navigateUp(Activity parent)
    {
        Intent upIntent = NavUtils.getParentActivityIntent(parent);
        NavUtils.navigateUpTo(parent, upIntent);
    }

    //a method to choose contact from the contact list
    public static void selectContact(Activity parent, int id)
    {
        Intent selectContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        parent.startActivityForResult(selectContactIntent, id);
    }

    //a method to send email by pressing the Email Tweet via button
    public static void sendEmail(Context context, String email, String subject, String body)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(emailIntent, "Sending Email"));
    }
}
