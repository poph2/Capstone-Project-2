package com.pop.pricecutz.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.pop.pricecutz.Outlet;
import com.pop.pricecutz.R;
import com.pop.pricecutz.Randomizer;
import com.pop.pricecutz.map.GPSPoint;

import java.util.ArrayList;

/**
 * Created by Pop H2 on 8/25/2016.
 * Pop Inc
 * Lagos Nigeria
 */
public class NearMeFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    public String TAG = NearMeFragment.class.getSimpleName();

    SupportMapFragment mSupportMapFragment;
    GoogleMap mMap;

    ArrayList<Outlet> mOutletArrayList;

    private Context mContext;

    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

//    String mProviderString;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute

    // Declaring a Location Manager
    protected LocationManager mLocationManager;

    private final int ACCESS_FINE_LOCATION_REQUEST = 1;

    public NearMeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_near_me, null, false);

        //initilizeMap();

        this.mContext = getContext();

        //initLocationManager();

        //getOutlets();

        return root;
    }

    private void initilizeMap() {
        mSupportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapwhere);

        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }
        if (mSupportMapFragment != null)
        {
            mSupportMapFragment.getMapAsync(this);
//            if (mMap != null)
//                mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
//                {
//                    @Override
//                    public void onMapClick(LatLng point)
//                    {
//                        //TODO: your onclick stuffs
//                    }
//                });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(this);

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 5000, null);
//            mMap.getUiSettings().setZoomControlsEnabled(false);
//            mMap.getUiSettings().setCompassEnabled(false);
//            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setMyLocationEnabled(true);
        }

        //addMarkers();

        Location location = getLocation();

        if(location != null) {

            Log.d(TAG, new Gson().toJson(location));

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2))
            );

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(latLng)              // Sets the center of the map to Mountain View
//                    .zoom(20)                   // Sets the zoom
//                    .bearing(0)                 // Sets the orientation of the camera to east
//                    .tilt(0)                    // Sets the tilt of the camera to 30 degrees
//                    .build();                   // Creates a CameraPosition from the builder
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//            // Zoom in, animating the camera.
//            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mLocationManager.removeUpdates(this);
        }
        catch(SecurityException se) {}
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            requestLocationUpdates();
        }
        catch(SecurityException se) {}
    }

    public void initLocationManager() {
        mLocationManager = null;
        try {

            mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

//            Criteria criteria = new Criteria();
//            criteria.setAccuracy(Criteria.ACCURACY_FINE);
//            criteria.setAltitudeRequired(false);
//            criteria.setBearingRequired(false);

//            mProviderString = mLocationManager.getBestProvider(criteria, false);

            requestLocationUpdates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Location getLocation() {
        Location location = null;
        try {

            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (mLocationManager != null) {
                    if(location == null) {
                        location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                    if(location == null) {
                        location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                    if(location == null) {
                        location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error in getting location");
        }

        Toast.makeText(mContext, new Gson().toJson(location), Toast.LENGTH_LONG).show();

        return location;
    }

    public void requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (mLocationManager != null) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 400, 1, this);
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1, this);
                mLocationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 400, 1, this);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode) {
            case ACCESS_FINE_LOCATION_REQUEST:
                Log.e("checkForPermission", "checkForPermission");
                getLocation();
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        zoomToMyLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void zoomToMyLocation(Location location) {
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
//        mMap.animateCamera(cameraUpdate);
    }

    AsyncTask<Void, Void, Void> zoomTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
//            try {
//                //Thread.sleep(5000);
//            } catch (InterruptedException e) { }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    };

    public void addMarkers() {

        for(int i = 0; i < mOutletArrayList.size(); i++) {
            Outlet outlet = mOutletArrayList.get(i);
            GPSPoint gpsPoint = outlet.getGpsPoint();
            LatLng latLng = new LatLng(gpsPoint.getLatitude(), gpsPoint.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            );
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
//        mMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
//        );
//        Log.d(TAG, latLng.latitude + ", " + latLng.longitude);
    }


    public void getOutlets() {
        Location location = getLocation();

        GPSPoint gpsPoint = new GPSPoint(location.getLatitude(), location.getLongitude());

        mOutletArrayList = Randomizer.getOutlets(100, gpsPoint, 0.02, 0.02);
    }
}
