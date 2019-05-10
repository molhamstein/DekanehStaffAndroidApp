package com.brain_socket.dekanehstaff.network;


import com.brain_socket.dekanehstaff.network.model.Area;
import com.brain_socket.dekanehstaff.network.model.Client;
import com.brain_socket.dekanehstaff.network.model.LoginRequest;
import com.brain_socket.dekanehstaff.network.model.LoginResponse;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.google.gson.JsonObject;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class AppApiHelper {


    public static Single<LoginResponse> login(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.STAFF_LOGIN)
                .addBodyParameter(request)
                .addQueryParameter("include", "user")
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    public static Maybe<String> logout(String accessToken) {
        return Rx2AndroidNetworking.post(ApiEndPoint.LOGOUT)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getStringMaybe();
    }

    public static Single<List<Order>> getOrders(String accessToken, String staffMemberId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.PENDING_ORDERS)
                //.addQueryParameter("access_token", accessToken)
                .addPathParameter("deliveryMemberId", staffMemberId)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<Order> patchOrder(String accessToken, Order order) {
        return Rx2AndroidNetworking.patch(ApiEndPoint.ORDERS)
                .addApplicationJsonBody(order)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Order.class);
    }

    public static Single<List<Client>> getClients(String accessToken) {
        return Rx2AndroidNetworking.get(ApiEndPoint.CLIENTS)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectListSingle(Client.class);
    }

    public static Single<Client> patchClient(String accessToken, Client client) {
        return Rx2AndroidNetworking.put(ApiEndPoint.USER)
                .addPathParameter("id", client.getId())
                .addApplicationJsonBody(client)
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(Client.class);
    }

    public static Single<List<Area>> getAreas() {
        return Rx2AndroidNetworking.get(ApiEndPoint.AREAS)
                .build()
                .getObjectListSingle(Area.class);
    }

    public static Single<JsonObject> deliver(String accessToken, Order order) {
        return Rx2AndroidNetworking.post(ApiEndPoint.DELIVER)
                .addPathParameter("orderId", order.getId())
                .addQueryParameter("access_token", accessToken)
                .build()
                .getObjectSingle(JsonObject.class);
    }


    public static Single<List<Order>> getWarehouseOrders() {
        return Rx2AndroidNetworking.get(ApiEndPoint.WAREHOUSE_ORDERS)
//                .addQueryParameter("access_token", accessToken)
                //.addPathParameter("deliveryMemberId", staffMemberId)
                .build()
                .getObjectListSingle(Order.class);
    }

    public static Single<List<WareHouseProduct>> getWarehouseStock(Integer limit,Integer skip) {
        return Rx2AndroidNetworking.get(ApiEndPoint.WAREHOUSE_STOCK)
//                .addQueryParameter("access_token", accessToken)
                .addPathParameter("skip", skip.toString())
                .addPathParameter("limit", limit.toString())
                .build()
                .getObjectListSingle(WareHouseProduct.class);
    }








}