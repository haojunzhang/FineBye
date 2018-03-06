package idv.haojun.finebye.app.warningsetting;

import android.widget.CheckBox;
import android.widget.SeekBar;

public interface WarningSettingContract {
    interface View{
        void exit();

        void displayDistance(float distance);

        void displayShock(boolean shock);
    }
    interface Presenter{
        void done(float km, boolean isShock);

        void initDefaultValue();

        void showcase(SeekBar sb_distance, CheckBox cb_shock);
    }
}
