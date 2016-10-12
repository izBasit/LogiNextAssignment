package me.iz.mobility.loginextassignment.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import me.iz.mobility.loginextassignment.LogiNextApp;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.events.ItemListEvent;
import me.iz.mobility.loginextassignment.events.OrderEvent;
import me.iz.mobility.loginextassignment.events.OrderStatusEvent;
import me.iz.mobility.loginextassignment.models.CustomerInfo;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.models.OrderStatus;
import me.iz.mobility.loginextassignment.ui.adapter.ItemListAdapter;
import me.iz.mobility.loginextassignment.ui.adapter.OrderStatusSpinnerAdapter;
import me.iz.mobility.loginextassignment.ui.decorators.DividerItemDecoration;
import me.iz.mobility.loginextassignment.ui.presenters.OrderDetailsPresenter;
import me.iz.mobility.loginextassignment.ui.screencontracts.OrderDetailScreen;
import me.iz.mobility.loginextassignment.utils.BaseActivity;
import timber.log.Timber;

public class OrderDetailsActivity extends BaseActivity implements OrderDetailScreen {
    @Inject
    OrderDetailsPresenter orderDetailsPresenter;

    @BindView(R.id.tvOrderNo)
    TextView tvOrderNo;

    @BindView(R.id.tvDate)
    TextView tvDate;

    @BindView(R.id.spOrderStatus)
    Spinner spOrderStatus;

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;

    @BindView(R.id.tvCustomerAddress)
    TextView tvCustomerAddress;

    @BindView(R.id.tvPhoneNo)
    TextView tvPhoneNo;

    @BindView(R.id.rvItems)
    RecyclerView rvItems;

    /*
     Flag used for ignoring the first event fired by spinners {@link Spinner.OnItemSelectedListener}
     */
    private boolean isFirstSelection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogiNextApp.from(this).getAppComponent().inject(this);
        setContentView(R.layout.activity_order_details);

    }

    @Override
    protected String getScreenName() {
        return getString(R.string.order_details);
    }

    @Override
    public void launchOrdersScreen() {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        orderDetailsPresenter.onBackPressed(this);
    }

    /**
     * {@link OrderEvent} is received from {@link OrdersActivity}
     * @param orderEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handle(OrderEvent orderEvent) {
        Timber.d("Order received");

        if(orderEvent.isEventReceived())
            return;

        orderEvent.setEventReceived(true);

        Order order = orderEvent.getOrder();
        String orderNo = String.format(getString(R.string.order_no),order.getOrderNo());
        tvOrderNo.setText(orderNo);
        tvDate.setText(order.getOrderDate());

        orderDetailsPresenter.loadCustomerInfo(order);
        orderDetailsPresenter.loadItemList(order);
        orderDetailsPresenter.loadStatus(order);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handle(CustomerInfo customerInfo) {
        tvCustomerName.setText(customerInfo.getCustomerName());
        tvCustomerAddress.setText(customerInfo.getCustAddress());
        tvPhoneNo.setText(customerInfo.getCustPhoneNumber());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handle(ItemListEvent itemListEvent) {
        Timber.d("Order real proxy received %s",itemListEvent.toString());

        ItemListAdapter mAdapter = new ItemListAdapter(itemListEvent.getItemList());
        rvItems.setAdapter(mAdapter);
        rvItems.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handle(OrderStatusEvent orderStatusEvent) {

        OrderStatusSpinnerAdapter mAdapter = new OrderStatusSpinnerAdapter(this, R.layout. listitem_order_item,
                android.R.layout.simple_spinner_item, orderStatusEvent.getOrderStatus());
        // Apply the adapter to the spinner
        spOrderStatus.setAdapter(mAdapter);
        spOrderStatus.setSelection(orderStatusEvent.getSelectedOrderStatus());
        spOrderStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isFirstSelection) {
                    isFirstSelection = false;
                    return;
                }
                OrderStatus orderStatus = (OrderStatus) parent.getItemAtPosition(position);
                Timber.i("Item selected %s",orderStatus.name());
                orderDetailsPresenter.onItemSelected(orderStatus);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
