package me.iz.mobility.loginextassignment.events;

import java.util.List;

import me.iz.mobility.loginextassignment.models.OrderStatus;

/**
 * Created by ibasit on 10/12/2016.
 */

public class OrderStatusEvent {

    private List<OrderStatus> orderStatus;

    private int selectedOrderStatus;

    public OrderStatusEvent(List<OrderStatus> orderStatus, int selectedOrderStatus) {
        this.orderStatus = orderStatus;
        this.selectedOrderStatus = selectedOrderStatus;
    }

    public int getSelectedOrderStatus() {
        return selectedOrderStatus;
    }

    public List<OrderStatus> getOrderStatus() {
        return orderStatus;
    }
}
