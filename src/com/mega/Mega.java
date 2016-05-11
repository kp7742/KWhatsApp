package com.mega;

import android.content.Context;
import android.support.v7.app.ActionBar;
import com.whatsapp.App;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;

public class Mega {
    private static Context ctx;
    static void RestartApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    private static boolean getBoolean(final String s) {
        if(Mega.ctx == null){
            Mega.ctx = ctx.getApplicationContext();
        }
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    public static boolean Archv_chats() {
        return getBoolean("Archv_chats");
    }
    public static boolean Audio_ears() {
        return getBoolean("Audio_ears");
    }
    public static boolean Audio_sensor() {
        return getBoolean("Audio_sensor");
    }
    public static String Apply(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }
    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }
    public static void ShowName(final ActionBar actionBar, final Context context) {
        CharSequence a = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            a = App.A(context);
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(App.aR());
            }
        }
        actionBar.setTitle(a);
    }
    private static String GetType(Object o) {
        for (final Field field : o.getClass().getFields()) {
            if (String.class.isAssignableFrom(field.getType())) {
                try {
                    final Object value = field.get(o);
                    if (value != null) {
                        if (value.toString().contains("@broadcast")) {
                            o = "B";
                            return (String)o;
                        }
                        if (value.toString().contains("@s.whatsapp.net")) {
                            o = "G";
                            return (String)o;
                        }
                        if (value.toString().contains("g.us")) {
                            o = "G";
                            return (String)o;
                        }
                    }
                }
                catch (Exception ignored) {}
            }
        }
        o = "C";
        return (String)o;
    }
    private static int getResId(String s1, String s2){
        return ctx.getResources().getIdentifier(s1, s2, ctx.getPackageName());
    }
    public static int BubbleStyle(final int n){
        int i1 = Integer.parseInt(ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("bubble_s", "0"));
        int n1 = getResId("balloon_incoming_normal", "drawable");
        int n2 = getResId("balloon_outgoing_normal", "drawable");
        int n3 = getResId("balloon_outgoing_normal_ext", "drawable");
        int n4 = getResId("balloon_incoming_normal_ext", "drawable");
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
    public static int getUpSize() {
        return 5120;
    }
    public static int getMaxImg() {
        return 5315;
    }

    public static SecretKey ab()
    {
        byte[] arrayOfByte = HexToByte("7905796AAFC283ADC6B2AD6CB2137D4F7821F94529D30230D31807613D5B9C28C4A8E25028246B5B17407B6CAFB0378224BF98E06DCF443D87505EE8520886A3");
        return new SecretKeySpec(arrayOfByte, 0, arrayOfByte.length, "PBKDF2WithHmacSHA1And8BIT");
    }
    public static byte[] a()
    {
        return HexToByte("07DEEF2607CD3BDAE43A0A264E43D88B");//This Key From Original Apk of v2.16.66.
    }
    public static byte[] b()
    {
        return HexToByte("30820332308202F0A00302010202044C2536A4300B06072A8648CE3804030500307C310B3009060355040613025553311330110603550408130A43616C69666F726E6961311430120603550407130B53616E746120436C61726131163014060355040A130D576861747341707020496E632E31143012060355040B130B456E67696E656572696E67311430120603550403130B427269616E204163746F6E301E170D3130303632353233303731365A170D3434303231353233303731365A307C310B3009060355040613025553311330110603550408130A43616C69666F726E6961311430120603550407130B53616E746120436C61726131163014060355040A130D576861747341707020496E632E31143012060355040B130B456E67696E656572696E67311430120603550403130B427269616E204163746F6E308201B83082012C06072A8648CE3804013082011F02818100FD7F53811D75122952DF4A9C2EECE4E7F611B7523CEF4400C31E3F80B6512669455D402251FB593D8D58FABFC5F5BA30F6CB9B556CD7813B801D346FF26660B76B9950A5A49F9FE8047B1022C24FBBA9D7FEB7C61BF83B57E7C6A8A6150F04FB83F6D3C51EC3023554135A169132F675F3AE2B61D72AEFF22203199DD14801C70215009760508F15230BCCB292B982A2EB840BF0581CF502818100F7E1A085D69B3DDECBBCAB5C36B857B97994AFBBFA3AEA82F9574C0B3D0782675159578EBAD4594FE67107108180B449167123E84C281613B7CF09328CC8A6E13C167A8B547C8D28E0A3AE1E2BB3A675916EA37F0BFA213562F1FB627A01243BCCA4F1BEA8519089A883DFE15AE59F06928B665E807B552564014C3BFECF492A0381850002818100D1198B4B81687BCF246D41A8A725F0A989A51BCE326E84C828E1F556648BD71DA487054D6DE70FFF4B49432B6862AA48FC2A93161B2C15A2FF5E671672DFB576E9D12AAFF7369B9A99D04FB29D2BBBB2A503EE41B1FF37887064F41FE2805609063500A8E547349282D15981CDB58A08BEDE51DD7E9867295B3DFB45FFC6B259300B06072A8648CE3804030500032F00302C021400A602A7477ACF841077237BE090DF436582CA2F0214350CE0268D07E71E55774AB4EACD4D071CD1EFAD");
    }
    private static byte[] HexToByte(String s)
    {
        int j = s.length();
        byte[] arb = new byte[j / 2];
        for (int i = 0; i < j; i += 2) {
            arb[(i / 2)] = ((byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16)));
        }
        return arb;
    }
    public static boolean TxtSelect() {
        return getBoolean("txt_select");
    }
    public static void init(final Context ctx) {
    Mega.ctx = ctx;
    Settings.ctx = ctx;
    if (Settings.ctx == null || Mega.ctx == null) {
        Mega.ctx = ctx.getApplicationContext();
        Settings.ctx = ctx.getApplicationContext();
    }
    }
    public static boolean getHideInfo() {
        return getBoolean("hideinfo");
    }
    public static boolean Img_limit() {
        return getBoolean("img_limit");
    }
    public static boolean HideSeen(){
        return getBoolean("hide_seen");
    }
    public static boolean HideRead(final Object o) {
        return getBoolean("HideRead" + GetType(o));
    }
    public static boolean HideCR(final String s) {
        boolean b;
        if (s != null) {
            b = getBoolean("HideRecord");
        }
        else {
            b = getBoolean("HideCompose");
        }
        return b;
    }
    public static boolean HidePlay(final Object o) {
        return getBoolean("HidePlay" + GetType(o));
    }
    public static boolean HideReceipt(final Object o) {
        return getBoolean("HideReceipt" + GetType(o));
    }
}