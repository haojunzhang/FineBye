package idv.haojun.finebye.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import idv.haojun.finebye.R;

public class SPHelper {

    public static final String NAME = "fineBye";

    public static final String AVATAR_URL = "avatar_url";
    public static final String THEME_COLOR = "theme_color";
    public static final String DISTANCE = "distance";
    public static final String IS_SHOCK = "is_shock";

    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static String getAvatarUrl(Context context) {
        return getSP(context).getString(AVATAR_URL, "");
    }

    public static void setAvatarUrl(Context context, String avatarUrl) {
        getSP(context).edit().putString(AVATAR_URL, avatarUrl).apply();
    }

    public static int getThemeColor(Context context) {
        return getSP(context).getInt(THEME_COLOR, ContextCompat.getColor(context, R.color.colorBluePrimary));
    }

    public static void setThemeColor(Context context, int color) {
        getSP(context).edit().putInt(THEME_COLOR, color).apply();
    }

    public static float getDistance(Context context) {
        return getSP(context).getFloat(DISTANCE, 500f);
    }

    public static void setDistance(Context context, float distance) {
        getSP(context).edit().putFloat(DISTANCE, distance).apply();
    }

    public static boolean isShock(Context context) {
        return getSP(context).getBoolean(IS_SHOCK, true);
    }

    public static void setShock(Context context, boolean isShock) {
        getSP(context).edit().putBoolean(IS_SHOCK, isShock).apply();
    }
}
