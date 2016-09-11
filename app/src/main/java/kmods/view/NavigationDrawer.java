package kmods.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import kmods.Utils;

import static kmods.Utils.getResID;

public class NavigationDrawer {
    public static void initHome(final com.whatsapp.HomeActivity a) {
        android.support.v7.app.a actionbar = a.h();
        actionbar.a(true);
        Drawable upIndicator = a.getResources().getDrawable(getResID("wamod_ic_menu", "drawable"));
        actionbar.c(upIndicator);
        View pager_holder = a.findViewById(getResID("pager_holder", "id"));
        ViewGroup content = (ViewGroup) a.findViewById(getResID("content", "id"));
        pager_holder.setPadding(0,0,0,0);
        content.setPadding(0,0,0,48);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = a.getWindow();
            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setStatusBarColor(Color.parseColor("#00000000"));
            int padding = NavigationDrawerGoogle.getStatusBarHeight(a);
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) a.findViewById(getResID("drawer_overlay", "id"));
            coordinatorLayout.setPadding(0,padding,0,0);
        }
    }

    /* Called on
     *    com.whatsapp.HomeActivity.onPrepareOptionsMenu(Landroid/view/Menu;)Z
     * Where
     *    Replace the entire method
     * Smali
     *    .locals 1
     *    .prologue
     *    invoke-static {p1}, Lkmods/view/NavigationDrawer;->_onPrepareOptionsMenu(Landroid/view/Menu;)V
     *    invoke-super {p0, p1}, Lcom/whatsapp/DialogToastActivity;->onPrepareOptionsMenu(Landroid/view/Menu;)Z
     *    move-result v0
     *    return v0
     */
    public static void _onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        Drawable searchIcon = Utils.ctx.getResources().getDrawable(getResID("ic_action_search","drawable"));
        menu.add(0, 0, 0, "Search").setIcon(searchIcon).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    /* Called on
     *    com.whatsapp.HomeActivity.onOptionsItemSelected(Landroid/view/MenuItem;)Z
     * Where
     *    After prologue
     * Smali
     *    invoke-static {p0, p1}, Lkmods/view/NavigationDrawer;->_onOptionsItemSelected(Lcom/whatsapp/HomeActivity;Landroid/view/MenuItem;)Z
     *    move-result v0
     *    if-nez v0, :cond_4
     *
     *    ...
     *
     *    :cond_4
     *    const/4 v0, 0x1
     *    :goto_0
     *    return v0
     */
    public static boolean _onOptionsItemSelected(com.whatsapp.HomeActivity a, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavigationView navigationView = (NavigationView) a.findViewById(getResID("mod_drawer", "id"));
                DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(getResID("drawer_parent", "id"));
                drawerLayout.openDrawer(navigationView);
                return true;
            case 0:
                a.onSearchRequested();
                return true;
        }
        return false;
    }

    /* Called on
     *    com.whatsapp.HomeActivity.onBackPressed()V
     * Where
     *    After prologue
     * Smali
     *    invoke-static {p0}, Lkmods/view/NavigationDrawer;->_onBackPressed(Lcom/whatsapp/HomeActivity;)Z
     *    move-result v0
     *    if-nez v0, :cond_2
     *
     *    ...
     *
     *    :cond_2
     *    return-void
     */
    public static boolean _onBackPressed(com.whatsapp.HomeActivity a) {
        NavigationView navigationView = (NavigationView) a.findViewById(getResID("mod_drawer", "id"));
        DrawerLayout drawerLayout = (DrawerLayout) a.findViewById(getResID("drawer_parent", "id"));
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
            return true;
        } else return false;
    }
}