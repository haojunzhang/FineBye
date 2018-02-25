package idv.haojun.finebye.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SPHelper {

    public static final String NAME = "fineBye";

    public static final String AVATAR_URL = "avatar_url";

    private static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static String getAvatarUrl(Context context) {
        return getSP(context).getString(AVATAR_URL, "");
    }

    public static void setAvatarUrl(Context context, String avatarUrl) {
        getSP(context).edit().putString(AVATAR_URL, avatarUrl).apply();
    }
}
