package me.iz.mobility.loginextassignment.models;

import io.realm.RealmObject;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrderItem extends RealmObject {

    private String orderNo;

    private long itemId;

    public OrderItem() {
    }

    public OrderItem(String orderNo, long itemId) {
        this.orderNo = orderNo;
        this.itemId = itemId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
