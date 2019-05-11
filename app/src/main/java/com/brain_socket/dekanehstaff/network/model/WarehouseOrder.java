
package com.brain_socket.dekanehstaff.network.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class WarehouseOrder implements Serializable {

    @SerializedName("printable")
    @Expose
    private Boolean printable;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("priceBeforeCoupon")
    @Expose
    private Integer priceBeforeCoupon;
    @SerializedName("clientType")
    @Expose
    private String clientType;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("warehouseDate")
    @Expose
    private String warehouseDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("clientId")
    @Expose
    private String clientId;
    @SerializedName("warehouseKeeperId")
    @Expose
    private String warehouseKeeperId;
    @SerializedName("warehouseId")
    @Expose
    private String warehouseId;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("orderProducts")
    @Expose
    private List<OrderProduct> orderProducts = null;
    @SerializedName("warehouseKeeper")
    @Expose
    private WarehouseKeeper warehouseKeeper;
    @SerializedName("warehouse")
    @Expose
    private Warehouse warehouse;
    private final static long serialVersionUID = 5346530049653893058L;

    public Boolean getPrintable() {
        return printable;
    }

    public void setPrintable(Boolean printable) {
        this.printable = printable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPriceBeforeCoupon() {
        return priceBeforeCoupon;
    }

    public void setPriceBeforeCoupon(Integer priceBeforeCoupon) {
        this.priceBeforeCoupon = priceBeforeCoupon;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWarehouseDate() {
        return warehouseDate;
    }

    public void setWarehouseDate(String warehouseDate) {
        this.warehouseDate = warehouseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getWarehouseKeeperId() {
        return warehouseKeeperId;
    }

    public void setWarehouseKeeperId(String warehouseKeeperId) {
        this.warehouseKeeperId = warehouseKeeperId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public WarehouseKeeper getWarehouseKeeper() {
        return warehouseKeeper;
    }

    public void setWarehouseKeeper(WarehouseKeeper warehouseKeeper) {
        this.warehouseKeeper = warehouseKeeper;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

}


