package staff.dekaneh.brain_socket.com.dekanehstaff.network;


import staff.dekaneh.brain_socket.com.dekanehstaff.BuildConfig;

public class ApiEndPoint {

    private ApiEndPoint() {
    }


    //    public static final String PRODUCTS = BuildConfig.BASE_API_URL ;
    public static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    public static final String PENDING_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"}]},\"include\":[\"client\"]}";
    public static final String USERS = BuildConfig.BASE_API_URL + "users";
    public static final String USER = BuildConfig.BASE_API_URL + "users/{id}";
    public static final String STAFF_LOGIN = USERS + "/staffLogin";
    public static final String LOGOUT = USERS + "/logout";
    public static final String CLIENTS = USERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"}]}}";
    public static final String AREAS = BuildConfig.BASE_API_URL + "areas";

}
