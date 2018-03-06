package idv.haojun.finebye.app.warningsetting;

import android.app.Activity;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.SeekBar;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.SimpleShowcaseEventListener;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import idv.haojun.finebye.R;
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
    public void showcase(SeekBar sb_distance, final CheckBox cb_shock) {
        new ShowcaseView.Builder((Activity) mView)
                .withMaterialShowcase()
                .setTarget(new ViewTarget(sb_distance))
                .setContentTitle(R.string.warning_setting_showcase_distance_title)
                .setContentText(R.string.warning_setting_showcase_distance_text)
                .setStyle(R.style.CustomShowcaseTheme1)
                .replaceEndButton(R.layout.showcase_button_warning_setting)
                .singleShot(0)
                .setShowcaseEventListener(new SimpleShowcaseEventListener(){
                    @Override
                    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
                        super.onShowcaseViewDidHide(showcaseView);

                        new ShowcaseView.Builder((Activity) mView)
                                .withMaterialShowcase()
                                .setTarget(new ViewTarget(cb_shock))
                                .setContentTitle(R.string.warning_setting_showcase_shock_title)
                                .setContentText(R.string.warning_setting_showcase_shock_text)
                                .setStyle(R.style.CustomShowcaseTheme1)
                                .replaceEndButton(R.layout.showcase_button_warning_setting)
                                .singleShot(1)
                                .build();
                    }
                })
                .build();
    }

    @Override
    public void done(float distance, boolean isShock) {
        SPHelper.setDistance(context, distance);
        SPHelper.setShock(context, isShock);
        mView.exit();
    }
}
