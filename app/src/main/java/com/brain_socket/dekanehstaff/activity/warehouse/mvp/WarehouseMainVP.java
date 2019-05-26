package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.model.User;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.Enums;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import java.util.List;

public class WarehouseMainVP {

    public interface View extends BaseView {


        void setupMainView(String userName);

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

        void getFilteredOrders(Enums.WarehouseStatuses status);

        void onOrderClicked(WarehouseOrder order);

        void showSearchLoader();

        void hideSearchLoader();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

        ProfileDrawerItem getProfileItem();

        void getOrders();

        void searchStock(String keyword,Integer limit, Integer skip);

        void getStock(Integer limit, Integer skip);

        void filterOrders(Enums.WarehouseStatuses status);

        void logout();


    }
}
