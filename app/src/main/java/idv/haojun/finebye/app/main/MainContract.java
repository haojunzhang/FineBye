package idv.haojun.finebye.app.main;

import com.google.android.gms.maps.GoogleMap;

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

        void setGoogleMap(GoogleMap googleMap);
    }
    
}
