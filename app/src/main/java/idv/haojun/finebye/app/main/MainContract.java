package idv.haojun.finebye.app.main;

public interface MainContract {
    
    interface View {

        void displayAvatarUrl(String avatarUrl);
    }

    interface Presenter {

        void getAvatar();

        void setAvatarUrlDialog();
    }
    
}
