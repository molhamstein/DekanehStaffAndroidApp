package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderItem implements Serializable {

    @SerializedName("productId")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("sellingPrice")
    @Expose
    private int retailPrice;
    @SerializedName("product")
    @Expose
    private Product product;


    public int getCount() {
        return count;
    }

    public String getNameAr() {
        if (product != null && product.getNameAr() != null) {
            return product.getNameAr();
        }else{
            return "";
        }
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getDockanBuyingPrice() {
        if (product != null && product.getProductAbstract() != null) {
            return product.getProductAbstract().getOfficialMassMarketPrice();
        }
        return 0;
    }

    public String getPack() {
        if (product != null) {
            return product.getPack();
        }
        return "";
    }

    public String getThumbnailUrl() {
        if (product != null && product.getMedia() != null) {
            return product.getMedia().getThumbnail();
        } else {
            return null;
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }


    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }


    public OrderItem(OrderItem item) {
        this.id = item.id;
        this.count = item.count;
        this.retailPrice = item.retailPrice;
        this.product = item.product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "count=" + count +
                ", retailPrice=" + retailPrice +
                '\'' +
                '}';
    }

    public class Media implements Serializable {

        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;

        public String getThumbnail() {
            return thumbnail;
        }
    }
}
