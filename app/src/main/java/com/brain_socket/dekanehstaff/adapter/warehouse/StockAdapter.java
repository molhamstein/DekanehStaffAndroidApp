package com.brain_socket.dekanehstaff.adapter.warehouse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private List<WareHouseProduct> data ;
    private Context context ;

    @Inject
    public StockAdapter(Context context) {
        this.context = context ;
        data  = new ArrayList<>() ;
    }

    public void addAll(List<WareHouseProduct>data){
        this.data.addAll(data) ;
        notifyItemRangeInserted(this.data.size()-1,data.size());
    }
    public void reset(){
        data = new ArrayList<>() ;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StockAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stock_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(data.get(position).getProductAbstract().getNameAr());
        holder.manufacture.setText(data.get(position).getProductAbstract().getManufacturer().getNameAr());
        holder.quantityText.setText(data.get(position).getTotalCount().toString());
        if(data.get(position).getTotalCount()<= data.get(position).getThreshold()) holder.quantityText.setTextColor(ContextCompat.getColor(context,R.color.md_red_500));
        else if (data.get(position).getTotalCount()<=data.get(position).getWarningThreshold()) holder.quantityText.setTextColor(ContextCompat.getColor(context,R.color.md_orange_500));
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
