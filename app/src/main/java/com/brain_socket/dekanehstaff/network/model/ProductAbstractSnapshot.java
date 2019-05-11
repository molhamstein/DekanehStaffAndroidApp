package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductAbstractSnapshot implements Serializable {

    @SerializedName("officialConsumerPrice")
    @Expose
    private Integer officialConsumerPrice;
    @SerializedName("officialMassMarketPrice")
    @Expose
    private Integer officialMassMarketPrice;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("id")
    @Expose
    private String id;
    private final static long serialVersionUID = 6433660763166481614L;

    public Integer getOfficialConsumerPrice() {
        return officialConsumerPrice;
    }

    public void setOfficialConsumerPrice(Integer officialConsumerPrice) {
        this.officialConsumerPrice = officialConsumerPrice;
    }

    public Integer getOfficialMassMarketPrice() {
        return officialMassMarketPrice;
    }

    public void setOfficialMassMarketPrice(Integer officialMassMarketPrice) {
        this.officialMassMarketPrice = officialMassMarketPrice;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
