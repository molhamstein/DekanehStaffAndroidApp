package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import android.support.v7.app.AppCompatActivity;

import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.OrderProduct;
import com.brain_socket.dekanehstaff.network.model.Product;
import com.brain_socket.dekanehstaff.network.model.Report;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;

import java.util.List;

public class OrderDetailsVP {


    public interface View extends BaseView {
        void openDialog(Product product);

        AppCompatActivity getActivity();

        List<OrderProduct> getAllProducts();

        void updateProduct(String productId);

        void  report(Report report) ;


    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void checkBarcode(String Barcode);

        void setProductChecked(String productId,String orderId);

        void assignPack(String orderId);

        void report(Report report);


    }
}
