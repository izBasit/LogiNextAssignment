package me.iz.mobility.loginextassignment.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.inf.Item;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.utils.OrderDetailCallBack;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrderAdapter extends ExpandableRecyclerAdapter<Order,Item, OrderViewHolder,OrderItemViewHolder> {

    private LayoutInflater mInflater;

    private List<Order> orders;

    private OrderDetailCallBack orderDetailCallBack;

    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public OrderAdapter(Context context, @NonNull List<Order> parentList, OrderDetailCallBack orderDetailCallBack) {
        super(parentList);
        orders = parentList;
        this.orderDetailCallBack = orderDetailCallBack;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View orderView = mInflater.inflate(R.layout.listitem_order, parentViewGroup,false);
        return new OrderViewHolder(orderView);
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View itemView = mInflater.inflate(R.layout.listitem_order_item, childViewGroup, false);
        return new OrderItemViewHolder(itemView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull OrderViewHolder parentViewHolder, int parentPosition, @NonNull Order parent) {
        parentViewHolder.bind(parent, orderDetailCallBack);
    }

    @Override
    public void onBindChildViewHolder(@NonNull OrderItemViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull Item child) {
        childViewHolder.bind(child);
    }
}
