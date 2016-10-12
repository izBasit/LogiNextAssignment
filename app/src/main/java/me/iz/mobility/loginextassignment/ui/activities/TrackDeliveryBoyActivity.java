package me.iz.mobility.loginextassignment.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.events.LocationEvent;
import me.iz.mobility.loginextassignment.services.GetLocationService;
import me.iz.mobility.loginextassignment.utils.BaseActivity;
import me.iz.mobility.loginextassignment.utils.Constants;
import me.iz.mobility.loginextassignment.utils.Preferences;
import me.iz.mobility.loginextassignment.utils.Utility;
import timber.log.Timber;

import static me.iz.mobility.loginextassignment.R.id.map;

public class TrackDeliveryBoyActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private GoogleMap mMap;

    private Marker marker = null;

    private LatLng previousLoc = null;

    private boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_delivery_boy);

        RxPermissions.getInstance(this)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) {
                        initMap();
                    } else {
                        Toast.makeText(this, "Permission denied. Can't fetch location.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }

    private void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        isStarted = Preferences.getBoolean(Constants.SERVICE_STARTED, false);
        fab.setActivated(isStarted);

    }

    @OnClick(R.id.fab)
    public void onFabClickListener() {
        if (!Utility.isGPSOn(this)) {
            Snackbar.make(fab, "Please turn on GPS", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Turn On", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent gpsOptionsIntent = new Intent(
                                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(gpsOptionsIntent);
                        }
                    })
                    .setActionTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
                    .show();

            return;
        }

        Intent intent = new Intent();
        intent.setClass(this, GetLocationService.class);
        if (isStarted) {
            isStarted = false;
            stopService(intent);
        } else {
            isStarted = true;
            // Pass the update request to the requester object
            startService(intent);
        }
        Preferences.saveBoolean(Constants.SERVICE_STARTED, isStarted);
        fab.setActivated(isStarted);
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.track);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.clear();
        mMap.setMyLocationEnabled(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handle(LocationEvent event) {
        Toast.makeText(this, "Location Received", Toast.LENGTH_SHORT).show();
        Location location = event.getLocation();
        LatLng currentLatLong = new LatLng(location.getLatitude(), location.getLongitude());
        Timber.d("Plotting location %s", currentLatLong);
        plotDeliveryBoyLocation(currentLatLong);
    }

    private void plotDeliveryBoyLocation(LatLng currentLoc) {

        if (currentLoc == null) {
            Timber.w("Lat long is null");
            return;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));

        if (marker == null) {
            marker = mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.red_pin))
                    .position(currentLoc)
                    .flat(true)
            );

            final CameraPosition cameraPosition = CameraPosition.builder()
                    .target(currentLoc)
                    .zoom(17)
                    .bearing(90)
                    .build();

            // Animate the change in camera view over 2 seconds
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                    2000, null);

            marker.setPosition(currentLoc);

            // Drawing Polyline
            if(previousLoc != null) {
                drawPolyLine(previousLoc,currentLoc);
            }
            // Setting the current location as the previous location
            previousLoc = currentLoc;

        }
    }

    private void drawPolyLine(LatLng oldLoc, LatLng newLoc) {
        Toast.makeText(this, "Trying to draw polyline", Toast.LENGTH_SHORT).show();
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(oldLoc, newLoc)
                .width(5)
                .color(Color.BLUE));
    }

    private void stopService() {
        Intent intent = new Intent();
        intent.setClass(this, GetLocationService.class);
        stopService(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        stopService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }
}
