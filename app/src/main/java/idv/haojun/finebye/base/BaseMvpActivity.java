package idv.haojun.finebye.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
