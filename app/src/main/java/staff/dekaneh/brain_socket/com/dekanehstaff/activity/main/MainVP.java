package staff.dekaneh.brain_socket.com.dekanehstaff.activity.main;


import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import java.util.List;
import staff.dekaneh.brain_socket.com.dekanehstaff.base.BasePresenter;
import staff.dekaneh.brain_socket.com.dekanehstaff.base.BaseView;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.Area;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.Client;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.Order;

public class MainVP {

    public interface View extends BaseView {
        void addOrders(List<Order> orders);
        void addClients(List<Client> clients);
        void markItem(int position);
        void updateClientDetailsSheet(String phoneNumber, String clientName, String shopName, Client.Type type, String location, Client.Status status);
        void showUpdateLocationView();
        void hideUpdateLocationView();
        void addAreas(List<Area> areas);

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T>, OnMapReadyCallback {
        void fetchOrders();
        void fetchClients();
        void focusOn(double lat, double lng);
        void clearMap();
        void addMarkers();
        void onEditOrderStatus(Order order, android.view.View view);
        void setClientSheet(Client client);
        void updateClient(String phoneNumber, String clientName, String shopName, Client.Type type, Client.Status status);
        ProfileDrawerItem getProfileItem();
        void logout();
        void moveToCurrentUserLocation(Context context);
        void setClientLocation();

    }

}
