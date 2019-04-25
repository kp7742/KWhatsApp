package in.kmods.kwhatsapp;

import android.graphics.Color;

public class ColorsManager {
    public static final String STATUSBAR = "ff2d99da";
    public static final String TOOLBAR = "ff2d99da";
    public static final String TOOLBAR_TITLE = "ffffffff";
    public static final String TOOLBAR_SUBTITLE = "ffffffff";
    public static final String TOOLBAR_ICONS = "ffffffff";
    public static final String NAVBAR = "ff2d99da";
    public static final String BACKGROUND = "ff2d99da";
    public static final String TEXT_PRIMARY = "ffffffff";
    public static final String TEXT_SECONDARY = "ffffffff";
    public static final String CARD_BACKGROUND = "ff2d99da";

    public static final String CONVERSATION_BACKGROUND = "ff2d99da";
    public static final String CONVERSATION_BUBBLE_RIGHT_BACKGROUND = "ffffffff";
    public static final String CONVERSATION_BUBBLE_RIGHT_MESSAGE = "ff000000";
    public static final String CONVERSATION_BUBBLE_RIGHT_DATE = "ff000000";
    public static final String CONVERSATION_BUBBLE_LEFT_BACKGROUND = "ffffffff";
    public static final String CONVERSATION_BUBBLE_LEFT_MESSAGE = "ff000000";
    public static final String CONVERSATION_BUBBLE_LEFT_DATE = "ff000000";
    public static final String CONVERSATION_BUBBLE_PARTICIPANT = "ff2d99da";

    public static int getColor(String colorName) {
         return Color.parseColor("#" + colorName);
    }
}