package in.kmods.kwhatsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.whatsapp.ContactInfo;
import com.whatsapp.Conversation;
import com.whatsapp.HomeActivity;
import com.whatsapp.MediaView;
import com.whatsapp.QuickContactActivity;
import com.whatsapp.ViewProfilePhoto;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class KMODs {
    private static Context ctx;
    private static Prefs prefs;
    public static ClassLoader cl;

    public static Context getContext(){
        return ctx;
    }

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

        XposedHelpers.findAndHookMethod("com.whatsapp.DialogToastActivity", cl, "onResume", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                Activity act = (Activity) param.thisObject;
                if(act == null)
                    return;

                if(act instanceof PreferenceActivity)
                    DoColorP((PreferenceActivity) act);
                else
                    DoColor(act);
            }
        });
    }

    private static void DoColor(Activity act){
        try {
            boolean coloredToolbarColor = !(act instanceof MediaView) &&
                    !(act instanceof ViewProfilePhoto) &&
                    !(act instanceof QuickContactActivity) &&
                    !(act instanceof ContactInfo);

            if(coloredToolbarColor){
                final ViewGroup actionbarVG = (ViewGroup) act.findViewById(Resources.id.action_bar);
                if (actionbarVG != null) {
                    actionbarVG.setBackground(new ColorDrawable(ColorsManager.getColor(ColorsManager.TOOLBAR)));
                    actionbarVG.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            for (int i = 0; i < actionbarVG.getChildCount(); i++) {
                                View child = actionbarVG.getChildAt(i);
                                if (child instanceof TextView) {
                                    ((TextView) child).setTextColor(
                                            ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE)
                                    );
                                }
                                if (child instanceof ImageView) {
                                    ((ImageView) child).setColorFilter(
                                            ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS),
                                            PorterDuff.Mode.MULTIPLY
                                    );
                                }
                                if (child instanceof ImageButton) {
                                    ((ImageButton) child).setImageDrawable(
                                            tintToColor(((ImageButton) child).getDrawable(),
                                                    ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS))
                                    );
                                }
                            }
                        }
                    });
                }
            }

            final Toolbar toolbar = (Toolbar) act.findViewById(Resources.id.toolbar);
            if(toolbar != null){
                toolbar.setBackgroundColor(ColorsManager.getColor(ColorsManager.TOOLBAR));
                toolbar.setTitleTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE));
                toolbar.setSubtitleTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_SUBTITLE));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toolbar.setOverflowIcon(tintToColor(toolbar.getOverflowIcon(), ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS)));
                }

                toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        for (int i=0; i<toolbar.getChildCount(); i++) {
                            View view = toolbar.getChildAt(i);
                            if (view instanceof ImageButton) {
                                ((ImageButton) view).setImageDrawable(
                                        tintToColor(((ImageButton) view).getDrawable(),
                                                ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS))
                                );
                            }
                            if (view instanceof ImageView) {
                                ((ImageView) view).setColorFilter(
                                        ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS),
                                        PorterDuff.Mode.MULTIPLY
                                );
                            }
                        }
                    }
                });

                if (act instanceof Conversation) {
                    TextView conversation_contact_name = (TextView) toolbar.findViewById(Resources.id.conversation_contact_name);
                    if(conversation_contact_name != null)
                        conversation_contact_name.setTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE));

                    TextView conversation_contact_status = (TextView) toolbar.findViewById(Resources.id.conversation_contact_status);
                    if(conversation_contact_status != null)
                        conversation_contact_status.setTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE));
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (coloredToolbarColor)
                    act.getWindow().setStatusBarColor(ColorsManager.getColor(ColorsManager.STATUSBAR));

                act.getWindow().setNavigationBarColor(ColorsManager.getColor(ColorsManager.NAVBAR));

                if (!isColorDark(act.getWindow().getStatusBarColor(), 0.5)
                        && Build.VERSION.SDK_INT >= 23)
                    act.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                if (act instanceof QuickContactActivity)
                    act.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }

            if(act instanceof HomeActivity)
                in.kmods.kwhatsapp.WAClass.HomeActivity._onCreate((HomeActivity) act);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void DoColorP(PreferenceActivity act) {
        try {
            ActionBar actionbar = act.getActionBar();
            if(actionbar != null){
                actionbar.setBackgroundDrawable(new ColorDrawable(ColorsManager.getColor(ColorsManager.TOOLBAR)));
                final ViewGroup actionbarVG = (ViewGroup) act.findViewById(Resources.id.action_bar);
                if (actionbarVG != null) {
                    actionbarVG.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            for (int i = 0; i < actionbarVG.getChildCount(); i++) {
                                View child = actionbarVG.getChildAt(i);
                                if (child instanceof TextView)
                                    ((TextView) child).setTextColor(
                                            ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE)
                                    );
                                if (child instanceof ImageButton)
                                    ((ImageButton) child).setColorFilter(
                                            ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS),
                                            PorterDuff.Mode.MULTIPLY
                                    );
                            }
                        }
                    });
                }
            }

            final Toolbar toolbar = (Toolbar) act.findViewById(Resources.id.toolbar);
            if (toolbar != null) {
                toolbar.setBackgroundColor(ColorsManager.getColor(ColorsManager.TOOLBAR));
                toolbar.setTitleTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_TITLE));
                toolbar.setSubtitleTextColor(ColorsManager.getColor(ColorsManager.TOOLBAR_SUBTITLE));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toolbar.setOverflowIcon(tintToColor(toolbar.getOverflowIcon(), ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS)));
                }

                toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        for (int i=0; i<toolbar.getChildCount(); i++) {
                            View view = toolbar.getChildAt(i);
                            if (view instanceof ImageButton) {
                                ((ImageButton) view).setImageDrawable(
                                        tintToColor(((ImageButton) view).getDrawable(),
                                                ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS))
                                );
                            }
                            if (view instanceof ImageView){
                                ((ImageView) view).setColorFilter(
                                        ColorsManager.getColor(ColorsManager.TOOLBAR_ICONS),
                                        PorterDuff.Mode.MULTIPLY
                                );
                            }
                        }
                    }
                });
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                act.getWindow().setStatusBarColor(ColorsManager.getColor(ColorsManager.STATUSBAR));
                act.getWindow().setNavigationBarColor(ColorsManager.getColor(ColorsManager.NAVBAR));
                if (!isColorDark(ColorsManager.getColor(ColorsManager.STATUSBAR), 0.5) &&
                        Build.VERSION.SDK_INT >= 23) {
                    act.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    act.getWindow().getDecorView().setSystemUiVisibility(0);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //Other Methods
    public static void tintDialog(Dialog dialog) {
        View content = dialog.findViewById(android.R.id.content);
        content.setBackgroundColor(ColorsManager.getColor(ColorsManager.BACKGROUND));
    }

    public static void tintAndShowDialog(AlertDialog.Builder dialog) {
        Dialog dialog1 = dialog.create();
        dialog1.show();
        View content = dialog1.findViewById(android.R.id.content);
        content.setBackgroundColor(ColorsManager.getColor(ColorsManager.BACKGROUND));
    }

    public static String getUserName() {
        return prefs.read("push_name", "WhatsApp");
    }

    public static String getStatus() {
        return prefs.read("my_current_status", "_Set_Your_Status_");
    }

    public static Drawable getUserPicture() {
        String s = getApplicationPath();
        String pathName = s + "/files/me.jpg";
        return Drawable.createFromPath(pathName);
    }

    public static int getResID(String name, String type) {
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }

    private static String getApplicationPath() {
        return ctx.getApplicationInfo().dataDir;
    }

    public static int[] Nexus6PResToActualDevice(Context ctx, int x, int y) {
        int[] newValues = new int[2];
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();

        newValues[0] = (metrics.widthPixels * x) / 1440;
        newValues[1] = (metrics.heightPixels * y) / 2560;

        return newValues;
    }

    public static String getVersionName() {
        return "v2.6";
    }

    public static String getWhatsAppVersionName() {
        String versionName = "";
        try {
            PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            versionName = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static Drawable tintToColor(Drawable drawable, int color) {
        if (drawable == null) return null;
        int red   = (color & 0xFF0000) / 0xFFFF;
        int green = (color & 0xFF00) / 0xFF;
        int blue  = color & 0xFF;
        float[] matrix = { 0, 0, 0, 0, red,
                0, 0, 0, 0, green,
                0, 0, 0, 0, blue,
                0, 0, 0, 1, 0 };
        ColorFilter colorFilter = new ColorMatrixColorFilter(matrix);
        drawable.setColorFilter(colorFilter);
        return drawable;
    }

    public static boolean isColorDark(final int n, final double n2) {
        return 1.0 - (0.299 * Color.red(n) + 0.587 * Color.green(n) + 0.114 * Color.blue(n)) / 255.0 >= n2;
    }
}
