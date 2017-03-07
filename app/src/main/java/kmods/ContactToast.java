package kmods;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.widget.Toast;

public class ContactToast extends AsyncTask {
    private Context context;
    ContactToast(Context context) {
        this.context = context;
    }
    private String Calculate(String[] strArr) {
        Exception exception;
        String str = strArr[0];
        String str2 = "";
        if (str.isEmpty() || str.length() < 1 || str == null) {
            throw new NullPointerException();
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(str)), new String[]{"display_name"}, null, null, null);
            if (query != null) {
                str = query.moveToFirst() ? query.getString(query.getColumnIndex("display_name")) : str2;
                try {
                    query.close();
                } catch (NullPointerException e) {
                    str2 = str;
                    if (str2 == null) {
                        str2 = "";
                    }
                    return str2;
                } catch (SecurityException e2) {
                    str2 = str;
                    Toast.makeText(context, "Please give WhatsApp permission to Contacts!!\nGoogle: 'android 6.0.1 permissions' for more info.", Toast.LENGTH_LONG).show();
                    return str2;
                } catch (Exception e3) {
                    str2 = str;
                    exception = e3;
                    exception.printStackTrace();
                    return str2;
                }
                query.close();
            }
            str = str2;
            str2 = str;
        } catch (NullPointerException e4) {
            if (str2 == null) {
                str2 = "";
            }
            return str2;
        } catch (SecurityException e5) {
            Toast.makeText(context, "Please give WhatsApp permission to Contacts!!\nGoogle: 'android 6.0.1 permissions' for more info.", Toast.LENGTH_LONG).show();
            return str2;
        } catch (Exception e6) {
            exception = e6;
            exception.printStackTrace();
            return str2;
        }
        return str2;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        Object obj = "null";
        try {
            obj = Calculate((String[]) objects);
        } catch (Exception ignored) {}
        return obj;
    }
    @Override
    protected void onPostExecute(Object o) {
        String str = (String) o;
        if (str != null && !str.equals("null")) {
            super.onPostExecute(str);
            Toast.makeText(context, String.valueOf(str) + " Is Now Online.", Toast.LENGTH_SHORT).show();
        }
    }
}