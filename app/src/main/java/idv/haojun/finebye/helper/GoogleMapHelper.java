package idv.haojun.finebye.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleMapHelper {

    public static void addMarkerToMap(GoogleMap mMap, LatLng latLng) {
        addMarkerToMap(mMap, latLng, null);
    }

    public static void addMarkerToMap(GoogleMap mMap, LatLng latLng, Bitmap bitmap) {
        MarkerOptions options = new MarkerOptions();
        options.position(latLng);
        if (bitmap != null)
            options.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
        mMap.addMarker(options);
    }

    public static void moveMap(GoogleMap mMap, LatLng place) {
        // build camera
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(16)
                        .build();
        // move
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public static void moveMap(Context context, GoogleMap mMap, LatLng from, LatLng to) {
        int padding = (int) (100 * context.getResources().getDisplayMetrics().density);
        LatLngBounds.Builder b = new LatLngBounds.Builder();
        b.include(from);
        b.include(to);
        // move camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(b.build(), padding));
    }

    public static Polyline addPolyLineToMap(GoogleMap mMap, List<LatLng> latLngs, int color) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .addAll(latLngs)
                .width(16)
                .color(color)
                .geodesic(true);
        // add polyline to map
        Polyline polyline = mMap.addPolyline(polylineOptions);
        polyline.setClickable(true);
        return polyline;
    }

    public static String getLocationsQueryString(List<LatLng> list) {
        StringBuilder sb = new StringBuilder();
        boolean f = true;
        for (LatLng l : list) {
            sb.append(f ? "" : "|").append(String.format("%f,%f", l.latitude, l.longitude));
            f = false;
        }
        return sb.toString();
    }


    public static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    public static double distance(double lat1, double lng1, double lat2, double lng2) {
        Location l1 = new Location("L1");
        Location l2 = new Location("L2");
        l1.setLatitude(lat1);
        l1.setLongitude(lng1);
        l2.setLatitude(lat2);
        l2.setLongitude(lng2);
        return l1.distanceTo(l2);
    }
}