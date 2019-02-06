package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderItem implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("price")
    @Expose
    private int retailPrice;
    @SerializedName("media")
    @Expose
    private Media media;
    private String pack;
    @SerializedName("pack")


    public int getCount() {
        return count;
    }

    public String getNameAr() {
        return nameAr;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public String getPack() {
        return pack;
    }

    public String getThumbnailUrl() {
        return media.thumbnail;
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
