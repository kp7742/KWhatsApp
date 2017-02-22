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
    public static ShortcutsManager getShortcutsManager(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) return null;
        else return new ShortcutsManager(context);
    }
    public void loadShortcuts() {
        ContactChooserTargetService contactChooserTargetService = new ContactChooserTargetService();
        List<ChooserTarget> targets = contactChooserTargetService.onGetChooserTargets(null, null);
        for (int i = 0; i<4; i++)
            addByTarget(targets.get(i));
    }
    private void addByTarget(ChooserTarget target) {
        mSystemShortcutManager.removeAllDynamicShortcuts();
        Icon icon = target.getIcon();
        if (icon == null)
            icon = Icon.createWithBitmap(getRoundedCornerBitmap(getContactPhoto(target.getIntentExtras().getString("jid"))));
        shortcuts.add(0, new ShortcutInfo.Builder(context, target.getIntentExtras().getString("jid"))
                .setShortLabel(target.getTitle())
                .setIcon(icon)
                .setIntent(new Intent(context, Conversation.class).putExtra("jid", target.getIntentExtras().getString("jid")).setAction(Intent.ACTION_ASSIST))
                .build());
        /*shortcuts.add(1, new ShortcutInfo.Builder(context, "kmods")
                .setShortLabel("KMODs")
                .setIntent(new Intent(context, kmods.Settings.class))
                .build());
        shortcuts.add(2, new ShortcutInfo.Builder(context, "privacy")
                .setShortLabel("Privacy")
                .setIntent(new Intent(context, kmods.Privacy.class))
                .build());*/
        mSystemShortcutManager.addDynamicShortcuts(shortcuts);
    }
    public Bitmap getContactPhoto(String jabberId) {
        String s = Environment.getDataDirectory().getAbsolutePath() + "data/com.whatsapp";
        String pathName = s + "/files/Avatars/" + jabberId + ".j";
        return BitmapFactory.decodeFile(pathName);
    }
    public static Bitmap getRoundedCornerBitmap(final Bitmap bitmap) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final Bitmap bitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);
        final Paint paint = new Paint();
        final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
        final float n = Math.max(width, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xfff5f5f5);
        canvas.drawRoundRect(rectF, n, n, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmap2;
    }
}