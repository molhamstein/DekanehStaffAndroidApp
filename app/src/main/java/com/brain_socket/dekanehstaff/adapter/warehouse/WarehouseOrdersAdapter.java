package com.brain_socket.dekanehstaff.adapter.warehouse;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.activity.warehouse.WarehouseMainActivity;
import com.brain_socket.dekanehstaff.activity.warehouse.mvp.WarehouseMainVP;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.DateHelper;
import com.brain_socket.dekanehstaff.utils.Enums;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WarehouseOrdersAdapter extends RecyclerView.Adapter<WarehouseOrdersAdapter.ViewHolder> implements Filterable {


    private List<WarehouseOrder> orders;
    private List<WarehouseOrder> filteredOrders;
    private Context context;
    private String status = Enums.WarehouseStatuses.all.toString();

    @Inject
    public WarehouseOrdersAdapter(Context context) {
        this.context = context;
        orders = new ArrayList<>();
        filteredOrders = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WarehouseOrdersAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.warehouse_order_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WarehouseMainActivity) context).onOrderClicked(filteredOrders.get(position));
            }
        });

        holder.marketName.setText(filteredOrders.get(position).getClient().getShopName());
        holder.owenerName.setText(filteredOrders.get(position).getClient().getOwnerName());
        holder.totalPrice.setText(filteredOrders.get(position).getTotalPrice().toString());
        holder.status.setText(filteredOrders.get(position).getStatus());
        if (filteredOrders.get(position).getStatus().toString().equals(Enums.WarehouseStatuses.inWarehouse.toString()))
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.md_orange_500));
        else
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.light_green));


        if (holder.timer != null)
            holder.timer.cancel();


        Pair<String, Long> p = DateHelper.diffBetweenDateAndCurrentTime(filteredOrders.get(position).getOrderDate());
        holder.timer = new CountDownTimer(p.second, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (position < filteredOrders.size())
                    holder.timerText.setText(DateHelper.diffBetweenDateAndCurrentTime(filteredOrders.get(position).getOrderDate()).first);
            }

            @Override
            public void onFinish() {

            }
        }.start();


//        holder.handler = new Handler();
//        holder.runnable = new Runnable() {
//            @Override
//            public void run() {
//                    try {
//                        filteredOrders.get(position).setDiffTime(DateHelper.diffBetweenDateAndCurrentTime(filteredOrders.get(position).getOrderDate()));
//                        notifyItemChanged(position);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                holder.handler.postDelayed(this, 1000);
//            }
//        };
//
//        holder.handler.postDelayed(holder.runnable, 1000);


    }

    @Override
    public int getItemCount() {
        return filteredOrders.size();
    }


    public void reset() {
        this.orders = new ArrayList<>();
        this.filteredOrders = new ArrayList<>();

    }

    public void addAllOrders(List<WarehouseOrder> orders) {
        this.orders.addAll(orders);
        notifyItemRangeInserted(this.orders.size(), orders.size());
        getFilter().filter(status);
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                status = charSequence.toString();

                filteredOrders.clear();

                if (TextUtils.isEmpty(charSequence) || charSequence.equals(Enums.WarehouseStatuses.all.toString())) {
                    filteredOrders.addAll(orders);
                } else {
                    List<WarehouseOrder> filteredList = new ArrayList<>();
                    for (WarehouseOrder order : orders) {
                        if (order.getStatus().equals(charSequence))
                            filteredList.add(order);
                    }
                    filteredOrders = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredOrders;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //noinspection unchecked
                notifyDataSetChanged();
                filteredOrders = (List<WarehouseOrder>) filterResults.values;
                if (filteredOrders.isEmpty())
                    ((WarehouseMainVP.View) context).showEmptyOrdersIcon();
                else
                    ((WarehouseMainVP.View) context).hideEmptyOrdersIcon();
                notifyDataSetChanged();

            }

        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.marketName)
        TextView marketName;
        @BindView(R.id.owenerName)
        TextView owenerName;
        @BindView(R.id.totalPrice)
        TextView totalPrice;
        @BindView(R.id.arrowIcon)
        ImageView arrowIcon;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.timer)
        TextView timerText;

        public CountDownTimer timer;
        public Runnable runnable;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


    }


}
