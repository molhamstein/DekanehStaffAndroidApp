package com.brain_socket.dekanehstaff.network;


import com.brain_socket.dekanehstaff.BuildConfig;

public class ApiEndPoint {

    private ApiEndPoint() {
    }


    //    public static final String PRODUCTS = BuildConfig.BASE_API_URL ;
    public static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    public static final String WAREHOUSE = BuildConfig.BASE_API_URL + "warehouseProducts";
    public static final String PENDING_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"inDelivery\"},{\"deliveryMemberId\":\"{deliveryMemberId}\"}]},\"include\":[\"client\"]}";
    public static final String USERS = BuildConfig.BASE_API_URL + "users";
    public static final String USER = BuildConfig.BASE_API_URL + "users/{id}";
    public static final String STAFF_LOGIN = USERS + "/staffLogin";
    public static final String LOGOUT = USERS + "/logout";
    public static final String CLIENTS = USERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"}]}}";
    public static final String AREAS = BuildConfig.BASE_API_URL + "areas";
    public static final String DELIVER = ORDERS + "/{orderId}/delivered";
    public static final String WAREHOUSE_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"warehouseKeeperId\":\"225b98bdad41477e4d8958bc36\"}]}}";
    public static final String WAREHOUSE_STOCK = WAREHOUSE + "?filter={\"limit\":20,\"skip\":20}";

}
