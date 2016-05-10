package com.mega;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Update2 extends AsyncTask<String, String, String>
{
    private int a = 0;
    private int b = 0;
    private int v1 = 1;
    private int v2 = 3;
    private Context ctx;
    public Update2(Context ctx){
        this.ctx = ctx;
    }
    protected String doInBackground(final String... array) {
        try {
            InputStreamReader in = new InputStreamReader(new URL("http://kwhatsapp.tk/update/UpdateC.php").openStream());
            BufferedReader br = new  BufferedReader(in);
            String string = "";
            while (true) {
                final String line = br.readLine();
                if (line == null) {
                    break;
                }
                string = String.valueOf(string) + line;
            }
            final JSONObject jsonObject = new JSONObject(string);
            this.a = jsonObject.getInt("v1");
            this.b = jsonObject.getInt("v2");
            return "1";
        }
        catch (Exception ex) {
            return "?";
        }
    }
    protected void onPostExecute(final String s) {
        if (this.a > this.v1 || this.b > this.v2) {
            WebView wv = new WebView(ctx);
            wv.loadUrl("http://kwhatsapp.tk/update/CL.html");
            AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
            builder.setTitle("New Update Of KWhatsApp v" + this.a + "." + this.b);
            builder.setView(wv);
            builder.setPositiveButton("Download Now", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ctx.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://kwhatsapp.tk/")));
                }
            });
            builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(ctx, "Ok Update Later!", 0).show();
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
        }
    }
}