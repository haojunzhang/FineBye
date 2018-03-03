package idv.haojun.finebye.app.welcome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import idv.haojun.finebye.base.App;
import idv.haojun.finebye.data.Cam;
import idv.haojun.finebye.retrofit.GovResponse;
import idv.haojun.finebye.retrofit.HttpHelper;
import idv.haojun.finebye.retrofit.ResponseHelper;
import io.objectbox.Box;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View mView;
    private Context context;

    WelcomePresenter(WelcomeContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
    }

    @Override
    public void displayWelcome() {
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            checkCams();
        }
    };

    private void checkCams() {
        final Box<Cam> camBox = ((App) ((Activity) mView).getApplication()).getBoxStore().boxFor(Cam.class);
        List<Cam> cams = camBox.query().build().find();

        if (!cams.isEmpty()) {
            mView.openMainActivity();
        } else {
            HttpHelper.getInstance().getCams(new Callback<GovResponse<Cam>>() {
                @Override
                public void onResponse(Call<GovResponse<Cam>> call, Response<GovResponse<Cam>> response) {
                    if (ResponseHelper.isResponseSuccess(context, response)) {
                        GovResponse<Cam> result = response.body();
                        if (result != null && result.isSuccess() && result.getResult() != null && result.getResult().getRecords() != null)
                            camBox.put(result.getResult().getRecords());
                        mView.openMainActivity();
                    }
                }

                @Override
                public void onFailure(Call<GovResponse<Cam>> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
