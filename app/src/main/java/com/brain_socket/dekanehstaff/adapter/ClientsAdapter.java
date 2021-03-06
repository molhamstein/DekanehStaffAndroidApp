package com.brain_socket.dekanehstaff.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.Client;
import com.brain_socket.dekanehstaff.utils.DekanehUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder> {

    private List<Client> clients;
    private OnClientClickListener onClientClickListener;

    @Inject
    public ClientsAdapter() {
        clients = new ArrayList<>();
    }

    @NonNull
    @Override
    public ClientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ClientsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_client, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsViewHolder clientsViewHolder, int i) {
        final Client client = clients.get(i);

        clientsViewHolder.shopName.setText(client.getShopName());
        clientsViewHolder.ownerName.setText(client.getOwnerName());
        clientsViewHolder.phoneNumber.setText(client.getPhoneNumber());
        clientsViewHolder.clientLocation.setText(client.getLocation());

        clientsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClientClickListener.onClick(client);
            }
        });

        clientsViewHolder.phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DekanehUtils.call(view.getContext(), client.getPhoneNumber());
            }
        });

    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public void addAllClients(List<Client> clients) {
        this.clients = clients;
        notifyDataSetChanged();
    }


    public void setOnClientClickListener(OnClientClickListener onClientClickListener) {
        this.onClientClickListener = onClientClickListener;
    }

    class ClientsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shopName)
        TextView shopName;
        @BindView(R.id.ownerName)
        TextView ownerName;
        @BindView(R.id.phoneNumber)
        TextView phoneNumber;
        @BindView(R.id.phoneLayout)
        View phoneLayout;
        @BindView(R.id.clientLocation)
        TextView clientLocation;


        public ClientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClientClickListener {
        void onClick(Client client);
    }

}
