package me.iz.mobility.loginextassignment.ui.presenters;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmResults;
import me.iz.mobility.loginextassignment.events.OrderListEvent;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.ui.screencontracts.OrdersScreen;
import timber.log.Timber;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrdersPresenter {

    private  Realm realm;

    @Inject
    public OrdersPresenter(Realm realm) {
        this.realm = realm;
    }

    @DebugLog
    public void onShowPostOrderDetailClick(OrdersScreen ordersScreen, Order order) {
        ordersScreen.launchOrderDetails(order);
    }

    @DebugLog
    public void loadOrders() {
        RealmResults results = realm.where(Order.class).findAll();
        if(results == null) {
            Timber.e("Results are null");
            return;
        }

        List<Order> mOrderList = new ArrayList(results.subList(0,results.size()));
        EventBus.getDefault().postSticky(new OrderListEvent(mOrderList));
    }


}
