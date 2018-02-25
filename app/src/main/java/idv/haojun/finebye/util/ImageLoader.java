package idv.haojun.finebye.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;

public class ImageLoader {
    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .into(iv);
    }

    public static void loadCenterCrop(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().centerCrop())
                .into(iv);
    }

    public static void loadBlur(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .apply(
                        new RequestOptions()
                                .centerCrop()
                                .transform(new BlurTransformation(25))
                )
                .into(iv);
    }
}
