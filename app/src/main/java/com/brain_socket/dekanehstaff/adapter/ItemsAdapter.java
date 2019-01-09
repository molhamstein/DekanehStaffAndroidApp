package com.brain_socket.dekanehstaff.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<OrderItem> orderItems;

    @Inject
    public ItemsAdapter() {
        orderItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

        OrderItem orderItem = orderItems.get(i);
        itemViewHolder.name.setText(orderItem.getNameAr());
        itemViewHolder.count.setText(String.valueOf(orderItem.getCount()));
        itemViewHolder.price.setText(String.valueOf(orderItem.getRetailPrice()));
        if (!orderItem.getThumbnailUrl().equals(""))
            Picasso.get().load(orderItem.getThumbnailUrl()).into(itemViewHolder.itemImage);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public void addAllItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemName)
        TextView name;
        @BindView(R.id.itemCount)
        TextView count;
        @BindView(R.id.itemPrice)
        TextView price;
        @BindView(R.id.itemImage)
        ImageView itemImage;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
