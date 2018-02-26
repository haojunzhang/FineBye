package idv.haojun.finebye.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;

import idv.haojun.finebye.R;

public class SPHelper {

    public static final String NAME = "fineBye";

    public static final String AVATAR_URL = "avatar_url";
    public static final String THEME_COLOR = "theme_color";

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
}
