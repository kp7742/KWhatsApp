package com.mega;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.Preference;
import android.util.AttributeSet;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.net.URL;

public class UpdateP extends Preference implements Preference.OnPreferenceClickListener
{
    public UpdateP(final Context context) {
        super(context);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public UpdateP(final Context context, final AttributeSet set) {
        super(context, set);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public UpdateP(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        new update().execute((String[]) new String[0]);
        return false;
    }
    
    public class update extends AsyncTask<String, String, String>
    {
        public int a = 0;
        public int b = 0;
        public String c = null;
        ProgressDialog progDlg;
        
        protected String doInBackground(final String... array) {
            try {
                final DataInputStream dataInputStream = new DataInputStream(new URL("http://kwhatsapp.tk/update/UpdateC").openStream());
                String string = "";
                while (true) {
                    final String line = dataInputStream.readLine();
                    if (line == null) {
                        break;
                    }
                    string = String.valueOf(string) + line;
                }
                final JSONObject jsonObject = new JSONObject(string);
                a = jsonObject.getInt("v1");
                b = jsonObject.getInt("v2");
                c = jsonObject.getString("v3");
                return String.valueOf(jsonObject.getString("ver1")) + "." + jsonObject.getString("ver2");
            }
            catch (Exception ex) {
                return "?";
            }
        }
        
        protected void onPostExecute(final String s) {
            if (this.a > 1 || this.b > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.ctx);
                builder.setTitle("KWhatsApp v " + this.a + "." + this.b);
                builder.setMessage("Changelog: " + this.c);
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
                AlertDialog.Builder builder2 = new AlertDialog.Builder(Settings.ctx);
                builder2.setTitle("Good!").setMessage("You have latest update!");
                builder2.create();
                builder2.show();
            }
        }
        
        protected void onPreExecute() {
            (this.progDlg = new ProgressDialog(UpdateP.this.getContext())).setMessage("Connecting...");
            this.progDlg.setCancelable(false);
            this.progDlg.show();
        }
    }
}
