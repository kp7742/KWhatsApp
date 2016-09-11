package kmods;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.view.*;
import kmods.view.NavigationDrawer;
import kmods.view.NavigationDrawerGoogle;
import com.whatsapp.*;

import static kmods.Utils.getResID;

class WA {
    static void initHome(com.whatsapp.HomeActivity a){
        NavigationDrawer.initHome(a);
        View pager = a.findViewById(getResID("pager", "id"));
        pager.setBackgroundColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            a.getWindow().setStatusBarColor(Color.parseColor("#ff01adc3"));
        }
        if(Utils.Auto_update()) {
            new Update2(a).execute((String[]) new String[0]);
        }
    }
    static void initConv(final Conversation activity){}
}
