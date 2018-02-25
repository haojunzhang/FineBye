package idv.haojun.finebye.retrofit;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Response;

public class ResponseHelper {
    public static boolean isResponseSuccess(Context context, Response<?> response) {
        if (!response.isSuccessful()) {
            Toast.makeText(context, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (response.body() == null) {
            Toast.makeText(context, "No response", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
