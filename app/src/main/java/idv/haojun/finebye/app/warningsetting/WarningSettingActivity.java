package idv.haojun.finebye.app.warningsetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import idv.haojun.finebye.R;
import idv.haojun.finebye.base.BaseActivity;

public class WarningSettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_setting);
        initTitle(R.string.warning_setting);
    }
}
