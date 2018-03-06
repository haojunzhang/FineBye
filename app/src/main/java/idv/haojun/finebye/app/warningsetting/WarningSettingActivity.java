package idv.haojun.finebye.app.warningsetting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import idv.haojun.finebye.R;
import idv.haojun.finebye.base.BaseActivity;

public class WarningSettingActivity extends BaseActivity implements WarningSettingContract.View, SeekBar.OnSeekBarChangeListener {

    @BindView(R.id.iv_warning_setting_done)
    ImageView iv_done;

    @BindView(R.id.tv_warning_setting_distance)
    TextView tv_distance;

    @BindView(R.id.sb_warning_setting_distance)
    SeekBar sb_distance;

    @BindView(R.id.cb_warning_setting_shock)
    CheckBox cb_shock;

    private WarningSettingContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_setting);
        initTitle(R.id.tv_warning_setting_tittle, R.string.warning_setting);
        ButterKnife.bind(this);

        sb_distance.setOnSeekBarChangeListener(this);

        mPresenter = new WarningSettingPresenter(this);
        mPresenter.initDefaultValue();
        mPresenter.showcase(sb_distance, cb_shock);
    }

    @OnClick(R.id.iv_warning_setting_done)
    public void done(){
        mPresenter.done(
                sb_distance.getProgress() * 0.5f + 0.5f,
                cb_shock.isChecked()
        );
    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void displayDistance(float distance) {
        sb_distance.setProgress((int) ((distance - 0.5f) / 0.5f));
    }

    @Override
    public void displayShock(boolean shock) {
        cb_shock.setChecked(shock);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tv_distance.setText(String.format("%s : %.1fkm",getString(R.string.distance),sb_distance.getProgress() * 0.5f + 0.5f));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
