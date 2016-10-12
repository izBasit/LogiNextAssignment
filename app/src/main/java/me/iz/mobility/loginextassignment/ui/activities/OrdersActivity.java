package me.iz.mobility.loginextassignment.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import hugo.weaving.DebugLog;
import me.iz.mobility.loginextassignment.LogiNextApp;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.events.OrderEvent;
import me.iz.mobility.loginextassignment.events.OrderListEvent;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.ui.adapter.OrderAdapter;
import me.iz.mobility.loginextassignment.ui.decorators.DividerItemDecoration;
import me.iz.mobility.loginextassignment.ui.presenters.OrdersPresenter;
import me.iz.mobility.loginextassignment.ui.screencontracts.OrdersScreen;
import me.iz.mobility.loginextassignment.utils.BaseActivity;
import me.iz.mobility.loginextassignment.utils.OrderDetailCallBack;
import timber.log.Timber;

public class OrdersActivity extends BaseActivity implements OrderDetailCallBack, OrdersScreen {

    @Inject
    OrdersPresenter ordersPresenter;

    @BindView(R.id.rvOrderList)
    RecyclerView rvOrders;

    private OrderAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        LogiNextApp.from(this).getAppComponent().inject(this);

        ordersPresenter.loadOrders();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_orders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_track) {
            final Intent intent = new Intent();
            intent.setClass(this, TrackDeliveryBoyActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String getScreenName() {
        return "Order Management";
    }

    /**
     * Call back method called from {@link @OrderAdapter}
     * @param order
     */
    @Override
    public void viewDetail(Order order) {
        // Click is presented to presenter
        ordersPresenter.onShowPostOrderDetailClick(this, order);
    }

    /**
     * Call back from screen contract {@link OrdersScreen}
     * @param order
     */
    @Override
    public void launchOrderDetails(Order order) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        startActivity(intent);
        Timber.d("Sending Order");
        EventBus.getDefault().postSticky(new OrderEvent(order));
        finish();
    }

    /**
     * Data is received over here from DB on the {@link EventBus}
     * @param orderListEvent
     */
    @DebugLog
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handle(OrderListEvent orderListEvent) {

        if(orderListEvent == null || orderListEvent.isEventReceived()) {
            return;
        }
        // Doing this so that we dont receive the same event again and again, which is kind of a flaw in eventbus sending sticky events
        orderListEvent.setEventReceived(true);
        mAdapter = new OrderAdapter(this,orderListEvent.getOrders(),this);
        rvOrders.setAdapter(mAdapter);
        rvOrders.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        rvOrders.setLayoutManager(new LinearLayoutManager(this));
    }
}
