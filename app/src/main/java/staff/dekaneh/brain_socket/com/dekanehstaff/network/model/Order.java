package staff.dekaneh.brain_socket.com.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

    public static final String TAG = Order.class.getSimpleName();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("orderDate")
    @Expose
    private Date orderDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalPrice")
    @Expose
    private int totalPrice;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("assignedDate")
    @Expose
    private Date assignedDate;
    @SerializedName("deliveredDate")
    @Expose
    private Date deliveredDate;
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("deliveryMemberId")
    @Expose
    private String deliveryMemberId;
    @SerializedName("couponId")
    @Expose
    private String couponId;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("products")
    @Expose
    private List<OrderItem> orderItems;



    public String getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getClientType() {
        return clientType;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public String getClientId() {
        return clientId;
    }

    public String getDeliveryMemberId() {
        return deliveryMemberId;
    }

    public String getCouponId() {
        return couponId;
    }

    public Client getClient() {
        return client;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", clientType='" + clientType + '\'' +
                ", assignedDate=" + assignedDate +
                ", deliveredDate=" + deliveredDate +
                ", clientId='" + clientId + '\'' +
                ", deliveryMemberId='" + deliveryMemberId + '\'' +
                ", couponId='" + couponId + '\'' +
                '}';
    }

    public class Client implements Serializable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("shopName")
        @Expose
        private String shopName;
        @SerializedName("ownerName")
        @Expose
        private String ownerName;
        @SerializedName("phoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("locationPoint")
        @Expose
        private Location locationPoint;
        @SerializedName("location")
        @Expose
        private String location;


        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getShopName() {
            return shopName;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Location getLocationPoint() {
            return locationPoint;
        }

        public String getLocation() {
            return location;
        }
    }

    public class Location implements Serializable {

        @SerializedName("lat")
        @Expose
        private double lat;
        @SerializedName("lng")
        @Expose
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }


}
