package me.iz.mobility.loginextassignment.impl;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/10/2016.
 */

public class Mobile implements Item {
    @Override
    public long getItemSerialNo() {
        return 131231;
    }

    @Override
    public String getItemName() {
        return "One plus 3";
    }

    @Override
    public double getItemPrice() {
        return 27000;
    }
}
