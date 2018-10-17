package staff.dekaneh.brain_storm.com.dekanehstaff.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;
import java.util.List;
import io.reactivex.Single;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Client;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Order;

public class AppApiHelper {


//    public static Single<LoginResponse> login(LoginRequest request) {
//        return Rx2AndroidNetworking.post(ApiEndPoint.LOGIN)
//                .addBodyParameter(request)
//                .addQueryParameter("include", "user")
//                .build()
//                .getObjectSingle(LoginResponse.class);
//    }

    public static Single<List<Order>> getOrders() {
        return Rx2AndroidNetworking.get(ApiEndPoint.PENDING_ORDERS)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<Order> patchOrder(String accessToken, Order order) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.ORDERS)
                .addApplicationJsonBody(order)
                .addQueryParameter("access_token", "4PhJEPwLlwjSoeIjuAPbsckeqWYRtcZatrD0pqQEPad0mhFZ522bby2pZFE0hJG2")
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Client>> getClients() {
        return Rx2AndroidNetworking.get(ApiEndPoint.CLIENTS)
                .build()
                .getObjectListSingle(Client.class);
    }

    public static Single<Client> patchClient(String accessToken, Client client) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.USER)
                .addPathParameter("id", client.getId())
                .addBodyParameter(client)
                .addQueryParameter("access_token", "4PhJEPwLlwjSoeIjuAPbsckeqWYRtcZatrD0pqQEPad0mhFZ522bby2pZFE0hJG2")
                .build()
                .getObjectSingle(Client.class);
    }

}