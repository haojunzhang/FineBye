package idv.haojun.finebye.app.main;

import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;

import idv.haojun.finebye.data.DrawerItem;

public interface MainContract {

    int REQUEST_WARNING_SETTING = 0;
    int REQUEST_SEARCH = 1;
    
    interface View {

        void displayAvatarUrl(String avatarUrl);

        void openWelcomeActivity();

        void openWarningSettingActivity();

        void exit();

        void openSearchActivity();
    }

    interface Presenter {

        void getAvatar();

        void setAvatarUrlDialog();

        void onDrawerItemClick(DrawerItem position);

        void setGoogleMap(GoogleMap googleMap);

        void getLastKnownLocation();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }
}
