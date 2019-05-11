package com.brain_socket.dekanehstaff.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductSnapshot implements Serializable {

    @SerializedName("nameAr")
    @Expose
    private String nameAr;
    @SerializedName("nameEn")
    @Expose
    private String nameEn;
    @SerializedName("horecaPrice")
    @Expose
    private Integer horecaPrice;
    @SerializedName("horecaPriceDiscount")
    @Expose
    private Integer horecaPriceDiscount;
    @SerializedName("wholeSalePrice")
    @Expose
    private Integer wholeSalePrice;
    @SerializedName("wholeSalePriceDiscount")
    @Expose
    private Integer wholeSalePriceDiscount;
    @SerializedName("pack")
    @Expose
    private String pack;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("offerSource")
    @Expose
    private String offerSource;
    @SerializedName("isOffer")
    @Expose
    private Boolean isOffer;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("productAbstractSnapshot")
    @Expose
    private ProductAbstractSnapshot productAbstractSnapshot;
    @SerializedName("warehouseProductSnapshot")
    @Expose
    private WarehouseProductSnapshot warehouseProductSnapshot;
    @SerializedName("consumerPriceDiscount")
    @Expose
    private Integer consumerPriceDiscount;
    @SerializedName("consumerPrice")
    @Expose
    private Integer consumerPrice;
    @SerializedName("isFeatured")
    @Expose
    private Boolean isFeatured;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("availableTo")
    @Expose
    private String availableTo;
    @SerializedName("isFavorite")
    @Expose
    private Boolean isFavorite;
    @SerializedName("offerMaxQuantity")
    @Expose
    private Integer offerMaxQuantity;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("productAbstractId")
    @Expose
    private String productAbstractId;
    @SerializedName("parentCount")
    @Expose
    private Integer parentCount;
    private final static long serialVersionUID = -3793788273450766401L;

    public String getNameAr() {
        return nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Integer getHorecaPrice() {
        return horecaPrice;
    }

    public void setHorecaPrice(Integer horecaPrice) {
        this.horecaPrice = horecaPrice;
    }

    public Integer getHorecaPriceDiscount() {
        return horecaPriceDiscount;
    }

    public void setHorecaPriceDiscount(Integer horecaPriceDiscount) {
        this.horecaPriceDiscount = horecaPriceDiscount;
    }

    public Integer getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(Integer wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public Integer getWholeSalePriceDiscount() {
        return wholeSalePriceDiscount;
    }

    public void setWholeSalePriceDiscount(Integer wholeSalePriceDiscount) {
        this.wholeSalePriceDiscount = wholeSalePriceDiscount;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfferSource() {
        return offerSource;
    }

    public void setOfferSource(String offerSource) {
        this.offerSource = offerSource;
    }

    public Boolean getIsOffer() {
        return isOffer;
    }

    public void setIsOffer(Boolean isOffer) {
        this.isOffer = isOffer;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public ProductAbstractSnapshot getProductAbstractSnapshot() {
        return productAbstractSnapshot;
    }

    public void setProductAbstractSnapshot(ProductAbstractSnapshot productAbstractSnapshot) {
        this.productAbstractSnapshot = productAbstractSnapshot;
    }

    public WarehouseProductSnapshot getWarehouseProductSnapshot() {
        return warehouseProductSnapshot;
    }

    public void setWarehouseProductSnapshot(WarehouseProductSnapshot warehouseProductSnapshot) {
        this.warehouseProductSnapshot = warehouseProductSnapshot;
    }

    public Integer getConsumerPriceDiscount() {
        return consumerPriceDiscount;
    }

    public void setConsumerPriceDiscount(Integer consumerPriceDiscount) {
        this.consumerPriceDiscount = consumerPriceDiscount;
    }

    public Integer getConsumerPrice() {
        return consumerPrice;
    }

    public void setConsumerPrice(Integer consumerPrice) {
        this.consumerPrice = consumerPrice;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Integer getOfferMaxQuantity() {
        return offerMaxQuantity;
    }

    public void setOfferMaxQuantity(Integer offerMaxQuantity) {
        this.offerMaxQuantity = offerMaxQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getProductAbstractId() {
        return productAbstractId;
    }

    public void setProductAbstractId(String productAbstractId) {
        this.productAbstractId = productAbstractId;
    }

    public Integer getParentCount() {
        return parentCount;
    }

    public void setParentCount(Integer parentCount) {
        this.parentCount = parentCount;
    }

}
