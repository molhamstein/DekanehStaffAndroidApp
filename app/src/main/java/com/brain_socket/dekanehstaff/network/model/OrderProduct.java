package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderProduct implements Serializable {

    @SerializedName("sellingPrice")
    @Expose
    private Integer sellingPrice;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("productSnapshot")
    @Expose
    private ProductSnapshot productSnapshot;
    @SerializedName("product")
    @Expose
    private Product product;
    private final static long serialVersionUID = 2707724014355845879L;

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ProductSnapshot getProductSnapshot() {
        return productSnapshot;
    }

    public void setProductSnapshot(ProductSnapshot productSnapshot) {
        this.productSnapshot = productSnapshot;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
