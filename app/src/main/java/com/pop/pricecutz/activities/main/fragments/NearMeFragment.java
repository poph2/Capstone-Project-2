package com.pop.pricecutz.activities.main.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.pop.pricecutz.R;
import com.pop.pricecutz.SharedUtils;
import com.pop.pricecutz.backend.discountApi.model.Discount;
import com.pop.pricecutz.data.entries.CompanyEntry;
import com.pop.pricecutz.data.entries.DiscountEntry;
import com.pop.pricecutz.data.entries.OutletEntry;
import com.pop.pricecutz.data.mirrorbeans.Company;
import com.pop.pricecutz.data.mirrorbeans.Outlet;

import java.util.ArrayList;

/**
 * Created by adeniyi.bello on 11/24/2016.
 */

public class NearMeFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static String[] PERMISSIONS_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_LOCATION = 0;

    SupportMapFragment mSupportMapFragment;

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;

    GoogleMap mMap;

    boolean mapReady = false;
    boolean initialZoom = true;

    ArrayList<Discount> discountArrayList;
    ArrayList<Company> companyArrayList;
    ArrayList<Outlet> outletArrayList;

    Context mContext;

    View rootView;

    int buildVersion;

    private final String LOG_TAG = NearMeFragment.class.getSimpleName();

    public NearMeFragment() {
        discountArrayList = new ArrayList<>();
        companyArrayList = new ArrayList<>();
        outletArrayList = new ArrayList<>();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getContext();

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if(rootView == null) {

            rootView = inflater.inflate(R.layout.fragment_near_me, null, false);

            buildVersion = Build.VERSION.SDK_INT;

            getLoaderManager().initLoader(0, null, this);

            initilizeMap();
        }

        return rootView;
    }

    private void initilizeMap() {
        mSupportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapwhere);

        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if(mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        mMap = googleMap;

        if(initialZoom) {
            updateInitialCamera();

            addOutletMarkers();
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);


        if (checkLocationPermission() != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        else {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }

        if(initialZoom) {
            updateInitialCamera();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //Log.d(LOG_TAG, "Location - " + location.getLatitude() + ", " + location.getLongitude());

//        mMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .title("")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2))
//        );

    }

    private void updateInitialCamera() {

        if (checkLocationPermission() != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        }
        else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if(mLastLocation != null) {
                initialZoom = true;
                LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng)      // Sets the center of the map to Mountain View
                        .zoom(15)                   // Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 5000, null);

                addOutletMarkers();
            }
            mMap.getUiSettings().setZoomControlsEnabled(false);
            mMap.getUiSettings().setCompassEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.setMyLocationEnabled(true);

        }
    }


    private int checkLocationPermission() {
        if(buildVersion >= 21) {
            return ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
        }
        else {
            return PackageManager.PERMISSION_GRANTED;
        }
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        CursorLoader cursorLoader = null;

        //Log.d(LOG_TAG, " CursorLoader ID - " + id);

        switch (id) {
            case 0:                 //Get Discounts
                String condition =
                        DiscountEntry.TABLE_NAME + "." + DiscountEntry.COLUMN_STATUS + " = '" + SharedUtils.DISCOUNT_STATUS_ACTIVE + "' AND " +
                        DiscountEntry.TABLE_NAME + "." + DiscountEntry.COLUMN_AVAILABLE_BAL + " > 0";

                cursorLoader = new CursorLoader(
                        mContext,
                        DiscountEntry.CONTENT_URI_WITH_COMPANY,
                        null,
                        condition,
                        null,
                        null);
                break;

            case 1:             //Get Outlets

                //Log.d(LOG_TAG, " 2222 ");

                String condition2 = OutletEntry.TABLE_NAME + "." + OutletEntry.COLUMN_COY_ID + " IN ( ";

                for(Company company: companyArrayList) {
                    condition2 = condition2 + " '" + company.getId() + "' , ";
                }

                condition2 = condition2.substring(0, condition2.length() - 3);

                condition2 = condition2 + " ) ";

                //Log.d(LOG_TAG, condition2);

                cursorLoader = new CursorLoader(
                        mContext,
                        OutletEntry.CONTENT_URI,
                        null,
                        condition2,
                        null,
                        null);

                break;
        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {


        switch (loader.getId()) {

            case 0:
                companyArrayList = new ArrayList<>();

                data.moveToFirst();

                if(data.moveToFirst()){
                    do{

                        long companyID = data.getLong(data.getColumnIndex(DiscountEntry.COLUMN_COY_ID));

//                        Log.d(LOG_TAG, "Company - " + new Gson().toJson(c));

                        if (!searchCompanyArrayList(companyID)) {
                            Company c = new Company();
                            c.update(data, DiscountEntry.COLUMN_COY_ID);

//                            Log.d(LOG_TAG, "Company - " + new Gson().toJson(c));

                            companyArrayList.add(c);
                        }
                    }while(data.moveToNext());
                }

//                Log.d(LOG_TAG, "companyArrayList.size() - " + companyArrayList.size());

                if(companyArrayList.size() > 0) {
                    getLoaderManager().initLoader(1, null, this);
                }
                break;
            case 1:

                outletArrayList = new ArrayList<>();

                //Log.d(LOG_TAG, "Outlet - " + data.getCount());

                if(data.moveToFirst()) {
                    do {

                        long outletID = data.getLong(data.getColumnIndex(OutletEntry._ID));

                        if (!searchOutletArrayList(outletID)) {
                            Outlet o = new Outlet();
                            o.update(data, OutletEntry._ID);

                            //Log.d(LOG_TAG, "Outlet - " + new Gson().toJson(o));

                            outletArrayList.add(o);
                        }
                    } while (data.moveToNext());
                }

                addOutletMarkers();
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public boolean searchCompanyArrayList(Long id) {
        for(Company c: companyArrayList) {
            if(c.getId() == new Long(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchOutletArrayList(Long id) {
        for(Outlet o: outletArrayList) {
            if(o.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public Company getCompany(Outlet o) {
        for (Company c : companyArrayList) {
            if(c.getId() == o.getCoy_id()) {
                return c;
            }
        }
        return null;
    }

    public void addOutletMarkers() {

        //mMap.clear();

//        Log.d(LOG_TAG, "mLastLocation - " + new Gson().toJson(mLastLocation));

        if(mLastLocation != null) {

            for (Outlet o : outletArrayList) {
                Company c = getCompany(o);
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(mLastLocation.getLatitude() + o.getOutlet_latitude()/10, mLastLocation.getLongitude() + o.getOutlet_longitude()/10))
                        .title(c.getCoy_name()));
            }
        }
    }
}
