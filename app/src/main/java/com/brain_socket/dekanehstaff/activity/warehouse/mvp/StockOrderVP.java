package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.WarehouseStatuses;

import java.util.List;

public class StockOrderVP {

    public interface View extends BaseView {


        void setupOrdersLayout();

        void setupStockLayout();

        void addOrders(List<WarehouseOrder> orders);

        void addWareHouseProducts(List<WareHouseProduct> wareHouseProducts);

        void hideEmptyOrdersIcon();

        void showEmptyOrdersIcon();

        void hideEmptyStockIcon();

        void showEmptyStockIcon();

        void stopOrdersRefreshing();

        void stopStockRefreshing();

        void getFilteredOrders(WarehouseStatuses status);

        void onOrderClicked(WarehouseOrder order);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void getOrders();

        void getStock(Integer limit, Integer skip);

        void filterOrders(WarehouseStatuses status);


    }
}
