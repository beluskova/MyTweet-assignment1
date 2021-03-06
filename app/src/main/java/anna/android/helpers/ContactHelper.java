//a new helping class to select contact's name or their email (Select Contact button)
package anna.android.helpers;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.content.ContentResolver;


public class ContactHelper
{
    public static String getDisplayName(Context context, Intent data)
    {
        String contact = "unable to find contact";
        Uri contactUri = data.getData();
        String[] queryFields = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
        Cursor c = context.getContentResolver().query(contactUri, queryFields, null, null, null);
        if (c.getCount() == 0)
        {
            c.close();
            return contact;
        }
        c.moveToFirst();
        contact = c.getString(0);
        c.close();

        return contact;
    }

    //to display email address on the Select Contact button
    public static String getEmail(Context context, Intent data)
    {
        String email = "no email";

        Uri contactUri = data.getData();
        ContentResolver cr = context.getContentResolver();

        Cursor cur = cr.query(contactUri, null, null, null, null);

        if (cur.getCount() > 0)
        {
            try
            {
                cur.moveToFirst();
                String contactId = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor emails = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
                emails.moveToFirst();
                email = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                emails.close();
            }
            catch (Exception e)
            {
            }
        }
        return email;
    }

    public static String getContact(Context context, Intent data)
    {
        String contact = "unable to find contact";
        Uri contactUri = data.getData();
        String[] queryFields = new String[] { ContactsContract.Contacts.DISPLAY_NAME };
        Cursor c = context.getContentResolver().query(contactUri, queryFields, null, null, null);
        if (c.getCount() == 0)
        {
            c.close();
            return contact;
        }
        c.moveToFirst();
        contact = c.getString(0);
        c.close();

        return contact;
    }
}
