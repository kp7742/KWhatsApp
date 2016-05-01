package com.mega;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

public class UpdateP extends Preference implements Preference.OnPreferenceClickListener
{
    public UpdateP(final Context context) {
        super(context);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public UpdateP(final Context context, final AttributeSet set) {
        super(context, set);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public UpdateP(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setOnPreferenceClickListener((Preference.OnPreferenceClickListener)this);
    }
    
    public boolean onPreferenceClick(final Preference preference) {
        new Update(UpdateP.this.getContext()).execute((String[]) new String[0]);
        return false;
    }
}
