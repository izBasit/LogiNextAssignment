package me.iz.mobility.loginextassignment.impl;

import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/10/2016.
 */

public class Mouse implements Item {
    @Override
    public long getItemSerialNo() {
        return 131313;
    }

    @Override
    public String getItemName() {
        return "Logitech MX510";
    }

    @Override
    public double getItemPrice() {
        return 799;

    }
}
