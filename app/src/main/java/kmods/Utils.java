package kmods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.whatsapp.App;
import com.whatsapp.GroupChatInfo;
import io.fabric.sdk.android.Fabric;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import static kmods.Privacy.JID;

public class Utils {
    static int v1 = 2;
    static int v2 = 3;
    private static Context ctx;
    private static SQLiteOpenHelper sql;
    private static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    static void toTxt(String str, String name){
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory().getPath(), name));
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void SetDB(final SQLiteOpenHelper sql) {
        Utils.sql = sql;
    }
    public static LinkedHashMap GetGroupMsgs(final String s) {
        final LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        final Cursor rawQuery = Utils.sql.getReadableDatabase().rawQuery("SELECT remote_resource, count(remote_resource) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND remote_resource!=\"\" GROUP BY remote_resource UNION SELECT remote_resource, count(key_from_me) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND key_from_me=1 And receipt_server_timestamp!=-1 GROUP BY remote_resource ORDER BY total DESC", (String[])null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
        }
        else {
            do {
                linkedHashMap.put(rawQuery.getString(0), rawQuery.getInt(1));
            } while (rawQuery.moveToNext());
            rawQuery.close();
        }
        return linkedHashMap;
    }
    public static void SetMsgs(final String s, final GroupChatInfo groupChatInfo, final View view) {
        final LinkedHashMap counter = groupChatInfo.Counter;
        final TextView textView = (TextView)view.findViewById(getResID("gcounter", "id"));
        if (counter != null) {
            if(getBoolean("gCounter_check")) {
                textView.setVisibility(View.VISIBLE);
            }
            String s2 = s;
            if (s.equals("me")) {
                s2 = null;
            }
            if (groupChatInfo.Counter.get(s2) == null) {
                textView.setText("0");
            }
            else {
                textView.setText(String.valueOf(groupChatInfo.Counter.get(s2)));
            }
        }
    }
    public static Drawable getCwallp(final Drawable drawable) {
        try {
            final String string = "/data/data/com.whatsapp/files/" + JID + "_wallpaper.jpg";
            final File file = new File("/data/data/com.whatsapp/files/wallpaper.jpg");
            Drawable drawable2;
            if (new File(string).exists() && getBoolean("custom_wallp")) {
                drawable2 = Drawable.createFromPath(string);
            } else {
                drawable2 = drawable;
                if (file.exists() && file.length() > 2L) {
                    drawable2 = Drawable.createFromPath("/data/data/com.whatsapp/files/wallpaper.jpg");
                }
            }
            return drawable2;
        }
        catch (Exception ex) {
            return drawable;
        }
    }
    public static String setCwallp(final String s) {
        String name = s;
        final String jid = JID;
        try {
            if (getBoolean("custom_wallp") && jid != null) {
                name = jid.concat("_").concat(s);
            }
            return name;
        }
        catch (Exception ex) {
            return name;
        }
    }
    public static Intent multiTask(Intent intent){
        if(getBoolean("Multi_chats") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }
    public static void ShowName(final android.support.v7.app.a actionBar, final Context ctx) {
        CharSequence name = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            name = App.b(ctx);
            if (getBoolean("show_my_status_check")) {
                actionBar.b(App.e());
            }
        }
        actionBar.a(name);
    }
    public static int getUpSize() {
        int n = 16;
        if(getBoolean("up_size")){
            n = 5120;
        }
        return n;
    }
    public static int getMaxImg() {
        int n = 1600;
        if(getBoolean("high_res")){
            n = 5315;
        }
        return n;
    }
    static int getResID(String name, String type){
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }
    public static boolean contact_online_toast() {
        return getBoolean("contact_online_toast");
    }
    private static void SetPrefString(final String name, final String value) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        edit.putString(name, value);
        edit.apply();
    }
    private static void SetPrefInt(final String name, final int value) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        edit.putInt(name, value);
        edit.apply();
    }
    private static void SetPrefBool(final String name, final Boolean value) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        edit.putBoolean(name, value);
        edit.apply();
    }
    public static void init(Context ctx){
        if (ctx instanceof Activity) {
            Utils.ctx = ctx.getApplicationContext();
            Privacy.pctx = ctx.getApplicationContext();
        } else {
            Utils.ctx = ctx;
            Privacy.pctx = ctx;
        }
        if(Utils.ctx == null || Privacy.pctx == null){
            Log.d("KMods", "Context var initialized to NULL!!!");
        }
        Fabric.with(ctx.getApplicationContext(), new Answers(), new Crashlytics());
        PrefSet();
    }
    public static String ChangeP(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp")
                .replace("com.whatsapp.util", "com.whatsapp.util")
                .replace("com.whatsapp.Voip", "com.whatsapp.Voip")
                .replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter")
                .replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer")
                .replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder")
                .replace("com.whatsapp.proto", "com.whatsapp.proto");
    }
    public static String ChangeF(final String s) {
        String replace = s;
        String WA = "KWhatsApp";
        if (WA.equals("KWhatsApp")) {
            return replace;
        }
        try {
            final String lowerCase = s.toLowerCase();
            if (!lowerCase.contains("whatsapp")) {
                replace = s;
                if (!lowerCase.contains("WhatsApp")) {
                    return replace;
                }
            }
            replace = s;
            if (!lowerCase.contains("k")) {
                replace = s;
                if (!lowerCase.contains(".jpg")) {
                    replace = s;
                    if (!lowerCase.contains(".3gp")) {
                        replace = s;
                        if (!lowerCase.contains(".mp4")) {
                            replace = s;
                            if (!lowerCase.contains(".mp3")) {
                                replace = s;
                                if (!lowerCase.contains(".3ga")) {
                                    replace = s;
                                    if (!lowerCase.contains(".aac")) {
                                        replace = s;
                                        if (!lowerCase.contains(".m4a")) {
                                            replace = s.replace("whatsapp", "kwhatsapp").replace("WhatsApp", "KWhatsapp");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return replace;
        }
        catch (Exception ex) {
            replace = s;
            return replace;
        }
    }
    public static boolean Archv_chats() {
        return getBoolean("Archv_chats");
    }
    public static boolean Audio_ears() {
        return getBoolean("Audio_ears");
    }
    public static boolean Audio_sensor() {
        return getBoolean("Audio_sensor");
    }
    public static boolean Auto_update() {
        return getBoolean("Auto_update");
    }
    static boolean Auto_restart() {
        return getBoolean("Auto_restart");
    }
    public static boolean Img_limit() {
        return getBoolean("img_limit");
    }
    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }
    private static void PrefSet(){
        SetPrefString("documents", "csv,pdf,txt,doc,docx,xls,xlsx,ppt,pptx,apk,zip,unknown");
        SetPrefInt("document_limit_mb", 1024);
    }
    public static boolean TxtSelect() {
        return getBoolean("txt_select");
    }
    public static boolean getHideInfo() {
        return getBoolean("hideinfo");
    }
}
