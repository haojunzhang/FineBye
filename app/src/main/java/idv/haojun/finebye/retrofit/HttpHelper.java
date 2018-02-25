package idv.haojun.finebye.retrofit;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;

import idv.haojun.finebye.data.Cam;
import idv.haojun.finebye.retrofit.apis.CamApis;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;

public class HttpHelper {

    private static final String HOST = "https://od.moi.gov.tw/";

    private static HttpHelper instance;

    private Retrofit retrofit;
    private CamApis camApi;

    private HttpHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        camApi = retrofit.create(CamApis.class);
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    public void getCams(Callback<GovResponse<Cam>> callback) {
        camApi.getCams().enqueue(callback);
    }
}
