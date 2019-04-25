package in.kmods.kwhatsapp;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class KMODs {
    private static Context ctx;
    private static Prefs prefs;
    public static ClassLoader cl;

    /*
    * Class: c.d.b.a.a.a.c
    * Place: after super.attachBaseContext()
    * invoke-static {p0}, Lin/kmods/kwhatsapp/KMODs;->init(Landroid/content/Context;)V
    */
    public static void init(Context base){
        if(base == null){
            Log.d("KMODs", "Context var initialized to NULL!!!");
            return;
        }

        if(KMODs.ctx == null)
            KMODs.ctx = base;

        if(KMODs.cl == null)
            KMODs.cl = KMODs.class.getClassLoader();

        if(KMODs.prefs == null)
            KMODs.prefs = Prefs.with(KMODs.ctx);

        //Hidden Features
        prefs.writeBoolean("dark_mode_enabled", true);
        prefs.writeBoolean("search_by_image", true);
        prefs.writeBoolean("shape_picker_v2_enabled", true);
        prefs.writeBoolean("payments_web_enabled", true);
        prefs.writeBoolean("group_join_permissions", true);
        prefs.writeBoolean("auth_fingerprint_enabled", true);
        prefs.writeBoolean("stickers_keyboard_integration_enabled", true);
        prefs.writeBoolean("status_ranking", true);
        prefs.writeBoolean("add_contact_by_phone_number_enabled", true);
        prefs.writeBoolean("stad_display", true);
    }

    private static String getUserName(Context ctx) {
        return prefs.read("push_name", "WhatsApp");
    }
    private static String getStatus(Context ctx) {
        return prefs.read("my_current_status", "_Set_Your_Status_");
    }

    public static int getResID(String name, String type) {
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }
}
