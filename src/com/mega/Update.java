package com.mega;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Update extends AsyncTask<Void, Void, String> {
    public static Context ctx;
    boolean d;
    int a = 0;
    int b = 0;
    String c;
    protected Update(Context ctx) {
       Update.ctx = ctx;
    }
    protected String doInBackground(Void... params) {
        try {
            final String line = new BufferedReader(new InputStreamReader(new URL("http://kwhatsapp.tk/update/UpdateC").openStream())).readLine();
            if (line != null) {
                final JSONObject jsonObject = new JSONObject(line);
                this.a = jsonObject.getInt("v1");
                this.b = jsonObject.getInt("v2");
                this.c = jsonObject.getString("v3");
            }
            return null;
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (IOException ex2) {
            ex2.printStackTrace();
            return null;
        }
        catch (JSONException ex3) {
            ex3.printStackTrace();
            return null;
        }
    }
    protected void onPostExecute(final Void void1) {
        if (a > 1 || b > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("KWhatsApp v " + a + "." + b);
            builder.setMessage("Changelog: " + c);
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
            AlertDialog.Builder builder2 = new AlertDialog.Builder(ctx);
            builder2.setTitle("Good!").setMessage("You have latest update!");
            builder2.create();
            builder2.show();
        }
    }
}
