package me.iz.mobility.loginextassignment.impl;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/11/2016.
 */

public class ItemImpl extends RealmObject
        implements Item {

    private String itemName;
    private double itemPrice;
    @PrimaryKey
    private long itemSerialNo;

    public ItemImpl() {
    }

    public ItemImpl(Item item) {
        this(item.getItemName(),item.getItemPrice(),item.getItemSerialNo());
    }

    public ItemImpl(String itemName, double itemPrice, long itemSerialNo) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSerialNo = itemSerialNo;
    }

    @Override
    public long getItemSerialNo() {
        return itemSerialNo;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public double getItemPrice() {
        return itemPrice;
    }
}
