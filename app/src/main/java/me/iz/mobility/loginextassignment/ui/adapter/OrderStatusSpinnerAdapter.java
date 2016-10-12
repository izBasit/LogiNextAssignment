/*
 * Copyright 2016 Basit Parkar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */

package me.iz.mobility.loginextassignment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iz.mobility.loginextassignment.R;
import me.iz.mobility.loginextassignment.models.OrderStatus;

/**
 * Created by hadoop on 1/10/15.
 */
public class OrderStatusSpinnerAdapter extends ArrayAdapter<OrderStatus> {

    private List<OrderStatus> listOfStatus;
    private LayoutInflater mLayoutInflater;

    public OrderStatusSpinnerAdapter(Context context, int resource, int textViewResourceId,
                                     List<OrderStatus> listOfStatus) {
        super(context, resource, textViewResourceId, listOfStatus);

        this.listOfStatus = listOfStatus;
        mLayoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        return getCustomView(position, view, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = mLayoutInflater.inflate(R.layout.listitem_order_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.tvListItem.setText(listOfStatus.get(position).name());

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.tvItem)
        TextView tvListItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}