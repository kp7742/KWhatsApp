package kmods;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.whatsapp.*;

import static kmods.Utils.getResID;
import static kmods.WA.*;

public class Rango {
    private static int Toolbarcolor = Color.parseColor("#ff00e3ff");
    private static int Toolbaritemcolor = Color.parseColor("#ffffffff");
    private static int Statusbarcolor = Color.parseColor("#ffff5252");
    private static int Navbarcolor = Color.parseColor("#ffff5252");
    public static void DoColor(final android.support.v7.a.a actionbar, final android.support.v7.a.m act){
        try{
            if(act instanceof com.whatsapp.HomeActivity){
                initHome((com.whatsapp.HomeActivity)act);
            } else if (act instanceof com.whatsapp.Conversation){
                initConv((com.whatsapp.Conversation)act);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DoColor(final android.support.v7.a.m act){
        try {
            boolean coloredToolbarColor = !(act instanceof MediaView) &&
                    !(act instanceof ViewProfilePhoto) &&
                    !(act instanceof QuickContactActivity) &&
                    !(act instanceof ContactInfo) &&
                    !(act instanceof ei);
            if (coloredToolbarColor) {
                act.h().b(new ColorDrawable(Toolbarcolor));
                final ViewGroup actionbarVG = (ViewGroup) act.findViewById(getResID("action_bar", "id"));
                if (actionbarVG != null) {
                    actionbarVG.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onGlobalLayout() {
                            for (int i = 0; i < actionbarVG.getChildCount(); i++) {
                                View child = actionbarVG.getChildAt(i);
                                if (child instanceof TextView)
                                    ((TextView) child).setTextColor(Toolbaritemcolor);
                                if (child instanceof ImageButton || child instanceof ImageView)
                                    ((ImageView) child).setColorFilter(Toolbaritemcolor, PorterDuff.Mode.MULTIPLY);
                            }
                            actionbarVG.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });
                }
            }
            final Toolbar toolbar = (Toolbar) act.findViewById(getResID("toolbar", "id"));
            if (toolbar != null && coloredToolbarColor) {
                toolbar.setBackgroundColor(Toolbarcolor);
                toolbar.setTitleTextColor(Toolbaritemcolor);
                toolbar.setSubtitleTextColor(Toolbaritemcolor);
                toolbar.setOverflowIcon(tintToColor(toolbar.getOverflowIcon(), Toolbaritemcolor));
                toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onGlobalLayout() {
                        View up2 = toolbar.getChildAt(1);
                        if (up2 != null && up2 instanceof ImageButton || up2 instanceof ImageView)
                            ((ImageView) up2).setImageDrawable(tintToColor(((ImageView) up2).getDrawable(), Toolbaritemcolor));
                        toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }
            if (act instanceof QuickContactActivity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                act.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            if (coloredToolbarColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                act.getWindow().setStatusBarColor(Statusbarcolor);
                act.getWindow().setNavigationBarColor(Navbarcolor);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void DoColorP(final PreferenceActivity act) {
        if (act.getActionBar() != null){
            act.getActionBar().setBackgroundDrawable(new ColorDrawable(Toolbarcolor));
        }
        final Toolbar toolbar = (Toolbar) act.findViewById(getResID("toolbar", "id"));
        if (toolbar != null) {
            toolbar.setBackgroundColor(Toolbarcolor);
            toolbar.setTitleTextColor(Toolbaritemcolor);
            toolbar.setOverflowIcon(tintToColor(toolbar.getOverflowIcon(), Toolbaritemcolor));
            toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    View up2 = toolbar.getChildAt(1);
                    if (up2 != null && up2 instanceof ImageButton || up2 instanceof ImageView)
                        ((ImageView) up2).setImageDrawable(tintToColor(((ImageView) up2).getDrawable(), Toolbaritemcolor));
                    toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            act.getWindow().setStatusBarColor(Statusbarcolor);
            act.getWindow().setNavigationBarColor(Navbarcolor);
        }
    }
    public static void DoColorB(final cy bubble) {
        ImageView status = (ImageView) bubble.findViewById(getResID("status", "id"));
        boolean rightBubble = false;
        if (status != null) {rightBubble = true;}
        View quoted_color = bubble.findViewById(getResID("quoted_color", "id"));
        if (quoted_color != null) {
            quoted_color.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
    }
    private static Drawable tintToColor(Drawable drawable, int color) {
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
}
