package com.mega;

import android.content.Context;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.AttributeSet;

public class UpdateP extends Preference implements OnPreferenceClickListener {
    public UpdateP(Context context) {
        super(context);
        init();
    }

    public UpdateP(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public UpdateP(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        setOnPreferenceClickListener(this);
    }

    public boolean onPreferenceClick(Preference preference) {
        new Update(UpdateP.this.getContext()).execute((String[]) new String[0]);
        return false;
    }
}

