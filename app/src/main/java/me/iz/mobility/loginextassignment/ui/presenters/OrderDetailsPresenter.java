package me.iz.mobility.loginextassignment.ui.presenters;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.realm.Realm;
import me.iz.mobility.loginextassignment.events.ItemListEvent;
import me.iz.mobility.loginextassignment.events.OrderStatusEvent;
import me.iz.mobility.loginextassignment.models.CustomerInfo;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.models.OrderStatus;
import me.iz.mobility.loginextassignment.ui.screencontracts.OrderDetailScreen;
import timber.log.Timber;

/**
 * Created by ibasit on 10/12/2016.
 */

public class OrderDetailsPresenter {

    private Realm realm;

    private Order order;

    @Inject
    public OrderDetailsPresenter(Realm realm) {
        this.realm = realm;
    }

    public void onBackPressed(OrderDetailScreen orderDetailScreen) {
        orderDetailScreen.launchOrdersScreen();
    }

    @DebugLog
    public void loadCustomerInfo(Order order) {
        CustomerInfo customerInfo = realm.where(CustomerInfo.class).equalTo("custId",order.getCustomerId()).findFirst();
        Timber.d("Customer found %s",customerInfo.toString());
        EventBus.getDefault().postSticky(customerInfo);
    }

    @DebugLog
    public void loadItemList(Order order) {
        EventBus.getDefault().postSticky(new ItemListEvent(order.getChildList()));
    }

    @DebugLog
    public void loadStatus(Order order) {
        this.order = order;
        int currentStatus = order.getOrderStatus().getStatus();
        List<OrderStatus> orderStatusList = Arrays.asList(OrderStatus.values());
        EventBus.getDefault().postSticky(new OrderStatusEvent(orderStatusList, currentStatus));
    }

    @DebugLog
    public void onItemSelected(OrderStatus orderStatus) {
        Timber.d("Updating order status");
        // Updating the status in db
        realm.beginTransaction();
        try {
            Order updateOrder = realm.where(Order.class).equalTo("orderNo",order.getOrderNo()).findFirst();
            updateOrder.setOrderStatus(orderStatus);
            realm.copyToRealmOrUpdate(updateOrder);
        } finally {
            realm.commitTransaction();
        }

        Timber.d("Order status updated");
    }
}
