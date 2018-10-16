package staff.dekaneh.brain_storm.com.dekanehstaff.network;


import staff.dekaneh.brain_storm.com.dekanehstaff.BuildConfig;

public class ApiEndPoint {

    private ApiEndPoint() {
    }

    //    public static final String PRODUCTS = BuildConfig.BASE_API_URL ;
    public static final String ORDERS = BuildConfig.BASE_API_URL + "orders";
    public static final String PENDING_ORDERS = ORDERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"}]},\"include\":[\"client\"]}";
    public static final String USERS = BuildConfig.BASE_API_URL + "users";
    public static final String STAFF_LOGIN = USERS + "/staffLogin";
    public static final String CLIENTS = USERS + "?filter={\"where\":{\"and\":[{\"status\":\"pending\"}]}}";

}
