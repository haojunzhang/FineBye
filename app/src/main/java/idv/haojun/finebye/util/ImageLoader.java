package idv.haojun.finebye.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).into(iv);
    }

    public static void loadCenterCrop(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).apply(new RequestOptions().centerCrop()).into(iv);
    }
}
