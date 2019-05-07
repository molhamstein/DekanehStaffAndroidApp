package com.brain_socket.dekanehstaff.adapter.warehouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.Order;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class WarehouseOrdersAdapter extends  RecyclerView.Adapter<WarehouseOrdersAdapter.ViewHolder> {


    private OrderClickListener listener;
    private List<Order> orders;

    @Inject
    public WarehouseOrdersAdapter(OrderClickListener listener) {
        this.listener = listener;
        orders  = new ArrayList<>() ;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WarehouseOrdersAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.warehouse_order_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.marketName)
        TextView marketName;
        @BindView(R.id.owenerName)
        TextView owenerName;
        @BindView(R.id.quantityText)
        TextView quantityText;
        @BindView(R.id.arrowIcon)
        ImageView arrowIcon;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.timer)
        TextView timer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }




    public interface OrderClickListener{
        void onClick(int pos);
    }
}
