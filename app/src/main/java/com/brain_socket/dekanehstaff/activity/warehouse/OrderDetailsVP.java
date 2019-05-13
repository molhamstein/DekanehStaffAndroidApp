package com.brain_socket.dekanehstaff.activity.warehouse;

import android.support.v7.app.AppCompatActivity;

import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.Product;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;

import java.util.List;

public class OrderDetailsVP {


    public interface View extends BaseView {
        void openDialog(Product product) ;
        AppCompatActivity getActivity() ;

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

       void checkBarcode(String Barcode) ;




    }
}