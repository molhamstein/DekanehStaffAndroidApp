
package com.brain_socket.dekanehstaff.network.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Barcode implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("product")
    @Expose
    private Product product;
    private final static long serialVersionUID = 5887104283044951912L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}





