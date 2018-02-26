package idv.haojun.finebye.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

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

    private void initTheme(){
        int color = SPHelper.getThemeColor(this);
        if (color == ContextCompat.getColor(this, R.color.colorBluePrimary)){
            setTheme(R.style.BlueTheme);
        }else if(color == ContextCompat.getColor(this, R.color.colorRedPrimary)){
            setTheme(R.style.RedTheme);
        }else if(color == ContextCompat.getColor(this, R.color.colorGreenPrimary)){
            setTheme(R.style.GreenTheme);
        }
    }
}
