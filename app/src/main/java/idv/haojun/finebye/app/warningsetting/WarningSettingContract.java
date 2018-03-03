package idv.haojun.finebye.app.warningsetting;

public interface WarningSettingContract {
    interface View{
        void exit();

        void displayDistance(float distance);

        void displayShock(boolean shock);
    }
    interface Presenter{
        void done(float km, boolean isShock);

        void initDefaultValue();
    }
}
