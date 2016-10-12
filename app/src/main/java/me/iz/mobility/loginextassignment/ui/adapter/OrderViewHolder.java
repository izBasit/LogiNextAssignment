package me.iz.mobility.loginextassignment.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.models.Order;
import me.iz.mobility.loginextassignment.models.OrderStatus;
import me.iz.mobility.loginextassignment.utils.OrderDetailCallBack;
import me.iz.mobility.loginextassignment.utils.Utility;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrderViewHolder extends ParentViewHolder {

    @BindView(R.id.tvOrderNo)
    TextView tvOrderNo;

    @BindView(R.id.tvOrderStatus)
    TextView tvOrderStatus;

    @BindView(R.id.tvEta)
    TextView tvEta;

    @BindView(R.id.btnViewDetails)
    Button btnViewDetails;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(@NonNull Order order, OrderDetailCallBack orderDetailCallBack) {

        tvOrderNo.setText(order.getOrderNo());
        OrderStatus orderStatus = order.getOrderStatus();
        if(orderStatus == OrderStatus.PICKED_UP) {
            tvOrderStatus.setTextColor(ContextCompat.getColor(tvOrderStatus.getContext(), android.R.color.holo_orange_dark));
        }else if( orderStatus == OrderStatus.DELIVERED) {
            tvOrderStatus.setTextColor(ContextCompat.getColor(tvOrderStatus.getContext(), android.R.color.holo_green_dark));
        }else {
            tvOrderStatus.setTextColor(ContextCompat.getColor(tvOrderStatus.getContext(), android.R.color.holo_red_dark));
        }
        tvOrderStatus.setText(orderStatus.name());

        tvEta.setText(Utility.getHumanizedETA(order.getEta()));

        btnViewDetails.setOnClickListener(v -> orderDetailCallBack.viewDetail(order));
    }
}
