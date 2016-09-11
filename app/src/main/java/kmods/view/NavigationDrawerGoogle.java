package kmods.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.*;
import android.widget.*;

import com.pkmmte.view.CircularImageView;
import com.whatsapp.*;

import static kmods.Utils.getResID;

public class NavigationDrawerGoogle extends RelativeLayout {
    private HomeActivity activity;
    public NavigationDrawerGoogle(Context context) {
        super(context);
        activity = (HomeActivity) context;
        init2();
    }
    public NavigationDrawerGoogle(Context context, AttributeSet attrs) {
        super(context, attrs);
        activity = (HomeActivity) context;
        init2();
    }
    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        activity = (HomeActivity) context;
        init2();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationDrawerGoogle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        activity = (HomeActivity) context;
        init2();
    }
    private void init2() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                init();
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    private void init() {
        final NavigationView navigationView = (NavigationView) activity.findViewById(getResID("mod_drawer", "id"));
        final DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(getResID("drawer_parent", "id"));
        final ViewStub wamod_drawer_header = (ViewStub) activity.findViewById(getResID("drawer_header", "id"));
        wamod_drawer_header.setLayoutResource(getResID("home_drawer_header_centered", "layout"));
        wamod_drawer_header.inflate();
        try {
            int bgColor = Color.parseColor("#404040");
            setBackgroundColor(bgColor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LinearLayout buttons = (LinearLayout) activity.findViewById(getResID("drawer_buttons", "id"));
        for (int i=0; i<buttons.getChildCount(); i++) {
            if (buttons.getChildAt(i) instanceof RelativeLayout) {
                final RelativeLayout item = (RelativeLayout) buttons.getChildAt(i);
                int color = Color.parseColor("#ffffff");
                View label1 = item.getChildAt(0);
                if (label1 != null && label1 instanceof TextView) ((TextView) label1).setTextColor(color);
                View label = item.getChildAt(1);
                if (label != null && label instanceof TextView) ((TextView) label).setTextColor(color);
                View icon = item.getChildAt(0);
                if (icon != null && icon instanceof ImageView) ((ImageView) icon).setColorFilter(color);
                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        int id = item.getId();
                        if (id == getResID("drawer_newgroup", "id")) {
                            intent = new Intent(activity, GroupMembersSelector.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_web", "id")) {
                            intent = new Intent(activity, WebSessionsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_setstatus", "id")) {
                            intent = new Intent(activity, SetStatus.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_starredmessages", "id")) {
                            intent = new Intent(activity, StarredMessagesActivity.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_archivedchats", "id")) {
                            intent = new Intent(activity, ArchivedConversationsActivity.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_privacy", "id")) {
                            intent = new Intent(activity, kmods.Privacy.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_settings", "id")) {
                            intent = new Intent(activity, Settings.class);
                            activity.startActivity(intent);
                        } else if (id == getResID("drawer_modsettings", "id")) {
                            intent = new Intent(activity, kmods.Settings.class);
                            activity.startActivity(intent);
                        }
                        drawerLayout.closeDrawer(navigationView);
                }
                });
            }
        }
        TextView userNameTV                  = (TextView)          findViewById(getResID("drawer_username", "id"));
        TextView userNumberTV                = (TextView)          findViewById(getResID("drawer_userdetail", "id"));
        CircularImageView wamod_drawer_photo = (CircularImageView) findViewById(getResID("drawer_photo", "id"));
        userNameTV.setText(getUserName(activity));
        userNumberTV.setText(getStatus(activity));
        Drawable userPic = getUserPicture(activity);
        if (userPic != null) wamod_drawer_photo.setImageDrawable(userPic);
        wamod_drawer_photo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
                Intent intent = new Intent(activity, ProfileInfoActivity.class);
                activity.startActivity(intent);
            }
        });
        ImageView drawerHeaderBg = (ImageView) findViewById(getResID("drawer_bgview", "id"));
        drawerHeaderBg.setImageDrawable(getResources().getDrawable(getResID("wamod_drawer_bg", "drawable")));
        final CircularImageView wamod_drawer_header_2ndprofilepic = (CircularImageView) findViewById(getResID("drawer_header_2ndprofilepic", "id"));
        wamod_drawer_header_2ndprofilepic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
                Intent intent = new Intent(activity, ProfileInfoActivity.class);
                activity.startActivity(intent);
            }
        });
        wamod_drawer_header_2ndprofilepic.setVisibility(GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int padding = getStatusBarHeight(getContext());
            ViewGroup userInfo = (ViewGroup) wamod_drawer_photo.getParent();
            ((LinearLayout.LayoutParams) userInfo.getLayoutParams()).topMargin = padding;
            final RelativeLayout drawerHeader = (RelativeLayout) findViewById(getResID("drawer_header", "id"));
            drawerHeader.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    LayoutParams params = new LayoutParams(NavigationDrawerGoogle.this.getWidth(), drawerHeader.getHeight() + padding);
                    drawerHeader.setLayoutParams(params);
                    LayoutParams params1 = (LayoutParams) wamod_drawer_header_2ndprofilepic.getLayoutParams();
                    params1.setMargins(params1.leftMargin, params1.topMargin + padding, params1.rightMargin, params1.bottomMargin);
                    final LinearLayout statusbar = (LinearLayout) findViewById(getResID("drawer_statusbar", "id"));
                    statusbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            statusbar.setLayoutParams(new LayoutParams(NavigationDrawerGoogle.this.getWidth(), getStatusBarHeight(getContext())));
                            statusbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                    drawerHeader.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
    private static String getUserName(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences", 0);
        return prefs.getString("push_name", "");
    }
    private static String getStatus(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("com.whatsapp_preferences", 0);
        return prefs.getString("my_current_status", "_Set_Your_Status_");
    }
    private static Drawable getUserPicture(Context ctx) {
        String s = getApplicationPath(ctx);
        String pathName = s + "/files/me.jpg";
        return Drawable.createFromPath(pathName);
    }
    private static String getApplicationPath(Context ctx) {
        try {
            PackageManager m = ctx.getPackageManager();
            String s = ctx.getPackageName();
            PackageInfo p = m.getPackageInfo(s, 0);
            s = p.applicationInfo.dataDir;
            return s;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
    static int getStatusBarHeight(Context ctx) {
        int result = 0;
        int resourceId = ctx.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ctx.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}