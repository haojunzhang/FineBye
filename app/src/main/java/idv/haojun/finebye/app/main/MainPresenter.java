package idv.haojun.finebye.app.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import idv.haojun.finebye.R;
import idv.haojun.finebye.adapter.BaseRecyclerViewAdapter;
import idv.haojun.finebye.adapter.ThemeColorRVAdapter;
import idv.haojun.finebye.app.finebot.FineBotActivity;
import idv.haojun.finebye.app.search.SearchActivity;
import idv.haojun.finebye.app.warningsetting.WarningSettingActivity;
import idv.haojun.finebye.base.App;
import idv.haojun.finebye.data.Cam;
import idv.haojun.finebye.data.DrawerItem;
import idv.haojun.finebye.helper.ColorHelper;
import idv.haojun.finebye.helper.GoogleMapHelper;
import idv.haojun.finebye.helper.SPHelper;
import io.objectbox.Box;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private Context context;
    private LocationManager mLocationManager;
    private GoogleMap mMap;
    private Circle circleCurrent;

    MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.context = (Context) mView;
    }

    private AlertDialog ad;

    private void themePickDialog() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_theme_pick, null);
        RecyclerView rv = v.findViewById(R.id.rv_dialog_theme_pick);

        final List<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(context, R.color.colorBluePrimary));
        colors.add(ContextCompat.getColor(context, R.color.colorRedAccent));
        colors.add(ContextCompat.getColor(context, R.color.colorGreenAccent));

        ThemeColorRVAdapter adapter = new ThemeColorRVAdapter(context, R.layout.item_rv_theme_pick);
        rv.setLayoutManager(new GridLayoutManager(context, 3));
        rv.setAdapter(adapter);
        adapter.setData(colors);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (SPHelper.getThemeColor(context) != colors.get(position)) {
                    ad.dismiss();
                    SPHelper.setThemeColor(context, colors.get(position));
                    mView.openWelcomeActivity();
                }
            }
        });

        ad = new AlertDialog.Builder(context)
                .setTitle(R.string.theme)
                .setView(v)
                .show();
    }

    @Override
    public void getAvatar() {
        String avatarUrl = SPHelper.getAvatarUrl(context);
        if (!avatarUrl.isEmpty())
            mView.displayAvatarUrl(avatarUrl);
    }

    @Override
    public void setAvatarUrlDialog() {
        final EditText et = new EditText(context);
        new AlertDialog.Builder(context)
                .setView(et)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String avatarUrl = et.getText().toString();
                        if (avatarUrl.isEmpty()) {
                            return;
                        }
                        SPHelper.setAvatarUrl(context, avatarUrl);
                        mView.displayAvatarUrl(avatarUrl);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onDrawerItemClick(DrawerItem drawerItem) {
        switch (drawerItem.getId()) {
            case DrawerItem.MAP:

                break;
            case DrawerItem.WARNING_SETTING:
                mView.openWarningSettingActivity();
                break;
            case DrawerItem.FINE_BOT:
                context.startActivity(new Intent(context, FineBotActivity.class));
                break;
            case DrawerItem.SEARCH:
                context.startActivity(new Intent(context, SearchActivity.class));
                break;
            case DrawerItem.THEME:
                themePickDialog();
                break;
        }
    }

    @Override
    public void setGoogleMap(GoogleMap googleMap) {
        this.mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setIndoorEnabled(true);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void getLastKnownLocation() {
        if (mLocationManager == null)
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MainContract.REQUEST_WARNING_SETTING:
                getLastKnownLocation();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                mView.exit();
                Toast.makeText(context, R.string.permission_denied, Toast.LENGTH_SHORT).show();
                return;
            }
        }
        getLastKnownLocation();
    }

    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (location != null) {

                // move to current position
                LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                GoogleMapHelper.moveMap(mMap, current);

                // clear perv circle
                if (circleCurrent != null) {
                    circleCurrent.remove();
                    circleCurrent = null;
                }

                // draw circle
                circleCurrent = mMap.addCircle(new CircleOptions()
                        .center(current)
                        .radius(SPHelper.getDistance(context) * 1000)
                        .strokeColor(SPHelper.getThemeColor(context))
                        .fillColor(ColorHelper.addAlpha(SPHelper.getThemeColor(context), 0.3f))
                );

                // add speed camera marker
                final Box<Cam> camBox = ((App) ((Activity) mView).getApplication()).getBoxStore().boxFor(Cam.class);
                List<Cam> cams = camBox.query().build().find();
                for (int i = 0; i < cams.size(); i++) {
                    if (i == 0) continue;
                    Cam cam = cams.get(i);
                    double lat = Double.valueOf(cam.getLatitude());
                    double lng = Double.valueOf(cam.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lat, lng))
                            .title(cam.getAddress())
                            .snippet(String.format("方向:%s, 速限:%s", cam.getDirect(), cam.getLimit()))
                    );
                }
            }
            mLocationManager.removeUpdates(locationListener);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };
}
