package me.iz.mobility.loginextassignment.events;

import me.iz.mobility.loginextassignment.models.Order;

/**
 * Created by ibasit on 10/12/2016.
 */

public class OrderEvent {

    private Order order;

    private boolean isEventReceived;

    public OrderEvent(Order order) {
        this.order = order;
        this.isEventReceived = false;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isEventReceived() {
        return isEventReceived;
    }

    public void setEventReceived(boolean eventReceived) {
        isEventReceived = eventReceived;
    }
}
