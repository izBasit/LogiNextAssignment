package me.iz.mobility.loginextassignment.impl;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/10/2016.
 */

public class SmartBand implements Item {
    @Override
    public long getItemSerialNo() {
        return 9700;
    }

    @Override
    public String getItemName() {
        return "Mi Band 2";
    }

    @Override
    public double getItemPrice() {
        return 1999;
    }
}
