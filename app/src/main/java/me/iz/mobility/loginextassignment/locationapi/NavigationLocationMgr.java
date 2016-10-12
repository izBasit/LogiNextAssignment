/*
 * Copyright 2015 Basit Parkar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package me.iz.mobility.loginextassignment.locationapi;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * This class gives user's current location.
 */
public class NavigationLocationMgr implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final int FASTEST_INTERVAL = 1000 * 10; // 30 seconds
    private static final int UPDATE_INTERVAL = 1000 * 10; // 30 seconds
    private static final int DISPLACEMENT_MTRS = 10;

    /**
     * Tag for showing logs.
     */
    private static final String TAG = "LocationMgr";


    /**
     * Our location call back for getting location updates.
     */
    private LogiNextLocationListener mLocationListener = null;

    private LocationListener locationListener;

    private GoogleApiClient mGoogleApiClient;

    private Context mContext;

    private LocationManager manager;

    private LocationRequest mLocReq;

    // constructor
    public NavigationLocationMgr(Context context) {
        mContext = context;
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        buildGoogleApiClient();
    }

    public void getLocation(LogiNextLocationListener locationListener) {
        this.mLocationListener = locationListener;
        mGoogleApiClient.connect();
    }

    public boolean isGpsEnabled() {
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocReq = new LocationRequest();
        mLocReq.setFastestInterval(FASTEST_INTERVAL);
        mLocReq.setInterval(UPDATE_INTERVAL);
//        mLocReq.getSmallestDisplacement();
        mLocReq.setSmallestDisplacement(DISPLACEMENT_MTRS);
        mLocReq.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocReq.setNumUpdates(NUM_OF_UPDATE);

        locationListener = location -> {
            if (location != null) {
                mLocationListener.onLocationFetched(location);
            }
        };
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "onConnected");
        if (servicesAvailable()) {
//            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                    mGoogleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates
                    (mGoogleApiClient, mLocReq, locationListener);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onDisconnected");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed" + connectionResult.getErrorCode());
    }

    private boolean servicesAvailable() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
        return ConnectionResult.SUCCESS == resultCode;
    }

    public void stopLocationRequests() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, locationListener);
    }
}
