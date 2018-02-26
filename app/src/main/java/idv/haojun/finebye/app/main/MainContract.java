package idv.haojun.finebye.app.main;

import idv.haojun.finebye.data.DrawerItem;

public interface MainContract {
    
    interface View {

        void displayAvatarUrl(String avatarUrl);

        void openWelcomeActivity();
    }

    interface Presenter {

        void getAvatar();

        void setAvatarUrlDialog();

        void onDrawerItemClick(DrawerItem position);
    }
    
}
