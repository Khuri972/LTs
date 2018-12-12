package com.ltsonline.netUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CRAFT BOX on 1/23/2017.
 */

public class MyPreferences {

    Context context;

    public static String PreferenceName = "cdms";
    public static String EncraptionKey = "cdms@2442";

    public static String welcomeScreen = "welcomeScreen";
    public static String loginType = "login_type",refreshedtoken="refreshedtoken";
    public static String id = "id",notification_count="notification_count";


    public MyPreferences(Context context) {
        this.context = context;
    }

    String value = "";

    public String getPreferences(String key) {
        value = "";
        try {
            SharedPreferences channel = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            value = AESCrypt.decrypt("" + EncraptionKey, channel.getString("" + key, "").toString());
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }
        return value;
    }

    public void setPreferences(String key, String value) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString("" + key, AESCrypt.encrypt("" + EncraptionKey, "" + value));
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + PreferenceName, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* todo tutoral praferance */

    public static String PreferenceTutoralName = "cdms_tutoral";

    public static String dashboard_tutorial = "dashboard_tutorial";
    public static String my_contact_tutorial = "my_contact_tutorial";
    public static String my_document_tutorial = "my_document_tutorial";
    public static String courier_tutorial = "courier_tutorial";
    public static String my_task_tutorial = "my_task_tutorial";
    public static String cheque_tutorial = "cheque_tutorial";

    public String getTutoralPreferences(String key) {
        String value = null;
        try {
            SharedPreferences channel = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            value = AESCrypt.decrypt("" + EncraptionKey, channel.getString("" + key, "").toString());
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }

        return value;
    }

    public void setTutoralPreferences(String key, String value) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString("" + key, AESCrypt.encrypt("" + EncraptionKey, "" + value));
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearTutoralPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + PreferenceTutoralName, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* todo alias preference */

    public static String Preferencealias = "cdms_alias";
    public static String change_to_alias = "change_to_alias";
    public static String aliasArray = "aliasArray";
    public static String currency = "currency";

    public String getaliasPreferences(String key) {
        String value = null;
        try {
            SharedPreferences channel = context.getSharedPreferences("" + Preferencealias, Context.MODE_PRIVATE);
            value = AESCrypt.decrypt("" + EncraptionKey, channel.getString("" + key, "").toString());
        } catch (Exception e) {
            e.printStackTrace();
            value = "";
            return value;
        }

        return value;
    }

    public void setaliasPreferences(String key, String value) {
        try {
            SharedPreferences sharedpreferences = context.getSharedPreferences("" + Preferencealias, Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putString("" + key, AESCrypt.encrypt("" + EncraptionKey, "" + value));
            ed.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean clearaliasPreferences() {
        try {
            SharedPreferences settings = context.getSharedPreferences("" + Preferencealias, Context.MODE_PRIVATE);
            return settings.edit().clear().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
