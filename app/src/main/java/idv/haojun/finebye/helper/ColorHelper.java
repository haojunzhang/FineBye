package idv.haojun.finebye.helper;

import android.graphics.Color;

public class ColorHelper {
    public static int addAlpha(int color, float v){
        int alpha = Math.round(Color.alpha(color) * v);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
