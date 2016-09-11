package com.whatsapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import kmods.Rango;

public class BubbleRelativeLayout extends ViewGroup {
    public BubbleRelativeLayout(Context context) {
        super(context);
    }
    public BubbleRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public BubbleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BubbleRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rango.DoColorB(this);
    }
}
