package kmods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whatsapp.App;
import com.whatsapp.Conversation;
import com.whatsapp.GroupChatInfo;
import com.whatsapp.TextEmojiLabel;
import com.whatsapp.data.bl;

import java.io.File;
import java.util.LinkedHashMap;

import static kmods.Privacy.JID;

public class Utils {
    static String ver = "2.6";
    static String[] vers = ver.split("\\.");
    public static LinkedHashMap<String, Integer> GCounter;
    static Context ctx;
    private static SQLiteOpenHelper sql;
    private static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    //Menu
    public static void addMenu(final com.whatsapp.HomeActivity a, MenuItem b){
        if(b.getItemId() == getResID("mods", "id")){
            a.startActivity(new Intent(a, kmods.Settings.class));
        }
        if(b.getItemId() == getResID("privacy", "id")){
            a.startActivity(new Intent(a, kmods.Privacy.class));
        }
        if(b.getItemId() == getResID("openchat", "id")){
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(a);
            builder.setTitle("New Chat");
            builder.setMessage("Enter Number");
            final EditText input = new EditText(a);
            input.setHint("Ex: +91 XXXXXXXXXX");
            builder.setView(input);
            builder.setPositiveButton("Message!",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            String number = input.getText().toString().trim().replace("+", "").replace(" ", "");
                            String s = number + "@s.whatsapp.net";
                            if (number.contains("-")) {
                                s = number + "@g.us";
                            }
                            a.startActivity(Utils.OpenChat(s));
                        }
                    });
            builder.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
    }
    public static MenuItem setMenuS(Menu menu){
        return menu.add(1,getResID("mods", "id"),0,getResID("Mods", "string"));
    }
    public static MenuItem setMenuP(Menu menu){
        return menu.add(1,getResID("privacy", "id"),0,getResID("Privacy", "string"));
    }
    public static MenuItem setMenuC(Menu menu){
        return menu.add(1,getResID("openchat", "id"),0,getResID("open_chat", "string"));
    }
    //Group Counter
    public static void SetDB(final SQLiteOpenHelper sql) {
        Utils.sql = sql;
    }
    public static LinkedHashMap GetGroupMsgs(final String s) {
        final LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        final Cursor rawQuery = Utils.sql.getReadableDatabase().rawQuery("SELECT remote_resource, count(remote_resource) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND remote_resource!=\"\" GROUP BY remote_resource UNION SELECT remote_resource, count(key_from_me) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND key_from_me=1 And receipt_server_timestamp!=-1 GROUP BY remote_resource ORDER BY total DESC", null);
        rawQuery.moveToFirst();
        if (rawQuery == null || rawQuery.getCount() <= 0) {
            rawQuery.close();
            return linkedHashMap;
        }
        do {
            linkedHashMap.put(rawQuery.getString(0), rawQuery.getInt(1));
        } while (rawQuery.moveToNext());
        rawQuery.close();
        return linkedHashMap;
    }
    public static void GetGroupMsgs2(final String s) {
        final LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        final Cursor rawQuery = Utils.sql.getReadableDatabase().rawQuery("SELECT remote_resource, count(remote_resource) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND remote_resource!=\"\" GROUP BY remote_resource UNION SELECT remote_resource, count(key_from_me) as total FROM messages WHERE key_remote_jid=\"" + s + "\" AND key_from_me=1 And receipt_server_timestamp!=-1 GROUP BY remote_resource ORDER BY total DESC", null);
        rawQuery.moveToFirst();
        if (rawQuery == null || rawQuery.getCount() <= 0) {
            rawQuery.close();
            GCounter = linkedHashMap;
            return;
        }
        do {
            linkedHashMap.put(rawQuery.getString(0), rawQuery.getInt(1));
        } while (rawQuery.moveToNext());
        rawQuery.close();
        GCounter = linkedHashMap;
    }
    public static void SetGroupMsgs(String s, final GroupChatInfo groupChatInfo, final View view) {
        final TextView textView = (TextView)view.findViewById(getResID("gcounter", "id"));
        if (groupChatInfo.Counter != null) {
            if(getBoolean("gCounter_check")) {
                textView.setVisibility(View.VISIBLE);
            }
            if (s.equals("me")) {
                s = null;
            }
            if (groupChatInfo.Counter.get(s) != null) {
                textView.setText(new StringBuilder().append(groupChatInfo.Counter.get(s)));
                return;
            }
            textView.setText("0");
        }
    }
    //multitask
    public static Intent multiTask(Intent intent){
        if(getBoolean("Multi_chats") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }
    //OpenChat
    private static Intent OpenChat(String number){
        try {
            return Conversation.a(number);
        } catch (Exception e){
            return null;
        }
    }
    public static Intent OpenChat2(String number){
        try {
            Intent intent = new Intent(App.n().getApplicationContext(), Conversation.class);
            if (getBoolean("Multi_chats") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            }
            intent.putExtra("jid", number);
            return intent;
        } catch (Exception e){
            return null;
        }
    }
    //Make Call
    public static CharSequence[] Calldialog(final Conversation.a convo) {
        return new CharSequence[] { convo.a(getResID("audio_call", "string")), convo.a(getResID("video_call", "string")), convo.a(getResID("call_phone", "string")) };
    }
    public static void dialNumber(Activity act, bl bl){
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + com.whatsapp.data.bl.b(bl.t)));
        act.startActivity(intent);
    }
    //ActionBar
    public static void DoColor(final android.support.v7.a.a actionbar, final android.support.v7.a.d act) {
        try{
            if(act instanceof com.whatsapp.HomeActivity){                
                if(Utils.Auto_update()) {
                    new Update2(act).execute((String[]) new String[0]);
                }
                CharSequence name = "WhatsApp";
                if (getBoolean("show_my_name_check")) {
                    name = getUserName(act);
                    if (getBoolean("show_my_status_check") && getBoolean("old_status")) {
                        actionbar.b(getStatus(act));
                    }
                }
                actionbar.a(name);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static String getUserName(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences", 0);
        return prefs.getString("push_name", "WhatsApp");
    }
    private static String getStatus(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences", 0);
        return prefs.getString("my_current_status", "_Set_Your_Status_");
    }
    //Media Size
    public static int getUpSize() {
        int n = 16;
        if(getBoolean("up_size")){
            n = 1024;
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
    //online toast
    public static void OnlineToast(String s){
        if(getBoolean("contact_online_toast")) {
            kmods.plus.Utils.checkContactOnline(App.n(), s, App.R.jabber_id);
            //checkContactOnline(App.n(), s, App.R.jabber_id);
        }
    }
    //Exo Init
    public static void init(Context ctx){
        if (ctx instanceof Activity) {
            Utils.ctx = ctx.getApplicationContext();
            Privacy.pctx = ctx.getApplicationContext();
            Settings.sctx = ctx.getApplicationContext();
        } else {
            Utils.ctx = ctx;
            Privacy.pctx = ctx;
            Settings.sctx = ctx;
        }
        if(Utils.ctx == null || Privacy.pctx == null || Settings.sctx == null){
            Log.d("KMods", "Context var initialized to NULL!!!");
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            final ShortcutsManager shortcutsManager = ShortcutsManager.getShortcutsManager(ctx);
            if (shortcutsManager != null) {
                shortcutsManager.loadShortcuts();
            }
        }
        PrefSet();
    }
    private static void PrefSet(){
        int n = 1;
        int n2 = 1;
        if(!getBoolean("gif_s_provider")) {
            n = 0;
        }
        if(!getBoolean("old_status")) {
            n2 = 0;
        }
        SetPrefString("documents", "csv,pdf,txt,doc,docx,xls,xlsx,ppt,pptx,apk,zip,unknown");
        SetPrefInt("media_limit_mb",700);
        SetPrefInt("gif_provider", n);
        SetPrefInt("status_v2", n2);
        SetPrefInt("subject_length_limit", 45);
    }
    //Other
    static void refreshApplication(Activity activity) {
        Intent app = activity.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(activity.getBaseContext().getPackageName());
        app.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent current = new Intent(activity, com.whatsapp.HomeActivity.class);
        activity.finish();
        activity.startActivity(app);
        activity.startActivity(current);
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
    public static boolean Archv_chats() {
        return getBoolean("Archv_chats");
    }
    public static boolean Audio_ears() {
        return getBoolean("Audio_ears");
    }
    public static boolean Audio_sensor() {
        return getBoolean("Audio_sensor");
    }
    private static boolean Auto_update() {
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
    public static void TxtSelect(TextEmojiLabel tl) {
        if(getBoolean("txt_select")){
            tl.setTextIsSelectable(true);
        }
    }
    public static boolean getHideInfo() {
        return getBoolean("hideinfo");
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
    static int getResID(String name, String type){
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }
    private static void checkContactOnline(final Context ctxt,final String s, final String s2) {
        try {
            String stripJID = s;
            if (stripJID.contains("@g.us") || stripJID.contains("@s.whatsapp.net") || stripJID.contains("@broadcast")) {
                stripJID = stripJID.substring(0, stripJID.indexOf("@"));
            }
            if ((!s.contains("-") && !stripJID.equals(s2)) && (s != null && !s.isEmpty())) {
                new ContactToast(ctxt).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[])new String[] { stripJID });
            }
        }
        catch (Exception ignored) {}
    }
}