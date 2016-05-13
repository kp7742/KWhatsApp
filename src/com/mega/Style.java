package com.mega;

import android.content.Context;

import static com.mega.Mega.getResId;

public class Style {
    private static Context ctx = Mega.ctx;
    public static int BubbleStyle(final int n){
        int i1 = Integer.parseInt(ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("bubble_s", "0"));
        int n1 = 0;int n2 = 0;int n3 = 0;int n4 = 0;
        switch (i1){
            case 0: {
                n1 = getResId("balloon_incoming_normal", "drawable");
                n2 = getResId("balloon_outgoing_normal", "drawable");
                n3 = getResId("balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 1: {
                n1 = getResId("fbm_balloon_incoming_normal", "drawable");
                n2 = getResId("fbm_balloon_outgoing_normal", "drawable");
                n3 = getResId("fbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("fbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 2: {
                n1 = getResId("hangouts_balloon_incoming_normal", "drawable");
                n2 = getResId("hangouts_balloon_outgoing_normal", "drawable");
                n3 = getResId("hangouts_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("hangouts_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 3: {
                n1 = getResId("wapaper_balloon_incoming_normal", "drawable");
                n2 = getResId("wapaper_balloon_outgoing_normal", "drawable");
                n3 = getResId("wapaper_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("wapaper_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 4: {
                n1 = getResId("wp_balloon_incoming_normal", "drawable");
                n2 = getResId("wp_balloon_outgoing_normal", "drawable");
                n3 = getResId("wp_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("wp_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 5: {
                n1 = getResId("walb_balloon_incoming_normal", "drawable");
                n2 = getResId("walb_balloon_outgoing_normal", "drawable");
                n3 = getResId("walb_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("walb_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 6: {
                n1 = getResId("materialized_balloon_incoming_normal", "drawable");
                n2 = getResId("materialized_balloon_outgoing_normal", "drawable");
                n3 = getResId("materialized_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("materialized_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 7: {
                n1 = getResId("telegram_balloon_incoming_normal", "drawable");
                n2 = getResId("telegram_balloon_outgoing_normal", "drawable");
                n3 = getResId("telegram_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("telegram_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 8: {
                n1 = getResId("hike_balloon_incoming_normal", "drawable");
                n2 = getResId("hike_balloon_outgoing_normal", "drawable");
                n3 = getResId("hike_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("hike_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 9: {
                n1 = getResId("bryed_balloon_incoming_normal", "drawable");
                n2 = getResId("bryed_balloon_outgoing_normal", "drawable");
                n3 = getResId("bryed_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("bryed_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 10: {
                n1 = getResId("chaton_balloon_incoming_normal", "drawable");
                n2 = getResId("chaton_balloon_outgoing_normal", "drawable");
                n3 = getResId("chaton_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("chaton_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 11: {
                n1 = getResId("bbm_balloon_incoming_normal", "drawable");
                n2 = getResId("bbm_balloon_outgoing_normal", "drawable");
                n3 = getResId("bbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("bbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 12: {
                n1 = getResId("doodlehang_balloon_incoming_normal", "drawable");
                n2 = getResId("doodlehang_balloon_outgoing_normal", "drawable");
                n3 = getResId("doodlehang_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("doodlehang_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 13: {
                n1 = getResId("fold_balloon_incoming_normal", "drawable");
                n2 = getResId("fold_balloon_outgoing_normal", "drawable");
                n3 = getResId("fold_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("fold_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 14: {
                n1 = getResId("eclipsis_balloon_incoming_normal", "drawable");
                n2 = getResId("eclipsis_balloon_outgoing_normal", "drawable");
                n3 = getResId("eclipsis_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("eclipsis_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 15: {
                n1 = getResId("altcr_balloon_incoming_normal", "drawable");
                n2 = getResId("altcr_balloon_outgoing_normal", "drawable");
                n3 = getResId("altcr_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("altcr_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 16: {
                n1 = getResId("apple_balloon_incoming_normal", "drawable");
                n2 = getResId("apple_balloon_outgoing_normal", "drawable");
                n3 = getResId("apple_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("apple_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 17: {
                n1 = getResId("gosms_balloon_incoming_normal", "drawable");
                n2 = getResId("gosms_balloon_outgoing_normal", "drawable");
                n3 = getResId("gosms_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("gosms_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 18: {
                n1 = getResId("in_balloon_incoming_normal", "drawable");
                n2 = getResId("in_balloon_outgoing_normal", "drawable");
                n3 = getResId("in_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("in_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 19: {
                n1 = getResId("md_balloon_incoming_normal", "drawable");
                n2 = getResId("md_balloon_outgoing_normal", "drawable");
                n3 = getResId("md_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("md_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 20: {
                n1 = getResId("trans_balloon_incoming_normal", "drawable");
                n2 = getResId("trans_balloon_outgoing_normal", "drawable");
                n3 = getResId("trans_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("trans_balloon_incoming_normal_ext", "drawable");
                break;
            }
        }
        switch (n) {
            default: {
                return n1;
            }
            case 1: {
                return n2;
            }
            case 2: {
                return n3;
            }
            case 3: {
                return n4;
            }
        }
    }
}
