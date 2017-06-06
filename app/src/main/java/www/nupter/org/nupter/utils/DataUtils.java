package www.nupter.org.nupter.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import www.nupter.org.nupter.application.MyApplication;
import www.nupter.org.nupter.http.RequestUrl;

/**
 * Created by fangzhenyi on 15/11/15.
 */
public class DataUtils {
    private static final String PREFERENCE_NAME = "Preferences";

    private static final SharedPreferences sharedPreferences = MyApplication
            .getContext().getSharedPreferences(PREFERENCE_NAME, Activity.MODE_PRIVATE);

    private DataUtils() {
    }

    public static void removePreferences(String key) {
        if (key == null) {
            return;
        }
        SharedPreferences.Editor editor = DataUtils.sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    public static void putPreferences(String key, String value) {
        SharedPreferences.Editor editor = DataUtils.sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreferences(String key, String defValue) {
        return DataUtils.sharedPreferences.getString(key, defValue);
    }

    public static void putPreferences(String key, int value) {
        SharedPreferences.Editor editor = DataUtils.sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getPreferences(String key, int defValue) {
        return DataUtils.sharedPreferences.getInt(key, defValue);
    }

    public static String convertohtml(String tag,String info){
        String str = "<font color=\"#009688\">" + tag+ "</font><font  color=\"#4c4c4c/\">   |  "  + info +"</font>";
        return  str;
    }

    public static String convert2Imgurl(String url){
        return RequestUrl.baseimgurl+url;
    }
}
