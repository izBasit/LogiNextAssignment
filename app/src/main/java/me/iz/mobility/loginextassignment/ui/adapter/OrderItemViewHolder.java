package me.iz.mobility.loginextassignment.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.inf.Item;

/**
 * Created by ibasit on 10/11/2016.
 */

public class OrderItemViewHolder extends ChildViewHolder {

    @BindView(R.id.tvItem)
    TextView tvItem;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public OrderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(@NonNull Item item) {
        tvItem.setText(item.getItemName());
    }
}
