package idv.haojun.finebye.app.finebot;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import idv.haojun.finebye.R;
import idv.haojun.finebye.base.BaseActivity;
import idv.haojun.finebye.util.FineCalculate;

public class FineBotActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_fine_bot_road_way)
    RadioGroup rg_road_way;

    @BindView(R.id.rg_fine_bot_traffic_way)
    RadioGroup rg_traffic_way;

    @BindView(R.id.rg_fine_bot_over_speed)
    RadioGroup rg_over_speed;

    @BindView(R.id.tv_fine_bot_fine)
    TextView tv_fine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_bot);
        initTitle(R.string.fine_bot);
        ButterKnife.bind(this);
        rg_road_way.setOnCheckedChangeListener(this);
        rg_traffic_way.setOnCheckedChangeListener(this);
        rg_over_speed.setOnCheckedChangeListener(this);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int way = FineCalculate.FREE_WAY;
        int traffic = FineCalculate.MOTO;
        int overSpeed = FineCalculate.OVER_0_20;
        switch (rg_road_way.getCheckedRadioButtonId()) {
            case R.id.rb_fine_bot_highway:
                way = FineCalculate.HIGH_WAY;
                break;
            case R.id.rb_fine_bot_normalway:
                way = FineCalculate.NORMAL_WAY;
                break;
        }

        if (rg_traffic_way.getCheckedRadioButtonId() == R.id.rb_fine_bot_car) {
            traffic = FineCalculate.CAR;
        }

        switch (rg_over_speed.getCheckedRadioButtonId()) {
            case R.id.rb_fine_bot_speed_20_40:
                overSpeed = FineCalculate.OVER_20_40;
                break;
            case R.id.rb_fine_bot_speed_40_60:
                overSpeed = FineCalculate.OVER_40_60;
                break;
            case R.id.rb_fine_bot_speed_60_80:
                overSpeed = FineCalculate.OVER_60_80;
                break;
            case R.id.rb_fine_bot_speed_80_100:
                overSpeed = FineCalculate.OVER_80_100;
                break;
            case R.id.rb_fine_bot_speed_100_up:
                overSpeed = FineCalculate.OVER_100_up;
                break;
        }
        tv_fine.setText(String.format("$ %d", FineCalculate.calculateFine(way, traffic, overSpeed)));
    }
}
