package me.iz.mobility.loginextassignment.models;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.iz.mobility.loginextassignment.impl.ItemImpl;
import me.iz.mobility.loginextassignment.inf.Item;
import me.iz.mobility.loginextassignment.utils.RealmHelper;
import me.iz.mobility.loginextassignment.utils.Utility;

/**
 * Created by ibasit on 10/10/2016.
 */

public class Order extends RealmObject implements Parent<Item> {

    @PrimaryKey
    private String orderNo;

    private String orderDate;

    private RealmList<OrderItem> orderItems = new RealmList<>();

    private int orderStatus;

    private long customerId;

    private long eta;

    public Order() {
    }

    public Order(String orderNo, String orderDate, RealmList<OrderItem> orderItems, int orderStatus, long customerId, long eta) {
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.eta = eta;
    }

    public Order(long customerId) {
        orderNo = "LN"+Utility.randInt();
        this.orderStatus = OrderStatus.PICKED_UP.getStatus();
        this.customerId = customerId;
        this.eta = Utility.getRandomETA();
        this.orderDate = Utility.getDate();
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Price of the order
     * @return
     */
//    public double getOrderPrice() {
//
//        double cost = 0.0d;
//
//        for (Item item : orderItems) {
//            cost += item.getItemPrice();
//        }
//        return cost;
//    }

    public void addItems(Item item) {
        orderItems.add(new OrderItem(orderNo,item.getItemSerialNo()));
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

//    public List<ItemImpl> getOrderItems() {
//        return orderItems.subList(0,orderItems.size());
//    }
//
//    public void setOrderItems(List<ItemImpl> orderItems) {
//        this.orderItems = orderItems;
//    }

    public OrderStatus getOrderStatus() {

        if(orderStatus == 0)
            return OrderStatus.PICKED_UP;

        if(orderStatus == 1)
            return OrderStatus.DELIVERED;

        if(orderStatus == 2)
            return OrderStatus.CANCELLED;

        return OrderStatus.CANCELLED;

    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getStatus();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEta() {
        return eta;
    }

    public void setEta(long eta) {
        this.eta = eta;
    }

    @Override
    public List<Item> getChildList() {
        Realm realm = RealmHelper.getRealmInstance();
        List<Item> items = new ArrayList<>(orderItems.size());
        for (OrderItem orderItem : orderItems) {
            ItemImpl result = realm.where(ItemImpl.class).equalTo("itemSerialNo",orderItem.getItemId()).findFirst();
            items.add(result);
        }

        return items;
    }

    public RealmList<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
