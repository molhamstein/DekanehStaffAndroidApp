package com.brain_socket.dekanehstaff.unitTest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.adapter.ClientsAdapter;

import butterknife.BindView;

public class OrdersAdapter  extends  RecyclerView.Adapter<OrdersAdapter.ViewHolder> {


    private OrderClickListener listener;

    public OrdersAdapter(OrderClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrdersAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staff_order_item, viewGroup, false));
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
        return 10;
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
