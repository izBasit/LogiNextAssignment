package me.iz.mobility.loginextassignment.events;

import java.util.List;

import me.iz.mobility.loginextassignment.models.Order;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrderListEvent {

    private List<Order> orders;

    private boolean isEventReceived;

    public OrderListEvent(List<Order> orders) {
        this.orders = orders;
        this.isEventReceived = false;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isEventReceived() {
        return isEventReceived;
    }

    public void setEventReceived(boolean eventReceived) {
        isEventReceived = eventReceived;
    }
}
