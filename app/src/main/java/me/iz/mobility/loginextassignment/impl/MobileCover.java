package me.iz.mobility.loginextassignment.impl;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/10/2016.
 */

public class MobileCover implements Item {
    @Override
    public long getItemSerialNo() {
        return 98080;
    }

    @Override
    public String getItemName() {
        return "Spigen Slim Armor";
    }

    @Override
    public double getItemPrice() {
        return 999;
    }
}
