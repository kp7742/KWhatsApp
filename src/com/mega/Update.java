package com.mega;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.net.URL;

public class Update extends AsyncTask<Void, Void, String> {
    ProgressDialog progDlg;
    boolean d;
    int a = 0;
    int b = 0;
    String c;
    protected void onPostExecute(final Void void1) {
        if (a > 1 || b > 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(Mega.ctx);
            builder.setTitle((CharSequence)("KWhatsApp v " + this.a + "." + this.b));
            builder.setMessage((CharSequence)("Changelog: " + this.c));
            builder.setPositiveButton("Download Now", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
        }
        else {
            final AlertDialog.Builder builder2 = new AlertDialog.Builder(Mega.ctx);
            builder2.setTitle((CharSequence)"Good!").setMessage((CharSequence)"You have latest update!");
            builder2.create().show();
        }
    }
    @Override
    protected String doInBackground(Void... params) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new URL("http://kwhatsapp.tk/update/UpdateC").openStream());
            String string = "";
            while (true) {
                final String line = dataInputStream.readLine();
                if (line == null) {
                    break;
                }
                string = (Object)string + line;
            }
            final JSONObject jsonObject = new JSONObject((String)string);
            a = jsonObject.getInt("v1");
            b = jsonObject.getInt("v2");
            c = jsonObject.getString("v3");
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
