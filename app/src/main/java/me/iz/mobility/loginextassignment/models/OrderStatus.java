package me.iz.mobility.loginextassignment.models;

/**
 * Created by ibasit on 10/10/2016.
 */

public enum OrderStatus {

    PICKED_UP(0), DELIVERED(1), CANCELLED(2);

    private int status;

    OrderStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
