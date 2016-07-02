package com.kmods;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

public class ActionP extends Preference {
    public ActionP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ActionP(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActionP(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        super.onClick();
        switch (getKey()) {
            case "cfu":
                new Update(getContext()).execute((String[]) new String[0]);
                break;
        }
    }
}
