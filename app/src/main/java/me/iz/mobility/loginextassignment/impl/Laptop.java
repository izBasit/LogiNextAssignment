package me.iz.mobility.loginextassignment.impl;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/10/2016.
 */

public class Laptop implements Item {
    @Override
    public long getItemSerialNo() {
        return 999707;
    }

    @Override
    public String getItemName() {
        return "HP Pavilion X2";
    }

    @Override
    public double getItemPrice() {
        return 30390;
    }
}
