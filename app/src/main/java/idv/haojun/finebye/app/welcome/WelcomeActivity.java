package idv.haojun.finebye.app.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import idv.haojun.finebye.R;
import idv.haojun.finebye.app.main.MainActivity;
import idv.haojun.finebye.base.BaseActivity;

public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    private WelcomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mPresenter = new WelcomePresenter(this);
        mPresenter.displayWelcome();
    }

    @Override
    public void openMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
