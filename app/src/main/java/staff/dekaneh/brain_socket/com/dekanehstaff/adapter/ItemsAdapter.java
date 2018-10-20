package staff.dekaneh.brain_socket.com.dekanehstaff.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import staff.dekaneh.brain_socket.com.dekanehstaff.R;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.OrderItem;

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


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
