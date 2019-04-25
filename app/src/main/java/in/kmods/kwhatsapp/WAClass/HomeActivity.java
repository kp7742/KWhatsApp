package in.kmods.kwhatsapp.WAClass;

import android.view.View;
import android.widget.TextView;

import in.kmods.kwhatsapp.KMODs;
import in.kmods.kwhatsapp.Resources;

public class HomeActivity {
    public static void _onCreate(final com.whatsapp.HomeActivity a) {
        View actionbar = a.findViewById(Resources.id.action_bar);
        TextView title = actionbar.findViewById(Resources.id.action_bar_title);
        if(title != null) {
            title.setText(KMODs.getUserName());
            TextView subtitle = actionbar.findViewById(Resources.id.action_bar_subtitle);
            subtitle.setVisibility(View.VISIBLE);
            subtitle.setText(KMODs.getStatus());
        }
    }
}
