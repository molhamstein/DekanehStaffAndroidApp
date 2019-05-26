package com.brain_socket.dekanehstaff.adapter.warehouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.OrderProduct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockCheckAdapter extends RecyclerView.Adapter<StockCheckAdapter.ViewHolder> {


    List<OrderProduct> data;
    private CacheStore cacheStore ;

    @Inject
    public StockCheckAdapter(CacheStore cacheStore) {

        data = new ArrayList<>();
        this.cacheStore  = cacheStore ;

    }

    public void addAll(List<OrderProduct> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StockCheckAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checked_stock_item, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(data.get(position).getProduct().getNameAr());
        holder.manufacture.setText(data.get(position).getProduct().getManufacturer().getNameAr());
        holder.piecesNumber.setText(data.get(position).getProduct().getPack());
        holder.quantityText.setText(data.get(position).getCount().toString());

        holder.checkIcon.setChecked(cacheStore.getSession().getProductChecked(data.get(position).getProductId()+data.get(position).getOrderId()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.manufacture)
        TextView manufacture;
        @BindView(R.id.totalPrice)
        TextView quantityText;
        @BindView(R.id.checkIcon)
        CheckBox checkIcon;
        @BindView(R.id.piecesNumber)
        TextView piecesNumber;
        @BindView(R.id.pieces)
        LinearLayout pieces;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
