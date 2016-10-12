package me.iz.mobility.loginextassignment.events;

import android.location.Location;

/**
 * Created by ibasit on 10/12/2016.
 */

public class LocationEvent {

    private Location location;

    public LocationEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
