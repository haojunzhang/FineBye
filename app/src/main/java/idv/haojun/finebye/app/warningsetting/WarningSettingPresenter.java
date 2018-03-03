package idv.haojun.finebye.app.warningsetting;

import android.content.Context;

import idv.haojun.finebye.helper.SPHelper;

public class WarningSettingPresenter implements WarningSettingContract.Presenter {
    private WarningSettingContract.View mView;
    private Context context;

    WarningSettingPresenter(WarningSettingContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
    }

    @Override
    public void initDefaultValue() {
        mView.displayDistance(SPHelper.getDistance(context));
        mView.displayShock(SPHelper.isShock(context));
    }

    @Override
    public void done(float distance, boolean isShock) {
        SPHelper.setDistance(context, distance);
        SPHelper.setShock(context, isShock);
        mView.exit();
    }
}
