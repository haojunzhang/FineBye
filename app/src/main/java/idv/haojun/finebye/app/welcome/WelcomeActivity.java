package idv.haojun.finebye.app.welcome;

import android.os.Bundle;

import idv.haojun.finebye.R;
import idv.haojun.finebye.base.BaseActivity;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View{

    private WelcomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mPresenter = new WelcomePresenter(this);
    }
}
