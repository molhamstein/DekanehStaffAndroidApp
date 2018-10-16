package staff.dekaneh.brain_storm.com.dekanehstaff.activity.main;


import com.google.android.gms.maps.OnMapReadyCallback;
import java.util.List;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BasePresenter;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BaseView;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Client;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Order;

public class MainVP {

    public interface View extends BaseView {
        void addOrders(List<Order> orders);
        void addClients(List<Client> clients);
        void markItem(int position);
        void updateClientDetailsSheet(String phoneNumber, String clientName, String shopName);
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T>, OnMapReadyCallback {
        void fetchOrders();
        void fetchClients();
        void focusOn(double lat, double lng);
        void clearMap();
        void addMarkers();
        void onEditOrderStatus(Order order, android.view.View view);
        void setClientSheet(Client client);
        void updateClient(String phoneNumber, String clientName, String shopName);

    }

}
