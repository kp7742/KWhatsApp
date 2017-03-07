package kmods;

import android.annotation.SuppressLint;
import android.content.*;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Environment;
import android.service.chooser.ChooserTarget;
import com.whatsapp.ContactChooserTargetService;
import com.whatsapp.Conversation;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class ShortcutsManager {
    Context context;
    private ShortcutManager mSystemShortcutManager;
    private List<ShortcutInfo> shortcuts = new ArrayList<>();
    private ShortcutsManager(Context context) {
        this.context = context;
        mSystemShortcutManager = context.getSystemService(ShortcutManager.class);
    }
    static ShortcutsManager getShortcutsManager(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return null;
        else return new ShortcutsManager(context);
    }
    void loadShortcuts() {
        List<ChooserTarget> targets = new ContactChooserTargetService().onGetChooserTargets((ComponentName)null, (IntentFilter)null);
        int size;
        if (!targets.isEmpty() && targets.size() > 3) {
            size = 4;
        } else {
            size = targets.size();
        }
        for (int i = 0; i< size; ++i) {
            addByTarget(targets.get(i));
        }
    }
    private void addByTarget(ChooserTarget target) {
        mSystemShortcutManager.removeAllDynamicShortcuts();
        Icon icon = target.getIcon();
        if (icon == null) {
            icon = Icon.createWithBitmap(drawableToBitmap(context.getDrawable(context.getResources().getIdentifier("ic_shortcut_contact", "drawable", context.getPackageName()))));
        }
        shortcuts.add(0, new ShortcutInfo.Builder(context, target.getIntentExtras().getString("jid"))
                .setShortLabel(target.getTitle())
                .setIcon(icon)
                .setIntent(new Intent(context, Conversation.class).putExtra("jid", target.getIntentExtras().getString("jid")).setAction(Intent.ACTION_ASSIST))
                .build());
        shortcuts.add(1, new ShortcutInfo.Builder(context, "kmods")
                .setShortLabel("KMODs")
                .setIcon(icon)
                .setIntent(new Intent(context, kmods.Settings.class))
                .build());
        shortcuts.add(2, new ShortcutInfo.Builder(context, "privacy")
                .setShortLabel("Privacy")
                .setIcon(icon)
                .setIntent(new Intent(context, kmods.Privacy.class))
                .build());
        shortcuts.add(3, new ShortcutInfo.Builder(context, "Profile")
                .setShortLabel("Profile")
                .setIcon(icon)
                .setIntent(new Intent(context, com.whatsapp.ProfileInfoActivity.class))
                .build());
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }
    private void addByTarget2(final ChooserTarget chooserTarget) {
        mSystemShortcutManager.removeAllDynamicShortcuts();
        /*shortcuts.add(0, new ShortcutInfo.Builder(context, target.getIntentExtras().getString("jid"))
                .setShortLabel(target.getTitle())
                .setIcon(icon)
                .setIntent(new Intent(context, Conversation.class).putExtra("jid", target.getIntentExtras().getString("jid")).setAction(Intent.ACTION_ASSIST))
                .build());*/
        shortcuts.add(1, new ShortcutInfo.Builder(context, "kmods")
                .setShortLabel("KMODs")
                .setIntent(new Intent(context, kmods.Settings.class))
                .build());
        shortcuts.add(2, new ShortcutInfo.Builder(context, "privacy")
                .setShortLabel("Privacy")
                .setIntent(new Intent(context, kmods.Privacy.class))
                .build());
        shortcuts.add(3, new ShortcutInfo.Builder(context, "Profile")
                .setShortLabel("Profile")
                .setIntent(new Intent(context, com.whatsapp.ProfileInfoActivity.class))
                .build());
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }
    private static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xfff5f5f5;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = Math.max(w, h);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        /*canvas.drawRect(0, 0, w/2, h/2, paint);
        canvas.drawRect(w/2, 0, w, h/2, paint);
        canvas.drawRect(0, h/2, w/2, h, paint);
        canvas.drawRect(w/2, h/2, w, h, paint);*/
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return output;
    }
    public Bitmap getContactPhoto(final String s) {
        return BitmapFactory.decodeFile("/data/data/com.whatsapp/files/Avatars/" + s + ".j");
    }
}