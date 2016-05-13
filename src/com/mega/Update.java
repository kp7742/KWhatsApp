package com.mega;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

public class Update extends AsyncTask<String, String, String>
{
    private int a = 0;
    private int b = 0;
    private ProgressDialog progDlg;
    private Context ctx;
    private static boolean dis;
    public Update(Context ctx, boolean t){
        this.ctx = ctx;
        dis = t;
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
        if (this.a > Main.v1 || this.b > Main.v2) {
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
        if(dis) {
            if (s.equals("?")) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(ctx);
                builder2.setTitle("Error!").setMessage("You Not Connect With Internet!");
                builder2.create();
                builder2.show();
            } else {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(ctx);
                builder3.setTitle("Good!").setMessage("You have latest update!:" + " KWhatsApp v" + Main.v1 + "." + Main.v2);
                builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder3.create();
                builder3.show();
            }
            this.progDlg.dismiss();
        }
    }
    protected void onPreExecute() {
        if(dis) {
            (this.progDlg = new ProgressDialog(ctx)).setMessage("Checking...");
            this.progDlg.setCancelable(true);
            this.progDlg.show();
        }
    }
}