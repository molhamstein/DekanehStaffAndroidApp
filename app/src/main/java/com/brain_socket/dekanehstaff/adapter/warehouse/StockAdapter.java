package com.brain_socket.dekanehstaff.adapter.warehouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;

import javax.inject.Inject;

import butterknife.BindView;


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    @Inject
    public StockAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StockAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stock_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.manufacture)
        TextView manufacture;
        @BindView(R.id.quantityText)
        TextView quantityText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
