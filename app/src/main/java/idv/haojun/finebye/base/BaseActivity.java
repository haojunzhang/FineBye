package idv.haojun.finebye.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import idv.haojun.finebye.R;
import idv.haojun.finebye.helper.SPHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().registerActivity(this);
        initTheme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().unregisterActivity(this);
    }

    protected void initTitle(int titleId) {
        initTitle(R.id.tv_toolbar_tittle, getString(titleId));
    }

    protected void initTitle(int tvId, int titleId) {
        initTitle(tvId, getString(titleId));
    }

    protected void initTitle(int tvId, String title) {
        TextView tv = findViewById(tvId);
        tv.setText(title);
    }

    private void initTheme() {
        int color = SPHelper.getThemeColor(this);
        if (color == ContextCompat.getColor(this, R.color.colorBluePrimary)) {
            setTheme(R.style.BlueTheme);
        } else if (color == ContextCompat.getColor(this, R.color.colorBrownPrimary)) {
            setTheme(R.style.BrownTheme);
        } else if (color == ContextCompat.getColor(this, R.color.colorGreenPrimary)) {
            setTheme(R.style.GreenTheme);
        }
    }
}
