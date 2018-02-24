package idv.haojun.finebye.app.welcome;

import android.content.Context;

public class WelcomePresenter implements WelcomeContract.Presenter {

    private WelcomeContract.View mView;
    private Context context;

    WelcomePresenter(WelcomeContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
    }
}
