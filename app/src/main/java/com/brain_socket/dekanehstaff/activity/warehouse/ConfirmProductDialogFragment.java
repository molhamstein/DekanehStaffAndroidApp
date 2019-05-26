package com.brain_socket.dekanehstaff.activity.warehouse;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.network.model.Product;
import com.brain_socket.dekanehstaff.network.model.Report;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ConfirmProductDialogFragment extends DialogFragment {


    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.quantity)
    TextView quantity;
    @BindView(R.id.productImage)
    ImageView productImage;
    @BindView(R.id.guidline1)
    Guideline guidline1;
    @BindView(R.id.productName)
    TextView productName;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.manufacturer)
    TextView manufacturer;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.pack)
    TextView pack;
    @BindView(R.id.checkButton)
    ImageView checkButton;
    @BindView(R.id.buttonReport)
    Button buttonReport;
    @BindView(R.id.buttonCancel)
    Button buttonCancel;
    Unbinder unbinder;


    private Product data;

    public ConfirmProductDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_product_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);

        data = (Product) getArguments().getSerializable("Product");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        productName.setText(data.getNameAr());
        quantity.setText(data.getParentCount().toString());
        manufacturer.setText(data.getManufacturer().getNameAr());
        pack.setText(data.getPack());
        Picasso.get().load(data.getMedia().getUrl()).into(productImage);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buttonCancel)
    void onCancelClicked() {
        dismiss();
    }


    @OnClick(R.id.buttonReport)
    void onReportClicked() {
        Report report = new Report() ;
        report.setWarehouseProudctId(data.getId());
        ((OrderDetailsActivity)getActivity()).report(report);
        dismiss();
    }

    @OnClick(R.id.checkButton)
    void onCheckClicked() {
        ((OrderDetailsActivity)getActivity()).updateProduct(data.getId());
        dismiss();
    }


}
