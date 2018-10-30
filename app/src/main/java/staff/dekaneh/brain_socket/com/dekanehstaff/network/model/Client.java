package staff.dekaneh.brain_socket.com.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("clientType")
    @Expose
    private Type clientType;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("locationPoint")
    @Expose
    private LocationPoint locationPoint;
    @SerializedName("location")
    @Expose
    private String location;



    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Type getClientType() {
        return clientType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setClientType(Type clientType) {
        this.clientType = clientType;
    }

    public void setLocationPoint(LocationPoint locationPoint) {
        this.locationPoint = locationPoint;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public enum Type {
        wholesale, retailCostumer
    }


}
