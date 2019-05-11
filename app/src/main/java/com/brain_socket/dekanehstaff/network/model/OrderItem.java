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
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("price")
    @Expose
    private int retailPrice;
    @SerializedName("dockanBuyingPrice")
    @Expose
    private int dockanBuyingPrice;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("pack")
    @Expose
    private String pack;


    public int getCount() {
        return count;
    }

    public String getNameAr() {
        return nameAr;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getDockanBuyingPrice() {
        return dockanBuyingPrice;
    }

    public String getPack() {
        return pack;
    }

    public String getThumbnailUrl() {
        return media.thumbnail;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public OrderItem(OrderItem item) {
        this.id = item.id;
        this.count = item.count;
        this.nameAr = item.nameAr;
        this.retailPrice = item.retailPrice;
        this.dockanBuyingPrice = item.getDockanBuyingPrice();
        this.media = item.media;
        this.pack = item.pack;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "count=" + count +
                ", nameAr='" + nameAr + '\'' +
                ", retailPrice=" + retailPrice +
                ", media=" + media +
                ", pack='" + pack + '\'' +
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
