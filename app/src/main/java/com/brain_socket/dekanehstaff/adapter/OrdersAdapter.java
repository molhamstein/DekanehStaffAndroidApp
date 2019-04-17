package com.brain_socket.dekanehstaff.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.OrderItem;
import com.brain_socket.dekanehstaff.utils.DekanehUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.DynamicConfig;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Order> orders;
    private OnOrderClickListener onOrderClickListener;

    @Inject
    public OrdersAdapter() {
        orders = new ArrayList<>();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder orderViewHolder, int i) {

        final Order order = orders.get(i);
        long orderDateMilliSeconds = order.getOrderDate().getTime() + 86400000 - new Date().getTime();

        orderViewHolder.shopName.setText(order.getClient().getShopName());
        orderViewHolder.ownerName.setText(order.getClient().getOwnerName());
        orderViewHolder.phoneNumber.setText(order.getClient().getPhoneNumber());
        orderViewHolder.location.setText(order.getClient().getLocation());
        orderViewHolder.price.setText(String.valueOf(order.getTotalPrice()));
        orderViewHolder.countDown.updateShow(orderDateMilliSeconds);
        orderViewHolder.countDown.start(orderDateMilliSeconds);
        if (orderViewHolder.countDown.getRemainTime() < 7200000) {
            orderViewHolder.countDown.dynamicShow(new DynamicConfig.Builder().setSuffixTextColor(orderViewHolder.countDown.getContext().getResources().getColor(R.color.bad_rating_color)).setTimeTextColor(orderViewHolder.countDown.getContext().getResources().getColor(R.color.bad_rating_color)).build());
        } else {
            orderViewHolder.countDown.dynamicShow(new DynamicConfig.Builder().setSuffixTextColor(orderViewHolder.countDown.getContext().getResources().getColor(R.color.primary_dark)).setTimeTextColor(orderViewHolder.countDown.getContext().getResources().getColor(R.color.primary_dark)).build());
        }

        orderViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOrderClickListener.onItemClick(order.getOrderItems(), order.getClient().getShopName());
            }
        });
        orderViewHolder.locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getClient().getLocationPoint() != null)
                    onOrderClickListener.onLocationClick(order.getClient().getLocationPoint().getLat(), order.getClient().getLocationPoint().getLng());
            }
        });
        orderViewHolder.editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOrderClickListener.onEditClick(order, orderViewHolder.editLayout);
            }
        });

        orderViewHolder.phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DekanehUtils.call(view.getContext(), order.getClient().getPhoneNumber());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void addAllOrder(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
        this.onOrderClickListener = onOrderClickListener;
    }

    public boolean empty() {
        return this.orders.isEmpty();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shopName)
        TextView shopName;
        @BindView(R.id.ownerName)
        TextView ownerName;
        @BindView(R.id.phoneNumber)
        TextView phoneNumber;
        @BindView(R.id.location)
        TextView location;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.locationLayout)
        View locationLayout;
        @BindView(R.id.editLayout)
        View editLayout;
        @BindView(R.id.phoneLayout)
        View phoneLayout;
        @BindView(R.id.countDown)
        CountdownView countDown;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnOrderClickListener {
        void onLocationClick(double lat, double lng);

        void onEditClick(Order order, View view);

        void onItemClick(List<OrderItem> orderItems, String shopName);

    }
}
