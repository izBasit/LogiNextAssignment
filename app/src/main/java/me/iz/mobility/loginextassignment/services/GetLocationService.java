/*
 * Copyright 2016 Basit Parkar.
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

package me.iz.mobility.loginextassignment.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import me.iz.mobility.loginextassignment.events.LocationEvent;
import me.iz.mobility.loginextassignment.locationapi.LogiNextLocationListener;
import me.iz.mobility.loginextassignment.locationapi.NavigationLocationMgr;
import me.iz.mobility.loginextassignment.utils.Constants;
import me.iz.mobility.loginextassignment.utils.Preferences;
import timber.log.Timber;


/**
 * Location Service
 */
public class GetLocationService extends Service implements LogiNextLocationListener {

    private static final String TAG = "GetLocationService";

    private NavigationLocationMgr mLocManager;
    private boolean isRunning;

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = false;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && !isRunning) {
            new Thread(() -> {
                isRunning = true;
                Preferences.saveBoolean(Constants.SERVICE_STARTED, true);
                mLocManager = new NavigationLocationMgr(getApplicationContext());
                mLocManager.getLocation(GetLocationService.this);
            }).start();
        }

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationFetched(Location location) {
        Timber.d("Location Obtained %s",location);
        if (location != null) {

            Timber.d("Posting Location %s",location);
            EventBus.getDefault().post(new LocationEvent(location));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cleanup();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        cleanup();
    }

    private void cleanup() {
        Timber.d("Cleanup service");
        if (mLocManager != null) {
            mLocManager.stopLocationRequests();
            mLocManager = null;
        }
        isRunning = false;
        Preferences.saveBoolean(Constants.SERVICE_STARTED, false);
    }
}
